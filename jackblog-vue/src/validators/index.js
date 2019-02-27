import Vue from 'vue'
import VeeValidate from 'vee-validate'
import { Validator } from 'vee-validate'
import api from '../api'

Validator.remove('required')
Validator.extend('required',{
  getMessage: field => '请输入'+ field,
  validate: value => !!(value)
})
Validator.extend('nickname', {
  getMessage: field => '请输入正确的呢称(2-15字符，中英文、数字和下划线)',
  validate: value => /^[(\u4e00-\u9fa5)0-9a-zA-Z\_\s@]+$/.test(value)
})
Validator.extend('username', {
  getMessage: field => '请输入正确的邮箱',
  validate: value => /^[a-z0-9](?:[-_.+]?[a-z0-9]+)*@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(value)
})
Validator.extend('captcha', {
  getMessage: field => '验证码错误',
  validate: value => api.checkCaptch(value).then(response => {
    const json=response.data
    if(200 == json.code){
      return true
    }else{
      return false
    }
  }).catch(error => {
    console.log(error)
  })
})


const config = {
  errorBagName: 'errors', // change if property conflicts.
  fieldsBagName: 'fields', 
  delay: 0, 
  locale: 'zh_CN', 
  dictionary: null, 
  strict: true, 
  enableAutoClasses: true,
  classNames: {
    touched: 'touched', // the control has been blurred
    untouched: 'untouched', // the control hasn't been blurred
    valid: 'ng-valid', // model is valid
    invalid: 'ng-invalid', // model is invalid
    pristine: 'pristine', // control has not been interacted with
    dirty: 'ng-dirty' // control has been interacted with
  },
  events: 'input|blur',
  inject: true  
}
Vue.use(VeeValidate,config)