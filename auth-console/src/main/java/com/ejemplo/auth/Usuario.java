package com.ejemplo.auth;

/**
 * Representa un usuario simple con email y contraseña.
 */
public class Usuario {
    // Email del usuario
    private final String email;
    // Contraseña del usuario
    private final String password;

    /**
     * Constructor que inicializa el usuario.
     * @param email Email del usuario.
     * @param password Contraseña del usuario.
     */
    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Método para obtener el email del usuario
    public String getEmail() { return email; }

    // Método para obtener la contraseña del usuario
    public String getPassword() { return password; }
}
