<template>
  <div class="sort-container">
    <div class="sort-list" v-for="(edition,index) in editions" :key="index">
      <div class="sort-item">
        <Card :bordered="false" dis-hover style="background: 0 0;">
          <div style="text-align:center" class="sort-content" >
            <router-link :to="{ name: 'category', params: {eid: edition.id } }" class="sort-title">
              {{edition.title}} 
            </router-link>
          </div>
        </Card>
      </div>
    </div>
  </div>
</template>
<script>
import { Card } from 'iview'
import { mapState,mapActions } from 'vuex'
export default {
  components:{Card},
  computed:{
    ...mapState({
      editions: ({category}) => category.editions,
    })
  },
  methods:{
    ...mapActions([
      'getCategory'
    ])
  },
  created(){
    this.getCategory();
  }
}
</script>
<style>
.sort-container{
  width:60%;
  margin: 0 auto;
  text-align: center;
}
.sort-list{
  width:30%;
  display: inline-block;
}
.sort-item{
  width:80%;
  display: inline-block;
  margin: 10%;
}
.sort-content{
  height: 60px;
  display: flex;
}
.sort-title{
  margin: auto;
  font-size: 20px;
  color: white!important;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
  font-family: "KaiTi";
  text-decoration:none; 
  border-bottom:1px solid #ccc; /* #ccc换成链接的颜色 */
  display: inline-block; 
  padding-bottom:3px;  /*这里设置你要空的距离*/
}
</style>
