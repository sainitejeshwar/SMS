package com.flipkart.helper;

import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public interface clientHelper{
	static  Logger logger = Logger.getLogger(clientHelper.class);
	default String getSalutation(String Gender) {
		if(Gender.equals("M"))
			return "Mr. ";
		return "Miss. ";
	}

}
