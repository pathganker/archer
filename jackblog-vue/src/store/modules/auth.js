import api from '../../api'
import {showMsg,getCaptchaUrl} from '../actions'
import {
  LOGIN_SUCCESS,
  USERINFO_SUCCESS,
  USERINFO_FAILURE,
  LOGOUT_USER,
  UPDATE_USER_SUCCESS,
  GET_ACCESS_TOKEN,
  REFRESH_ACCESS_TOKEN
} from '../types'
import { getCookie,saveCookie,signOut } from '../../utils/authService'
import router from '../../router'

const state = {
  token: getCookie('token') || null,
  user: null,
  isRefreshToken: false,
  accessToken: '',
  expireTime: ''
}

const actions = {
  logout({commit}){
    signOut()
    commit(LOGOUT_USER)
    window.location.pathname = '/'
  },
  localLogin(store, userInfo){
    api.localLogin(userInfo).then(response => {
      const json=response.data
      if(200!=json.code){
        getCaptchaUrl(store)
        return showMsg(store,json.message || '登录失败')
      }
      const token = json.token
      saveCookie('token',token)
      store.dispatch('getUserInfo')
      store.commit(LOGIN_SUCCESS, {token: token })
      showMsg(store,'登录成功,欢迎光临!','success')
      router.push({path:'/'})
    }).catch(error => {
      console.log(error) 
    })
  },
  getUserInfo({ commit }){
    api.getMe().then(response => {
      if(!response.ok){
        return commit(USERINFO_FAILURE)
      }
      commit(USERINFO_SUCCESS, { user: response.data })
    }, response => {
      commit(USERINFO_FAILURE)
    })
  },
  updateUser(store,userInfo){
    api.mdUser(userInfo).then(response => {
      if(!response.ok){
        return showMsg(store,'更新用户资料失败!')
      }
      store.commit(UPDATE_USER_SUCCESS, { user: response.data.data })
      showMsg(store,'更新资料成功!','success')
    }, response => {
      showMsg(store,'更新用户资料失败!')
    })
  },
  getAccessToken: async function({commit}){
    let data = await api.getAccessToken().then(response => {
      const json=response.data
      if(200 == json.code){
        commit(GET_ACCESS_TOKEN, {
          accessToken: json.data.jwt,
          expireTime: json.data.expireTime
        })
        return response
      }else{
        console.log(response)
      }
    }).catch(error => {
      console.log(error)
    })
    return data
  }
}



const mutations = {
  [LOGIN_SUCCESS](state , action){
    state.token = action.token
  },
  [USERINFO_SUCCESS](state,action){
    state.user = action.user
  },
  [USERINFO_FAILURE](state,action){
    state.user = null
  },
  [LOGOUT_USER](state,action){
    state.token = getCookie('token') || null
    state.user = null
    state.token = null
  },
  [UPDATE_USER_SUCCESS](state,action){
    state.user = action.user
  },
  [GET_ACCESS_TOKEN](state, data){
    state.accessToken = data.accessToken
    state.expireTime = data.expireTime
  },
  [REFRESH_ACCESS_TOKEN](state, data){
    state.isRefreshToken = data.isRefreshToken
  }
}

export default {
  state,
  actions,
  mutations
}