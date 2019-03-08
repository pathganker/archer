<template>
  <div class="creation-content">
    <Edition :editionList="editionList" :cured="cured" :curar="curar"></Edition>
    <div class="mask" id="editmask" @dblclick="editActive()"></div>
    <Markdown :editionList="editionList" :cured="cured" :curar="curar"></Markdown>
    <Savemodal ref='savmodal' ></Savemodal>
  </div>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import Markdown from './markdown.vue'
import Edition from './edition.vue'
import store from '../../store'
import Savemodal from './savmodal.vue'
import {
  CURRENT_ARTICLE,
} from '../../store/types'
import {getCookie} from '../../utils/cookies'
import {formatDate, uuid} from '../../utils/stringUtils'
export default{
  components:{ Edition, Markdown, Savemodal},
  computed:{
    ...mapState({
      editionList: ({editionList}) => editionList.items,
      cured: ({editionList}) => editionList.cured == null ?  getCookie('cured') : editionList.cured,
      curar: ({editionList}) => editionList.curar == null ?  getCookie('curar') : editionList.curar,
      user: ({auth}) =>auth.user
    })
  },
  created(){
    this.getEditionList()
  },
  methods:{
    ...mapActions([
      'getEditionList',
      'addBackendArticle',
      'addEdition',
      'updateEdition',
      'updateBackendArticle',
      'deleteBackendArticle'
    ]),
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
      this.isedit = false
      this.updateBackendArticle(blog)
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
    }
  },
  watch:{
    isedit(val){
      if(this.isedit){
        document.getElementById('editmask').style.display='none'
      }else{
        document.getElementById('editmask').style.display='block'
      }
    }
  },
  data(){
    return {
      newblog:'',
      isedit: false,
    }
  }
}

</script>
<style>
.mask{
  width: 80%;
  height: 100%;
  background-color: rgba(0,0,0,0.5);
  opacity: 1;
  bottom: 0;
  right: 0;
  position: fixed;
  z-index: 998;

}
</style>
