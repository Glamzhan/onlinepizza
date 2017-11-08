package com.shop1.shop1.services;

import com.shop1.shop1.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient createIngridient(Ingredient ingredient);
    List<Ingredient> allIngredients();
    void deleteIngredient(Long id);
    Ingredient findIngredient(Long id);
}
