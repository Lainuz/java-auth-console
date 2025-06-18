# Sistema de Autenticación por Consola en Java

## Descripción

Este proyecto es un sistema sencillo de autenticación de usuarios por consola, pensado para enseñanza de fundamentos DevOps y buenas prácticas de testing en Java. Permite a los estudiantes experimentar con:

- **Registro de usuarios**
- **Autenticación (login)**
- **Recuperación de contraseña**
- **Pruebas unitarias y reportes**
- **Cobertura de código**
- **Automatización con hooks de Git**

Incluye comentarios didácticos en el código y está diseñado para ser ejecutado fácilmente en Codespaces, VSCode o cualquier entorno con Maven y Java 17+.

## Estructura del proyecto

```plaintext
auth-console/
│
├── pom.xml                    # Archivo principal de Maven
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── ejemplo/
│   │               └── auth/
│   │                   ├── App.java
│   │                   ├── Usuario.java
│   │                   ├── UsuarioService.java
│   │                   └── Autenticador.java
│   └── test/
│       └── java/
│           └── com/
│               └── ejemplo/
│                   └── auth/
│                       ├── AutenticadorTest.java
│                       ├── RecuperarContrasenaTest.java
│                       └── (otros tests...)
├── target/
│   └── ...                    # Archivos generados por Maven
└── .github/
    └── workflows/
        └── ci.yml             # (Opcional) Workflow de CI/CD para GitHub Actions
```

## ¿Cómo compilar y ejecutar la aplicación?

### **Compilar el proyecto**

Desde la carpeta raíz (`auth-console/`), ejecuta:

```bash
mvn compile
```

Esto compila todo el código fuente Java y crea los archivos `.class` necesarios en la carpeta `target/`.

### **Ejecutar la aplicación de consola**

Luego de compilar, ejecuta la aplicación con:

```bash
mvn exec:java -Dexec.mainClass="com.ejemplo.auth.App"
```

* Se mostrará un menú en consola donde podrás registrar usuarios, autenticarte (login) o recuperar contraseñas.

## ¿Cómo correr los tests?

### **Ejecutar todos los tests**

```bash
mvn test
```

### **Ejecutar una clase de test específica**

```bash
mvn -Dtest=NombreDeLaClaseTest test
```

Ejemplo:

```bash
mvn -Dtest=AutenticadorTest test
```

### **Ejecutar un método de test específico**

```bash
mvn -Dtest=AutenticadorTest#autenticaUsuarioCorrecto test
```

Puedes combinar varias clases o métodos separados por coma:

```bash
mvn -Dtest=AutenticadorTest,RecuperarContrasenaTest test
```

## ¿Cómo generar reportes de pruebas y cobertura?

### **Reporte HTML de pruebas unitarias**

1. Ejecuta:

   ```bash
   mvn test
   mvn surefire-report:report
   ```

2. Abre el reporte HTML generado en:

   ```
   target/site/surefire-report.html
   ```

### **Reporte de cobertura de código (JaCoCo)**

1. Ejecuta:

   ```bash
   mvn test
   ```

2. Abre el archivo de cobertura en:

   ```
   target/site/jacoco/index.html
   ```

## ¿Cómo agregar un hook pre-commit para validar los tests antes de cada commit?

> **Nota:** Los hooks NO se comparten por Git; cada usuario debe instalarlo en su máquina.

1. Ve a la carpeta de hooks de tu repo:

   ```bash
   cd .git/hooks
   ```

2. Crea el archivo `pre-commit`:

   ```bash
   nano pre-commit
   ```

3. Pega este contenido y **guarda** (reemplaza `auth-console` por el nombre real de tu subcarpeta con el pom.xml si es distinto):

   ```bash
   #!/bin/bash
   cd auth-console
   echo "Ejecutando tests antes del commit..."
   mvn -q test
   RESULT=$?
   if [ $RESULT -ne 0 ]; then
       echo "❌ Los tests fallaron. Commit cancelado."
       exit 1
   else
       echo "✅ Todos los tests pasaron. Continuando con el commit..."
       exit 0
   fi
   ```

4. Dale permisos de ejecución:

   ```bash
   chmod +x pre-commit
   ```

Ahora, cada vez que intentes commitear, el hook correrá los tests y solo permitirá el commit si todos pasan.
