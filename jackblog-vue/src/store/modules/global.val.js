import {API_ROOT} from '../../config'
import {
  CHANGE_STYLE_MODE,
  GET_INDEX_IMG,
  GET_CAPTCHAURL
} from '../types'
import { getCookie,saveCookie } from '../../utils/authService'

const state = {
  indexImg: '',
  styleMode: getCookie('styleMode') || 'day-mode',
  captchaUrl: API_ROOT + 'auth/captcha/',
  captchaKey: Math.random()
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
    state.captchaKey = Math.random()
    state.captchaUrl = API_ROOT+'auth/captcha/'+state.captchaKey
  }
}

export default {
  state,
  mutations
}