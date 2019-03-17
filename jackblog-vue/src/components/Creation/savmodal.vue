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