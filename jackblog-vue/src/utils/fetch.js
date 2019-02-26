import axios from 'axios'
import store from '../store'
import api from '../api'
import {API_ROOT} from '../config'
import {
  ACCESS_TOKEN
} from '../store/types'
// 创建axios实例
const service = axios.create({
  baseURL: API_ROOT, // api的base_url
  timeout: 5000,                  // 请求超时时间
  withCredentials: true,
  params: {
    'jwt': '',
    'clientKey': '001'
  }
})

// request拦截器
service.interceptors.request.use(config => {
  if(store.state.globalVal.accessToken){
    config.params['jwt']=store.state.globalVal.accessToken
  }
  return config
}, error => {
  
})

// respone拦截器
service.interceptors.response.use(
  console.log(response)
  response => {
    
    if(response.status == 401){
      api.getAccessToken().then(response => {
        const json=response.data
        console.log(json)
        if(200 == json.code){
          store.commit(ACCESS_TOKEN, {accessToken: json.data})
        }else{
          console.log(response)
        }
      }).catch(error => {
        console.log(error)
      })
    }
    return response
  },
  error => {
    console.log('err' + error)// for debug
    return Promise.reject(error)
  }
)
export default service
