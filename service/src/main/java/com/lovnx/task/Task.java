package com.lovnx.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lovnx.websocket.MyWebSocket;

@Component
public class Task {
	
	//@Scheduled(cron="0/1 09 17 * * ?")
	public void task2() {
		System.out.println(Thread.currentThread().getName()+"-------task2");
	}
	
}
