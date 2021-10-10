package com.hackX;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class WebController {
    @RequestMapping("/login")
    public String login() {
        return "/login.html";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/login-error.html";
    }

    @RequestMapping("/paymentSuccess")
    public String paymentSuccess() {
        return "/paymentSuccess.html";
    }

    // Login form with error
    @RequestMapping("/payment-error")
    public String paymentError(Model model) {
        model.addAttribute("paymentError", false);
        return "/payment-error.html";
    }

    @RequestMapping("/payment")
    public String payment(@RequestParam(value = "username",required = false) String username,@RequestParam(value = "cvvNo",required = false) String cvvNo, @RequestParam(value = "creditCardNo",required = false) String creditCardNo) throws NoSuchAlgorithmException {
        if (username != null && username.equals("user")) {
            byte[] salt = EncryptionAlgorithmForPaymentSystems.getSalt();
            String paswordKey = username + cvvNo + creditCardNo;
            String generatedPassword = EncryptionAlgorithmForPaymentSystems.getSecurePassword(paswordKey, salt);
            System.out.println("Generated password = " + generatedPassword);
            //@Todo
            //Validate the above password generated against the password stored in the mysql
            // database table
            // then redirect to paymentSuccess.html
            return "/paymentSuccess";
        }
        else{
            return "payment.html";
        }
    }
}
