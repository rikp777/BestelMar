<template>

</template>

<script>
    export default {
        name: "TableDelete",
        created() {
            if(this.$route.params.id){
                this.$store.dispatch('deleteTable', this.$route.params.id)
                    .then(() => {
                        this.$router.push({ name: 'tableList' })
                    }).catch(() => {
                    console.log("Not found")
                })
            }
            this.$router.push({ name: 'tableList'})
        }
    }
</script>

