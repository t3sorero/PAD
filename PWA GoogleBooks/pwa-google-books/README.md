# 📚 Buscador de Libros PWA - Google Books

Una **Progressive Web App (PWA)** moderna desarrollada con **Next.js 16** y **React 19** que permite buscar libros utilizando la API de Google Books. La aplicación funciona completamente offline gracias a Service Workers y cacheo inteligente.

---

## 🌐 Demo en Vivo

**[https://t3sorero.github.io/PAD](https://t3sorero.github.io/PAD)**

---

## ✨ Características Principales

### 🎯 Funcionalidades Core
- **Búsqueda de libros** en tiempo real usando Google Books API
- **Historial de búsquedas** guardado en localStorage
- **Visualización de últimos libros consultados** en la página principal
- **Información detallada** de cada libro (título, autor, fecha de publicación, portada)
- **Diseño responsive** optimizado para móviles, tablets y escritorio

### 🚀 PWA Features
- **Instalable** en dispositivos móviles y escritorio
- **Funciona offline** gracias al Service Worker
- **Cacheo inteligente** de recursos y API calls
  - NetworkFirst para llamadas API (24h de caché)
  - CacheFirst para imágenes (30 días de caché)
- **Manifest.json** configurado para instalación como app nativa
- **Iconos optimizados** (192x192 y 512x512)

### 🎨 UI/UX Mejorada
- **Diseño moderno** con gradientes y animaciones suaves
- **Efectos hover interactivos** en todas las tarjetas
- **Animaciones CSS**:
  - Fade-in en listas de resultados
  - Elevación de tarjetas al pasar el mouse
  - Spinner animado durante la carga
  - Efecto shimmer en botón de búsqueda
- **Sistema de diseño consistente** con CSS custom properties
- **Accesibilidad mejorada** con estados de focus visibles

---

## 🛠️ Tecnologías Utilizadas

### Frontend
- **[Next.js 16](https://nextjs.org/)** - Framework React con SSR/SSG
- **[React 19](https://react.dev/)** - Biblioteca de UI
- **[TypeScript](https://www.typescriptlang.org/)** - Tipado estático
- **CSS Modules** - Estilos encapsulados por componente
- **[Tailwind CSS 4](https://tailwindcss.com/)** - Framework de utilidades CSS

### PWA & Performance
- **[Workbox](https://developers.google.com/web/tools/workbox)** - Service Worker avanzado
- **Next.js Static Export** - Generación de sitio estático
- **localStorage API** - Persistencia de datos local

### APIs & Servicios
- **[Google Books API](https://developers.google.com/books)** - Búsqueda de libros
- **[Axios](https://axios-http.com/)** - Cliente HTTP

### DevOps & Deploy
- **[GitHub Pages](https://pages.github.com/)** - Hosting estático
- **[gh-pages](https://www.npmjs.com/package/gh-pages)** - Deploy automatizado
- **ESLint** - Linting de código

---

## 📁 Estructura del Proyecto

```
pwa-google-books/
├── app/
│   ├── layout.tsx          # Layout principal con metadata
│   ├── page.tsx            # Página principal
│   └── globals.css         # Estilos globales y variables CSS
├── components/
│   ├── BookCard.tsx        # Tarjeta individual de libro
│   ├── BookList.tsx        # Lista/Grid de libros
│   ├── RecentBooks.tsx     # Sección de libros recientes
│   └── SearchBar.tsx       # Barra de búsqueda
├── hooks/
│   └── useBookSearch.ts    # Hook personalizado para búsqueda
├── services/
│   ├── googleBooksApi.ts   # Servicio de API de Google Books
│   └── localStorage.ts     # Gestión de localStorage
├── styles/
│   ├── BookCard.module.css
│   ├── BookList.module.css
│   ├── RecentBooks.module.css
│   └── SearchBar.module.css
├── types/
│   └── Book.ts             # Tipos TypeScript
├── public/
│   ├── manifest.json       # Manifest de PWA
│   ├── icon-192.png        # Icono PWA 192x192
│   ├── icon-512.png        # Icono PWA 512x512
│   └── sw.js               # Service Worker (generado)
├── workbox-config.js       # Configuración de Workbox
├── next.config.js          # Configuración de Next.js
└── package.json            # Dependencias y scripts
```

---

## 🚀 Instalación y Uso

### Prerrequisitos
- Node.js 20 o superior
- npm, yarn, pnpm o bun

### Instalación

```bash
# Clonar el repositorio
git clone https://github.com/t3sorero/PAD.git
cd PAD/pwa-google-books

# Instalar dependencias
npm install
```

### Desarrollo

```bash
# Iniciar servidor de desarrollo
npm run dev
```

Abre [http://localhost:3000](http://localhost:3000) en tu navegador.

### Compilación para Producción

```bash
# Generar build estático + Service Worker
npm run build
```

### Deploy a GitHub Pages

```bash
# Build y deploy automático
npm run deploy
```

El sitio se desplegará en: `https://[tu-usuario].github.io/PAD`

---

## 📊 Sistema de Cacheo PWA

### Estrategias de Cache

| Recurso | Estrategia | Duración | Max Entries |
|---------|-----------|----------|-------------|
| **API Google Books** | NetworkFirst | 24 horas | 50 |
| **Imágenes (portadas)** | CacheFirst | 30 días | 60 |
| **Assets estáticos** | Precache | ∞ | - |

### NetworkFirst (API)
1. Intenta obtener datos de la red
2. Si falla o tarda >10s, usa caché
3. Actualiza caché en background

### CacheFirst (Imágenes)
1. Busca primero en caché
2. Si no existe, descarga de red
3. Guarda en caché para futuras visitas

---

## 🎨 Sistema de Diseño

### Paleta de Colores
```css
--primary-color: #1976d2;    /* Azul principal */
--primary-dark: #1565c0;     /* Azul oscuro */
--primary-light: #42a5f5;    /* Azul claro */
--secondary-color: #ff6f00;  /* Naranja acento */
```

### Sombras
- **shadow-sm**: Elementos en reposo
- **shadow-md**: Elementos elevados
- **shadow-lg**: Elementos activos/hover

### Animaciones
- **fadeIn**: Aparición suave de elementos
- **spin**: Rotación de spinner
- **pulse**: Pulsación durante carga

---

## 🔧 Configuración

### Base Path (GitHub Pages)

En `next.config.js`:
```javascript
basePath: '/PAD',
output: 'export',
```

En `manifest.json`:
```json
"start_url": "/PAD/",
```

### Variables de Entorno

No se requieren variables de entorno. La API de Google Books no necesita API key para uso básico.

---

## 📱 Características PWA

### Instalación
1. Visita el sitio en Chrome/Edge/Safari
2. Haz clic en "Instalar" en la barra de direcciones
3. La app se instalará como aplicación nativa

### Offline First
- Todos los recursos estáticos se cachean automáticamente
- Las búsquedas anteriores están disponibles offline
- Las imágenes se cachean por 30 días

---

## 🧪 Características Técnicas Destacadas

### Arquitectura
- **App Router de Next.js 16** con React Server Components
- **Client Components** para interactividad
- **Custom Hooks** para lógica reutilizable
- **Separación de responsabilidades** (components/services/hooks)

### Performance
- **Lazy loading** de imágenes
- **CSS Modules** para estilos optimizados
- **Static Site Generation** para máxima velocidad
- **Precaching** de recursos críticos

### Accesibilidad
- Estados de focus visibles
- Textos alternativos en imágenes
- Contraste de colores WCAG AA
- Scroll suave entre secciones

---

## 📝 Scripts Disponibles

```bash
npm run dev          # Servidor de desarrollo
npm run build        # Build de producción
npm run start        # Servidor de producción (local)
npm run lint         # Linting con ESLint
npm run deploy       # Build + Deploy a GitHub Pages
npm run generate-sw  # Generar Service Worker
```

---

## 🌟 Mejoras Realizadas

### Estilización Avanzada
- ✅ Sistema de variables CSS para theming
- ✅ Gradientes modernos en título y botones
- ✅ Animaciones de entrada (fade-in)
- ✅ Efectos hover con elevación
- ✅ Spinner de carga animado
- ✅ Responsive design completo

### PWA Optimizations
- ✅ Service Worker con Workbox
- ✅ Manifest.json configurado
- ✅ Iconos optimizados
- ✅ Cacheo inteligente por tipo de recurso
- ✅ Estrategias offline-first

### UX Improvements
- ✅ Feedback visual en todas las interacciones
- ✅ Estados de carga claros
- ✅ Mensajes de error informativos
- ✅ Historial de búsquedas
- ✅ Grid responsivo adaptativo

---

## 🐛 Troubleshooting

### El Service Worker no se actualiza
```bash
# Limpiar caché del navegador
Ctrl + Shift + Delete

# O en DevTools
Application > Service Workers > Unregister
```

### Error en GitHub Pages
```bash
# Verificar base path en next.config.js
basePath: '/PAD',  # Debe coincidir con el nombre del repo
```

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

---

## 👨‍💻 Autor

**Javier Marín**

- GitHub: [@t3sorero](https://github.com/t3sorero)
- Proyecto: [PAD - PWA Google Books](https://t3sorero.github.io/PAD)

---

## 🙏 Agradecimientos

- [Google Books API](https://developers.google.com/books) por proporcionar los datos
- [Next.js](https://nextjs.org/) por el excelente framework
- [Workbox](https://developers.google.com/web/tools/workbox) por facilitar PWAs

---

## 📚 Recursos Adicionales

- [Next.js Documentation](https://nextjs.org/docs)
- [PWA Guide](https://web.dev/progressive-web-apps/)
- [Google Books API Docs](https://developers.google.com/books/docs/v1/using)
- [Workbox Documentation](https://developers.google.com/web/tools/workbox/guides/get-started)

---

**⭐ Si te gusta este proyecto, dale una estrella en GitHub**
