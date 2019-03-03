// import Vue from 'vue'
// import VueResource from 'vue-resource'
// import {API_ROOT} from '../config'
// import { getCookie,signOut,isLogin } from '../utils/authService'
// //import axios from 'axios'

// // // 创建axios实例
// // const service = axios.create({
// //   baseURL: API_ROOT, // api的base_url
// //   timeout: 5000,                  // 请求超时时间
// // })

// // // request拦截器
// // service.interceptors.request.use(config => {
// //   console.log(config)
// //   // if(store.getters.token){
// //   //   config.headers["Auth-Token"]=store.getters.token;
// //   // }
// //   return config
// // }, error => {

// //   console.log(error) // for debug
// //   Promise.reject(error)
// // })

// // // respone拦截器
// // service.interceptors.response.use(
// //   response => {
// //     console.log(response)
// //     return response
// //   },
// //   error => {
// //     console.log('err' + error)// for debug
// //     vue.$Message.error({
// //       message: error.message,
// //       duration: 5 * 1000,
// //       closable: true
// //     })
// //     return Promise.reject(error)
// //   }
// // )
// Vue.use(VueResource)

// // HTTP相关
// Vue.http.options.crossOrigin = true
// Vue.http.options.credentials = true

// Vue.http.interceptors.push((request, next)=>{
//   // 这里对请求体进行处理
//   request.headers = request.headers || {}
//   if (isLogin()) {
//     request.headers.set('Authorization', 'Bearer '+ getCookie('token').replace(/(^\")|(\"$)/g, ''))
//   }
//   next((response) => {
//     // 这里可以对响应的结果进行处理
//     if (response.status === 401) {
//       signOut()
//       window.location.pathname = '/login'
//     }
//   })
// })

// export const UserResource = Vue.resource(API_ROOT + 'users{/id}')
// export const AuthResource = Vue.resource(API_ROOT + 'auth{/id}')
// export const ArticleResource = Vue.resource(API_ROOT + 'article{/id}{/controller}')
// export const TagResource = Vue.resource(API_ROOT + 'tags{/id}')
// export const CommentResource = Vue.resource(API_ROOT + 'comment{/id}{/controller}')
// export const MobileResource = Vue.resource(API_ROOT + 'mobile{/id}')
// // export const UserResource = service({
// //   url: 'users{/id}',
// //   method: 'get'
// // })
// // export const AuthResource = service({
// //   url: 'auth{/id}',
// //   method:'get'
// // })
// // export const ArticleResource = service({
// //   url: 'article{/id}{/controller}',
// //   method:'get'
// // })
// // export const TagResource = service({
// //   url: 'tags{/id}',
// //   method:'get'
// // })
// // export const CommentResource = service({
// //   url: 'comment{/id}{/controller}',
// //   method:'get'
// // })
// // export const MobileResource = service({
// //   url: 'mobile{/id}',
// //   method:'get'
// // })