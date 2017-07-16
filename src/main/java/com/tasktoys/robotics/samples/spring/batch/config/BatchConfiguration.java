package com.tasktoys.robotics.samples.spring.batch.config;

import com.tasktoys.robotics.samples.spring.batch.tasklet.SampleTasklet;
import com.tasktoys.robotics.samples.spring.batch.writer.SampleWriter;
import com.tasktoys.robotics.samples.spring.batch.reader.SampleReader;
import com.tasktoys.robotics.samples.spring.batch.processor.SampleProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * バッチ処理の設定ファイル
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);


	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	/** jobBuilderFactory. */
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

    /**
     *
     */
    @Autowired
	private SampleTasklet sampleTasklet;
	
	@Autowired
	private SampleReader sampleReader;
	
	@Autowired
	private SampleProcessor sampleProcessor;
	
	@Autowired
	private SampleWriter sampleWriter;


	/**
	 * バッチ処理のエントリーポイント
	 * @return
	 */
	@Bean
	public Job test(){
		return jobBuilderFactory.get("test")
				.start(testStep())
				.next(testChunkStep())
                .next(lambdaStep())
                .build();
	}

	@Bean
	public Step lambdaStep(){
		LOGGER.info("lamda");
	    return stepBuilderFactory.get("lambda step").tasklet(
                (stepContribution ,chunkContex ) ->  RepeatStatus.FINISHED
        ).build();
    }

    @Bean
	public Step testStep(){
		return stepBuilderFactory.get("test step ")
				.tasklet(sampleTasklet).build();
	}

	@Bean
	public Step testChunkStep(){
		return stepBuilderFactory.get("chunk step")
				.<String,String>chunk(5)
				.reader(sampleReader)
				.processor(sampleProcessor)
				.writer(sampleWriter)
				.build();
	}
	
	
	
}
