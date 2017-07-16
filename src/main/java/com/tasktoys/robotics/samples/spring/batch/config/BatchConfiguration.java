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
     * 前後の接続のない場合
     */
    @Autowired
	private SampleTasklet sampleTasklet;

	/**
	 * DB やファイルなどからデータを読み込むクラス
	 */
	@Autowired
	private SampleReader sampleReader;

	/**
	 * 受け取ったデータのデータ変換を行うクラス
	 */
	@Autowired
	private SampleProcessor sampleProcessor;

	/**
	 * データをDBやファイルなどに出力をするクラス
	 */
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

	/**
	 * Taskletはラムダ式で記述することも可能
	 * @return
	 */
	@Bean
	public Step lambdaStep(){
		LOGGER.info("lamda");
	    return stepBuilderFactory.get("lambda step").tasklet(
                (stepContribution ,chunkContex ) ->  RepeatStatus.FINISHED
        ).build();
    }

	/**
	 * 単一のタスクレットを読み込むステップ
	 * @return
	 */
	@Bean
	public Step testStep(){
		return stepBuilderFactory.get("test step ")
				.tasklet(sampleTasklet).build();
	}

	/**
	 * Reader -> Processor -> Writerを設定するステップ
	 * @return
	 */
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
