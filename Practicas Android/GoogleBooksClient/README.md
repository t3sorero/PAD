# Práctica 2 - Acceso a Servicio Remoto (Google Books Client)

**Asignatura**: Programación de Aplicaciones para Dispositivos móviles (PAD)
**Universidad Complutense de Madrid (UCM)**

---

## 📱 Descripción

Segunda práctica de la asignatura PAD centrada en la comunicación con APIs REST remotas. Se implementa un cliente Android para la Google Books API que permite buscar libros y revistas, mostrando los resultados en una lista eficiente mediante RecyclerView.

La aplicación realiza peticiones HTTP asíncronas utilizando `AsyncTaskLoader`, parsea las respuestas JSON y presenta la información en tarjetas (CardView) con diseño Material Design.

---

## ✨ Características Principales

- **Búsqueda en Google Books API** por título y/o autor
- **Filtrado por tipo de publicación** (Libros, Revistas, o Ambos)
- **Peticiones asíncronas** con AsyncTaskLoader
- **RecyclerView** para visualización eficiente de resultados
- **CardView** con Material Design para cada resultado
- **Intent implícito** para abrir URLs de libros en el navegador
- **Indicador de carga** durante las búsquedas
- **Manejo de estados** (cargando, vacío, error)
- **Soporte multiidioma** (español e inglés)
- **Parsing JSON nativo** sin librerías externas

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java
- **IDE**: Android Studio
- **SDK mínimo**: API 24 (Android 7.0 "Nougat")
- **Componentes Android**:
  - RecyclerView
  - CardView
  - AsyncTaskLoader
  - LoaderManager
  - HttpURLConnection
- **Networking**: HttpURLConnection
- **Data Format**: JSON
- **API**: Google Books API
- **Build System**: Gradle

---

## 📁 Estructura del Proyecto

```
GoogleBooksClient/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/es/ucm/fdi/pad/googlebooksclient/
│   │   │   │   ├── MainActivity.java                 # Activity principal
│   │   │   │   ├── BookLoader.java                   # AsyncTaskLoader
│   │   │   │   ├── BookInfo.java                     # Modelo de datos
│   │   │   │   └── BooksResultListAdapter.java       # Adapter RecyclerView
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml             # Layout principal
│   │   │   │   │   └── book_item.xml                 # Item de libro
│   │   │   │   ├── values/
│   │   │   │   │   └── strings.xml                   # Textos en español
│   │   │   │   └── values-en/
│   │   │   │       └── strings.xml                   # Textos en inglés
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   │       └── java/...                              # Tests unitarios
│   └── build.gradle
```

---

## 🎯 Funcionalidades Implementadas

### 1. Interfaz de Búsqueda
- **RadioGroup** para seleccionar tipo de búsqueda:
  - Solo libros
  - Solo revistas
  - Ambos
- **EditText para autor** (solo para libros)
- **EditText para título** (obligatorio)
- **Botón de búsqueda** con validación

### 2. Comunicación con API
**BookLoader.java** (AsyncTaskLoader):
- Construcción de URL de consulta con parámetros
- Petición HTTP GET a Google Books API
- Descarga y lectura de respuesta JSON
- Parsing de datos en background thread
- Retorno de lista de resultados

```java
@Override
public List<BookInfo> loadInBackground() {
    String jsonResponse = getBookInfoJson(queryString, printType);
    return BookInfo.fromJsonResponse(jsonResponse);
}
```

### 3. Modelo de Datos
**BookInfo.java**:
- Campos: `title`, `authors`, `infoLink`
- Constructor y getters
- Método estático `fromJsonResponse()` para parsing JSON
- Manejo de campos opcionales

### 4. Visualización de Resultados
**RecyclerView con BooksResultListAdapter**:
- ViewHolder pattern para eficiencia
- Inflado de CardView para cada libro
- Binding de datos (título, autor, link)
- Click listener para abrir URL

### 5. Estados de la Aplicación
- **Estado inicial**: Mensaje de bienvenida
- **Estado cargando**: ProgressBar visible
- **Estado con resultados**: RecyclerView con libros
- **Estado vacío**: Mensaje "No se encontraron resultados"
- **Estado error**: Mensaje de error de red

---

## 🌐 Google Books API

