package com.example.BatchExample.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.example.BatchExample.Entity.Items;


public class Reader extends FlatFileItemReader<Items>{
	public Reader(Resource resource)
	{
		super();
		setResource(resource);
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] {"itemId","name","price"});
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		BeanWrapperFieldSetMapper<Items> fieldMapper=new BeanWrapperFieldSetMapper<>();
		fieldMapper.setTargetType(Items.class);
		DefaultLineMapper<Items> defaultLineMapper=new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldMapper);
		setLineMapper(defaultLineMapper);
		
	}

}
