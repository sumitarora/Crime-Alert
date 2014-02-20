package com.crimealert.data;

import org.springframework.stereotype.Component;

import com.crimealert.model.Shop;

@Component
public class DummyDataGenerator {

	public Shop createShop() {
	   final Shop s = new Shop();
	   s.setName("Shop Name");
	   s.setEmplNumber(12);
	   return s;
	}
}
