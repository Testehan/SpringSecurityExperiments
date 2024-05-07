package com.testehan.springsecurity.chpt4.ex3;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.Arrays;

public class MainKeyGeneratorsExamples {

    public static void main(String[] args) {
        StringKeyGenerator key = KeyGenerators.string();
        System.out.println(key.generateKey());

        BytesKeyGenerator keyAsBytes = KeyGenerators.secureRandom();
        System.out.println(Arrays.toString(keyAsBytes.generateKey()));

        BytesKeyGenerator keyAsBigNumberOfBytes = KeyGenerators.secureRandom(256);
        System.out.println(Arrays.toString(keyAsBigNumberOfBytes.generateKey()));

        /*
         In some cases, we prefer an implementation that returns the same key value for each
        call of the same key generator. In this case, we can create a BytesKeyGenerator with
        the KeyGenerators.shared(int length) method. In this code snippet, key1 and
        key2 have the same value:

         */
        BytesKeyGenerator keyGeneratorShared = KeyGenerators.shared(3);
        byte [] key1 = keyGeneratorShared.generateKey();
        byte [] key2 = keyGeneratorShared.generateKey();
        System.out.println(Arrays.toString(key1));
        System.out.println(Arrays.toString(key2));      // samee key
    }
}
