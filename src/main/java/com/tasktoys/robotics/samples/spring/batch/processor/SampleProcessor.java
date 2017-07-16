package com.tasktoys.robotics.samples.spring.batch.processor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
public class SampleProcessor implements ItemProcessor<String, String>{

	@Override
	public String process(String item) throws Exception {
		return new StringBuilder("****")
				.append(item)
				//.append("["+"test"+ "]")
				.append("*****").toString();
	}

	
	
}
