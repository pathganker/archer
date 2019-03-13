<template>
	<Modal 
    v-model="showConfirmModal" class-name="vertical-center-modal"
    width="400"
    ok-text="离开"
    cancel-text="继续编辑"
    @on-ok="confirm"
    @on-cancel="cancel"
  >
  <p>编辑内容还未保存</p>
	</Modal>
</template>
<script>
import { Modal } from 'iview'

export default {
  components:{
    Modal,
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
      this.$parent.editCancel()
      this.showConfirmModal = false
    },
    cancel(){
      this.showConfirmModal = false
    }
  },
}
</script>
<style>
/* .modal.in .modal-dialog{
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
} */

</style>