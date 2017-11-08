package com.shop1.shop1.repositories;

import com.shop1.shop1.entities.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository  extends JpaRepository<Good, Long> {
}
