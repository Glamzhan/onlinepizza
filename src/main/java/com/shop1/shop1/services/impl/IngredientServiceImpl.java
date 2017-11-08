package com.shop1.shop1.services.impl;

import com.shop1.shop1.entities.Ingredient;
import com.shop1.shop1.repositories.IngredientRepository;
import com.shop1.shop1.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    IngredientRepository ingridientrepository;

    @Override
    public Ingredient createIngridient(Ingredient ingredient) {
        return ingridientrepository.save(ingredient);
    }

    @Override
    public List<Ingredient> allIngredients() {
        return ingridientrepository.findAll();
    }

    @Override
    public void deleteIngredient(Long id) {
        ingridientrepository.delete(id);
    }

    @Override
    public Ingredient findIngredient(Long id) {
        return ingridientrepository.findOne(id);
    }
}
