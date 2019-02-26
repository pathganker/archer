import {API_ROOT} from '../../config'
import {
  CHANGE_STYLE_MODE,
  GET_INDEX_IMG,
  GET_CAPTCHAURL,
  ACCESS_TOKEN
} from '../types'
import { getCookie,saveCookie } from '../../utils/authService'
import api from '../../api'
const state = {
  indexImg: '',
  styleMode: getCookie('styleMode') || 'day-mode',
  captchaUrl: API_ROOT + 'users/getCaptcha?',
  accessToken: ''
}
const actions = {
  getAccessToken ({ commit }){
    api.getAccessToken().then(response => {
      const json=response.data
      if(200 == json.code){
        commit(ACCESS_TOKEN, {accessToken: json.data})
      }else{
        console.log(response)
      }
    }).catch(error => {
      console.log(error)
    })
  }
}
const mutations = {
  [CHANGE_STYLE_MODE](state){
    state.styleMode = (state.styleMode === 'day-mode')?'night-mode':'day-mode'
    saveCookie('styleMode', state.styleMode)
  },
  [GET_INDEX_IMG](state, action){
    state.indexImg = action.indexImg
  },
  [GET_CAPTCHAURL](state){
    state.captchaUrl = API_ROOT+'auth/captcha?jwt='+state.accessToken+'&'+Math.random()
  },
  [ACCESS_TOKEN](state, action){
    state.accessToken = action.accessToken
  }
}

export default {
  state,
  mutations,
  actions
}