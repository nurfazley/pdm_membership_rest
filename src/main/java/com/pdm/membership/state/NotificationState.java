package com.pdm.membership.state;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class NotificationState implements Serializable {
	
	private static final long serialVersionUID = -1945523242331720960L;

	private boolean notificationPending = true;

	
	public synchronized boolean isNotificationPending() {
		return notificationPending;
	}

	public synchronized void setNotificationPending(boolean notificationPending) {
		this.notificationPending = notificationPending;
	}
}
