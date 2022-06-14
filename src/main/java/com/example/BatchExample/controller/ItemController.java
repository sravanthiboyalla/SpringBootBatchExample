package com.example.BatchExample.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BatchExample.Entity.Items;
import com.example.BatchExample.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("accountJob")
	Job accountKeeperJob;
	
	@Autowired
	ItemRepository itemRepository;
	
	@GetMapping("/run-batch-job")
	@Scheduled(fixedDelay=1000)
	public String handle() throws Exception
	{
		JobParameters jobParameters=new JobParametersBuilder()
																.addString("source", "Spring Boot")
																.toJobParameters();
		jobLauncher.run(accountKeeperJob,jobParameters);
				
		return "Batch Job has been invoked";
	}
	@GetMapping("/{name}")
	public Items getItem(@PathVariable String name )
	{
		return itemRepository.findByName(name);
	}
	
}
