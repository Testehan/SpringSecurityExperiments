package com.testehan.springsecurity.chpt4.ex4;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Arrays;

public class MainEncryptorExamples {

    public static void main(String[] args) {
        byteEncryptorExample();
        textEncryptorExample();
    }

    private static void textEncryptorExample() {
        String valueToEncrypt = "HELLO";
        TextEncryptor e = Encryptors.noOpText();    // this does nothing
        String encrypted = e.encrypt(valueToEncrypt);
        System.out.println(encrypted);

        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        TextEncryptor te = Encryptors.text(password, salt);
        encrypted = te.encrypt(valueToEncrypt);
        String decrypted = te.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    private static void byteEncryptorExample() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";
        BytesEncryptor e = Encryptors.standard(password, salt);
//        BytesEncryptor e = Encryptors.stronger(password, salt);       stronger encryption
        byte [] encrypted = e.encrypt(valueToEncrypt.getBytes());
        byte [] decrypted = e.decrypt(encrypted);

        System.out.println(Arrays.toString(encrypted));
        System.out.println(Arrays.toString(decrypted));
        System.out.println(new String(decrypted));
    }
}
