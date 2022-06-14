package com.example.BatchExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BatchExample.Entity.Items;

public interface ItemRepository extends JpaRepository<Items,Integer>{

	public Items findByName(String name);

}
