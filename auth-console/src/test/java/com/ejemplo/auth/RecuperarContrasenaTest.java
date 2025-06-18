package com.ejemplo.auth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para la recuperación de contraseña.
 * Verifica que se puede recuperar correctamente la contraseña de un usuario existente.
 */
class RecuperarContrasenaTest {

    @Test
    void recuperaPasswordDeUsuarioExistente() {
        // Arrange: Creamos el servicio y registramos un usuario
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.registrar("prueba@mail.com", "miClaveSegura");

        // Act: Buscamos el usuario para recuperar la contraseña
        Usuario usuario = usuarioService.buscarPorEmail("prueba@mail.com");

        // Assert: La contraseña debe coincidir
        assertNotNull(usuario);
        assertEquals("miClaveSegura", usuario.getPassword());
    }

    @Test
    void retornaNullSiUsuarioNoExiste() {
        // Arrange: Servicio sin ese usuario
        UsuarioService usuarioService = new UsuarioService();

        // Act: Intentamos buscar un usuario inexistente
        Usuario usuario = usuarioService.buscarPorEmail("noexiste@mail.com");

        // Assert: Debe retornar null
        assertNull(usuario);
    }
}
