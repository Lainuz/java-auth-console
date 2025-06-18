package com.ejemplo.auth;

import java.util.Scanner;

/**
 * Aplicación principal que muestra el registro de usuarios por consola.
 */
public class App {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== REGISTRO DE USUARIO ===");
        // Solicita el email
        System.out.print("Email: ");
        String email = scanner.nextLine();
        // Solicita la contraseña
        System.out.print("Contraseña (mínimo 6 caracteres): ");
        String password = scanner.nextLine();

        // Intenta registrar el usuario con los datos ingresados
        boolean exito = usuarioService.registrar(email, password);

        // Muestra el resultado al usuario
        if (exito) {
            System.out.println("✅ Registro exitoso");
        } else {
            System.out.println("❌ Registro fallido. Verifica el email y la contraseña.");
        }
    }
}
