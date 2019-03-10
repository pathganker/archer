<template>
	<modal :show.sync="showLoginModal"  effect="fade" width="400">
	  <div slot="modal-header" class="modal-header">
	  	<h4 class="modal-title text-center">请用以下方式登录</h4>
	  </div>
	  <div slot="modal-body" class="modal-body">
      <div class="portlet-body">
          <div id="signinForm">
            <form class="signin-form form-horizontal">
              <div class="form-group">
                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-envelope-o"></i>
                  </div>
                  <input type="text" name="username" data-vv-as="用户名" v-model="user.username" v-validate="'required|username'" data-vv-delay="100" class="form-control" placeholder="邮箱" />
                </div>
                <span class="tip-span">{{ errors.first('username') }}</span>
                <span class="tip-span">{{ errors.first('required') }}</span>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <div class="input-group-addon"><i class="fa fa-unlock-alt"></i></div>
                  <input type="password" name="password" data-vv-as="密码" v-model="user.password" v-validate="'required'" class="form-control current-password" placeholder="密码" autocomplete />
                </div>
                <span class="tip-span">{{ errors.first('password') }}</span>
              </div>
              <div class="form-group">
                <div class="col-xs-6 captcha-code">
                  <input type="text" name="captcha" data-vv-as="验证码"  v-validate="'required|captcha'" maxlength="6" class="form-control" placeholder="验证码" />
                </div>
                <div class="col-xs-6 captcha-img">
                  <a href="javascript:;" @click.prevent="getCaptchaUrl()">
                    <img :src="captchaUrl"/>
                  </a>
                </div>
              <span class="tip-span-captcha">{{ errors.first('captcha') }}</span>
              </div>
              <div class="form-group">
                <button class="btn btn-primary btn-lg btn-block" @click.stop.prevent="login(user)" id="signin_btn">登 录</button>
              </div>
            </form>
          </div>
      </div>
	  </div>
	  <div slot="modal-footer">
	  </div>
	</modal>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import { modal } from 'vue-strap'

export default {
  components:{
    modal
  },
  computed: {
    ...mapState({
      captchaUrl: ({ globalVal }) => globalVal.captchaUrl,     
    })
  },
  data(){
    return {
      showLoginModal:false,
      user: {
        username: '',
        password: ''
      }
    }
  },
  created () {
    this.getCaptchaUrl()
  },
  methods: {
    ...mapActions([
      'getCaptchaUrl',
      'localLogin',
      'showMsg'
    ]),    
    showModal(){
      this.showLoginModal = true
    },
    login() {
      this.$validator.validateAll().then(valid => {
        if(valid){
          this.localLogin(this.user).then(
             this.showLoginModal = false
          )
        }
        else{
          this.getCaptchaUrl()
          this.showMsg("请填写用户名/密码/验证码",'error');
        }
      })
    }
  }
}
</script>