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
    props: ['login', 'mdp'],
    methods: {
        loginPost() {
            axios.post(`${window.origin}/auth/login?login=${this.login}&password=${this.mdp}`)
            .then(response => {
                console.log(response)
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