/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat2;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author eufra
 */
public class Criptografia {

    protected SecretKey secretKey;
    public  String chaveSecretaString = "orwUKckqAlJU+Wjzqa6YPw==";

    public Criptografia() throws Exception {
        this.secretKey = generateAESKey();
    }

    // Gere uma chave secreta AES
    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Tamanho da chave (128, 192 ou 256 bits)
        return keyGenerator.generateKey();
    }

    // Criptografe a mensagem
    public byte[] encryptMessage(String mensagem) throws Exception {
        
        //aqui
        
  
        byte[] chaveSecretaBytes = this.chaveSecretaString.getBytes("UTF-8");
        this.secretKey = new SecretKeySpec(chaveSecretaBytes, "AES");
       
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        return cipher.doFinal(mensagem.getBytes(StandardCharsets.UTF_8));
    }

    // Descriptografe a mensagem
    public String decryptMessage(byte[] mensagemCriptografada) throws Exception {
         Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] mensagemDescriptografadaBytes = cipher.doFinal(mensagemCriptografada);
        return new String(mensagemDescriptografadaBytes, StandardCharsets.UTF_8);
    }

}
