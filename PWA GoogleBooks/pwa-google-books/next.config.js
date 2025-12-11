/** @type {import('next').NextConfig} */
const nextConfig = {
  output: 'export',
  basePath: '/PAD',
  assetPrefix: '/PAD',
  images: {
    unoptimized: true
  }
};

module.exports = nextConfig;