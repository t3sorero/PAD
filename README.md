# Programación de Aplicaciones para Dispositivos móviles (PAD)

**Universidad Complutense de Madrid (UCM)**  
Grado en Ingeniería del Software - Optativa 4º Curso
Curso 2025-2026

## 📱 Descripción

Repositorio de las prácticas de la asignatura **Programación de Aplicaciones para Dispositivos móviles (PAD)**, centrada en el desarrollo de aplicaciones Android nativas utilizando Java y Android Studio.

---

## 📖 Contenido de las Prácticas

### Práctica 1 - Toma de Contacto con Android
**Objetivo**: Familiarización con Android Studio, implementación de una primera app sencilla y aprendizaje de técnicas básicas de depuración.

**Características principales**:
- Aplicación calculadora básica (suma de dos números)
- Uso de `ConstraintLayout` y chains para diseño responsivo
- Navegación entre actividades mediante Intents
- Implementación de layouts en modo vertical y apaisado
- Soporte multiidioma (español e inglés)
- Testing unitario con JUnit
- Depuración con breakpoints y LogCat

**Conceptos aplicados**:
- Ciclo de vida de Activities
- Diseño de interfaces con XML
- Constraints y chains
- Strings resources para i18n
- Unit testing
- Logging y debugging

---

### Práctica 2 - Acceso a Servicio Remoto
**Objetivo**: Comunicación con una API remota (Google Books API) y visualización eficiente de resultados mediante RecyclerView.

**Características principales**:
- Búsqueda de libros y revistas en Google Books API
- Filtrado por autor y/o título
- Uso obligatorio de `AsyncTaskLoader` para peticiones asíncronas
- Visualización de resultados con `RecyclerView`
- Parsing de respuestas JSON
- Credenciales de API mediante Google Cloud Console

**Conceptos aplicados**:
- Comunicación HTTP con `HttpURLConnection`
- AsyncTaskLoader y LoaderManager
- Parsing de JSON
- RecyclerView y Adapters
- ViewHolder pattern
- RadioGroup y RadioButton

**Partes opcionales implementadas**:
- CardView para cada entrada de la lista
- Event handlers para abrir URLs en navegador (Intent implícito)
- Indicador "Cargando..." durante búsquedas
- Manejo de búsquedas sin resultados
- Variante en idioma inglés

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java
- **IDE**: Android Studio
- **SDK mínimo**: API 24 (Android 7.0 "Nougat")
- **Componentes Android**:
  - ConstraintLayout
  - RecyclerView
  - AsyncTaskLoader
  - HttpURLConnection
  - Intent (explícito e implícito)
  - CardView
- **Testing**: JUnit
- **API externa**: Google Books API

---

## 📁 Estructura del Repositorio
```
PAD/
├── README.md
├── Android01/
│   ├── app/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/es/ucm/fdi/pad/android01/
│   │   │   │   │   ├── MainActivity.java
│   │   │   │   │   ├── CalculatorAdd.java
│   │   │   │   │   └── CalculatorAddResultActivity.java
│   │   │   │   ├── res/
│   │   │   │   │   ├── layout/
│   │   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   │   ├── activity_main.xml (land)
│   │   │   │   │   │   └── activity_calculator_add_result.xml
│   │   │   │   │   ├── values/
│   │   │   │   │   │   └── strings.xml
│   │   │   │   │   └── values-en/
│   │   │   │   │       └── strings.xml
│   │   │   │   └── AndroidManifest.xml
│   │   │   └── test/
│   │   │       └── java/es/ucm/fdi/pad/android01/
│   │   │           └── CalculatorAddUnitTest.java
│   │   └── build.gradle
│
└── GoogleBooksClient/
    ├── app/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/es/ucm/fdi/pad/googlebooksclient/
    │   │   │   │   ├── MainActivity.java
    │   │   │   │   ├── BookLoader.java
    │   │   │   │   ├── BookInfo.java
    │   │   │   │   └── BooksResultListAdapter.java
    │   │   │   ├── res/
    │   │   │   │   ├── layout/
    │   │   │   │   │   ├── activity_main.xml
    │   │   │   │   │   └── book_item.xml
    │   │   │   │   └── values/
    │   │   │   │       └── strings.xml
    │   │   │   └── AndroidManifest.xml
    │   │   └── test/
    │   └── build.gradle

```

---

## 🚀 Cómo Ejecutar las Prácticas

