<template>
    
</template>
<script>
import { mapActions } from 'vuex'
export default {
    created(){
        let provider = this.$route.params.provider
        let code = this.$route.query.code
        if(provider && code){
            this.getOauthToken({provider:provider,code:code}).then(()=>{
               this.getUserInfo().then(()=>{
                    this.$router.push({path:'/'})
               })
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
