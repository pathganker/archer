<template>
	<modal v-model="showConfirmModal" effect="fade" width="400" >
	  <div slot="modal-header" class="modal-header">
	  	<h4 class="modal-title text-center">保存</h4>
	  </div>
	  <div slot="modal-body" class="modal-body">
	  	<div class="portlet-body">
        <a>编辑内容还未保存</a>
	  	</div>
	  </div>
	  <div slot="modal-footer" class="modal-footer"> 
            <button type="button" class="btn btn-primary" @click="confirm()">不保存</button>
            <button type="button" class="btn btn-default" @click="cancle()">继续编辑</button>
	  </div>
	</modal>
</template>
<script>
import { modal } from 'vue-strap'

export default {
  components:{
    modal,
  },
  data(){
    return {
      showConfirmModal:false,
    }
  },
  methods: {
    showModal(){
      this.showConfirmModal = true
    },
    confirm(){
      this.$parent.isedit = false
      this.showConfirmModal = false
    },
    cancle(){
      this.showConfirmModal = false
    }
  },
}
</script>
<style>
.modal.in .modal-dialog{
  -webkit-transform:translate(0,-50%);
  -ms-transform:translate(0,-50%);
  -o-transform:translate(0,-50%);
  transform:translate(0,-50%)
  }
.modal-dialog{
  position:absolute;
  width:20%;
  margin:10px auto;
  left:0;right:0;top:40%;
}

</style>