### Requisitos Previos
- **Android Studio** (última versión estable)
- **JDK 8 o superior**
- **SDK de Android** con API 24 o superior
- **Emulador Android** o dispositivo físico para pruebas

### Práctica 1 - Android01

1. Abrir Android Studio
2. Seleccionar `File` → `Open` y navegar a `Android01/`
3. Esperar a que Gradle sincronice las dependencias
4. Crear un AVD (Android Virtual Device) si no existe:
   - `Tools` → `Device Manager` → `Create Device`
   - Seleccionar un dispositivo (ej: Pixel 5)
   - Seleccionar una imagen del sistema (API 24+)
5. Ejecutar la app: `Run` → `Run 'app'` o presionar `Shift + F10`

**Ejecutar tests unitarios**:
```bash
# Desde Android Studio
1. Abrir CalculatorAddUnitTest.java
2. Click derecho → Run 'CalculatorAddUnitTest'

# Desde terminal
./gradlew test
```

**Probar diferentes orientaciones**:
- En el emulador: `Ctrl + F11` / `Cmd + Left/Right` (Mac)
- Verificar que el layout apaisado se muestra correctamente

**Probar multiidioma**:
- Cambiar idioma del emulador: `Settings` → `System` → `Languages`
- Verificar strings en español e inglés

---

### Práctica 2 - GoogleBooksClient

1. Abrir Android Studio
2. Seleccionar `File` → `Open` y navegar a `GoogleBooksClient/`
3. Esperar a que Gradle sincronice las dependencias

