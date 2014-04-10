package com.crimealert.model;

import java.math.BigInteger;

import lombok.Data;

@Data
public class GeoSummary {
	private Double latitude;
	private Double longitude;
	private BigInteger magnitute;
}
