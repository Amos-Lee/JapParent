package com.jos.jap.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

@RestController
public class ExampleController {
    private KeyStore store;
    private final Object lock = new Object();

    @GetMapping("/hello")
    public String hello() {
//        String keyPath = "hello";
//        String keyPass = "hello2";
//        ClassPathResource resource = new ClassPathResource(keyPath);
//        char[] pem = keyPass.toCharArray();
//            synchronized (lock) {
//                if (store == null) {
//                    synchronized (lock) {
//                        try {
//                            store = KeyStore.getInstance("jks");
//                        } catch (KeyStoreException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            store.load(resource.getInputStream(), pem);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (NoSuchAlgorithmException e) {
//                            e.printStackTrace();
//                        } catch (CertificateException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        RSAPrivateCrtKey key = null;
//        try {
//            key = (RSAPrivateCrtKey) store.getKey("keyAlias", pem);
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnrecoverableKeyException e) {
//            e.printStackTrace();
//        }
        RSAPublicKeySpec spec = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
        PublicKey publicKey = null;
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        KeyPair keyPair = new KeyPair(publicKey, key);
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RsaSigner signer = new RsaSigner(privateKey);
        return JwtHelper.encode("payload", signer).getEncoded();
    }
}
