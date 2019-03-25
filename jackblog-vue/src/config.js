export const API_ROOT = (process.env.NODE_ENV === 'production')
  ? 'http://106.12.198.62:7000/'
  :'http://127.0.0.1:7000/'

export const CookieDomain = (process.env.NODE_ENV === 'production')
  ? ''
  : ''