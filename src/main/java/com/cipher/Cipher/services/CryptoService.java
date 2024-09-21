package com.cipher.Cipher.services;

public interface CryptoService {
    String encrypt(String text, int key, String language);
    String decrypt(String cipherText, int key, String language);
    boolean validateKey(int key, String language);
}
