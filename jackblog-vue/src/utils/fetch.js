import axios from 'axios'
import store from '../store'
import {API_ROOT} from '../config'
import {showMsg} from '../store/actions'
import {
  REFRESH_ACCESS_TOKEN
} from '../store/types'
// 创建axios实例
const service = axios.create({
  baseURL: API_ROOT, // api的base_url
  timeout: 10000,                  // 请求超时时间
  withCredentials: true,
  headers: {
    'Auth-jwt': 'store.getters.accessToken',
    'Auth-clientKey': '001'
  }
})
// request拦截器
service.interceptors.request.use(config => {
  if(store.getters.accessToken){
    config.headers['Auth-jwt']=store.getters.accessToken
  }
  return config
}, error => {
  return Promise.reject(error)
})

// let refreshSubscribers = []
// function onRrefreshed (token) {
//   refreshSubscribers.map(config => {
//     config.headers['Auth-jwt']=token
//     return config
//   })
// }
// respone拦截器
service.interceptors.response.use(
  response => {
    let config = response.config
    if(105 == response.data.code){
      if(!store.getters.isRefreshToken){
        store.commit(REFRESH_ACCESS_TOKEN,{isRefreshToken: true})
        store.dispatch('getAccessToken').then(()=>{
          config.headers['Auth-jwt']=store.getters.accessToken
          store.commit(REFRESH_ACCESS_TOKEN,{isRefreshToken: false})
        })
        return service(config)
      }else{
        config.headers['Auth-jwt']=store.getters.accessToken
        return service(config)
      } 
    }
    if(104 == response.data.code){
      if(store.getters.accessToken){
        config.headers['Auth-jwt']=store.getters.accessToken
      }else{
        config.headers['Auth-jwt']='store.getters.accessToken'
      }
      return service(config)
    }
    return response
  },
  error => {
    console.log(error)
    showMsg(store, '连接服务器失败', 'error')
    return Promise.reject(error)
  }
)
export default service
