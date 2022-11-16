package com.kimremain.mavenoffline.common.vo;

import java.util.List;

import lombok.Data;

@Data
public class DbVo<T> {

	private String name;
	private List<T> rows;
}
