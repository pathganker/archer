<template>
  <div class="tag-container">
    <div class="tag-list">
      <div class="tag-item" v-for="(tag,index) in tags" :key="index">
          <div style="text-align:center" class="tag-content" >
            <!-- <router-link :to="{ name: 'category', params: {eid: edition.id } }" class="sort-title">
              {{edition.title}} 
            </router-link> -->
            <a class="tag-title"> {{tag.title}} </a>
          </div>
      </div>
    </div>
  </div>    
</template>
<script>
export default {
    data(){
        return {
            tags:[{title:'青团子'},{title:'EVA'},{title:'冰淇淋'},{title:'健身'},{title:'王者荣耀'},{title:'用户研究'},{title:'统计方法'},{title:'撸猫'},{title:'学姐'},{title:'双子座'}]
        }
    }
}
</script>
<style>
.tag-container{
  width:60%;
  margin: 0 auto;
  text-align: center;
}
.tag-list{
  width:100%;
  display: inline-block;
  text-align: center;
}
.tag-item{
  width:16%;
  display: inline-block;
}
.tag-content{
  height: 60px;
  display: flex;
}
.tag-title{
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

