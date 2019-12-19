<template>

</template>

<script>
    export default {
        name: "userDelete",
        created() {
            if(this.$route.params.id){
                this.$store.dispatch('deleteUser', this.$route.params.id)
                    .then(() => {
                        this.$router.push({ name: 'userList' })
                    }).catch(() => {
                    console.log("Not found")
                })
            }
            this.$router.push({ name: 'userList'})
        }
    }
</script>
