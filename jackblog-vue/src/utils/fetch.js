import axios from 'axios'
import store from '../store'
import {API_ROOT} from '../config'
// 创建axios实例
const service = axios.create({
  baseURL: API_ROOT, // api的base_url
  timeout: 5000,                  // 请求超时时间
  withCredentials: true
  // headers:{
  //   'Auth-Device': '001',
  //   'Auth-Token': '14211777000084152948490109770045'
  // },
})

// request拦截器
service.interceptors.request.use(config => {
  console.log(config)
  if(store.getters.token){
    // config.headers['Auth-Token']=store.getters.token
  }
  return config
}, error => {

  console.log(error) // for debug
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
    return response
  },
  error => {
    console.log('err' + error)// for debug
    return Promise.reject(error)
  }
)
export default service
