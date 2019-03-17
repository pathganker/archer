import Vue from 'vue'
import { sync } from 'vuex-router-sync'
import store from './store'
import router from './router'
import './validators'
import './filters'
import App from 'components/App'
import 'jackblog-sass/dist/index.min.css'
import 'vue-toast/dist/vue-toast.min.css'
import './assets/styles/index.css'
sync(store, router)

new Vue({
  el: '#root',
  router,
  store,
  render: h => h(App)
})