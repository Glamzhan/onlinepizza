package com.shop1.shop1.services;

import com.shop1.shop1.entities.Good;
import com.shop1.shop1.entities.Status;

import java.util.List;

public interface GoodService {
    Good createGood(Good good);
    Good findGood(long id);
    void deleteGood(long id);
    void updateGood(long id, Good good);
    List<Good> findAll();
}
