package com.crimealert.service;

import java.util.List;

import com.crimealert.exception.ShopNotFound;
import com.crimealert.model.Shop;

public interface ShopService {
	
	public Shop create(Shop shop);
	public Shop delete(int id) throws ShopNotFound;
	public List<Shop> findAll();
	public Shop update(Shop shop) throws ShopNotFound;
	public Shop findById(int id);

}
