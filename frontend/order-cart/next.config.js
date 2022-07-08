/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: false,
  async redirects() {
    return [
      {
        source: '/',
        destination: '/product',
        permanent: true,
      },
    ]
  },
}

module.exports = nextConfig
