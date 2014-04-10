package com.crimealert.model;

import java.util.List;

import lombok.Data;

@Data
public class Group {

	private String key;
	private List<GroupValue> values;
}
