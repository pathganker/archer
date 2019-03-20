import {API_ROOT} from '../../config'
import {
  CHANGE_STYLE_MODE,
  GET_INDEX_IMG,
  GET_CAPTCHAURL,
  CHANGE_SPLITER
} from '../types'
import { getCookie,saveCookie } from '../../utils/cookies'

const state = {
  indexImg: '',
  styleMode: getCookie('styleMode') || 'day-mode',
  captchaUrl: API_ROOT + 'auth/captcha/',
  captchaKey: Math.random(),
  splitper: 0.2,
  height: 1080,
  width: 1080,
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
  },
  [CHANGE_SPLITER](state,data){
    state.splitper = data.splitper
    state.height = data.height
    state.width = data.width
  }
}

export default {
  state,
  mutations
}