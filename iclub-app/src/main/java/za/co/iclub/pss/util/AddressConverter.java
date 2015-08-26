package za.co.iclub.pss.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import za.co.iclub.pss.model.ui.GoogleResponse;
import za.co.iclub.pss.model.ui.Result;

public class AddressConverter {
	
	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";
	private static final Logger LOGGER = Logger.getLogger(AddressConverter.class);
	
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
				LOGGER.info("Lattitude of address is :" + result.getFormatted_address());
				LOGGER.info("Longitude of address is :" + result.getGeometry().getLocation().getLng());
				LOGGER.info("Location is " + result.getGeometry().getLocation_type());
			}
		} else {
			LOGGER.info(res.getStatus());
		}
		
		LOGGER.info("\n");
		GoogleResponse res1 = new AddressConverter().convertFromLatLong("-28.4792905,24.6722914");
		if (res1.getStatus().equals("OK")) {
			for (Result result : res1.getResults()) {
				LOGGER.info("address is :" + result.getFormatted_address());
			}
		} else {
			LOGGER.info(res1.getStatus());
		}
		
	}
}
