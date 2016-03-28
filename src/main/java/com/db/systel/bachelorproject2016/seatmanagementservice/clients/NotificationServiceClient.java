package com.db.systel.bachelorproject2016.seatmanagementservice.clients;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class NotificationServiceClient {
	private static RestTemplate restTemplate;

	// TODO: Service Discovery f�r BuchungsDienste --> URL und Port
	private static String hostAddress = "http://localhost";
	private static int port = 8082;

	public static String notifySeatsDisabled(List<Integer> seatIDs) {

		restTemplate = new RestTemplate();

		/*
		 * Header setzen, damit der Dienst weiß, dass er ein JSON-Objekt bekommt
		 */

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");

		/*
		 * <T> gibt den Datentyp des RequestBodys an
		 */
		HttpEntity<List<Integer>> entity = new HttpEntity<List<Integer>>(headers);

		String URL = String.format("%s:%s/%s", hostAddress, port, "notify-seats-disabled?seatIDs=");
		
		for(int i = 0; i < seatIDs.size(); i++){
			URL += seatIDs.get(i);
			if (i < seatIDs.size()-1){
				URL += ",";
			}
		}
		try {

			/*
			 * Verbindung aufbauen. Wir erwarten einen String zurück
			 */
			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

			return response.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// TODO: Das ganze verschönern --> ExtractMethod
	public static String notifyTrainConnectionCancelled(Integer trainConnectionID, String date) {
		restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		headers.add("Content-Type", "application/json");

		HttpEntity<?> entity = new HttpEntity<>(headers);

		String URL = String.format("%s:%s/%s", hostAddress, port,
				"notify-train-cancelled?trainConnectionID=" + trainConnectionID + "&day=" + date);
		try {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

			return response.getBody();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
