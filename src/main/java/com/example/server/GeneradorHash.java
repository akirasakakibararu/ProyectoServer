package com.example.server;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneradorHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String nuevoHash = encoder.encode("password1"); // Cambia por tu contraseña real
        System.out.println("Hash generado: " + nuevoHash);
    }
}