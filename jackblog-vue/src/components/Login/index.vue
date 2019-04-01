<template>
<div class="main-box">
  <div class="signin-box">
    <div class="signin-container">
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
            <button class="btn btn-primary btn-lg btn-block" @click.prevent="login(user)" id="signin_btn">登 录</button>
          </div>
        </form>
      </div>
      <div class="login-sns">
        <p>您还可以通过以下方式直接登录</p>
        <p>≧▽≦仅开放评论功能≧▽≦</p>
        <snsloginbtns :logins="logins"></snsloginbtns>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import snsloginbtns from './snsLogin'
import { mapState, mapActions } from 'vuex'
import {Weibo,Github} from '../../config.js'
export default {
  components: {
    snsloginbtns
  },
  computed: {
    ...mapState({
      captchaUrl: ({ globalVal }) => globalVal.captchaUrl,
    })
  },
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      logins:[Weibo,Github]
    }
  },
  created() {
    this.getCaptchaUrl()
  //  this.getSnsLogins()
  },
  methods: {
    ...mapActions([
      'getCaptchaUrl',
      'pageLogin',
      'showMsg'
    ]),
    login() {
      this.$validator.validateAll().then(valid => {
        if(valid){
          this.pageLogin(this.user)
        }
        else{
          this.getCaptchaUrl()
          this.showMsg("请填写用户名/密码/验证码",'error');
        }
      })
    }
  }
}
//:disabled="!fields.valid()"
</script>
<style>
.signin-box{
  padding: 0;
}
.day-mode a.weibo, .day-mode a.github{
  background: 0 0;
}
</style>
