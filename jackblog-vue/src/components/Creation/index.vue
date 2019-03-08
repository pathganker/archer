<template>
  <div class="creation-content">
    <Edition :editionList="editionList" :cured="cured" :curar="curar"></Edition>
    <Markdown :editionList="editionList" :cured="cured" :curar="curar"></Markdown>
    <Confirmmodal ref='modal' ></Confirmmodal>
  </div>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import Markdown from './markdown.vue'
import Edition from './edition.vue'
import store from '../../store'
import Confirmmodal from './modal.vue'
import {
  CURRENT_ARTICLE
} from '../../store/types'
import {getCookie} from '../../utils/cookies'
import {formatDate, uuid} from '../../utils/stringUtils'
export default{
  components:{ Edition, Markdown, Confirmmodal},
  computed:{
    ...mapState({
      editionList: ({editionList}) => editionList.items,
      cured: ({editionList}) => editionList.cured == null ?  getCookie('cured') : editionList.cured,
      curar: ({editionList}) => editionList.curar == null ?  getCookie('curar') : editionList.curar,
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
      'updateBackendArticle'
    ]),
    handleAddEdition(content){
      this.addEdition({
        edition:{
          id: uuid(),
          title: content,
          articles: []
        }
      })
    },
    handleCreateBlog(id){
      let blog ={
        id: uuid(),
        title: formatDate(new Date()),
        backendContent: null,
        frontContent: null,
        edition: id
      }
      store.commit(ADD_ARTICLE, {article:blog})
      this.addBackendArticle()
    },
    handleUpdateBlog(blog){
      this.updateBackendArticle(blog)
    },
    handleUpdateEdition(){

    },
    openConfirmModal(){
      this.$refs.modal.showModal()
    },
  },
  data(){
    return {
      newblog:''
    }
  }
}

</script>
