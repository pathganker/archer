<template>
  <div class="creation-content">
    <Edition :editionList="editionList"></Edition>
    <Markdown :backendArticle="backendArticle"></Markdown>
  </div>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import Markdown from './markdown.vue'
import Edition from './edition.vue'
export default{
  components:{ Edition, Markdown },
  computed:{
    ...mapState({
      editionList: ({editionList}) => editionList.items,
      backendArticle: ({backendArticle}) => backendArticle
    })
  },
  created(){
    this.getEditionList()
    //this.getBackendArticle('test')
    if(this.editionList.length < 1){
      
    }
  },
  methods:{
    ...mapActions([
      'getEditionList',
      'getBackendArticle'
    ])
  }
}

</script>
