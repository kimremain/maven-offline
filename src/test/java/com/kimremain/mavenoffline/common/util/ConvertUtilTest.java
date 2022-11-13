package com.kimremain.mavenoffline.common.util;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.kimremain.mavenoffline.example.User;

class ConvertUtilTest {

	@Test
	void test() {
		fail("Not yet implemented");
		
		User user = new User("tom", 10, null);
	}

//	public <T> void test(Class<T> destinationClass) {
//		  T result = destinationClass.cast(getObject());
//		}

	public <T> T getObj(Class<T> target) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T obj = target.getConstructor().newInstance();
		return obj;
	}
}
