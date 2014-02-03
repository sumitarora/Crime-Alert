package com.crimealert.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crimealert.init.BaseTestCase;
import com.crimealert.model.Shop;

@Slf4j
public class TestShopService extends BaseTestCase {

	@Autowired
	private ShopService shopService;

  @Test
  public void testRoleCreate() {
   log.debug("Creating new Role");
   int i = 10;
   
   final Shop s = new Shop();
   s.setName("Shop Nameaaa");
   s.setEmplNumber(12);
   shopService.create(s);
   
   final List<Shop> shops = shopService.findAll();
   assertNotNull(shops);
   assertTrue(shops.size() > 0);
   
  }

}