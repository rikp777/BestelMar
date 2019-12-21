<template>

</template>

<script>
    export default {
        name: "userDelete",
        mounted() {
            if(this.$route.params.id){
                this.$store.dispatch('userDelete', this.$route.params.id)
                    .then(() => {
                      console.log("Deactivate")
                    }).catch(() => {
                    console.log("Not found")
                })
            }
        }
    }
</script>
