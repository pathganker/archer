<template>
  <div class="creation-content">
    <Split v-model="splitper"  ref="split" min="255" >
      <div slot="left" class="demo-split-pane no-padding">
        <Edition :editionList="editionList"  :edid="edid" :arid="arid"></Edition>
      </div>
      <div slot="right" class="demo-split-pane">
        <Markdown ref="mark" :article="article" :draft="draft"></Markdown>
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
} from '../../store/types'
import {getCookie,saveCookie,removeCookie} from '../../utils/cookies'
import {formatDate, uuid} from '../../utils/stringUtils'
export default{
  components:{ Edition, Markdown, Savemodal, Split, Cropper},
  computed:{
    ...mapState({
      draft: ({editionList}) => editionList.draft,
      article: ({backendArticle}) => backendArticle.article,
      editionList: ({editionList}) => editionList.items,
      user: ({auth}) => auth.user,
      splitmin: ({globalVal}) => globalVal.splitper,
      edid: ({editionList}) => editionList.edid,
      arid: ({editionList}) => editionList.arid,

    }),
  },
  created(){
    this.getEditionList()
    const aid = this.$route.params.aid
    if(null == aid){
      if(getCookie('arid')){
        console.log(getCookie('arid'))
        this.getBackendArticle(getCookie('arid'))
      }
    }else{
      store.commit(CURRENT_ARTICLE,{arid: aid})
       this.getBackendArticle(aid)
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
      'deleteBackendArticle'
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
      this.isedit = true
      saveCookie('isedit', true)
    },
    editCancel(){
      this.isedit = false
      removeCookie('isedit')
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
    }
  },
  data(){
    return {
      newblog:'',
      isedit: false,
      splitper: document.documentElement.clientWidth * 0.2>255?0.2:255/document.documentElement.clientWidth,
      croppershow: false,
    }
  }
}

</script>
<style>

</style>
