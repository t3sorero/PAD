# Práctica 1 - Toma de Contacto con Android

**Asignatura**: Programación de Aplicaciones para Dispositivos móviles (PAD)
**Universidad Complutense de Madrid (UCM)**

---

## 📱 Descripción

Primera práctica de la asignatura PAD centrada en la familiarización con Android Studio y el desarrollo de aplicaciones Android nativas. Se implementa una aplicación calculadora simple que permite sumar dos números, con navegación entre actividades, soporte multiidioma y testing unitario.

---

## ✨ Características Principales

- **Calculadora básica** que suma dos números decimales
- **Navegación entre Activities** mediante Intent explícito
- **Diseño responsivo** con ConstraintLayout y chains
- **Soporte de orientaciones** (vertical y apaisado)
- **Soporte multiidioma** (español e inglés)
- **Testing unitario** con JUnit
- **Validación de entrada** de datos
- **Material Design** básico

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java
- **IDE**: Android Studio
- **SDK mínimo**: API 24 (Android 7.0 "Nougat")
- **Layout**: ConstraintLayout
- **Testing**: JUnit 4
- **Build System**: Gradle

---

## 📁 Estructura del Proyecto

```
Android01/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/es/ucm/fdi/pad/android01/
│   │   │   │   ├── MainActivity.java              # Activity principal
│   │   │   │   ├── CalculatorAdd.java             # Lógica de suma
│   │   │   │   └── CalculatorAddResultActivity.java # Pantalla de resultado
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml          # Layout vertical
│   │   │   │   │   ├── activity_main.xml (land)   # Layout horizontal
│   │   │   │   │   └── activity_calculator_add_result.xml
│   │   │   │   ├── values/
│   │   │   │   │   └── strings.xml                # Textos en español
│   │   │   │   └── values-en/
│   │   │   │       └── strings.xml                # Textos en inglés
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   │       └── java/es/ucm/fdi/pad/android01/
│   │           └── CalculatorAddUnitTest.java     # Tests unitarios
│   └── build.gradle
```

---

## 🎯 Funcionalidades Implementadas

### 1. Entrada de Datos (MainActivity)
- Dos campos de texto (EditText) para ingresar números
- Botón "Calcular" para realizar la suma
- Validación de campos vacíos
- Toast messages para feedback al usuario

### 2. Cálculo y Navegación
- Clase `CalculatorAdd` con método estático para sumar
- Intent explícito para navegar a la pantalla de resultados
- Paso de datos entre Activities mediante Bundle

### 3. Pantalla de Resultados (CalculatorAddResultActivity)
- Recepción de datos del Intent
- Visualización del resultado en TextView
- Botón para volver a la pantalla principal

### 4. Soporte de Orientaciones
- Layout específico para modo vertical
- Layout específico para modo apaisado (landscape)
- Preservación del estado al rotar

### 5. Internacionalización (i18n)
- Strings resources en español (`values/strings.xml`)
- Strings resources en inglés (`values-en/strings.xml`)
- Cambio automático según idioma del dispositivo

### 6. Testing Unitario
Tests implementados en `CalculatorAddUnitTest.java`:
- Suma de números positivos
- Suma de números decimales
- Suma con números negativos
- Suma con cero

---

## 🚀 Instalación y Ejecución

### Requisitos Previos
- Android Studio (última versión estable)
- JDK 8 o superior
- Android SDK con API 24 o superior
- Emulador Android o dispositivo físico

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/t3sorero/PAD.git
cd "Practicas/Practicas Android/Android01"
```

2. **Abrir en Android Studio**
   - Abrir Android Studio
   - Seleccionar `File` → `Open`
   - Navegar a la carpeta `Android01`
   - Esperar a que Gradle sincronice las dependencias

3. **Crear AVD (Android Virtual Device)**
   - Ir a `Tools` → `Device Manager`
   - Click en `Create Device`
   - Seleccionar un dispositivo (ej: Pixel 5)
   - Seleccionar una imagen del sistema (API 24 o superior)
   - Finalizar configuración

4. **Ejecutar la aplicación**
   - Click en `Run` → `Run 'app'`
   - O presionar `Shift + F10` (Windows/Linux) / `Ctrl + R` (Mac)
   - Seleccionar el emulador o dispositivo

---

## 🧪 Ejecutar Tests Unitarios

### Desde Android Studio
1. Abrir `CalculatorAddUnitTest.java`
2. Click derecho en el archivo
3. Seleccionar `Run 'CalculatorAddUnitTest'`

### Desde Terminal
```bash
# En el directorio del proyecto
./gradlew test

