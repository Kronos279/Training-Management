package com.TrainingManagement.BatchDetails.util;

import java.util.Random;

public class ApplicationUtil {

	public static String generateBatchId() {
		Random random = new Random();
	        StringBuilder builder = new StringBuilder();
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	        for (int i = 0; i < 5; i++) {
	            int index = random.nextInt(characters.length());
	            builder.append(characters.charAt(index));
	        }
	        int number = random.nextInt(999 - 100 + 1) + 100;
	        builder.append("-");
	        builder.append(number);
	        return builder.toString();
	}
	
	
}
