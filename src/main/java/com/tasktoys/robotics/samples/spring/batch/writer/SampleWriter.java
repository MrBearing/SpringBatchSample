package com.tasktoys.robotics.samples.spring.batch.writer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing

public class SampleWriter implements ItemWriter<String> {
	private static final Logger LOGGER = Logger.getLogger(SampleWriter.class);

	@Autowired
	ApplicationArguments arguments;
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		
		LOGGER.info("non op arg"+arguments.getNonOptionArgs());
		LOGGER.info("option "+arguments.containsOption("inputFile"));
		LOGGER.info("option inputFile "+arguments.getOptionValues("inputFile"));
		LOGGER.info("source "+arguments.getSourceArgs());
		LOGGER.info("get names"+arguments.getOptionNames());
		
		items.forEach(LOGGER::info);
	}
	
	
	
}
