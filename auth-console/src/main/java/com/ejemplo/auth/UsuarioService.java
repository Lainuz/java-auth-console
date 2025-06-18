package com.ejemplo.auth;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar usuarios en memoria.
 * Aquí implementamos el registro y validación de usuarios.
 */
public class UsuarioService {
    // Lista que almacena los usuarios registrados en memoria.
    private final List<Usuario> usuarios = new ArrayList<>();

    /**
     * Intenta registrar un usuario con email y contraseña.
     * @param email Email ingresado por el usuario.
     * @param password Contraseña ingresada.
     * @return true si el registro fue exitoso, false si hubo algún error de validación.
     */
    public boolean registrar(String email, String password) {
        // Validar que el email no sea nulo y tenga formato válido (ejemplo: user@dominio.com)
        if (email == null || !email.matches("^\\S+@\\S+\\.\\S+$")) return false;
        // Validar que la contraseña tenga al menos 6 caracteres
        if (password == null || password.length() < 6) return false;
        // Validar que el email no esté ya registrado (no se permiten duplicados)
        if (buscarPorEmail(email) != null) return false;

        // Si pasa todas las validaciones, se agrega el usuario a la lista
        usuarios.add(new Usuario(email, password));
        return true;
    }

    /**
     * Busca un usuario registrado por email.
     * @param email Email a buscar.
     * @return El usuario si existe, o null si no se encuentra.
     */
    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
