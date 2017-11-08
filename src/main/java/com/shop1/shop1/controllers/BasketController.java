package com.shop1.shop1.controllers;

import com.shop1.shop1.entities.*;
import com.shop1.shop1.services.GoodService;
import com.shop1.shop1.services.OrderService;
import com.shop1.shop1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/basket")
public class BasketController {

    @Autowired

            GoodService goodService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String allGoods(Model model, HttpSession httpSession) {
        Card card = (Card) httpSession.getAttribute("goods");
        /*ArrayList<Good> goods = new ArrayList<Good>();
        for (int i=0;i<card.getList().size();i++){
            goods.add(goods.size(),goodService.findGood(card.getList().get(i)));
        }*/
        model.addAttribute("goods",card);
        model.addAttribute("login", httpSession.getAttribute("login"));
        return "basket";
    }

    @RequestMapping("/saveBasket")
    public String saveBasket(Model model, HttpSession httpSession) {
       Order order = new Order();
        User user = (User) httpSession.getAttribute("user");
       order.setUser(user);
       Card card = (Card) httpSession.getAttribute("goods");
       order.setGoods((List<Good>) card.getList());
       System.out.println(order.getGoods().get(1).getName()+" "+order.getGoods().get(1).getDescription()+"listItem 1 name");
       orderService.createOrder(order);
        return "redirect:/good/allGoods";
    }

    @RequestMapping("/allOrders/delete")
    public String delete(@RequestParam long id, Model model, HttpSession httpSession) {
        Order order =  orderService.findOrderById(id);
        order.setStatus(Status.ARCHIVED);
        orderService.createOrder(order);
        return "redirect:/basket/allOrders/";
    }

    @RequestMapping("/allOrders/resstablish")
    public String resstablish(@RequestParam long id, Model model, HttpSession httpSession) {
        Order order =  orderService.findOrderById(id);
        order.setStatus(Status.ACTIVE);
        orderService.createOrder(order);
        return "redirect:/basket/allOrders/";
    }

    @RequestMapping("/allOrders")
    public String allOrders(Model model, HttpSession httpSession) {
        model.addAttribute("goods",orderService.findAll());
        return "order";
    }
}
