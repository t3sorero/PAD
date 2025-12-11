importScripts('https://storage.googleapis.com/workbox-cdn/releases/7.0.0/workbox-sw.js');

if (workbox) {
  console.log('Workbox cargado');

  // Precachear archivos estáticos (HTML, CSS, JS)
  workbox.precaching.precacheAndRoute([]);

  // Estrategia NetworkFirst para la API de Google Books
  workbox.routing.registerRoute(
    ({ url }) => url.origin === 'https://www.googleapis.com',
    new workbox.strategies.NetworkFirst({
      cacheName: 'google-books-api',
      plugins: [
        new workbox.cacheableResponse.CacheableResponsePlugin({
          statuses: [0, 200],
        }),
        new workbox.expiration.ExpirationPlugin({
          maxEntries: 50,
          maxAgeSeconds: 60 * 60 * 24, // 24 horas
        }),
      ],
    })
  );

  // Estrategia CacheFirst para imágenes de portadas
  workbox.routing.registerRoute(
    ({ url }) => url.origin === 'https://books.google.com',
    new workbox.strategies.CacheFirst({
      cacheName: 'book-covers',
      plugins: [
        new workbox.expiration.ExpirationPlugin({
          maxEntries: 60,
          maxAgeSeconds: 30 * 24 * 60 * 60, // 30 días
        }),
      ],
    })
  );

  // Estrategia CacheFirst para assets locales (JS, CSS, imágenes)
  workbox.routing.registerRoute(
    ({ request }) => 
      request.destination === 'style' ||
      request.destination === 'script' ||
      request.destination === 'image',
    new workbox.strategies.CacheFirst({
      cacheName: 'assets',
      plugins: [
        new workbox.expiration.ExpirationPlugin({
          maxEntries: 100,
          maxAgeSeconds: 7 * 24 * 60 * 60, // 7 días
        }),
      ],
    })
  );

} else {
  console.error('Workbox no pudo cargarse');
}