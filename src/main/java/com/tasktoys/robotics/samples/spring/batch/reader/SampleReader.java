package com.tasktoys.robotics.samples.spring.batch.reader;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class SampleReader implements ItemReader<String>{

	
	int cnt = 0;

	/**
	 * データの生成・読み出しを行う
	 *
	 * @return
	 * @throws Exception
	 * @throws UnexpectedInputException
	 * @throws ParseException
	 * @throws NonTransientResourceException
	 */
	@Override
	public String read() throws UnexpectedInputException, ParseException, NonTransientResourceException {
		if(cnt > 100)
			return null;
		cnt++;
		StringBuilder builder = new StringBuilder("No."+cnt +"//");
		builder.append(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
		
		return builder.toString();
	}

}
