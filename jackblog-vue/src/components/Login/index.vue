<template>
<div class="main-box">
  <div class="signin-box">
    <div class="signin-container">
      <h4 class="title">登 录</h4>
      <div id="signinForm">
        <form class="signin-form form-horizontal">
          <div class="form-group">
            <div class="input-group">
              <div class="input-group-addon">
                <i class="fa fa-envelope-o"></i>
              </div>
              <input type="text" name="username" data-vv-as="用户名" v-model="siginForm.username" v-validate="'required|username'" data-vv-delay="100" class="form-control" placeholder="邮箱"></input>
            </div>
            <span class="tip-span">{{ errors.first('username') }}</span>
            <span class="tip-span">{{ errors.first('required') }}</span>
          </div>
          <div class="form-group">
            <div class="input-group">
              <div class="input-group-addon"><i class="fa fa-unlock-alt"></i></div>
              <input type="password" name="password" data-vv-as="密码" v-model="siginForm.password" v-validate="'required|password'" class="form-control" placeholder="密码"></input>
            </div>
            <span class="tip-span">{{ errors.first('password') }}</span>
          </div>
          <div class="form-group">
            <div class="col-xs-6 captcha-code">
              <input type="text" name="captcha" data-vv-as="验证码" v-model="siginForm.captcha" v-validate="'required|captcha|min:4'" maxlength="6" class="form-control" placeholder="验证码"></input>
            </div>
            <div class="col-xs-6 captcha-img">
              <a href="javascript:;" @click.prevent="getCaptchaUrl()">
                <img :src="captchaUrl"></img>
              </a>
            </div>
          <span class="tip-span">{{ errors.first('captcha') }}</span>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-lg btn-block" @click.prevent="login('siginForm')" id="signin_btn">登 录</button>
          </div>
        </form>
      </div>
      <div class="login-sns">
        <p>您还可以通过以下方式直接登录</p>
        <snsloginbtns :logins="logins"></snsloginbtns>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import snsloginbtns from './snsLogin'
import { mapState, mapActions } from 'vuex'
export default {
  components: {
    snsloginbtns
  },
  computed: {
    ...mapState({
      captchaUrl: ({ globalVal }) => globalVal.captchaUrl,
      logins: ({ logins }) => logins.items,
      token: ({ auth }) => auth.token
    })
  },
  data() {
    return {
      siginForm: {
        username: 'zankokutenshi@yeah.net',
        password: '123456',
        captcha: '3241'
      }
    }
  },
  created() {
    this.getCaptchaUrl()
    this.getSnsLogins()
  },
  methods: {
    ...mapActions([
      'getSnsLogins',
      'getCaptchaUrl',
      'localLogin',
      'showMsg'
    ]),
    login() {
      // this.$refs.siginForm.validate(valid => {
      //   if(valid){
      //     this.showMsg("SUCCESS",'info');
      //   }
      // })

    }
  }
}
//:disabled="!fields.valid()"
</script>