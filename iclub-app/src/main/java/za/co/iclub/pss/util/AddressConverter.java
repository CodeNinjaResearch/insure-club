package za.co.iclub.pss.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.codehaus.jackson.map.ObjectMapper;

import za.co.iclub.pss.web.bean.GoogleResponse;
import za.co.iclub.pss.web.bean.Result;

public class AddressConverter {
	
	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";
	
	public GoogleResponse convertToLatLong(String fullAddress) throws IOException {
		
		URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
		URLConnection conn = url.openConnection();
		
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
		in.close();
		return response;
	}
	
	public GoogleResponse convertFromLatLong(String latlongString) throws IOException {
		URL url = new URL(URL + "?latlng=" + URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
		in.close();
		return response;
	}
	
	public static void main(String[] args) throws IOException {
		
		GoogleResponse res = new AddressConverter().convertToLatLong("Jordaan Park HEIDELBURG Gauteng");
		if (res.getStatus().equals("OK")) {
			for (Result result : res.getResults()) {
				System.out.println("Lattitude of address is :" + result.getGeometry().getLocation().getLat());
				System.out.println("Longitude of address is :" + result.getGeometry().getLocation().getLng());
				System.out.println("Location is " + result.getGeometry().getLocation_type());
			}
		} else {
			System.out.println(res.getStatus());
		}
		
		System.out.println("\n");
		GoogleResponse res1 = new AddressConverter().convertFromLatLong("52.3735557,4.8799584");
		if (res1.getStatus().equals("OK")) {
			for (Result result : res1.getResults()) {
				System.out.println("address is :" + result.getFormatted_address());
			}
		} else {
			System.out.println(res1.getStatus());
		}
		
	}
}
