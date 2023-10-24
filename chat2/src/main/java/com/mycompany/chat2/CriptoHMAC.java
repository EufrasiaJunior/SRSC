/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chat2;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author eufra
 */
public class CriptoHMAC {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    private static final String SECRET_KEY = "a15C+iGuLBgimoBJjo2OSzK0q12PYQfa0NjqjKErehs=";
    private static final String INIT_VECTOR = "1234567890123456"; // 16 bytes inicialização

    public static String getChave() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        // Defina o tamanho da chave (128, 192 ou 256 bits)
        keyGenerator.init(256);

        // Gere a chave secreta
        SecretKey secretKey = keyGenerator.generateKey();

        // Converta a chave secreta em uma representação base64
        String secretKeyBase64 = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        return secretKeyBase64;
    }

    public static String encrypt(String mensagem) {
        try {
            SecretKeySpec chave = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(INIT_VECTOR.getBytes());

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, chave, ivSpec);

            byte[] mensagemCriptografada = cipher.doFinal(mensagem.getBytes());
            return Base64.getEncoder().encodeToString(mensagemCriptografada);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String mensagemCriptografada) {
        try {
            SecretKeySpec chave = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(INIT_VECTOR.getBytes());

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, chave, ivSpec);

            byte[] mensagemBytes = Base64.getDecoder().decode(mensagemCriptografada);
            byte[] mensagemDescriptografada = cipher.doFinal(mensagemBytes);

            return new String(mensagemDescriptografada);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String mensagemOriginal = "Mensagem a ser criptografada e descriptografada";
        String mensagemCriptografada = encrypt(mensagemOriginal);
        System.out.println("Mensagem criptografada: " + mensagemCriptografada);

        String mensagemDescriptografada = decrypt(mensagemCriptografada);
        System.out.println("Mensagem descriptografada: " + mensagemDescriptografada);
    }
}
