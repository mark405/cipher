package com.cipher.Cipher.services.impl;

import com.cipher.Cipher.services.CryptoService;
import org.springframework.stereotype.Service;

@Service
public class CryptoServiceImpl implements CryptoService {
    private static final String UKR_ALPHABET = "абвгґдежзийклмнопрстуфхцчшщьюя";
    private static final String ENG_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String encrypt(String text, int key, String language) {
        String alphabet = getAlphabet(language);
        int n = alphabet.length();
        key = ((key % n) + n) % n; // Normalize key to be within alphabet range, positive

        StringBuilder result = new StringBuilder();

        for (char c : text.toLowerCase().toCharArray()) {
            int index = alphabet.indexOf(c);
            if (index != -1) {
                int newIndex = (index + key) % n;
                result.append(alphabet.charAt(newIndex));
            } else {
                result.append(c); // Keep non-alphabet characters
            }
        }

        return result.toString();
    }

    @Override
    public String decrypt(String cipherText, int key, String language) {
        return encrypt(cipherText, -key, language); // Reuse encryption by reversing the key
    }

    @Override
    public boolean validateKey(int key, String language) {
        int n = getAlphabet(language).length();
        return key > 0 && key < n;
    }

    private String getAlphabet(String language) {
        return switch (language.toLowerCase()) {
            case "ukrainian" -> UKR_ALPHABET;
            case "english" -> ENG_ALPHABET;
            default -> throw new IllegalArgumentException("Unsupported language");
        };
    }
}
