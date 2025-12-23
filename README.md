# Programación de Aplicaciones para Dispositivos móviles (PAD)

**Universidad Complutense de Madrid (UCM)**
Grado en Ingeniería del Software - Optativa 4º Curso
Curso 2025-2026

## 📱 Descripción

Repositorio de las prácticas de la asignatura **Programación de Aplicaciones para Dispositivos móviles (PAD)**, que incluye tanto el desarrollo de aplicaciones Android nativas utilizando Java como una Progressive Web App (PWA) moderna con Next.js y React.

---

## 📖 Contenido de las Prácticas

### Práctica 1 - Toma de Contacto con Android (Android01)
📂 **Ubicación**: [Practicas Android/Android01](Practicas Android/Android01/)

**Objetivo**: Familiarización con Android Studio, implementación de una primera app sencilla y aprendizaje de técnicas básicas de depuración.

**Características principales**:
- Aplicación calculadora básica (suma de dos números)
- Uso de `ConstraintLayout` y chains para diseño responsivo
- Navegación entre actividades mediante Intents
- Implementación de layouts en modo vertical y apaisado
- Soporte multiidioma (español e inglés)
- Testing unitario con JUnit
- Depuración con breakpoints y LogCat

**Tecnologías**: Java, Android SDK, JUnit

---

### Práctica 2 - Acceso a Servicio Remoto (GoogleBooksClient)
📂 **Ubicación**: [Practicas Android/GoogleBooksClient](Practicas Android/GoogleBooksClient/)

**Objetivo**: Comunicación con una API remota (Google Books API) y visualización eficiente de resultados mediante RecyclerView.

**Características principales**:
- Búsqueda de libros y revistas en Google Books API
- Filtrado por autor y/o título
- Uso de `AsyncTaskLoader` para peticiones asíncronas
- Visualización de resultados con `RecyclerView`
- Parsing de respuestas JSON
- CardView con Material Design
- Intent implícito para abrir URLs en navegador
- Indicador de carga y manejo de estados vacíos

**Tecnologías**: Java, Android SDK, Google Books API, RecyclerView, AsyncTaskLoader

---

