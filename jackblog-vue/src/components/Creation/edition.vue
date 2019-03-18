<template>
  <div class="edition" id="editionNav">
      <Split v-model="splitper" mode="vertical" min=238>
        <div slot="top" class="demo-split-pane">
          <div class="edition-list">
            <ul class="nav nav-pills nav-stacked">
              <li class="navbar-item-menu"> 
								<router-link :to="{ path: '/' }" title="首页" class="item-menu"><span class="glyphicon glyphicon-home" aria-hidden="true"></span><br>
								首页
								</router-link>
								<router-link :to="{ path: '/edition' }"  title="分类" class="item-menu"><span class="glyphicon glyphicon-th" aria-hidden="true"></span><br>
								分类
								</router-link>
								<router-link :to="{ path: '/manage' }" title="管理" class="item-menu"><span class="glyphicon glyphicon-ice-lolly-tasted" aria-hidden="true"></span><br>
								管理
								</router-link>
                <a href="javascript:;" class="item-menu" @click="logout()"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span><br>
								 登出
						  	</a>  
							</li>       
              <li  @click="edition()" ><a class="fa fa-plus">&nbsp;&nbsp;&nbsp;新建文集</a></li>
              <li v-show="editionEdit" class="input-group medium" >
                <div class="panel-body">
                  <input type="text" name="newEdition" v-model="newEdition" v-validate="'required'" class="form-control" placeholder="新建文集" data-vv-as="文集名">
                  <span class="tip-span">{{ errors.first('newEdition') }}</span>
                </div>
                <div class="panel-footer">
                  <button type="button" class="btn btn-success" @click="confirm()">确认</button>
                  <button type="button" class="btn btn-info" @click="cancel()">取消</button>
                </div>
              </li>
            </ul>
            <ul class="nav nav-pills nav-stacked">
              <li v-for="(edition,index) in editionList" :key="index" @click="editionActive(edition,index)" :class="{active:index==cured}" @dblclick="edconfig(index)">
                <a>{{edition.title}}  
                  <span class="glyphicon glyphicon-cog " aria-hidden="true" @click="edconfig(index)" style="display:none;float:right;" :class="{active:index==cured}"></span>
                  <ul class="dropdown-menu"  :id="'ed_drop_menu_'+index" style="display:none;user-select:none;" v-clickany="index">
                    <li><a @click="edModify(edition)"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;&nbsp;修改名称</a></li>
                    <li><a @click="edDelete(edition)"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;删除文集</a></li>
                  </ul>
                </a>
              </li>
              <li v-if="editionList.length <1">正在大力回车...</li>
            </ul>
          </div>
        </div>
        <div slot="bottom" class="demo-split-pane">
          <div class="title-list">
            <ul class="nav nav-pills nav-stacked">
              <li><a class="fa fa-plus" @click="createblog()">&nbsp;&nbsp;&nbsp;新建文章</a></li>
            </ul>
            <ul v-if="editionList[cured]!=null" class="nav nav-pills nav-stacked">
              <li  v-for="(article,index) in editionList[cured].articles" :key="index" @click="articleActive(index,article.id)" :class="{active:index==curar}" @dblclick="arconfig(index)">
                <a>{{article.title}}
                  <span class="glyphicon glyphicon-cog" aria-hidden="true" style="display:none;float:right" @click="arconfig(index)" :class="{active:index==curar}"></span>
                    <ul class="dropdown-menu"  :id="'ar_drop_menu_'+index" style="user-select:none;" v-clickoutside="index">
                    <li><a v-if="!article.publish" @click="arPublish(article.id,index)"><span class="glyphicon glyphicon-share" aria-hidden="true"></span>&nbsp;&nbsp;发布文章</a>
                        <a v-else @click="arHide(article.id,index)"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span>&nbsp;&nbsp;取消发布</a></li>
                    <li><a @click="arModifyTitle(index)"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;&nbsp;修改标题</a></li>
                    <li><a @click="arMove(index)"><span class="glyphicon glyphicon-triangle-left" aria-hidden="true"></span>&nbsp;&nbsp;移动</a>
                    <ul class="dropdown-menu second" :id="'ar_drop_menu_sec_'+index" v-clickoutside="index">
                      <li v-for="(edition,key) in editionList" :key="key" @click="moveArticle(article.id, edition.id, index)"><a class="ar-title" v-if="key!=cured"><span class="glyphicon glyphicon-book" aria-hidden="true"></span>&nbsp;&nbsp;{{edition.title}}</a></li>
                    </ul>
                    </li>
                    <li>
                      <a  class="upload-button">
                        <input type="file"  class="file-input" ref="picInput" @change="addCover($event,article.id)" accept="image/gif,image/jpeg,image/jpg,image/png"/>
                        <span class="glyphicon glyphicon-picture" aria-hidden="true"></span>&nbsp;&nbsp;添加/修改封面</a>
                    </li>
                    <li><a @click="arDelete(article.id,index)"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;删除</a></li>
                  </ul>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </Split>
    <Modal v-model="cancelPublish" 
      width="400" 
      title="取消发布"
      @on-ok="hideConfirm"
      @on-cancel="hidecancel">
      <p>其他人将看不到这篇博客</p>
	  </Modal>

    <Modal v-model="showPublish" 
      width="400" 
      title="发布文章"
      @on-ok="publishConfirm"
      @on-cancel="publishCancel">
      <p>其他人将可以看到这篇博客</p>
    </Modal>

    <Modal v-model="showDelete"  width="400" 
      title="删除"
      @on-ok="deleteConfirm"
      @on-cancel="deleteCancel">
      <p>这篇博客将要人间蒸发</p>
    </Modal>

    <Modal v-model="editDelete"  width="400" 
      title="删除"
      :closable="false"
      :mask-closable="false">
      <p slot="header">删除</p>
      <div style="text-align:center">
        <p>删除文集会删除文集下所有文章</p>
        <p>请慎重操作</p>
      </div>
      <div slot="footer" style="text-align:center">
          <Button type="error"   :loading="edition_loading" @click="wholeDelete">删除文集及其所有文章</Button>
          <Button type="success" @click="editCancel">取消</Button>
      </div>
    </Modal>
  </div>
