<template>
  <div class="article-box">
    <ArtickeContent :article-detail="articleDetail"></ArtickeContent>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <Like :like-count="articleDetail.likeCount" :is-like="articleDetail.isLike"></Like>
    <Prenext :prev-article="prevArticle" :next-article="nextArticle"></Prenext>
    <Comment :comment-list="commentList" :user="user"></Comment>
    <Loginmodal ref='modal'></Loginmodal>
    <Scrolltop></Scrolltop>
  </div>
</template>
<script>
import ArtickeContent from './content.vue'
import Comment from './comment.vue'
import Prenext from './prenext.vue'
import Like from './like.vue'
import Loginmodal from '../Login/modal.vue'
import Scrolltop from '../Scrolltop/index.vue'
import { mapState,mapActions } from 'vuex'
import {formatDate, uuid} from '../../utils/stringUtils'
export default {
  components: { ArtickeContent,Like,Prenext,Comment,Scrolltop,Loginmodal },
  computed: {
    ...mapState({
      articleDetail: ({articleDetail}) => articleDetail.item,
      user: ({auth}) => auth.user,
      nextArticle: ({prenextArticle}) => prenextArticle.next,
      prevArticle: ({prenextArticle}) => prenextArticle.prev,
      commentList: ({commentList}) => commentList.items,
      options: ({options}) => options.item,
      aid: ({route}) => route.params.aid    
    })
  },
  created () {
    this.initData()
  },
  watch: {
    '$route': 'initData'
  },  
  methods:{
    ...mapActions([
      'getArticleDetail',
      'getPrenext',
      'getCommentList',
      'toggleLike',
      'addComment',
      'addReply'
    ]),
    initData(){
      const aid = this.$route.params.aid
      this.getPrenext(aid)
      this.getCommentList(aid)
      this.getArticleDetail(aid)
    }, 
    openLoginModal(){
      this.$refs.modal.showModal()
    },
    handleToggleLike(){
        this.toggleLike(this.$route.params.aid)
    },
    handleSubmitComment(content){
      if(this.user && content.trim() !== ''){
        this.addComment(
          {
        uid: uuid(),
        content: content,
        articleId: this.$route.params.aid,
        nickname: this.user.nickname,
        avatar: this.user.avatar,
        userId: this.user.id,
        createTime: new Date()
      })
      }else{
        this.openLoginModal()
      }
    },
    handleShowReply(content){
      //判断是否登录.未登录则弹出登录框.
      if(!this.user){
        this.openLoginModal()
      }
    },
    handleSubmitReplyToComment(cid,content){
      if(this.user && content.trim() !== ''){
        this.addReply({
          content: content,
          commentId: cid,
          createTime: new Date(),
          userId: this.user.id,
          nickname: this.user.nickname,
          targetId: this.articleDetail.userId,
          targetName: this.articleDetail.nickname
        })
      }else{
        this.openLoginModal()
      }
    },
    handleSubmitReplyReply(reply,content){
      if(this.user && content.trim() !== ''){
        this.addReply({
          content: content,
          commentId: reply.commentId,
          createTime: new Date(),
          userId: this.user.id,
          nickname: this.user.nickname,
          targetId: reply.userId,
          targetName: reply.nickname
        })
      }else{
        this.openLoginModal()
      }
    }
  }
}
</script>