### Endpoint Utilizado
```
GET https://www.googleapis.com/books/v1/volumes
```

### Parámetros de Consulta
- `q`: Query de búsqueda
  - `intitle:término` - Búsqueda por título
  - `inauthor:término` - Búsqueda por autor
- `printType`: Tipo de publicación
  - `books` - Solo libros
  - `magazines` - Solo revistas
  - `all` - Ambos
- `maxResults`: Número máximo de resultados (ej: 10)

### Ejemplo de URL
```
https://www.googleapis.com/books/v1/volumes?q=intitle:Harry+Potter+inauthor:Rowling&printType=books&maxResults=10
```

### Respuesta JSON (estructura simplificada)
```json
{
  "items": [
    {
      "volumeInfo": {
        "title": "Harry Potter and the Philosopher's Stone",
        "authors": ["J.K. Rowling"],
        "infoLink": "https://books.google.com/books?id=..."
      }
    }
  ]
}
```

---

## 🚀 Instalación y Ejecución

### Requisitos Previos
- Android Studio (última versión estable)
- JDK 8 o superior
- Android SDK con API 24 o superior
- Conexión a Internet
- Emulador Android o dispositivo físico

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/t3sorero/PAD.git
cd "Practicas/Practicas Android/GoogleBooksClient"
```

2. **Abrir en Android Studio**
   - Abrir Android Studio
   - Seleccionar `File` → `Open`
   - Navegar a la carpeta `GoogleBooksClient`
   - Esperar a que Gradle sincronice las dependencias

3. **Configurar permisos de Internet**
   - Verificar en `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

