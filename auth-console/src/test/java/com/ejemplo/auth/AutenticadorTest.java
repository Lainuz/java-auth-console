package com.ejemplo.auth;

// Importamos las anotaciones y los métodos de aserción de JUnit 5
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Autenticador.
 * Usamos un "fake" UsuarioService real (no mocks) para los tests,
 * porque Mockito no soporta Java 21 para mockear clases concretas.
 */
class AutenticadorTest {

    /**
     * Caso 1: El usuario existe y la contraseña es correcta.
     * Debe autenticarse exitosamente.
     */
    @Test
    void autenticaUsuarioCorrecto() {
        // Arrange: Creamos el UsuarioService real y registramos el usuario de prueba
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.registrar("test@mail.com", "clave123");

        // Creamos el Autenticador con el servicio real
        Autenticador autenticador = new Autenticador(usuarioService);

        // Act + Assert: El login debe ser exitoso
        assertTrue(autenticador.autenticar("test@mail.com", "clave123"));
    }

    /**
     * Caso 2: El usuario NO existe en el sistema.
     * La autenticación debe fallar.
     */
    @Test
    void fallaUsuarioNoExistente() {
        // Arrange: Servicio sin usuarios registrados
        UsuarioService usuarioService = new UsuarioService();
        Autenticador autenticador = new Autenticador(usuarioService);

        // Act + Assert: Intentar autenticar un usuario inexistente debe fallar
        assertFalse(autenticador.autenticar("noexiste@mail.com", "loquesea"));
    }

    /**
     * Caso 3: El usuario existe pero la contraseña es incorrecta.
     * La autenticación debe fallar.
     */
    @Test
    void fallaPasswordIncorrecta() {
        // Arrange: Registramos el usuario real en el servicio
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.registrar("test@mail.com", "clave123");
        Autenticador autenticador = new Autenticador(usuarioService);

        // Act + Assert: Contraseña incorrecta, debe fallar
        assertFalse(autenticador.autenticar("test@mail.com", "mala"));
    }
}
