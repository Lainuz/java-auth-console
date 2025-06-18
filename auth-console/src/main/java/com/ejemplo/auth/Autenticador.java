package com.ejemplo.auth;

/**
 * Clase encargada de la autenticación de usuarios.
 * Se separa para que pueda ser testeada/mockeada fácilmente.
 */
public class Autenticador {
    private final UsuarioService usuarioService;

    // Inyección de dependencia para facilitar el testing con mocks
    public Autenticador(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Autentica un usuario comprobando su email y contraseña.
     * @param email Email ingresado.
     * @param password Contraseña ingresada.
     * @return true si la autenticación es exitosa, false si falla.
     */
    public boolean autenticar(String email, String password) {
        Usuario user = usuarioService.buscarPorEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}
