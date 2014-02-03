package com.crimealert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimealert.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
