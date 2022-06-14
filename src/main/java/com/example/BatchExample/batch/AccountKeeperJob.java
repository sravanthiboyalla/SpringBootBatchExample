package com.example.BatchExample.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.BatchExample.Entity.Items;

@Component
public class AccountKeeperJob extends JobExecutionListenerSupport{

	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.file}")
	Resource resource;
	
	@Autowired
	Processor processor;
	
	@Autowired
	Writer writer;
	
	@Bean(name="accountJob")
	public Job accountKeeperJob()
	{
		System.out.println(resource);
		Step step=stepBuilderFactory.get("step-1")
					.<Items,Items> chunk(5)
					.reader(new Reader(resource))
					.processor(processor)
					.writer(writer)
					.build();
		
		Job job=jobBuilderFactory.get("accounting-job")
					.incrementer(new RunIdIncrementer())
					.listener(this)
					.start(step)
					.build();
		return job;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution)
	{
		if(jobExecution.getStatus()==BatchStatus.COMPLETED)
		{
			System.out.println("Batch job completed successfully");
		}
	}
}
