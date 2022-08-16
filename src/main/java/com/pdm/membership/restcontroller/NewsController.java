package com.pdm.membership.restcontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdm.membership.model.News;
import com.pdm.membership.service.NewsService;

@RestController
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	
	@GetMapping("/news")
	List<News> findAll() {
		List<News> newsList = newsService.findAllPublishedOrderByIdDesc();
		Comparator<News> comparator = (News n1, News n2) -> Long.compare(n2.getId(), n1.getId());
		newsList.sort(comparator);
		newsList.forEach(news -> printPrettyJson(news));
		
		return newsList;
	}
	
	@GetMapping("/news/{id}/listImage")
	public void getNewsListImage(@PathVariable Long id, HttpServletResponse response) {
		News news = newsService.findById(id);
		printPrettyJson(news);
		
		if (news != null && news.getImageList() != null) {
			news.getImageList().forEach(image -> {
				if (image.getImageName().toLowerCase().startsWith("li") && image.getContentByte() != null) {
					InputStream is = new ByteArrayInputStream(image.getContentByte());
					
					if (image.getContentType() == null) {
						response.setContentType(MediaType.IMAGE_JPEG_VALUE);
					}
					else {
						response.setContentType(image.getContentType());
					}
					
					try {
						IOUtils.copy(is, response.getOutputStream());
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	@GetMapping("/news/{id}/mainImage")
	public void getNewsMainImage(@PathVariable Long id, HttpServletResponse response) {
		News news = newsService.findById(id);
		printPrettyJson(news);
		
		if (news != null && news.getImageList() != null) {
			news.getImageList().forEach(image -> {
				if (image.getImageName().toLowerCase().startsWith("mi") && image.getContentByte() != null) {
					InputStream is = new ByteArrayInputStream(image.getContentByte());
					
					if (image.getContentType() == null) {
						response.setContentType(MediaType.IMAGE_JPEG_VALUE);
					}
					else {
						response.setContentType(image.getContentType());
					}
					
					try {
						IOUtils.copy(is, response.getOutputStream());
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	private String printPrettyJson(Object obj) {
		String jsonStr = new String();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			System.out.println(jsonStr);
		} 
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonStr;
	}
}
