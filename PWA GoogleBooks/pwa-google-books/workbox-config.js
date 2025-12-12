module.exports = {
  globDirectory: 'out/',
  globPatterns: [
    '**/*.{html,js,css,json,png,jpg,jpeg,svg,ico,webp,txt}'
  ],
  swDest: 'out/sw.js',
  modifyURLPrefix: {
    '': '/PAD/'
  },
  clientsClaim: true,
  skipWaiting: true,
  runtimeCaching: [
    {
      urlPattern: /^https:\/\/www\.googleapis\.com\/books\/v1\/.*/i,
      handler: 'NetworkFirst',
      options: {
        cacheName: 'google-books-api',
        expiration: {
          maxEntries: 50,
          maxAgeSeconds: 24 * 60 * 60
        },
        networkTimeoutSeconds: 10
      }
    },
    {
      urlPattern: /\.(?:png|jpg|jpeg|svg|gif|webp)$/i,
      handler: 'CacheFirst',
      options: {
        cacheName: 'image-cache',
        expiration: {
          maxEntries: 60,
          maxAgeSeconds: 30 * 24 * 60 * 60
        }
      }
    }
  ]
};