</template>
<script>
import { Split, Modal, Upload, Button } from 'iview'
import { mapState,mapActions } from 'vuex'
import store from '../../store'
import {
  CURRENT_ARTICLE,
  CURRENT_EDITION,
  ADD_EDITION,
  MOVE_ARTICLE
} from '../../store/types'
import {formatDate, uuid} from '../../utils/stringUtils'
export default {
  props:['editionList','cured','curar'],
  components:{
    Split,Modal,Upload,Button
  },
  methods:{
    ...mapActions([
      'getBackendArticle',
      'logout',
      'uploadCover',
      'deleteEdition',
    ]),
    editionActive(edition,num){
      if(num!= this.cured && this.$parent.$parent.isedit ){
        this.$parent.$parent.openSaveModal()
        return
      }
      this.newEdition=edition.title
      this.editionId=edition.id
      store.commit(CURRENT_EDITION,{cured:num})
      const id = edition.articles[0]?edition.articles[0].id:'null'
      store.commit(CURRENT_ARTICLE,{curar:0,arid: id})
      this.$parent.$parent.getBackendArticle(id)
    },
    articleActive(num, id){
      if(num!= this.curar && this.$parent.$parent.isedit){
        this.$parent.$parent.openSaveModal()
        return
      }
      store.commit(CURRENT_ARTICLE,{curar:num, arid: id})
      this.$parent.$parent.getBackendArticle(id)
    },
    newActive(num){
      store.commit(CURRENT_ARTICLE,{curar:num})
    },
    edition(){
      this.editionId=null
      this.newEdition='新建文件夹'+(1+this.editionList.length)
      this.editionEdit=true
    },
    confirm(){
      this.$validator.validateAll().then(valid =>{
        if(valid){
          if(this.editionId!=null){
            this.$parent.$parent.handleUpdateEdition(this.editionId, this.newEdition)
          }else{
            this.$parent.$parent.handleAddEdition(this.newEdition)
            this.editionId = this.editionList[this.cured].id
          }
          this.editionEdit=false
        }
      })
    },
    cancel(){
       this.editionEdit=false
    },
    createblog(){
      store.commit(CURRENT_ARTICLE, {curar:0})
      if(null ==this.cured){
        if(null !=this.editionList[0]){
          store.commit(CURRENT_EDITION,{cured:0})
          this.$parent.$parent.handleCreateBlog(this.editionList[0].id)
        }else{
          this.$parent.$parent.handleUpdateEdition('新建文件夹').then(this.createblog())
        }
      }else{
        this.$parent.$parent.handleCreateBlog(this.editionList[this.cured].id)
      }
      
    },
    edconfig(num){
      const dropmenu = document.getElementById('ed_drop_menu_'+num)
      const isshow = (dropmenu.style == null || dropmenu.style.display=="none") ? "block":"none"
      dropmenu.style.display=isshow
    },
    arconfig(num){
      const dropmenu = document.getElementById('ar_drop_menu_'+num)
      const isshow = (dropmenu.style == null || dropmenu.style.display=="none") ? "block":"none"
      dropmenu.style.display=isshow 
    },
    edModify(edition){
      this.newEdition=edition.title
      this.editionId=edition.id
      this.editionEdit = true
    },
    edDelete(edition){
      this.editDelete=true
      this.editionId= edition.id
    },
    arModifyTitle(index){
      this.$parent.$parent.isedit = true
      const titleinput = document.getElementById('title-input')
      titleinput.focus()
      titleinput.click()
      titleinput.select()
      document.getElementById('ar_drop_menu_'+index).style.display='none'
    },
    arPublish(id,index){
      document.getElementById('ar_drop_menu_'+index).style.display='none'
      this.showPublish=true
      this.newblog={
        id: id,
        publish: true,
        publishTime: new Date()
      }
    },
    publishConfirm(){
      store.commit(CURRENT_EDITION,{cured:this.cured})
      store.commit(CURRENT_ARTICLE,{curar:this.curar})
      this.$parent.$parent.handleUpdateBlog(this.newblog)
      this.showPublish=false
    },
    publishCancel(){
      this.showPublish=false
    },
    arHide(id,index){
      document.getElementById('ar_drop_menu_'+index).style.display='none'
      this.cancelPublish=true
      this.newblog={
        id: id,
        publish: false,
      }
    },
    hideConfirm(){
      store.commit(CURRENT_EDITION,{cured:this.cured})
      store.commit(CURRENT_ARTICLE,{curar:this.curar})
      this.$parent.$parent.handleUpdateBlog(this.newblog)
      this.cancelPublish=false
    },
    hidecancel(){
      this.cancelPublish=false
    },
    arMove(index){
      document.getElementById('ar_drop_menu_sec_'+index).style.display='block'
    },
    arDelete(id, index){
      this.showDelete=true
      this.deleteId=id
      this.dialog=index
    },
    deleteConfirm(id,index){
      store.commit(CURRENT_EDITION,{cured:this.cured})
      store.commit(CURRENT_ARTICLE,{curar:this.curar})
      document.getElementById('ar_drop_menu_'+this.dialog).style.display='none'
      this.$parent.$parent.hadleDeleteBlog(this.deleteId)
      this.showDelete=false
    },
    deleteCancel(){
      document.getElementById('ar_drop_menu_'+this.dialog).style.display='none'
      this.showDelete=false
    },
    moveArticle(aid,eid,index){
      this.newblog={
        id: aid,
        edition: eid
      }
      store.commit(CURRENT_EDITION,{cured:this.cured})
      store.commit(CURRENT_ARTICLE,{curar:this.curar})
      store.commit(MOVE_ARTICLE,this.newblog)
      this.$parent.$parent.handleUpdateBlog(this.newblog)
      document.getElementById('ar_drop_menu_'+index).style.display='none'
      document.getElementById('ar_drop_menu_sec_'+index).style.display='none'

    },
    addCover(e,id){
      let file = e.target.files[0]
      // let data = new FormData()
      // data.append('picture', file)
      // if(file) {
      //   this.uploadCover({
      //     picture: data,
      //     id: id
      //   })
      // }
      this.$parent.$parent.imageindex=file
      this.$parent.$parent.showCropper()
    },
    wholeDelete(){
      this.edition_loading=true
      this.deleteEdition(this.editionId).then(response =>{
        this.edition_loading=false
        this.editDelete=false
      })
    },
    editCancel(){
      this.editDelete=false
    }
  },
  directives:{
    //菜单外点击隐藏事件
    clickoutside:{
      bind:function(el,binding,vnode){
        function  hidemenu(el){
          el.style.display="none" 
        }
        function documentHandler(e){
          if(el.contains(e.target) || el.contains(e.target.nextElementSibling)|| el.contains(e.target.parentElement.nextElementSibling)){
            return true
          }else if (el.style.display=='block'|| el.style.display!='none'){
            hidemenu(el)
          }else if(this.editionEdit=true){
            this.editionEdit=false
          }
        }
        el._vueClickOutside_ = documentHandler
        document.addEventListener('click',documentHandler);
      },
      unbind:function(el,binding){
          document.removeEventListener('click',el._vueClickOutside_);
          delete el._vueClickOutside_;
      }
    },
    //任意点击隐藏菜单
    clickany:{
      bind:function(el,binding,vnode){
        function  hidemenu(el){
          el.style.display="none" 
        }
        function documentHandler(e){
          if(el.contains(e.target.nextElementSibling)){
            return true
          }else if (el.style.display=='block'){
            hidemenu(el)
          }
        }
        el._vueClickOutside_ = documentHandler
        document.addEventListener('click',documentHandler);
      },
      unbind:function(el,binding){
          document.removeEventListener('click',el._vueClickOutside_);
          delete el._vueClickOutside_;
      }
    }
  },
  data(){
    return {
      newEdition:'',
      editionId:'',
      newblog: '',
      cancelPublish: false,
      showPublish: false,
      showDelete: false,
      deleteId:'',
      dialog: '',
      splitper: 0.5,
      editionEdit: false,
      editDelete:false,
      edition_loading: false,
    }
  }
}
</script>
<style>
.ar-title{
  display:block;
  white-space:nowrap;
  overflow:hidden;
  text-overflow:ellipsis;
}
.demo-split-pane {
  height: 100%;
  width: 100%;
  overflow-y: auto;
}
.navbar-item-menu{
  text-align: center;
}
.navbar-item-menu .item-menu{
  display: inline-block;
}
.panel-footer{
  background: 0 0;
  border-top: none;
}
.panel-body{
  background: 0 0;
}
.upload-button{
  display: block!important;
  color: black!important;
}
.upload-button :hover{
  display: block!important;
  color: black!important;
}
.file-input{
  opacity: 0;
  position:absolute;
  width:100%;
  left: 0;
}
</style>
