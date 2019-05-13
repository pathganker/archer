<template>
  <div class="creation-content">
    <Split v-model="splitper"  ref="split" min="255" >
      <div slot="left" class="demo-split-pane no-padding">
        <Edition :editionList="editionList"  :edid="edid" :arid="arid" :isedit="isedit"></Edition>
      </div>
      <div slot="right" class="demo-split-pane">
        <Markdown ref="mark" :article="article"  :isedit="isedit" ></Markdown>
      </div>
     </Split>
     <Savemodal ref='savmodal' ></Savemodal>
     <Cropper ref='cropper' :croppershow="croppershow" :id="article.id"></Cropper>
  </div>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import Markdown from './markdown.vue'
import Edition from './edition.vue'
import store from '../../store'
import Savemodal from './savmodal.vue'
import Cropper from './cropper.vue'
import {Split} from 'iview'
import {
  CURRENT_EDITION,
  CURRENT_ARTICLE,
  CHANGE_EDIT_STATUS
} from '../../store/types'
import {getCookie,saveCookie,removeCookie} from '../../utils/cookies'
import {formatDate, uuid} from '../../utils/stringUtils'
export default{
  components:{ Edition, Markdown, Savemodal, Split, Cropper},
  computed:{
    ...mapState({
      article: ({backendArticle}) => backendArticle.article,
      editionList: ({editionList}) => editionList.items,
      user: ({auth}) => auth.user,
      splitmin: ({globalVal}) => globalVal.splitper,
      edid: ({editionList}) => editionList.edid,
      arid: ({editionList}) => editionList.arid,
      isedit: ({editionList}) => editionList.isedit
    }),
  },
  created(){
    this.getEditionList()
    const aid = this.$route.params.aid
    if(null == aid){
      if(typeof(getCookie('arid'))!=undefined){
        this.getBackendArticle(getCookie('arid')).then(()=>{
          if(this.article){
            store.commit(CURRENT_EDITION,{edid:this.article.edition})
            store.commit(CURRENT_ARTICLE,{arid:this.article.id})
          }
        })
      }
    }else{
       this.getBackendArticle(aid).then(()=>{
          if(this.article){
            store.commit(CURRENT_EDITION,{edid:this.article.edition})
            store.commit(CURRENT_ARTICLE,{arid:this.article.id})
          }
       })
    }
    this.$parent.hideNavbar()
  },
  methods:{
    ...mapActions([
      'getEditionList',
      'getBackendArticle',
      'addBackendArticle',
      'addEdition',
      'updateEdition',
      'updateBackendArticle',
      'deleteBackendArticle',
    ]),
    handleGetArticle(id){
      this.getBackendArticle(id)
    },
    handleAddEdition(data){
    return  this.addEdition(
        {
          id: data.id,
          title: data.title,
          articles: [],
          userId: this.user.id,
          createTime: new Date(),
          modifyTime: new Date(),
        }
      )
    },
    handleCreateBlog(data){
      let blog ={
        id: data.id,
        title: formatDate(new Date()),
        backendContent: null,
        frontContent: null,
        edition: data.edition,
        userId: this.user.id,
        createTime: new Date(),
      }
      this.addBackendArticle(blog)
    },
    handleUpdateBlog(blog){
      this.updateBackendArticle(blog).then(this.editCancel())
    },
    handleUpdateEdition(id,content){
      this.updateEdition({
        id: id,
        title: content,
        userId: this.user.id,
        modifyTime: new Date()
      })
    },
    hadleDeleteBlog(id){
      this.deleteBackendArticle(id)
    },
    openSaveModal(){
      this.$refs.savmodal.showModal()
    },
    editActive(){
      store.commit(CHANGE_EDIT_STATUS,true)
    },
    editCancel(){
      store.commit(CHANGE_EDIT_STATUS,false)
    },
    showCropper(){
      this.croppershow=true
    },
    hideCropper(){
      this.croppershow=false
    },
    changeImage(file){
      this.$refs.cropper.changeImage(file)
    }
  },
  watch:{
    splitmin(val){
      this.splitper = val
    },
    splitper(val){
      const contain= document.getElementById('markdownContent')
      contain.style.width=document.documentElement.clientWidth *(1-val)+'px'
    }
  },
  data(){
    return {
      newblog:'',
      splitper: document.documentElement.clientWidth * 0.2>255?0.2:255/document.documentElement.clientWidth,
      croppershow: false,
    }
  },
  mounted(){
  }
}

</script>
<style>

</style>
