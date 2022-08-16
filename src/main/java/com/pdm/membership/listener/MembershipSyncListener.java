package com.pdm.membership.listener;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.pdm.membership.model.GrossSystemMember;
import com.pdm.membership.model.Member;
import com.pdm.membership.model.MemberTransaction;
import com.pdm.membership.model.MembershipCard;
import com.pdm.membership.model.lookup.MembershipCardType;
import com.pdm.membership.service.GrossSystemMemberService;
import com.pdm.membership.service.MemberService;
import com.pdm.membership.service.MemberTransactionService;
import com.pdm.membership.service.MembershipCardService;


@WebListener
public class MembershipSyncListener implements ServletContextListener {
	
	private Scheduler scheduler;
	
	@Autowired
	private GrossSystemMemberService grossSystemMemberService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberTransactionService memberTransactionService;
	
	@Autowired
	private MembershipCardService membershipCardService;
		
	private static boolean DEBUG = false;
	
	
	public MembershipSyncListener() {}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContextListener.super.contextInitialized(sce);
		ServletContext sc = sce.getServletContext();
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, sc);
		
		JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("myJob1", "group1").build();
		CronTrigger trigger = TriggerBuilder.newTrigger()
											.withIdentity("trigger1", "group1")
											.withSchedule(CronScheduleBuilder.cronSchedule("0 30 3 * * ?"))
											.forJob("myJob1", "group1")
											.build();
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		
		try {
			scheduler = schedulerFactory.getScheduler();
			scheduler.scheduleJob(job, trigger);
			scheduler.getContext().put("grossSystemMemberService", grossSystemMemberService);
			scheduler.getContext().put("memberTransactionService", memberTransactionService);
			scheduler.getContext().put("memberService", memberService);
			scheduler.getContext().put("membershipCardService", membershipCardService);
			scheduler.start();		
		}
		catch (SchedulerException sE) {
			sE.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
		
		try {
			scheduler.shutdown();
		} 
		catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
		
	
	public static class MyJob implements Job {
		
		public MyJob() {}
		
		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			GrossSystemMemberService grossSystemMemberService = null;
			MemberTransactionService memberTransactionService = null;
			MemberService memberService = null;
			MembershipCardService membershipCardService = null;
			
			try {
				grossSystemMemberService = (GrossSystemMemberService) context.getScheduler().getContext().get("grossSystemMemberService");
				memberTransactionService = (MemberTransactionService) context.getScheduler().getContext().get("memberTransactionService");
				memberService = (MemberService) context.getScheduler().getContext().get("memberService");
				membershipCardService = (MembershipCardService) context.getScheduler().getContext().get("membershipCardService");
			} 
			catch (SchedulerException e) {
				e.printStackTrace();
			}
			
			memberTransactionService.truncateMemberTransactionAndGrossSystemMemberTable();
			
			// 1) Sync current user with Gross system
			System.out.println("Processing users from Gross system... ");
			List<GrossSystemMember> grossMemberList = getActiveMemberListFromGrossSystem();
			
			if (grossMemberList != null && !grossMemberList.isEmpty() && !DEBUG) {
				grossSystemMemberService.saveAll(grossMemberList);
			}
			else {
				System.out.println("Gross member list is emptied... ");
				return;
			}
			
			System.out.println("Completed processing gross members...");
			
			// 2) Sync member transactions from Gross system
			System.out.println("Processing member transactions...");
			
			for (GrossSystemMember grossMember : grossMemberList) {
				List<MemberTransaction> transactionList = getMemberTransactionFromGrossSystem(grossMember);
				memberTransactionService.saveAll(transactionList);
			}
			
			System.out.println("Completed processing member transaction...");
			
			// 3) Tie back with mobile app member
			List<Member> memberList = memberService.findAll();
			
			if (memberList != null && !memberList.isEmpty()) {
				for (Member member : memberList) {
					List<MemberTransaction> transactionList = memberTransactionService.findMemberTransactionByMemberNo(member.getMemberId());
					
					if (transactionList != null && !transactionList.isEmpty()) {
						for (MemberTransaction transaction : transactionList) {
							transaction.setMember(member);
							memberTransactionService.save(transaction);
						}
					}
			
					// Check membership whether create a new one or use existing
					MembershipCard membershipCard = member.getMembershipCard() == null ? new MembershipCard() : member.getMembershipCard();
					membershipCard.setLastUpdateDateTime(new Date());
					membershipCard.setCardNumber(member.getIc());
					membershipCard.setCardType(MembershipCardType.NORMAL);
					
					if (transactionList != null && !transactionList.isEmpty()) {
						double activePointAmount = transactionList.get(0).getPointEarnt();
						double expiredPointAmount = 0.0d;
						double issuedPointAmount = transactionList.get(0).getTotalPointBalance();
						double redeemedPointAmount = transactionList.get(0).getPointRedeemed();
						
						membershipCard.setActivePointAmount(Integer.getInteger(Double.toString(activePointAmount)));
						membershipCard.setExpiredPointAmount(Integer.getInteger(Double.toString(expiredPointAmount)));
						membershipCard.setIssuedPointAmount(Integer.getInteger(Double.toString(issuedPointAmount)));
						membershipCard.setRedeemedPointAmount(Integer.getInteger(Double.toString(redeemedPointAmount)));
					}
					else {
						membershipCard.setActivePointAmount(0);
						membershipCard.setExpiredPointAmount(0);
						membershipCard.setIssuedPointAmount(0);
						membershipCard.setRedeemedPointAmount(0);
					}
					
					membershipCard.setMember(member);
					membershipCardService.saveMembershipCard(membershipCard);
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		private List<GrossSystemMember> getActiveMemberListFromGrossSystem() {
			String uri = new String("http://192.168.1.222:8889/pdmgross/gross/systemmembers/active");
			RestTemplate restTemplate = new RestTemplate();
			Object[] memberArray = restTemplate.getForObject(uri, Object[].class);
			List<GrossSystemMember> grossMemberList = new ArrayList<>();
			
			for (int i = 0; i < memberArray.length; i++) {
				Map<String, Object> mem = (Map<String, Object>) memberArray[i];
				System.out.println("Object: [" + mem.get("memberNo") 
											   + ", " 
											   + mem.get("member") 
											   + ", " 
											   + mem.get("ic")
											   + ", " 
											   + mem.get("hpNo")
											   + ", " 
											   + mem.get("email")
											   + ", "
											   + mem.get("totalSpent")
											   + ", " 
											   + mem.get("totalPoints")
											   + ", " 
											   + mem.get("previousBalance")
											   + ", " 
											   + mem.get("address1") + " " + mem.get("address2") + " " + mem.get("address3") + " " + mem.get("address4")
											   + "]");
				
				GrossSystemMember grossMember = new GrossSystemMember();
				grossMember.setLastUpdateDateTime(new Date());
				grossMember.setMemberId(mem.get("memberNo").toString());
				grossMember.setFullName(mem.get("member").toString());
				grossMember.setIc(mem.get("ic").toString());
				grossMember.setPhoneNo(mem.get("hpNo") == null ? null : mem.get("hpNo").toString());
				grossMember.setEmail(mem.get("email") == null ? null : mem.get("email").toString());
				grossMember.setTotalSpent(new BigDecimal(mem.get("totalSpent").toString()));
				grossMember.setActivePointAmount(Integer.valueOf(mem.get("totalPoints").toString()));
				grossMember.setExpiredPointAmount(0);
				grossMember.setIssuedPointAmount(Integer.valueOf(mem.get("previousBalance").toString()));
				grossMember.setRedeemedPointAmount(Integer.valueOf(mem.get("previousBalance").toString()) - Integer.valueOf(mem.get("totalPoints").toString()));
				
				grossMemberList.add(grossMember);
			}
				
			return grossMemberList;
		}
		
		@SuppressWarnings("unchecked")
		private List<MemberTransaction> getMemberTransactionFromGrossSystem(GrossSystemMember grossSystemMember) {
			String uri = new String("http://192.168.1.222:8889/pdmgross/gross/membertransactions/" + grossSystemMember.getMemberId());
			RestTemplate restTemplate = new RestTemplate();
			Object[] memberArray = restTemplate.getForObject(uri, Object[].class);
			List<MemberTransaction> transactionList = new ArrayList<>();
			
			for (int i = 0; i < memberArray.length; i++) {
				Map<String, Object> mem = (Map<String, Object>) memberArray[i];
				
				String location = mem.get("location") == null ? null : mem.get("location").toString();
				Date transactionDate = null;
				String registerNo = mem.get("giftNoOrRegister").toString();
				String receiptNo = mem.get("giftOrReceiptNo").toString();
				Double quantity = Double.valueOf(mem.get("quantityOrValue").toString());
				Double pointEarned = Double.valueOf(mem.get("pointEarned").toString());
				Double pointRedeemed = Double.valueOf(mem.get("pointRedeemOrManual").toString());
				Double totalPointBalance = Double.valueOf(mem.get("totalPointBalance").toString());
				
				try {
					transactionDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(mem.get("transactionDateTime").toString());
				} 
				catch (ParseException e1) {
					e1.printStackTrace();
				}
								
				MemberTransaction memberTransaction = new MemberTransaction();
				memberTransaction.setLocation(location);
				memberTransaction.setTransactionDate(transactionDate);
				memberTransaction.setRegisterNo(registerNo);
				memberTransaction.setReceiptNo(receiptNo);
				memberTransaction.setQuantity(quantity);
				memberTransaction.setPointEarnt(pointEarned);
				memberTransaction.setPointRedeemed(pointRedeemed);
				memberTransaction.setTotalPointBalance(totalPointBalance);
				memberTransaction.setGrossSystemMember(grossSystemMember);
				memberTransaction.setRecordProcessedDateTime(new Date());
				
				transactionList.add(memberTransaction);
			}
			
			return transactionList;
		}
	}
}