# Ver reporte de tests
./gradlew test --info
```

---

## 📖 Uso de la Aplicación

1. **Iniciar la app** en el emulador o dispositivo
2. **Ingresar el primer número** en el campo "Número 1"
3. **Ingresar el segundo número** en el campo "Número 2"
4. **Pulsar el botón "Calcular"**
5. **Ver el resultado** en la nueva pantalla
6. **Volver** a la pantalla principal para hacer otro cálculo

### Probar Diferentes Orientaciones
- En el emulador: `Ctrl + F11` (Windows/Linux) / `Cmd + Left/Right` (Mac)
- Verificar que el layout cambia correctamente

### Probar Multiidioma
1. Ir a `Settings` → `System` → `Languages & input` → `Languages`
2. Cambiar entre español e inglés
3. Verificar que los textos de la app cambian automáticamente

---

## 🎓 Conceptos Aprendidos

### Ciclo de Vida de Activities
- `onCreate()`: Creación de la Activity
- `onStart()`: Activity visible
- `onResume()`: Activity en primer plano
- `onPause()`: Activity parcialmente oculta
- `onStop()`: Activity oculta
- `onDestroy()`: Activity destruida

### ConstraintLayout y Chains
- Uso de constraints para posicionar elementos
- Chains horizontales y verticales
- Distribución equitativa de espacio

### Intent y Navegación
- **Intent explícito**: Navegación entre Activities propias
- **Bundle**: Paso de datos entre Activities
- **extras**: `putExtra()` y `getStringExtra()`

### Recursos y Configuraciones
- **Strings resources**: Separación de textos del código
- **Configuration qualifiers**: `-land`, `-en`, etc.
- **Layouts alternativos**: Para diferentes orientaciones

### Testing
- **Unit tests**: Tests de lógica sin dependencias de Android
- **JUnit 4**: Framework de testing
- **Assertions**: `assertEquals()`, `assertTrue()`, etc.

---

## 🐛 Problemas Comunes y Soluciones

### La app se cierra al rotar el dispositivo
**Solución**: Implementar `onSaveInstanceState()` para guardar el estado:
```java
@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("numero1", editTextNum1.getText().toString());
    outState.putString("numero2", editTextNum2.getText().toString());
}
```

### Los strings no cambian de idioma
**Solución**: Verificar que:
- `strings.xml` está en `res/values/` (español por defecto)
- `strings.xml` está en `res/values-en/` (inglés)
- Los IDs de los strings coinciden en ambos archivos

### Tests unitarios fallan
**Solución**: Verificar que:
- La clase de test está en `src/test/java/` (no en `androidTest`)
- Se importa JUnit correctamente
- Los métodos de test tienen la anotación `@Test`

### Gradle sync falla
**Solución**:
1. `File` → `Invalidate Caches / Restart`
2. Eliminar carpetas `.gradle` y `.idea`
3. Volver a abrir el proyecto

---

## 📊 Mejoras Futuras

- [ ] Implementar más operaciones (resta, multiplicación, división)
- [ ] Añadir historial de cálculos
- [ ] Implementar validación más robusta (evitar overflow)
- [ ] Mejorar UI con Material Design 3
- [ ] Añadir tests instrumentados (UI tests)
- [ ] Implementar ViewModel para gestión de estado

---

## 👤 Autor

**Javier Martín - Tesorero Ruíz**
Estudiante de Ingeniería del Software - UCM
[GitHub](https://github.com/t3sorero) | [LinkedIn](https://linkedin.com/in/javier-martín-tesorero-0127a62b5)

---

## 📄 Licencia

Este proyecto tiene fines educativos y académicos. El código puede ser utilizado como referencia respetando las políticas de la UCM sobre integridad académica.
