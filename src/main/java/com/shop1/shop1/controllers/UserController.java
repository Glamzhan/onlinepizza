package com.shop1.shop1.controllers;

import com.shop1.shop1.entities.Card;
import com.shop1.shop1.entities.User;
import com.shop1.shop1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = "userJSP")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public String userAdded(@RequestParam String name, String password, Model model, HttpSession httpSession) {
        User user = new User();
        user.setPassword(md5Custom(password));

        user.setName(name);
        User saved = userService.createUser(user);
        //isAdmin = false;
        model.addAttribute("user", saved);
        return "redirect:/good/allGoods";
    }

    @RequestMapping(value = "/")
    public String User(Model model) {
        return "user";
    }

    @RequestMapping(value = "/admin")
    public String Admin(Model model, HttpSession httpSession) {
        httpSession.setAttribute("login", "admin");
        return "redirect:/good/allGoods";
    }

    @RequestMapping(value = "/login")
    public String login(Model model, HttpSession httpSession) {

        return "login";
    }

    @RequestMapping(value = "/signUp")
    public String SignUp(Model model, HttpSession httpSession) {

        return "signUp";
    }

    @RequestMapping(value = "/logined", method = RequestMethod.POST)
    public String userEntered(@RequestParam String name, Model model, String password, HttpSession httpSession) {
        User user = userService.getUserByName(name);
        if (user.getName().equals(name)){
            String pas1 = new String(user.getPassword()) ;
            String pas2 = md5Custom(password) ;
            System.out.println(pas1);
            System.out.println(pas2);
            if (pas1.equals(pas2)) {
                System.out.println("Logined");
                httpSession.setAttribute("user", user);
                Card card = new Card();
                httpSession.setAttribute("goods", card);
                return "redirect:/good/allGoods";
            }
        }
        return "user";
    }

    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            // тут можно обработать ошибку
            // возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

}
