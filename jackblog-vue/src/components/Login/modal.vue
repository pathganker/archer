<template>
	<Modal v-model="showLoginModal"  
    width="400"
    title="请用以下方式登录"
  >
      <div class="portlet-body">
        <p style="text-align:center;font-size:14px">≧▽≦仅开放评论功能≧▽≦</p>
        <SnsLogin :logins="logins"></SnsLogin>
      </div>
      <div slot="footer">
      </div>
	</Modal>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import { Modal } from 'iview'
import SnsLogin from './snsLogin.vue'
import {Weibo,Github} from '../../config.js'
export default {
  components:{
    Modal, SnsLogin
  },
  computed: {
    ...mapState({
      captchaUrl: ({ globalVal }) => globalVal.captchaUrl,     
    })
  },
  data(){
    return {
      showLoginModal:false,
      logins:[Weibo,Github]
    }
  },
  created () {
  },
  methods: {
    ...mapActions([
    ]),    
    showModal(){
      this.showLoginModal = true
    },
  }
}
</script>
<style>
.day-mode a.weibo {
  background-color: #ec782b
}
.day-mode a.github{
  background-color: rgba(15, 180, 9, 0.993)
}
</style>
