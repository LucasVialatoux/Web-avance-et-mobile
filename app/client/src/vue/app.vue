<template>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item" v-if="!this.$store.state.auth.connected">
                <router-link to="/" class="nav-link">Accueil</router-link>
            </li>
            <li class="nav-item" v-if="this.$store.state.auth.connected">
                <router-link to="/map" class="nav-link">Map</router-link>
            </li>
            <li class="nav-item-right" v-if="this.$store.state.auth.connected">
                <router-link to="/" class="nav-link" @click.native="disconnect()">Se Deconnecter</router-link>
            </li>
        </ul>
        </div>
    </nav>
    <router-view></router-view>
</div>
</template>

<script>
const axios = require('axios')

export default {
    name: "App",
    methods: {
        disconnect() {
            axios.delete(`${window.origin}/auth/logout`, {
                headers: {
                    'Authentication': this.$store.state.auth.token
                }
            }).then(response => {
                this.$store.commit('disconnected')
            })
        }
    }
}
</script>