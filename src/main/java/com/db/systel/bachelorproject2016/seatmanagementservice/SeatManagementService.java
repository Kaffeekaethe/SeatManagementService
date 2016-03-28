package com.db.systel.bachelorproject2016.seatmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.db.systel.bachelorproject2016.seatmanagementservice.api.SeatManagementController;
import com.db.systel.bachelorproject2016.seatmanagementservice.domainmodel.logic.SeatManagementDAO;

public class SeatManagementService {

	public static SeatManagementDAO seatManagementDAO;

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		seatManagementDAO = (SeatManagementDAO) context.getBean("seatManagementDAO");

		SpringApplication.run(SeatManagementController.class, args);
	}

}