**Configurar credenciales de Google Books API**:
1. Acceder a [Google Cloud Console](https://console.cloud.google.com/)
2. Crear un nuevo proyecto o seleccionar uno existente
3. Habilitar la API de Google Books
4. Crear credenciales (API Key)
5. Añadir la API Key en el código (clase `BookLoader`)

**Ejecutar la app**:
1. `Run` → `Run 'app'` o presionar `Shift + F10`
2. En la interfaz:
   - Seleccionar tipo de búsqueda (Libros/Revistas/Ambos)
   - Introducir autor (solo para libros)
   - Introducir título
   - Pulsar "Buscar"
3. Observar la lista de resultados en RecyclerView
4. (Opcional) Pulsar sobre un resultado para abrir su URL

**Verificar logs**:
```bash
# En Logcat filtrar por tag
adb logcat -s BookLoader:V
```

---

## 🎯 Detalles de Implementación

### Práctica 1: Arquitectura

**MainActivity.java**:
- Captura de entrada del usuario desde EditText
- Validación de campos vacíos
- Llamada a `CalculatorAdd.addNumbers()`
- Lanzamiento de Intent explícito hacia `CalculatorAddResultActivity`

**CalculatorAdd.java**:
```java
public static double addNumbers(double a, double b) {
    return a + b;
}
```

**CalculatorAddResultActivity.java**:
- Recepción de datos del Intent
- Visualización del resultado en TextView

**Testing**:
- Test de suma con enteros positivos
- Test de suma con decimales
- Test de suma con negativos
- Test de suma con cero

---

### Práctica 2: Arquitectura

**Flujo de datos**:
```
Usuario → MainActivity → BookLoaderCallbacks
                              ↓
                         BookLoader
                              ↓
                    getBookInfoJson() → Google Books API
                              ↓
                         JSON Response
                              ↓
                    BookInfo.fromJsonResponse()
                              ↓
                    List<BookInfo> → BooksResultListAdapter
                              ↓
                         RecyclerView
```

**BookLoader.java** (AsyncTaskLoader):
```java
@Override
public List<BookInfo> loadInBackground() {
    String jsonResponse = getBookInfoJson(queryString, printType);
    return BookInfo.fromJsonResponse(jsonResponse);
}

@Override
protected void onStartLoading() {
    forceLoad(); // Forzar carga sin caché
}
```

**BookInfo.java**:
- Campos: `title`, `authors`, `infoLink`
- Método estático `fromJsonResponse(String json)` para parsing

**BooksResultListAdapter.java**:
- Hereda de `RecyclerView.Adapter<ViewHolder>`
- Gestiona la lista de `BookInfo`
- Infla el layout de cada item (CardView)

**MainActivity.java**:
```java
public void searchBooks(View view) {
    Bundle queryBundle = new Bundle();
    queryBundle.putString(EXTRA_QUERY, queryString);
    queryBundle.putString(EXTRA_PRINT_TYPE, printType);
    LoaderManager.getInstance(this)
        .restartLoader(BOOK_LOADER_ID, queryBundle, callbacks);
}
```

---

## 📊 Resultados y Aprendizajes

### Práctica 1
- ✅ Comprensión del ciclo de vida de Activities
- ✅ Dominio de ConstraintLayout y chains
- ✅ Implementación de navegación entre Activities
- ✅ Diseño adaptable a orientaciones
- ✅ Internacionalización (i18n)
- ✅ Testing unitario básico
- ✅ Debugging con Android Studio

### Práctica 2
- ✅ Comunicación HTTP con APIs REST
- ✅ Gestión de operaciones asíncronas con AsyncTaskLoader
- ✅ Parsing de JSON sin librerías externas
- ✅ RecyclerView para listas eficientes
- ✅ ViewHolder pattern
- ✅ CardView para diseño Material Design
- ✅ Intents implícitos para abrir URLs
- ✅ Gestión de estados de carga (loading, error, empty)

---

## 🔧 Problemas Comunes y Soluciones

### Práctica 1

**Problema**: La app se cierra al rotar el dispositivo
- **Solución**: Implementar `onSaveInstanceState()` para guardar el estado

**Problema**: Los strings no cambian de idioma
- **Solución**: Verificar que `strings.xml` está en `values/` y `values-en/`

**Problema**: Tests unitarios fallan
- **Solución**: Verificar que la clase está en `src/test/java/` y no en `src/androidTest/`

---

### Práctica 2

**Problema**: `NetworkOnMainThreadException`
- **Solución**: Asegurarse de usar AsyncTaskLoader correctamente

**Problema**: No se muestran resultados
- **Solución**: 
  - Verificar permisos de Internet en `AndroidManifest.xml`
  - Comprobar API Key válida
  - Revisar logs en Logcat

**Problema**: RecyclerView no actualiza
- **Solución**: Llamar a `notifyDataSetChanged()` después de modificar datos

**Problema**: JSON parsing falla
- **Solución**: Usar `maxResults` para limitar respuesta y verificar estructura JSON

---

## 📚 Conceptos Clave

### Android Fundamentals
- **Activity Lifecycle**: onCreate, onStart, onResume, onPause, onStop, onDestroy
- **Intent**: Explícito (navegación interna) vs Implícito (apps externas)
- **Bundle**: Paso de datos entre Activities
- **Resources**: Strings, layouts, drawables
- **Configuration Changes**: Orientación, idioma

### UI Components
- **ConstraintLayout**: Sistema de restricciones para layouts flexibles
- **Chains**: Distribución equitativa de views
- **RecyclerView**: Lista eficiente con reciclaje de vistas
- **CardView**: Contenedor con elevación Material Design
- **RadioGroup**: Selección única entre opciones

### Networking & Threading
- **HttpURLConnection**: Cliente HTTP nativo de Android
- **AsyncTaskLoader**: Carga asíncrona de datos (deprecated pero requerido en práctica)
- **LoaderManager**: Gestión del ciclo de vida de loaders
- **JSON Parsing**: `JSONObject` y `JSONArray`

### Best Practices
- **Separation of Concerns**: Lógica separada de UI
- **ViewHolder Pattern**: Optimización de RecyclerView
- **Error Handling**: Try-catch para network y parsing
- **Logging**: Uso apropiado de Log.d, Log.e, etc.

---

## 🔗 Recursos Útiles

### Documentación Oficial
- [Android Developers](https://developer.android.com/)
- [Android Studio User Guide](https://developer.android.com/studio/intro)
- [RecyclerView Guide](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [Google Books API](https://developers.google.com/books/docs/v1/using)

### Tutoriales
- [Codelabs Android](https://codelabs.developers.google.com/?cat=Android)
- [Android Basics](https://developer.android.com/courses/android-basics-compose/course)

### Herramientas
- [JSON Formatter](https://jsonformatter.org/)
- [Material Design Guidelines](https://m3.material.io/)
- [Android API Levels](https://apilevels.com/)

---

## 👤 Autor

**Javier Martín - Tesorero Ruíz**  
Estudiante de Ingeniería del Software - UCM  
[GitHub](https://github.com/t3sorero) | [LinkedIn](https://linkedin.com/in/javier-martín-tesorero-0127a62b5)

---

## 📄 Licencia

Este proyecto tiene fines educativos y académicos. El código puede ser utilizado como referencia respetando las políticas de la UCM sobre integridad académica.
