package com.example.BatchExample.batch;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.BatchExample.Entity.Items;
import com.example.BatchExample.repository.ItemRepository;

@Component
public class Processor implements ItemProcessor<Items,Items>{
	@Autowired
	private ItemRepository itemRepo;
	
	@Override
	public Items process(Items items) throws Exception
	{
		Optional<Items> itemsFromDb=itemRepo.findById(items.getItemId());
		if(itemsFromDb.isPresent())
		{
			items.setName(items.getName().concat(itemsFromDb.get().getName()));
		}
		return items;
	}
	

}
