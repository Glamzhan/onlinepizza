package com.shop1.shop1.controllers;

import com.shop1.shop1.entities.*;
import com.shop1.shop1.services.GoodService;
import com.shop1.shop1.services.IngredientService;
import com.shop1.shop1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/good")
public class GoodController {

    @Autowired
    IngredientService ingredientService;

    @Autowired
    GoodService goodService;

    @RequestMapping(value = "/addedGood", method = RequestMethod.POST)
    public String addGood(
                          @RequestParam String name,
                          @RequestParam String description,
                          @RequestParam int price,
                          @RequestParam int size,
                          @RequestParam Long[] checkedIngredients,
                          Model model) {
        Good good = new Good();
        good.setDescription(description);
        good.setName(name);
        good.setPrice(price);
        good.setSize(size);
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        for (int i = 0; i<checkedIngredients.length;i++){
            ingredients.add(ingredients.size(),ingredientService.findIngredient(checkedIngredients[i]));
        }
       good.setIngredients(ingredients);
        System.out.println(good.getStatus());
        Good saved = goodService.createGood(good);
        return "redirect:/good/allGoods";
    }

    @RequestMapping(value = "/addGood", method = RequestMethod.GET)
    public String addGood(Model model) {
        model.addAttribute("ingredients", ingredientService.allIngredients());
        return "addGood";
    }


    @RequestMapping("/allGoods")
    public String allGoods(Model model, HttpSession httpSession) {
        List<Good> list = goodService.findAll();
        if ((httpSession.getAttribute("user") == null)){
            model.addAttribute("goods", deleteGoods(list));
        } else {
            User user = (User) httpSession.getAttribute("user");
            //System.out.println(httpSession.getAttribute("login"));
            if (user.getRole() == Role.ADMIN) {
                model.addAttribute("admin", true);
                model.addAttribute("userName",user.getName());
            } else {
                model.addAttribute("goods", deleteGoods(list));
                model.addAttribute("userName",user.getName());
                model.addAttribute("user", true);

            }
        }

        model.addAttribute("goods",list);
        return "allGoods";
    }

    @RequestMapping("/allGoods/order")
    public String order(@RequestParam long id, Model model, HttpSession httpSession) {
        System.out.println(id+" "+httpSession.getAttribute("login")+"goodId + login");
        Card card = (Card) httpSession.getAttribute("goods");
        Good good = goodService.findGood(id);
        System.out.println(good.getName()+good.getDescription());
        card.addListItem(good);
        httpSession.setAttribute("goods",card);
        /*int i=0;
        i = (int)httpSession.getAttribute("goodsQuantity")+1;
        httpSession.setAttribute("goodsQuantity", i);
        httpSession.setAttribute(String.valueOf(i), id );*/
        return "redirect:/basket/";
    }

    @RequestMapping("/allGoods/delete")
    public String delete(@RequestParam long id, Model model, HttpSession httpSession) {
       Good good =  goodService.findGood(id);
       good.setStatus(Status.ARCHIVED);
       goodService.createGood(good);
        return "redirect:/good/allGoods/";
    }

    @RequestMapping("/allGoods/resstablish")
    public String resstablish(@RequestParam long id, Model model, HttpSession httpSession) {
        Good good =  goodService.findGood(id);
        good.setStatus(Status.ACTIVE);
        goodService.createGood(good);
        return "redirect:/good/allGoods/";
    }

    @RequestMapping("/allGoods/user")
    public String addUser(Model model) {
        return "redirect:/user/";
    }

    private static List<Good> deleteGoods(List<Good> list){
        int i= 0;
        while(i<list.size()){
            if (list.get((int) i).getStatus() == Status.ARCHIVED){
                list.remove(i);
            } else {
                i++;
            }
        }
        return list;
    }
}