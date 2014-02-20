package com.crimealert.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crimealert.exception.ShopNotFound;
import com.crimealert.init.BaseTestCase;
import com.crimealert.model.Shop;

@Slf4j
public class TestShopService extends BaseTestCase {

	@Autowired
	private ShopService shopService;

	@Test
	public void testShopCreate() {
		log.debug("testing create shop");
		final Shop s = shopService.create(ddg.createShop());
		assertNotNull(s.getId());
		assertTrue(s.getId() > 0);
		
		final List<Shop> shops = shopService.findAll();
		assertNotNull(shops);
		assertTrue(shops.size() > 0);
		assertEquals(s.getName(), ddg.createShop().getName());
		assertEquals(s.getEmplNumber(), ddg.createShop().getEmplNumber());
	}

	@Test
	public void testShopDelete() throws ShopNotFound {
		log.debug("testing delete shop");
		final Shop s = shopService.create(ddg.createShop());
		assertTrue(s.getId() > 0);
		
		shopService.delete(s.getId());
		
		final Shop s2 = shopService.findById(s.getId());
		assertTrue(s2 == null);		
	}
	
	@Test
	public void testFindAll() {
		log.debug("testing find all shops");
		final Shop s = shopService.create(ddg.createShop());
		assertTrue(s.getId() > 0);
		
		final List<Shop> shops = shopService.findAll();
		assertNotNull(shops);
		assertTrue(shops.size() > 0);
	}
	
	@Test
	public void testShopUpdate() throws ShopNotFound {
		log.debug("testing shop update");
		final Shop s = shopService.create(ddg.createShop());
		assertTrue(s.getId() > 0);
		
		s.setName("updated name");
		s.setEmplNumber(123);
		shopService.update(s);
		
		final List<Shop> shops = shopService.findAll();
		assertNotNull(shops);
		assertTrue(shops.size() > 0);
		
		assertEquals(s.getId(), shops.get(0).getId());
		assertEquals(s.getName(), shops.get(0).getName());
		assertEquals(s.getEmplNumber(), shops.get(0).getEmplNumber());		
	}
	
	@Test
	public void testFindById() {
		log.debug("testing find shop by id");
		final Shop s = shopService.create(ddg.createShop());
		assertTrue(s.getId() > 0);
		
		final Shop s2 = shopService.findById(s.getId());
		assertNotNull(s2);
		assertEquals(s.getId(), s2.getId());
		assertEquals(s.getName(), s2.getName());
		assertEquals(s.getEmplNumber(), s2.getEmplNumber());
	}
	
}