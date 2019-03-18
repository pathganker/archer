<template>
<div v-show ="croppershow">
    <div class="cropper-mask"></div>
    <div class="cropper-container">
        <div class="cropper-image">
            <vueCropper
                ref="cropper"
                :img="option.img"
                :outputSize="option.size"
                :outputType="option.outputType"
                :auto-crop="option.autoCrop" :auto-crop-width="option.autoCropWidth" :auto-crop-height="option.autoCropHeight"
                :center-box="option.centerBox"
                :info="true" :full="option.full"
            ></vueCropper>
        </div>
        <div class="cropper-func">
            <div class="funcgroup">
                <a class="ivu-btn ivu-btn-primary ivu-btn-circle" @click="selectImage">
                    <i class="ivu-icon ivu-icon-md-add"></i>
                    <span>重选图片</span>
                    <input type="file"  id="cropper-input" class="cropper-input"  @change="cropperImage" accept="image/gif,image/jpeg,image/jpg,image/png"/>
                </a>
                <Button icon="md-sync"  type="primary" shape="circle" @click="refresh">&nbsp;&nbsp;重置&nbsp;&nbsp;</Button>
                <Button icon="md-arrow-back" type="primary" shape="circle" @click="rotateLeft"> &nbsp;&nbsp;左旋&nbsp;&nbsp;</Button>
                <Button icon="md-refresh" type="primary" shape="circle" @click="rotateRight">&nbsp;&nbsp;右旋&nbsp;&nbsp;</Button>
            </div>
            <div class="funcgroup">
                <Button icon="md-cloud-download" type="primary" shape="circle" @click="download">&nbsp;另存为&nbsp;&nbsp;</Button>
                <Button icon="md-camera" type="primary" shape="circle" @click="preview('blob')"> &nbsp;&nbsp;预览&nbsp;&nbsp;</Button>
                <Button icon="md-checkmark-circle" type="primary" shape="circle" @click="confirm">&nbsp;&nbsp;确定&nbsp;&nbsp;</Button>
                <Button icon="md-close" type="primary" shape="circle" @click="cancel">&nbsp;&nbsp;取消&nbsp;&nbsp;</Button>
            </div>
        </div>
    </div>
</div>
</template>
<script>
import { VueCropper  }  from 'vue-cropper'
import { ButtonGroup,Button } from 'iview'
export default {
    components:{ VueCropper  },
    props:['croppershow','imageindex'],
    // created:{
    //     inintData(){
    //         this.option.img=this.imageindex
    //     }
    // },
    methods:{
        refresh() {
          // clear
          this.$refs.cropper.refresh()
        },
        rotateLeft() {
          this.$refs.cropper.rotateLeft()
        },
        rotateRight() {
          this.$refs.cropper.rotateRight()
        },
        preview(type) {
          // 输出
          // var test = window.open('about:blank')
          // test.document.body.innerHTML = '图片生成中..'
          if (type === 'blob') {
            this.$refs.cropper.getCropBlob((data) => {
              console.log(data);
              var img = window.URL.createObjectURL(data)
              this.model = true
              this.modelSrc = img
            })
          } else {
            this.$refs.cropper.getCropData((data) => {
              this.model = true
              this.modelSrc = data
            })
          }
        },
        // 实时预览函数
        realTime(data) {
          this.previews = data
          console.log(data)
        },
        download() {
             // event.preventDefault()
            var aLink = document.createElement('a')
            aLink.download = 'demo'
             // 输出
            this.$refs.cropper.getCropBlob((data) => {
                this.downImg = window.URL.createObjectURL(data)
                aLink.href = window.URL.createObjectURL(data)
                aLink.click()
            })
        },
        selectImage(){
            document.getElementById("cropper-input").click()
        },
        cropperImage(e){
            this.option.img=e.target.files[0]
        },
        uploadImage(e, num) {
          //上传图片
          // this.option.img
          var file = e.target.files[0]
          if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG)$/.test(e.target.value)) {
            alert('图片类型必须是.gif,jpeg,jpg,png,bmp中的一种')
            return false
          }
          var reader = new FileReader()
          reader.onload = (e) => {
            let data
            if (typeof e.target.result === 'object') {
              // 把Array Buffer转化为blob 如果是base64不需要
              data = window.URL.createObjectURL(new Blob([e.target.result]))
            } else {
              data = e.target.result
            }
            if (num === 1) {
              this.option.img = data
            } else if (num === 2) {
              this.example2.img = data
            }
          }
          // 转化为base64
          // reader.readAsDataURL(file)
          // 转化为blob
          reader.readAsArrayBuffer(file)
        },
        imgLoad(msg) {
          console.log(msg)
        },
        confirm(){

        },
        cancel(){
            this.$parent.hideCropper()
        }
    },
    data(){
        return{
            option: {
                img: '',
                size: 1,
                full: true,
                outputType: 'png',
                canMove: true,
                fixedBox: false,
                original: false,
                canMoveBox: true,
                autoCrop: true,
                // 只有自动截图开启 宽度高度才生效
                autoCropWidth: 200,
                autoCropHeight: 150,
                centerBox: false,
                high: true
                }
        }
    }
}
</script>
<style>
.cropper-mask{
    position:absolute;
    top:0;
    bottom:0;
    right:0;
    left:0;
    background:rgba(0,0,0,0.5);
    z-index:3999;
}
.cropper-container{
    position: absolute;
    width: 80%;
    height: 70%;
    margin: auto;
    z-index: 4000;
    left: 0; 
    right: 0; 
    top: 0;
    bottom: 0;
    margin: auto;
    background:white;
    border-radius: 5px;
    border-style:solid;
    border-width:2px;
}
.cropper-image{
    float: left;
    width: 60%;
    height: 100%;
    padding: 5% 40px 5% 20px;
}
.cropper-func{
    float: left;
    width: 40%;
    height: 100%;
    padding: 10% 40px;
}
.funcgroup{
    height: 100px;
    margin: 20px 0;
}
.cropper-input{
    position:absolute;
    width: 60px;
    display: none!important;
}
</style>