### Práctica 3 - Progressive Web App (PWA GoogleBooks)
📂 **Ubicación**: [PWA GoogleBooks/pwa-google-books](PWA GoogleBooks/pwa-google-books/)
🌐 **Demo**: [https://t3sorero.github.io/PAD](https://t3sorero.github.io/PAD)

**Objetivo**: Desarrollo de una Progressive Web App moderna que funciona offline y se puede instalar como aplicación nativa.

**Características principales**:
- Búsqueda de libros en Google Books API con React y TypeScript
- **Funcionalidad offline** completa con Service Workers
- **Instalable** como app nativa en dispositivos móviles y escritorio
- Historial de búsquedas guardado en localStorage
- Diseño responsive con animaciones modernas
- Cacheo inteligente (NetworkFirst para API, CacheFirst para imágenes)
- Deploy automático a GitHub Pages

**Características PWA**:
- Service Worker con Workbox
- Manifest.json configurado
- Cacheo por estrategias (Network/Cache First)
- Offline-first architecture
- Iconos optimizados para instalación

**Tecnologías**: Next.js 16, React 19, TypeScript, Tailwind CSS 4, Workbox, Axios, GitHub Pages

---

## 🛠️ Tecnologías Utilizadas

### Desarrollo Android (Prácticas 1 y 2)
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

### Desarrollo Web (Práctica 3 - PWA)
- **Framework**: Next.js 16 con App Router
- **Biblioteca UI**: React 19
- **Lenguaje**: TypeScript
- **Estilos**: Tailwind CSS 4, CSS Modules
- **PWA**: Workbox (Service Workers)
- **HTTP Client**: Axios
- **Deploy**: GitHub Pages con gh-pages
- **Build**: Static Site Generation (SSG)

### APIs Externas
- **Google Books API** (usada en Práctica 2 y 3)

---

## 📁 Estructura del Repositorio
```
Practicas/
├── README.md
│
├── Practicas Android/
│   ├── Android01/                          # Práctica 1 - Calculadora Android
│   │   ├── README.md
│   │   ├── app/
│   │   │   ├── src/
│   │   │   │   ├── main/
│   │   │   │   │   ├── java/es/ucm/fdi/pad/android01/
│   │   │   │   │   │   ├── MainActivity.java
│   │   │   │   │   │   ├── CalculatorAdd.java
│   │   │   │   │   │   └── CalculatorAddResultActivity.java
│   │   │   │   │   ├── res/
│   │   │   │   │   │   ├── layout/
│   │   │   │   │   │   ├── values/
│   │   │   │   │   │   └── values-en/
│   │   │   │   │   └── AndroidManifest.xml
│   │   │   │   └── test/
│   │   │   │       └── java/.../CalculatorAddUnitTest.java
│   │   │   └── build.gradle
│   │
│   └── GoogleBooksClient/                  # Práctica 2 - Cliente Google Books
│       ├── README.md
│       ├── app/
│       │   ├── src/
│       │   │   ├── main/
│       │   │   │   ├── java/es/ucm/fdi/pad/googlebooksclient/
│       │   │   │   │   ├── MainActivity.java
│       │   │   │   │   ├── BookLoader.java
│       │   │   │   │   ├── BookInfo.java
│       │   │   │   │   └── BooksResultListAdapter.java
│       │   │   │   ├── res/
│       │   │   │   └── AndroidManifest.xml
│       │   │   └── test/
│       │   └── build.gradle
│
└── PWA GoogleBooks/
    └── pwa-google-books/                   # Práctica 3 - PWA Next.js
        ├── README.md
        ├── app/
        │   ├── layout.tsx
        │   ├── page.tsx
        │   └── globals.css
        ├── components/
        │   ├── BookCard.tsx
        │   ├── BookList.tsx
        │   ├── RecentBooks.tsx
        │   └── SearchBar.tsx
        ├── hooks/
        │   └── useBookSearch.ts
        ├── services/
        │   ├── googleBooksApi.ts
        │   └── localStorage.ts
        ├── public/
        │   ├── manifest.json
        │   ├── icon-192.png
        │   └── icon-512.png
        ├── workbox-config.js
        ├── next.config.js
        └── package.json
```

---

## 🚀 Cómo Ejecutar las Prácticas

### Requisitos Previos

#### Para Prácticas Android (1 y 2)
- **Android Studio** (última versión estable)
- **JDK 8 o superior**
- **SDK de Android** con API 24 o superior
- **Emulador Android** o dispositivo físico para pruebas

#### Para Práctica PWA (3)
- **Node.js 20 o superior**
- **npm, yarn, pnpm o bun**
- Navegador web moderno (Chrome, Edge, Firefox, Safari)

---

### Práctica 1 - Android01

📂 **Directorio**: `Practicas Android/Android01/`

1. Abrir Android Studio
2. Seleccionar `File` → `Open` y navegar a `Practicas Android/Android01/`
3. Esperar a que Gradle sincronice las dependencias
4. Crear un AVD (Android Virtual Device) si no existe
5. Ejecutar la app: `Run` → `Run 'app'` o presionar `Shift + F10`

Ver el [README de Android01](Practicas Android/Android01/README.md) para más detalles.

---

### Práctica 2 - GoogleBooksClient

📂 **Directorio**: `Practicas Android/GoogleBooksClient/`

1. Abrir Android Studio
2. Seleccionar `File` → `Open` y navegar a `Practicas Android/GoogleBooksClient/`
3. Esperar a que Gradle sincronice las dependencias
4. (Opcional) Configurar API Key de Google Books
5. Ejecutar la app: `Run` → `Run 'app'`

Ver el [README de GoogleBooksClient](Practicas Android/GoogleBooksClient/README.md) para más detalles.

---

### Práctica 3 - PWA GoogleBooks

📂 **Directorio**: `PWA GoogleBooks/pwa-google-books/`
🌐 **Demo en vivo**: [https://t3sorero.github.io/PAD](https://t3sorero.github.io/PAD)

```bash
# Navegar al directorio
cd "PWA GoogleBooks/pwa-google-books"

# Instalar dependencias
npm install

# Iniciar servidor de desarrollo
npm run dev
```

Abrir [http://localhost:3000](http://localhost:3000) en tu navegador.

**Para probar funcionalidades PWA**:
```bash
# Generar build de producción con Service Worker
npm run build

# Deploy a GitHub Pages
npm run deploy
```

Ver el [README de PWA GoogleBooks](PWA GoogleBooks/pwa-google-books/README.md) para más detalles.

---

## 📊 Aprendizajes Clave

### Desarrollo Android (Prácticas 1 y 2)
- ✅ Ciclo de vida de Activities y navegación con Intents
- ✅ Diseño de interfaces con ConstraintLayout
- ✅ RecyclerView y ViewHolder pattern para listas eficientes
- ✅ Comunicación HTTP con APIs REST (Google Books)
- ✅ AsyncTaskLoader para operaciones asíncronas
- ✅ Parsing de JSON nativo
- ✅ Testing unitario con JUnit
- ✅ Soporte multiidioma y configuraciones

### Desarrollo Web PWA (Práctica 3)
- ✅ Progressive Web Apps con Service Workers
- ✅ Next.js con App Router y React Server Components
- ✅ TypeScript para desarrollo type-safe
- ✅ Estrategias de cacheo (NetworkFirst, CacheFirst)
- ✅ Offline-first architecture
- ✅ Deploy automático con GitHub Pages
- ✅ Manifest.json para instalación como app nativa
- ✅ Custom hooks y separación de responsabilidades

---

## 🔗 Recursos Útiles

### Documentación Android
- [Android Developers](https://developer.android.com/)
- [Android Studio User Guide](https://developer.android.com/studio/intro)
- [RecyclerView Guide](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [Material Design Guidelines](https://m3.material.io/)

### Documentación Web/PWA
- [Next.js Documentation](https://nextjs.org/docs)
- [React Documentation](https://react.dev/)
- [PWA Guide](https://web.dev/progressive-web-apps/)
- [Workbox Documentation](https://developers.google.com/web/tools/workbox)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)

### APIs
- [Google Books API](https://developers.google.com/books/docs/v1/using)

### Herramientas
- [Android API Levels](https://apilevels.com/)
- [Can I Use](https://caniuse.com/) - Compatibilidad web
- [JSON Formatter](https://jsonformatter.org/)

---

## 👤 Autor

**Javier Martín - Tesorero Ruíz**  
Estudiante de Ingeniería del Software - UCM  
[GitHub](https://github.com/t3sorero) | [LinkedIn](https://linkedin.com/in/javier-martín-tesorero-0127a62b5)

---

## 📄 Licencia

Este proyecto tiene fines educativos y académicos. El código puede ser utilizado como referencia respetando las políticas de la UCM sobre integridad académica.
