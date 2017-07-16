package com.tasktoys.robotics.samples.spring.batch.tasklet;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * サンプルで作ったTasklet
 */
@Component
public class SampleTasklet implements Tasklet{
	private static final Logger LOGGER = Logger.getLogger(SampleTasklet.class);

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		LOGGER.info("now testing !!");
		return RepeatStatus.FINISHED;
	}

}
