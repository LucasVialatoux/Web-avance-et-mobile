<template>
<v-row justify="center">
    <v-col sm="12" md="8" lg="4">
        <v-card>
            <v-row justify="center">
                <v-col sm="11" md="8" lg="4">
                    <v-form>
                            <v-row justify="center">
                                <v-text-field label="Login" v-model="login"></v-text-field>
                            </v-row>
                            <v-row justify="center">
                                <v-text-field type="password" label="Mot de passe" v-model="mdp"></v-text-field>
                            </v-row>
                            <v-row justify="center">
                                <v-btn @click="loginPost()">Login</v-btn>
                            </v-row>
                    </v-form>
                </v-col>
            </v-row>
        </v-card>
    </v-col>
</v-row>
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