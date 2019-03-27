<template>
  <div class="settings-box">
    <div class="setting-nav">
      <ul class="nav nav-pills nav-stacked">
        
        <li v-for="(setting, index) in settings" :key="index" @click="setActive(index)" :class="{active:index==navIndex}">
          <a>
            <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
            {{setting.title}}</a></li>
      </ul>
    </div>
    <div class="settings-container">
      <div class="settings-content" :class="{active:navIndex==0}">
        <form class="form-horizontal"  novalidate >
          <div class="form-group">
            <a class="col-sm-4" v-bind:title="ori.nickname">
              <canvas id="canvascontainer" ref='cutCanvas' class="setting-avatar">
                您的浏览器不支持canvas，请升级最新版本
              </canvas>
              <!-- <img :src="newAvatar?newAvatar:defaultAvatar"/> -->
            </a>
            <a class="col-sm-2 btn setting-btn">
              <input class="file-input" type="file" name="newAvatar" @change="changeAvatar($event)" accept="image/gif,image/jpeg,image/jpg,image/png"/>
              <span class="file-input-span">修改头像</span>
            </a>
          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label">昵称</label>
            <div class="col-sm-8">
              <input type="text" name="nickname" v-model="user.nickname"  class="form-control" placeholder="2-15字符，中英文、数字和下划线" />
            </div>
          </div>
          <hr />
          <div class="form-group" style="margin-top:15px;">
            <label class="col-sm-4 control-label">邮箱</label>
            <div class="col-sm-8">
              <input type="text" name="email" v-model="user.email" v-validate="" class="form-control" placeholder="" />
            </div>
          </div>
          <div class="form-group">
          </div>
          <div class="form-group">
          </div>
          <div class="form-group">
            <div class="col-sm-4">
              <Button type="primary" @click="mdUser" :size="'large'" :loading="loading" style="float:left"> 保&nbsp;&nbsp;&nbsp;存</Button>
            </div>
          </div>
        </form>
      </div>
      <div class="settings-content" :class="{active:navIndex==1}"> 
        <ul><li>待开发</li></ul>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import { Validator } from 'vee-validate'
import { Button } from 'iview'
import defaultAvatar from '../../assets/images/avatar.png'
export default {
  components :{Button},
  data() {
    return {
      navIndex: 0,
      settings:[
        {title: '基本设置'},
        // {title: '其他设置'}
        ],
      newAvatar:'',
      cutCanvas: null,
      cutCtx: null,
      originCanvas: null,
      originCtx: null,
      imageFile: null,
      loading: false,
    }
  },
  computed: {
    ...mapState({
      ori:  state => state.auth.user,
    }),
    user: {
      get(){
        return {
          id: this.ori.id,
          nickname: this.ori.nickname,
          avatar: this.ori.avatar,
          email: this.ori.email,
        }
      },
      set(){
        return {
          nickname: value,
          email: value
        }  
      }
    },
  },
  created(){
    this.getUserInfo()
  },
  methods: {
    ...mapActions([
      'updateUser',
      'getUserInfo',
      'uploadAvatar',
      'showMsg',
    ]),  
    mdUser() {
      this.$validator.validateAll().then(valid =>{
        if(valid){
          this.loading = true
          if(null != this.imageFile){
            let params = new FormData()
            params.append('picture', this.imageFile)
            this.uploadAvatar({picture: params, id: this.user.id}).then(()=>{
              this.user.avatar = this.ori.avatar
              this.updateUser(this.user).then(()=>{
                this.loading = false
              })
            })
          }else{
            this.updateUser(this.user).then(()=>{
              this.loading = false
            })
          }
        }else{
          this.showMsg("请正确填写内容",'info')
        }
      })
    },
    setActive(num){
      this.navIndex=num
    },
    changeAvatar(e){
      let file = e.target.files[0]
      this.imageFile=e.target.files[0]
      if(file){
        this.changeDataURL(file, (data)=>{
          let cutImg  = new Image()
          cutImg.src = data
          this.clearCanvas()
          cutImg.onload =()=>{
            let width = 0
            let height = 0
            let imageWidth = 0
            let imageHeight = 0
            if(cutImg.width>= cutImg.height){
              imageHeight = 120
              imageWidth=cutImg.width/cutImg.height*120 == 0 ? cutImg.width : cutImg.width/cutImg.height*120
              width = (imageWidth-imageHeight)/(-2)
            }else{
              imageWidth = 120
              imageHeight = cutImg.height/cutImg.width*120
              height = (imageHeight - imageWidth)/(-2)
            }
            this.cutCtx.drawImage(cutImg,width,height,imageWidth,imageHeight)
          }
          
        })
      }
    },
    clearCanvas(){
      this.cutCtx.clearRect(0, 0, 120, 120)
    },
		changeDataURL:function (fileObj, callback) {
		  let file = new FileReader()
		  file.readAsDataURL(fileObj)
		  file.onload = (e) => { callback(e.target.result) }		  
    },
  },
  mounted(){
    let cutCanvas = this.cutCanvas = this.$refs.cutCanvas
    this.cutCtx = cutCanvas.getContext('2d')
    cutCanvas.width = 120
    cutCanvas.height = 120
    if(cutCanvas.getContext){
      let cutImg = new Image()
      cutImg.src = this.user.avatar
      cutImg.onload =()=>{
        this.cutCtx.drawImage(cutImg,0,0,120,120)
      }      
    }
  }

}



</script>
<style>
.settings-box{
  width: 50%;
  min-width: 760px;
  padding: 20px 45px 10px 45px;
  margin: 0px auto;
  text-align: center;
  background-color: aliceblue;
}
.setting-nav{
  display: inline-block;
  width: 18%;
}
.settings-container{
  display: inline-block;
  min-width: 450px;
  width: 80%;
}
.setting-avatar {
  border-radius: 50%;
}
.form-horizontal .form-group {
    margin-right: 15px; 
    margin-left: 15px; 
}
.form-horizontal .control-label{
  text-align: left
}
.btn.setting-btn{
  min-width: 80px;
  border-radius: 20px;
  outline: none;
  margin-top: 35px;
  background-color: white;
  border-color: rgb(5, 193, 240);
  color: rgb(5, 193, 240);
  height: 40px;
  margin-left: 15px;
}
.btn.setting-btn:focus{
  outline: none;
}
.file-input{
  position: absolute;
  z-index: 1000;
  width: 100%;
  height: 100%;
  opacity: 0;
  left: 0;
  top: 0;

}
.file-input-span{
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 100;
  top: 0;
  left: 0;
  margin-top: 10px;
}
</style>
