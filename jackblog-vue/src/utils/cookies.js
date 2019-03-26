import Cookies from 'universal-cookie'
const cookies = new Cookies()
import { CookieDomain } from '../config.js'
let cookieConfig = {}
// const today = new Date()
cookieConfig = { 
  domain: CookieDomain,
  // expires: new Date(today.getMilliseconds+3600*1000*24),
  maxAge: 3600*24*7,
  path: '/'
} //path:'/',maxAge:365*24*60*60

export function saveCookie(name,value) {
  cookies.set(name, value, cookieConfig)
}

export function getCookie(name) {
  return cookies.get(name)
}

export function removeCookie(name) {
  cookies.remove(name, cookieConfig)
}

export function signOut() {
  cookies.remove('token', cookieConfig)
  cookies.remove('isedit', cookieConfig)
  cookies.remove('arid', cookieConfig)
}

export function isLogin() {
  return !!cookies.get('token')
}