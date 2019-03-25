<template>
    
</template>
<script>
import { mapActions } from 'vuex'
export default {
    created(){
        let provider = this.$route.params.provider
        let code = this.$route.query.code
        let from = this.$route.from
        console.log(provider)
        console.log(code)
        if(provider && code){
            this.getOauthToken({provider:provider,code:code}).then(()=>{
               // this.$router.go(-1)
               this.getUserInfo()
            })
        }
        
    },
    methods: {
        ...mapActions([
            'getOauthToken',
            'getUserInfo'
        ]),    
    }
}
</script>
