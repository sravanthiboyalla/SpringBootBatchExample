package com.example.BatchExample.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.BatchExample.Entity.Items;
import com.example.BatchExample.repository.ItemRepository;

@Component
public class Writer implements ItemWriter<Items>{
	@Autowired
	private ItemRepository repo;
	
	@Override
	@Transactional
	public void write(List<? extends Items> items) throws Exception
	{
		repo.saveAll(items);
		Thread.sleep(2000);
	}
}
