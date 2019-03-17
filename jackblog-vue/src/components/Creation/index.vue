<template>
  <div class="creation-content">
    <Split v-model="splitper"  ref="split" min="255" >
      <div slot="left" class="demo-split-pane no-padding">
        <Edition :editionList="editionList" :cured="cured" :curar="curar"></Edition>
      </div>
      <div slot="right" class="demo-split-pane">
        <Markdown ref="mark" :article="article" :draft="draft"></Markdown>
      </div>
     </Split>
     <Savemodal ref='savmodal' ></Savemodal>
  </div>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import Markdown from './markdown.vue'
import Edition from './edition.vue'
import store from '../../store'
import Savemodal from './savmodal.vue'
import {Split} from 'iview'
import {
  CURRENT_EDITION,
  CURRENT_ARTICLE,
} from '../../store/types'
import {getCookie,saveCookie,removeCookie} from '../../utils/cookies'
import {formatDate, uuid} from '../../utils/stringUtils'
export default{
  components:{ Edition, Markdown, Savemodal,Split},
  computed:{
    ...mapState({
      draft: ({editionList}) => editionList.draft,
      article: ({backendArticle}) => backendArticle.article,
      editionList: ({editionList}) => editionList.items,
      cured: ({editionList}) => editionList.cured == null ?  getCookie('cured') : editionList.cured,
      curar: ({editionList}) => editionList.curar == null ?  getCookie('curar') : editionList.curar,
      user: ({auth}) =>auth.user,
      splitmin: ({globalVal}) => globalVal.splitper,
    }),
  },
  created(){
    this.getEditionList()
    if(getCookie('arid')){
      this.getBackendArticle(getCookie('arid'))
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
    handleAddEdition(content){
      this.addEdition(
        {
          id: uuid(),
          title: content,
          articles: [],
          userId: this.user.id,
          createTime: new Date(),
          modifyTime: new Date(),
        }
      )
    },
    handleCreateBlog(id){
      let blog ={
        id: uuid(),
        title: formatDate(new Date()),
        backendContent: null,
        frontContent: null,
        edition: id,
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
    }
  },
  watch:{
    splitmin(val){
      const WIDTH = document.documentElement.clientWidth * val
      this.splitper = val
    }
  },
  data(){
    return {
      newblog:'',
      isedit: false,
      splitper: document.documentElement.clientWidth * 0.2>255?0.2:255/document.documentElement.clientWidth
    }
  }
}

</script>
<style>

</style>
