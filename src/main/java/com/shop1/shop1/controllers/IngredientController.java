package com.shop1.shop1.controllers;

import com.shop1.shop1.entities.Good;
import com.shop1.shop1.entities.Ingredient;
import com.shop1.shop1.entities.Status;
import com.shop1.shop1.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/ingredient")
public class IngredientController {
    @Autowired
    IngredientService ingredientService;

    @RequestMapping(value = "/")
    public String allIngredients(Model model) {
        model.addAttribute("ingredients", ingredientService.allIngredients());
        return "addIngredient";
    }

    @RequestMapping(value = "/addIngredient")
    public String addIngredients(@RequestParam String name, Model model) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredientService.createIngridient(ingredient);
        return "redirect:/ingredient/";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam long id, Model model, HttpSession httpSession) {
        Ingredient ingredient = ingredientService.findIngredient(id);
        ingredient.setStatus(Status.ARCHIVED);
        ingredientService.createIngridient(ingredient);
        return "redirect:/ingredient/";
    }

    @RequestMapping("/resstablish")
    public String resstablish(@RequestParam long id, Model model, HttpSession httpSession) {
        Ingredient ingredient = ingredientService.findIngredient(id);
        ingredient.setStatus(Status.ACTIVE);
        ingredientService.createIngridient(ingredient);
        return "redirect:/ingredient/";
    }
}
