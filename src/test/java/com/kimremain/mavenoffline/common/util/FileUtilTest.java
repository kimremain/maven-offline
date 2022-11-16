package com.kimremain.mavenoffline.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kimremain.mavenoffline.common.vo.DbVo;
import com.kimremain.mavenoffline.common.vo.PersonVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class FileUtilTest {

	@Test
	void test() throws JsonGenerationException, JsonMappingException, IOException {
//		create();
		
		List<PersonVo> list = read(PersonVo.class);
		for(PersonVo person : list) {
			LogUtil.debugHighlightObject(person, "person");
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> read(Class<T> type) throws JsonParseException, JsonMappingException, IOException{
		DbVo<T> db = (DbVo<T>) FileUtil.readJsonFile("C://dev//db//person.json", DbVo.class);
		for(T t : db.getRows()) {
			LogUtil.debugHighlightObject(t);
		}
		
		 ObjectMapper mapper = new ObjectMapper();
		 return mapper.readValue(ConvertUtil.objectToJsonString(db.getRows()), mapper.getTypeFactory().constructCollectionType(ArrayList.class, type));
	}
	
	public <T> void create() throws JsonGenerationException, JsonMappingException, IOException {
		List<PersonVo> list = new ArrayList<>(); 
		PersonVo person = new PersonVo();
		person.setName("jane");
		person.setAge(10);
		list.add(person);
		
		person = new PersonVo();
		person.setName("tom");
		person.setAge(20);
		list.add(person);
		
		person = new PersonVo();
		person.setName("ann");
		person.setAge(30);
		list.add(person);
		
		DbVo<PersonVo> db = new DbVo<PersonVo>();
		db.setName("person");
		db.setRows(list);
		
		FileUtil.createJsonFile(db, "C://dev//db//person.json");
	}
}
