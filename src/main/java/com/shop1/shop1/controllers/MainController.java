package com.shop1.shop1.controllers;

import com.shop1.shop1.entities.Good;
import com.shop1.shop1.entities.User;
import com.shop1.shop1.repositories.GoodsRepository;
import com.shop1.shop1.repositories.UserRepository;
import com.shop1.shop1.services.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes(value = "user")
public class MainController {

    @Autowired
    GoodService goodService;

    private Boolean isAdmin = true;

    //@Autowired
    //UserRepository userRepository;

    @RequestMapping(value = "/")
    public String Enter(Model model, HttpServletRequest httpServletRequest, HttpSession httpSession) {
        model.addAttribute("goods", goodService.findAll());
        return "main";
    }
    /*@RequestMapping(value = "/addGood", method = RequestMethod.POST)
    public String addGood(@RequestParam String name,
                                @RequestParam String description, @RequestParam int price, @RequestParam int quantity, Model model) {
        Good good = new Good();
        good.setDescription(description);
        good.setName(name);
        good.setPrice(price);
        good.setQuantity(quantity);
        repository.save(good);

        return "redirect:/allGoods";
    }


    @RequestMapping(value = "/user/registered", method = RequestMethod.POST)
    public String userAdded(@RequestParam String name, Model model) {
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        isAdmin = false;
        model.addAttribute("user", user);
        return "redirect:/allGoods";
    }

    @RequestMapping(value = "/user/logined", method = RequestMethod.POST)
    public String userEntered(@RequestParam String name, Model model) {
        List <User> users = userRepository.findAll();
        for (int i = 0; i<users.size(); i++){
            if (users.get(i).getName().equals(name)){
                isAdmin = false;
                System.out.println("Logined");
                return "redirect:/allGoods";
            }
        }
        return "user";
    }*/
}