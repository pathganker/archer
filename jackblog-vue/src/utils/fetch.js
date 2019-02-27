import axios from 'axios'
import store from '../store'
// import api from '../api'
import {API_ROOT} from '../config'
import {
  REFRESH_ACCESS_TOKEN
} from '../store/types'
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
service.interceptors.request.use(async function (config){
  let temp = await refreshToken(store, config)
  // if(store.getters.accessToken){
  //   config.headers['Auth-jwt']=store.getters.accessToken
  // }else{
  //   if(!store.getters.isRefreshToken){
  //     store.commit(REFRESH_ACCESS_TOKEN,{isRefreshToken: true})
  //     const test = await store.dispatch('getAccessToken').then( data =>{
  //       retrun (data.data.data.jwt)
  //     })
  //     console.log(test)
  //   }
  // let retry = new Promise((resolve,reject) => {
  //   console.log(temp)
  //   resolve(temp) 
  // })
  // // setTokenByStore(config)
  // //setTokenByQuery(store, config)
  // return retry
  // console.log(store.getters)
  //console.log(config)
  // return retry
  return temp
}, error => {
  return Promise.reject(error)
})


// respone拦截器
service.interceptors.response.use(
  response => {
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
// function getToken(){
//   api.getAccessToken().then(response => {
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
// }
// async function setTokenByQuery(store, config){
//   store.commit(GET_ACCESS_TOKEN, await getToken())
//   setTokenByStore(config)
//   return config
// }
async function refreshToken(store, config){
  if(store.getters.accessToken){
    config.headers['Auth-jwt']=store.getters.accessToken
  }else{
    if(!store.getters.isRefreshToken){
      store.commit(REFRESH_ACCESS_TOKEN,{isRefreshToken: true})
      let test = await store.dispatch('getAccessToken').then( data =>{
        return (data.data.data.jwt)
      })
      store.commit(REFRESH_ACCESS_TOKEN,{isRefreshToken: false})
      config.headers['Auth-jwt']=test
    }
  }
  return config
}
export default service
