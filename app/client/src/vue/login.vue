<template>
<div>
    <div>
        <label for="login">Login</label>
        <input type="text" id="login" name="login" v-model="login"/>
    </div>
    <div>
        <label for="mdp">Mdp</label>
        <input type="password" id="mdp" name="mdp" v-model="mdp"/>
    </div>
    <button @click="loginPost()">Login</button>
</div>
</template>

<script>
const axios = require('axios')

export default {
    name: 'login',
    data() {
        return {
            login: undefined,
            mdp: undefined
        }
    },
    methods: {
        loginPost() {
            axios.post(`${window.origin}/auth/login?login=${this.login}&password=${this.mdp}`)
            .then(response => {
                console.log(response)
                if (response.status == 204) {
                    this.$store.commit('connected', {
                        token: response.headers.authentication,
                        user: this.login
                    })
                    this.$router.push('map')
                }
            })
            .catch(error => {
                console.log(error)
            })
        }
    }
}
</script>

<style scoped>
    div {
        width: 100%;
    }
    form {
        margin: 3px auto;
    }
</style>