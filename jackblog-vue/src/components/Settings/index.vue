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
        <form class="form-horizontal" @submit.prevent="mdUser()" novalidate >
          <div class="form-group">
            <a class="col-sm-4" v-bind:title="nickname">
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
              <input type="text" name="nickname" v-model="user.nickname" v-validate="'required|nickname|min:4|max:30'" class="form-control" placeholder="2-15字符，中英文、数字和下划线" />
            </div>
          </div>
          <hr />
          <div class="form-group">
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
              <button type="submit" class="btn btn-block btn-lg btn-primary">保 存</button>
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
import defaultAvatar from '../../assets/images/avatar.png'
export default {
  data() {
    return {
      user : {
        nickname: this.nickname,
        avator: this.avator == null ? defaultAvatar : this.avator,
        email: this.email
      },
      navIndex: 0,
      settings:[
        {title: '基本设置'},
        {title: '其他设置'}
        ],
      newAvatar:'',
      cutCanvas: null,
      cutCtx: null,
      originCanvas: null,
      originCtx: null,
    }
  },
  computed: {
    ...mapState({
      nickname: ({auth}) => auth.user && auth.user.username,
      avator: ({auth}) => auth.user && auth.user.avator,
      email: ({auth}) => auth.user && auth.user.email
    }),
  },
  created(){
  //  this.getUserInfo()
  },
  methods: {
    ...mapActions([
      'updateUser',
      'getUserInfo',
      'showMsg',
    ]),  
    mdUser() {
      this.$validator.validateAll().then(valid =>{
        if(valid){
          this.updateUser(this.user)
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
      if(file){
        this.changeDataURL(file, (data)=>{
          let cutImg  = new Image()
          cutImg.src = data
          this.cutCtx.clearRect(0,0,120,120)
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
            this.user.avator = this.cutCanvas.toDataURL('image/png')
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
      cutImg.src = this.user.avator
      cutImg.onload =()=>{
        this.cutCtx.drawImage(cutImg,0,0,120,120)
      }      
    }
  }

}



</script>
<style>
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
