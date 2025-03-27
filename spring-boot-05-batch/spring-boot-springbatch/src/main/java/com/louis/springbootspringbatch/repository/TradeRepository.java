package com.louis.springbootspringbatch.repository;

import com.louis.springbootspringbatch.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, String> {
}