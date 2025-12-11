export function registerSW() {
  if ('serviceWorker' in navigator) {
    window.addEventListener('load', () => {
      navigator.serviceWorker
        .register('/sw.js')
        .then((registration) => {
          console.log('Service Worker registrado:', registration);
        })
        .catch((error) => {
          console.error('Error al registrar Service Worker:', error);
        });
    });
  } else {
    console.warn('Service Worker no soportado en este navegador');
  }
}