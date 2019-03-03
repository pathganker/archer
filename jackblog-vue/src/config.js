export const API_ROOT = (process.env.NODE_ENV === 'production')
  ? 'http://localhost:7000/'
  :'http://localhost:7000/'

export const CookieDomain = (process.env.NODE_ENV === 'production')
  ? ''
  : ''