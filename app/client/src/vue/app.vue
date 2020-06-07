<template>
    <v-app id="inspire">
        <v-navigation-drawer
        v-model="drawer"
        fixed
        temporary
        v-if="mini"
        >
            <v-list dense nav>
                <v-list-item-group>
                    <v-list-item v-if="!this.$store.state.auth.connected" link>
                        <router-link to="/" class="nav-link">Accueil</router-link>
                    </v-list-item>
                    <v-list-item v-if="this.$store.state.auth.connected" link>
                        <router-link to="/map">Map</router-link>
                    </v-list-item>
                    <v-list-item v-if="this.$store.state.auth.connected" link>
                        <router-link to="/" class="nav-link" @click.native="disconnect()">Se Deconnecter</router-link>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>

        <v-app-bar
        app
        >
            <v-app-bar-nav-icon @click.stop="drawer = !drawer" v-if="mini"></v-app-bar-nav-icon>
            <v-toolbar-title>{{ this.$store.state.title }}</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn @click="changeTheme()" icon>
                <v-icon>mdi-moon-waning-crescent</v-icon>
            </v-btn>
            <v-btn exact v-if="!mini && !this.$store.state.auth.connected">
                Accueil
            </v-btn>
            <v-btn exact v-if="!mini && this.$store.state.auth.connected">
                <router-link to="/map">Map</router-link>
            </v-btn>
            <v-btn exact v-if="!mini && this.$store.state.auth.connected">
                <router-link to="/" class="nav-link" @click.native="disconnect()">Se Deconnecter</router-link>
            </v-btn>
        </v-app-bar>

        <v-content>
            <v-container fluid>
                <router-view></router-view>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    const axios = require('axios')
    
    export default {
        props: {
            source: String,
        },
        data: () => ({
            drawer: null,
        }),
        created () {
            this.$vuetify.theme.dark = false
        },
        computed: {
            mini() {
                switch (this.$vuetify.breakpoint.name) {
                    case 'xs': return true
                    case 'sm': return true
                    case 'md': return false
                    case 'lg': return false
                    case 'xl': return false
                }
            }
        },
        methods: {
            changeTheme() {
                this.$vuetify.theme.dark = !this.$vuetify.theme.dark
            },
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