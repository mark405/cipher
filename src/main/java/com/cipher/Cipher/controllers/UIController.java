package com.cipher.Cipher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
    @GetMapping("/cipher")
    public String caesarCipherInterface() {
        return "cipher";
    }
}
