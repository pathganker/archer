<template>
  <div class="creation-content">
    <Edition :editionList="editionList" :cured="cured" :curar="curar"></Edition>
    <Markdown :editionList="editionList" :cured="cured" :curar="curar"></Markdown>
  </div>
</template>

<script>
import { mapState,mapActions } from 'vuex'
import Markdown from './markdown.vue'
import Edition from './edition.vue'
import store from '../../store'
import {
  CURRENT_ARTICLE
} from '../../store/types'
import {getCookie} from '../../utils/cookies'
export default{
  components:{ Edition, Markdown },
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
    ])
  },
}

</script>
