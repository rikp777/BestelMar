<template>

</template>

<script>
    export default {
        name: "ArticleDelete",
        created() {
            if(this.$route.params.id){
                this.$store.dispatch('deleteArticle', this.$route.params.id)
                    .then(() => {
                        this.$router.push({ name: 'articleList' })
                    }).catch(() => {
                    console.log("Not found")
                })
            }
            this.$router.push({ name: 'articleList'})
        }
    }
</script>

