import axios from 'axios'
import store from '../store'
import {API_ROOT} from '../config'
import {
  REFRESH_ACCESS_TOKEN
} from '../store/types'
import { getCookie} from './authService'
// 创建axios实例
const service = axios.create({
  baseURL: API_ROOT, // api的base_url
  timeout: 5000,                  // 请求超时时间
  withCredentials: true,
  headers: {
    'Auth-jwt': '',
    'Auth-clientKey': '001'
  }
})
// request拦截器
service.interceptors.request.use(config => {
  if(store.getters.accessToken){
    config.headers['Auth-jwt']=store.getters.accessToken
  }
  if(getCookie('token')){
    config.headers['Auth-jwt']=getCookie('token')
  }
  return config
}, error => {
  return Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
    let config = response.config
    if(105 == response.data.code){
      if(!store.getters.isRefreshToken){
        store.commit(REFRESH_ACCESS_TOKEN,{isRefreshToken: true})
        store.dispatch('getAccessToken').then(
          config.headers['Auth-jwt']=store.getters.accessToken
        )
      }else{
        config.headers['Auth-jwt']=store.getters.accessToken
        store.commit(REFRESH_ACCESS_TOKEN,{isRefreshToken: false})
      }
      return service(config)
    }
    if(104 == response.data.code){
      config.headers['Auth-jwt']=store.getters.accessToken
      return service(config)
    }
    return response
  },
  error => {
    console.log('error' + error)// for debug
    return Promise.reject(error)
  }
)

// function setTokenByStore(config){
//   if(store.getters.accessToken){
//     config.headers['Auth-jwt']=store.getters.accessToken
//   }
//   return config
// }

// function getToken (store, config){
//   return new Promise(function(resolve,reject){
//     store.dispatch('getAccessToke').then( config => {
//       config.headers['Auth-jwt']=store.getters.accessToken
//       resolve(config)
//     })
//   })
// }
// async function  getToken(){
//   let data = await api.getAccessToken().then(response => {
//     const json=response.data
//     if(200 == json.code){
//       return {
//         accessToken: json.data.jwt,
//         expireTime: json.data.expireTime
//       }
//     }else{
//       console.log(response)
//     }
//   }).catch(error => {
//     console.log(error)
//   })
//   console.log(data)
//   return data     
// }
// async function setTokenByQuery(store, config){
//   store.commit(GET_ACCESS_TOKEN, await getToken())
//   setTokenByStore(config)
//   return config
// }
// async function refreshToken(store, config){
//   if(store.getters.accessToken){
//     config.headers['Auth-jwt']=store.getters.accessToken
//   }else{
//     if(!store.getters.isRefreshToken){
//       store.commit(REFRESH_ACCESS_TOKEN,{isRefreshToken: true})
//       let test = await store.dispatch('getAccessToken').then( data =>{
//         console.log(store.getters.accessToken)
//         return (data.data.data.jwt)
//       })
//       store.commit(REFRESH_ACCESS_TOKEN,{isRefreshToken: false})
//       config.headers['Auth-jwt']=test
//     }
//     let retry = new Promise((resolve,reject) => {
//       resolve(config) 
//     })
//     return retry
//   }

//   return config
// }
export default service
