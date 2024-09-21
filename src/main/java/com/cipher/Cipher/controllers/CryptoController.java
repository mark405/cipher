package com.cipher.Cipher.controllers;

import com.cipher.Cipher.services.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {
    private final CryptoService cryptoService;

    // Encrypt uploaded file content
    @PostMapping("/encrypt")
    public String encryptFile(@RequestParam("file") MultipartFile file,
                              @RequestParam("key") int key,
                              @RequestParam("language") String language) throws IOException {
        String content = new String(file.getBytes()); // Convert file content to String
        return cryptoService.encrypt(content, key, language);
    }

    // Decrypt uploaded file content
    @PostMapping("/decrypt")
    public String decryptFile(@RequestParam("file") MultipartFile file,
                              @RequestParam("key") int key,
                              @RequestParam("language") String language) throws IOException {
        String content = new String(file.getBytes()); // Convert file content to String
        return cryptoService.decrypt(content, key, language);
    }

    // Validate encryption key
    @GetMapping("/validateKey")
    public boolean validateKey(@RequestParam("key") int key,
                               @RequestParam("language") String language) {
        return cryptoService.validateKey(key, language);
    }

    // Information about the developer
    @GetMapping("/developer")
    public String getDeveloperInfo() {
        return "Developer: Mark Zavgorodniy";
    }

    // Exit the system
    @PostMapping("/exit")
    public void exitSystem() {
        System.exit(0);
    }
}

