<template>
    
</template>
<script>
export default {
    created(){
        let provider = this.$route.params.provider
        let code = this.$route.query.code
        console.log(provider)
        console.log(code)
        
    }
}
</script>