4. **(Opcional) Configurar API Key**
   - Por defecto, Google Books API permite peticiones sin API Key
   - Para mayor límite de peticiones:
     1. Ir a [Google Cloud Console](https://console.cloud.google.com/)
     2. Crear proyecto y habilitar Google Books API
     3. Crear credenciales (API Key)
     4. Añadir la key en `BookLoader.java`

5. **Ejecutar la aplicación**
   - Click en `Run` → `Run 'app'`
   - O presionar `Shift + F10` (Windows/Linux) / `Ctrl + R` (Mac)
   - Seleccionar el emulador o dispositivo

---

## 📖 Uso de la Aplicación

### Búsqueda Básica
1. **Seleccionar tipo de búsqueda** (Libros, Revistas, o Ambos)
2. **Ingresar autor** (opcional, solo para libros)
3. **Ingresar título** (obligatorio)
4. **Pulsar "Buscar"**
5. **Ver resultados** en la lista
6. **Pulsar en un resultado** para abrir más información en el navegador

### Ejemplos de Búsqueda

**Buscar libros de Harry Potter:**
- Tipo: Libros
- Autor: Rowling
- Título: Harry Potter

**Buscar todas las publicaciones sobre programación:**
- Tipo: Ambos
- Autor: (vacío)
- Título: Programming

**Buscar revistas científicas:**
- Tipo: Revistas
- Autor: (vacío)
- Título: Science

---

## 🎓 Conceptos Aprendidos

### Networking y Threading
- **AsyncTaskLoader**: Operaciones asíncronas con ciclo de vida
- **LoaderManager**: Gestión de loaders vinculados a Activities
- **HttpURLConnection**: Cliente HTTP nativo de Android
- **Background threads**: Evitar NetworkOnMainThreadException
- **Callbacks**: `LoaderManager.LoaderCallbacks<>`

### Parsing de Datos
- **JSONObject y JSONArray**: Parsing manual de JSON
- **Manejo de campos opcionales**: `has()`, `isNull()`
- **Try-catch**: Manejo de excepciones de parsing
- **Data mapping**: JSON → Objetos Java

### RecyclerView y Adapters
- **RecyclerView.Adapter**: Patrón adapter
- **ViewHolder pattern**: Reciclaje eficiente de vistas
- **LayoutManager**: LinearLayoutManager vertical
- **notifyDataSetChanged()**: Actualización de datos
- **Click listeners**: Manejo de eventos en items

### Material Design
- **CardView**: Tarjetas con elevación
- **Layout guidelines**: Márgenes y padding consistentes
- **ProgressBar**: Indicadores de carga
- **TextView states**: Mensajes de feedback

### Intents
- **Intent implícito**: Abrir URLs en navegador externo
- **ACTION_VIEW**: Acción para ver contenido web
- **Uri.parse()**: Conversión de String a Uri

---

## 🏗️ Arquitectura de Datos

### Flujo de Datos
```
Usuario → MainActivity (UI Thread)
              ↓
        LoaderManager.restartLoader()
              ↓
        BookLoader.loadInBackground() (Background Thread)
              ↓
        HttpURLConnection → Google Books API
              ↓
        JSON Response String
              ↓
        BookInfo.fromJsonResponse() → Parsing
              ↓
        List<BookInfo>
              ↓
        LoaderCallbacks.onLoadFinished() (UI Thread)
              ↓
        BooksResultListAdapter.setData()
              ↓
        RecyclerView actualiza UI
```

---

## 🐛 Problemas Comunes y Soluciones

### NetworkOnMainThreadException
**Problema**: Error al hacer peticiones HTTP en el hilo principal.
**Solución**: Asegurarse de usar AsyncTaskLoader correctamente y no hacer llamadas HTTP en `onCreate()`.

### No se muestran resultados
**Posibles causas**:
1. **Permisos de Internet**: Verificar `AndroidManifest.xml`
2. **URL incorrecta**: Revisar construcción de query en `BookLoader`
3. **Parsing falla**: Verificar estructura JSON en Logcat
4. **Adapter no actualiza**: Llamar a `notifyDataSetChanged()`

**Debugging**:
```bash
# Ver logs de la app
adb logcat -s BookLoader:V MainActivity:V
```

### RecyclerView no se actualiza
**Solución**:
```java
adapter.setData(newBooks);
adapter.notifyDataSetChanged();
```

### JSON parsing falla
**Solución**:
- Usar `maxResults` para limitar respuesta
- Verificar que los campos existen: `has("title")`
- Manejar arrays vacíos: `authors.length() > 0`

### Caracteres especiales en búsqueda
**Solución**: Usar `URLEncoder.encode()` para espacios y caracteres especiales:
```java
String encoded = URLEncoder.encode(query, "UTF-8");
```

### App lenta al hacer scroll
**Solución**: Verificar que se está usando ViewHolder pattern correctamente en el adapter.

---

## 📊 Mejoras Implementadas (Opcionales)

- ✅ CardView para cada entrada de la lista
- ✅ Event handler para abrir URLs en navegador
- ✅ Indicador "Cargando..." durante búsquedas
- ✅ Manejo de búsquedas sin resultados
- ✅ Variante en idioma inglés
- ✅ Diseño Material Design
- ✅ Validación de campos de entrada

---

## 🔮 Mejoras Futuras

- [ ] Migrar de AsyncTaskLoader a Coroutines/RxJava
- [ ] Implementar caché local de resultados
- [ ] Añadir paginación (cargar más resultados)
- [ ] Implementar búsqueda avanzada (categorías, fechas)
- [ ] Mostrar portadas de libros con Glide/Picasso
- [ ] Añadir favoritos con Room Database
- [ ] Implementar modo oscuro
- [ ] Añadir compartir libros (Share Intent)
- [ ] Tests instrumentados (UI tests con Espresso)

---

## 🔗 Recursos Útiles

### Documentación
- [Google Books API Documentation](https://developers.google.com/books/docs/v1/using)
- [RecyclerView Guide](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [AsyncTaskLoader (Deprecated)](https://developer.android.com/reference/android/content/AsyncTaskLoader)
- [HttpURLConnection](https://developer.android.com/reference/java/net/HttpURLConnection)

### Herramientas
- [JSON Formatter](https://jsonformatter.org/) - Visualizar JSON
- [Postman](https://www.postman.com/) - Probar API
- [Android Logcat](https://developer.android.com/studio/debug/logcat) - Debugging

---

## 👤 Autor

**Javier Martín - Tesorero Ruíz**
Estudiante de Ingeniería del Software - UCM
[GitHub](https://github.com/t3sorero) | [LinkedIn](https://linkedin.com/in/javier-martín-tesorero-0127a62b5)

---

## 📄 Licencia

Este proyecto tiene fines educativos y académicos. El código puede ser utilizado como referencia respetando las políticas de la UCM sobre integridad académica.
