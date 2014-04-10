package com.crimealert.model;

import java.math.BigInteger;

import lombok.Data;

@Data
public class GroupValue {
	private int series;
	private String shape;
	private BigInteger size;
	private int x;
	private int y;
}
