<template>
<v-container>
    <v-form>
            <v-row>
                <v-text-field label="Login" v-model="login"></v-text-field>
                <v-text-field label="Mot de passe" v-model="mdp"></v-text-field>
            </v-row>
            <v-row>
                <v-btn @click="loginPost()">Login</v-btn>
            </v-row>
    </v-form>
</v-container>
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