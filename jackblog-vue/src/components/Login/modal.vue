<template>
	<Modal v-model="showLoginModal"  
    width="400"
    title="请用以下方式登录"
  >
      <div class="portlet-body">
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
      logins:[{
        name: 'weibo',
        authorizationUri: 'https://api.weibo.com/oauth2/authorize',
        clientId: '18952497',
        redirectUri: 'http://127.0.0.1:3000/oauth2/callback/weibo'
      }]
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