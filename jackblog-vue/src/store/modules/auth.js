import api from '../../api'
import {showMsg,getCaptchaUrl} from '../actions'
import {
  LOGIN_SUCCESS,
  USERINFO_SUCCESS,
  USERINFO_FAILURE,
  LOGOUT_USER,
  UPDATE_USER_SUCCESS,
  GET_ACCESS_TOKEN,
  REFRESH_ACCESS_TOKEN,
} from '../types'
import { getCookie,saveCookie,signOut } from '../../utils/cookies'
import router from '../../router'

const state = {
  token: getCookie('token') || null,
  user: null,
  isRefreshToken: false,
  accessToken: '',
  isSigin: false,
  isAdmin: false,
}

const actions = {
  logout({commit}){
    signOut()
    commit(LOGOUT_USER)
    window.location.pathname = '/'
  },
  pageLogin(store, userInfo){
    api.localLogin(userInfo).then(response => {
      const json=response.data
      if(200!=json.code){
        getCaptchaUrl(store)
        return showMsg(store,json.message || '登录失败')
      }
      const token = json.data.jwt
      saveCookie('token',token)
      store.dispatch('getUserInfo')
      store.commit(LOGIN_SUCCESS, {token: token })
      showMsg(store,'登录成功,欢迎光临!','success')
      router.push({path:'/'})
    })
  },
  localLogin(store, userInfo){
    api.localLogin(userInfo).then(response => {
      const json=response.data
      if(200!=json.code){
        getCaptchaUrl(store)
        return showMsg(store,json.message || '登录失败')
      }
      const token = json.data.jwt
      saveCookie('token',token)
      store.dispatch('getUserInfo')
      store.commit(LOGIN_SUCCESS, {token: token })
      showMsg(store,'登录成功,欢迎光临!','success')
    })
  },
  getUserInfo({ commit }){
    return api.getMe().then(response => {
      const json = response.data
      if(200!=json.code){
        return commit(USERINFO_FAILURE)
      }
      commit(USERINFO_SUCCESS, json.data )
    }, 
    error => {
      commit(USERINFO_FAILURE)
    })
  },
  updateUser(store,user){
    api.mdUser(user).then(response => {
      const json =response.data
      if(200!=response.data){
        return showMsg(store,json.message || '更新用户资料失败!')
      }
      store.commit(UPDATE_USER_SUCCESS, { user: json.data })
      showMsg(store,'更新资料成功!','success')
    }, 
    error => {
      showMsg(store,'更新用户资料失败!')
    })
  },
  getAccessToken({commit}){
    api.getAccessToken().then(response => {
      const json=response.data
      if(200 == json.code){
        commit(GET_ACCESS_TOKEN, {
          accessToken: json.data.jwt,
        })
        return response
      }else{
        console.log(response)
      }
    }).catch(error => {
      console.log(error)
    })
  },
  getOauthToken({commit},data){
    return api.getOauthToken(data).then(response =>{
      const json = response.data
      if(200== json.code){
        commit(GET_ACCESS_TOKEN, {
          accessToken: json.data,
        })
        saveCookie('token',json.data)
      }
    },
    error =>{
      console.log(error)
    })
  }
}




const mutations = {
  [LOGIN_SUCCESS](state , action){
    state.token = action.token
    state.isSigin = true
  },
  [USERINFO_SUCCESS](state,data){
    state.user = data.user
    state.isSigin = true
    state.isAdmin = data.admin
  },
  [USERINFO_FAILURE](state,action){
    state.user = null
    state.isSigin = false
  },
  [LOGOUT_USER](state,action){
    state.token = getCookie('token') || null
    state.user = null
    state.token = null
    state.isSigin = false
  },
  [UPDATE_USER_SUCCESS](state,action){
    state.user = action.user
  },
  [GET_ACCESS_TOKEN](state, data){
    state.token = data.accessToken
    state.accessToken = data.accessToken
  },
  [REFRESH_ACCESS_TOKEN](state, data){
    state.isRefreshToken = data.isRefreshToken
  },
}

export default {
  state,
  actions,
  mutations
}