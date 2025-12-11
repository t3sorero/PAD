import type { Metadata } from 'next';
import './globals.css';

export const metadata: Metadata = {
  title: 'Buscador de Libros',
  description: 'Busca libros en Google Books',
  manifest: '/manifest.json',
  themeColor: '#1976d2',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="es">
      <head>
        <link rel="manifest" href="/manifest.json" />
        <meta name="theme-color" content="#1976d2" />
        <script
          dangerouslySetInnerHTML={{
            __html: `
              if ('serviceWorker' in navigator) {
                window.addEventListener('load', () => {
                  navigator.serviceWorker.register('/sw.js').then(reg => {
                    console.log('Service Worker registrado:', reg);
                  }, function (err) {
                    console.log('Error al registrar Service Worker:', err);
                  });
                });
              }
            `,
          }}
        />
      </head>
      <body>{children}</body>
    </html>
  );
}