package com.example.obspringsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class EncryptionTest {

    /**
     * BCrypt que genera su propio salt de 16 bytes
     * El resultado de cifrar con bcrypt será un string de 60 caracteres:
     * $a versión
     * $10 fuerza (valor que va de 4 a 31, por defecto vale 10, se puede adaptar a las necesidades de cada proyecto)
     * Los 22 siguientes caracteres son el salt generado
     */
    @Test
    void bcryptTest() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPassword = passwordEncoder.encode("admin");
        System.out.println(hashedPassword);

        boolean matches = passwordEncoder.matches("admin", hashedPassword);
        System.out.println(matches);
    }

    @Test
    void bcryptCheckMultiplePasswords() {
        for (int i = 0; i < 30; i++) {
            System.out.println(new BCryptPasswordEncoder().encode("admin"));
        }
    }

    @Test
    void pbkdf2() {
        for (int i = 0; i < 30; i++) {
            System.out.println(new Pbkdf2PasswordEncoder().encode("admin"));
        }
    }

    @Test
    void argon() {
        for (int i = 0; i < 30; i++) {
            System.out.println(new Argon2PasswordEncoder().encode("admin"));
        }
    }

    @Test
    void scrypt() {
        for (int i = 0; i < 30; i++) {
            System.out.println(new SCryptPasswordEncoder().encode("admin"));
        }
    }

    @Test
    void springPasswordEncoders() {

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("argon2", new Argon2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        // No seguro
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("sha256", new StandardPasswordEncoder());

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);

        String hashedPassword = passwordEncoder.encode("admin");

        System.out.println(hashedPassword);


    }
}