package com.shop1.shop1.services.impl;

import com.shop1.shop1.entities.Good;
import com.shop1.shop1.entities.Status;
import com.shop1.shop1.entities.User;
import com.shop1.shop1.repositories.GoodsRepository;
import com.shop1.shop1.repositories.UserRepository;
import com.shop1.shop1.services.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService{
    @Autowired
    GoodsRepository goodsRepository;
    @Override
    public Good createGood(Good good) {
        if (good.getName() == null){
            throw new IllegalArgumentException("Нет значения имени");
        }
        if (good.getName().isEmpty()){
            throw new IllegalArgumentException("Введите имя");
        }

        Good savedGood = goodsRepository.save(good);
        return savedGood;
    }

    @Override
    public Good findGood(long id) {
        return goodsRepository.findOne((long) id);
    }

    @Override
    public void deleteGood(long id) {
        goodsRepository.delete(id);
    }

    @Override
    public void updateGood(long id, Good good) {
        goodsRepository.save(good);
    }

    @Override
    public List<Good> findAll() {
        return goodsRepository.findAll();
    }
}
