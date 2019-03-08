<template>
  <div class="reply-list">
    <div v-for="(reply, i) in replys" :key="i" class="reply-item">
      <p class="reply-content">
        <a class="reply-user link-light">{{reply.nickname}} </a>：@{{reply.targetName}}
        {{reply.content}}
      </p>
      <div class="reply-footer text-right">
        <a class="reply" href="javascript:;" @click.prevent="showReply(k,i,reply)" >回复</a>
        <span class="reply-time pull-left">{{ reply.createTime | formatDate }}</span>
      </div>
      <form v-bind:id="k +'replyFormReply' + i" class="new-reply hide" @submit.prevent="submitReplyToReply(k,i,reply)" > 
          <div class="comment-text"> 
            <textarea v-bind:id="k+'replyContentReply' + i"
                required 
                maxLength="2000" 
                placeholder="写下你的回复">
            </textarea> 
            <div> 
              <input type="submit" value="发 表" class="btn btn-sm btn-info" />
            </div> 
          </div>
        </form>
    </div>
  </div>
  
</template>
<script>

export default {
  props:['replys','k'],
  methods:{
    showReply(k,i,reply){
      if(this.$parent.user){
        const eleForm = document.getElementById(k +'replyFormReply' + i)
        const eleTextarea = eleForm.getElementsByTagName('textarea')[0]
        if(eleForm.className.indexOf('hide') != -1){
          eleForm.className = 'new-reply'
          eleTextarea.focus()
          eleTextarea.placeholder = '@' + reply.nickname + ' '
        }else{
          eleForm.className += ' hide'
        }
      }else{
        this.$parent.$parent.openLoginModal()
      }
    },
    submitReplyToReply(k,i,reply){
      const eleForm = document.getElementById(k+'replyFormReply' + i)
      const eleTextarea = document.getElementById(k+'replyContentReply' + i)
      this.$parent.$parent.handleSubmitReplyReply(reply,eleTextarea.value)
      eleTextarea.value = ''
      eleForm.className += ' hide'
    },
  }
}
</script>