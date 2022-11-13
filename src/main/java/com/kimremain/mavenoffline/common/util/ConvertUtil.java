package com.kimremain.mavenoffline.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ConvertUtil {
	public static String objectToJsonString(Object src) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(src);
	}

	public static String objectToJsonString(Object src, Boolean indentOutput) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, indentOutput);
		return objectMapper.writeValueAsString(src);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonStringToMap(String src) throws JsonProcessingException {
		return new ObjectMapper().readValue(src, Map.class);
	}

	public static Long stringToLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			return 0L;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object mapToObject(Map<String, Object> map, Class c) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(map, c);
	}

	public static <T> Collector<T, ?, T> toSingleton() {
		return Collectors.collectingAndThen(Collectors.toList(), list -> {
			if (list.size() != 1) {
				throw new IllegalStateException();
			}
			return list.get(0);
		});
	}

	public <T> T getObj(Class<T> target) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T obj = target.getConstructor().newInstance();
		return obj;
	}

}
