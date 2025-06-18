package com.ejemplo.auth;

import java.util.Scanner;

/**
 * Aplicación de consola para registro, autenticación (login) y recuperación de contraseña.
 */
public class App {
    public static void main(String[] args) {
        // Creamos una instancia del servicio de usuarios, que maneja el registro y almacenamiento en memoria
        UsuarioService usuarioService = new UsuarioService();

        // Precargamos un usuario en el sistema para facilitar las pruebas de autenticación y recuperación
        usuarioService.registrar("test@mail.com", "clave123");

        // Creamos el objeto Scanner para leer las opciones y datos del usuario por consola
        Scanner scanner = new Scanner(System.in);

        // Mostramos el menú principal con las opciones disponibles
        System.out.println("Elige una opción:");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Login");
        System.out.println("3. Recuperar contraseña");
        System.out.print("Opción (1/2/3): ");
        String opcion = scanner.nextLine();

        // Procesamos la opción elegida por el usuario
        if ("1".equals(opcion)) {
            // Proceso de registro
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            // Intentamos registrar el usuario
            boolean exito = usuarioService.registrar(email, password);

            // Mostramos el resultado del registro
            if (exito) {
                System.out.println("✅ Registro exitoso");
            } else {
                System.out.println("❌ Registro fallido. Verifica el email y la contraseña.");
            }
        } else if ("2".equals(opcion)) {
            // Proceso de autenticación (login)
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            // Creamos el objeto Autenticador usando el mismo servicio de usuarios
            Autenticador autenticador = new Autenticador(usuarioService);

            // Intentamos autenticar al usuario con los datos ingresados
            boolean login = autenticador.autenticar(email, password);

            // Mostramos el resultado del login
            if (login) {
                System.out.println("✅ Login exitoso");
            } else {
                System.out.println("❌ Login fallido. Email o contraseña incorrectos.");
            }
        } else if ("3".equals(opcion)) {
            // Proceso de recuperación de contraseña
            System.out.print("Email: ");
            String email = scanner.nextLine();

            // Buscamos al usuario en el sistema
            Usuario user = usuarioService.buscarPorEmail(email);

            // Si el usuario existe, mostramos su contraseña; si no, mostramos un error
            if (user != null) {
                System.out.println("Tu contraseña es: " + user.getPassword());
            } else {
                System.out.println("❌ Usuario no encontrado.");
            }
        } else {
            // La opción ingresada no es válida
            System.out.println("Opción inválida.");
        }
    }
}
