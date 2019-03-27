export const API_ROOT = (process.env.NODE_ENV === 'production')
  ? 'http://106.12.198.62:7000/'
  :'http://127.0.0.1:7000/'

export const CookieDomain = (process.env.NODE_ENV === 'production')
  ? ''
  : ''

export const Weibo = (process.env.NODE_ENV === 'production')
  ? {
    name: 'weibo',
    authorizationUri: 'https://api.weibo.com/oauth2/authorize',
    clientId: '18952497',
    redirectUri: 'http://106.12.198.62/oauth2/callback/weibo'
  }
  : {
    name: 'weibo',
    authorizationUri: 'https://api.weibo.com/oauth2/authorize',
    clientId: '3748264479',
    redirectUri: 'http://127.0.0.1:3000/oauth2/callback/weibo'
  }

export const Github = (process.env.NODE_ENV === 'production')
  ? {
    name: 'github',
    authorizationUri: 'https://github.com/login/oauth/authorize',
    clientId: '19b4af3bf0e0c9ce92e4',
    redirectUri: 'http://106.12.198.62/oauth2/callback/github'
  }
  : {
    name: 'github',
    authorizationUri: 'https://github.com/login/oauth/authorize',
    clientId: '17a95ea931b526b43f7b',
    redirectUri: 'http://127.0.0.1:3000/oauth2/callback/github'
  }