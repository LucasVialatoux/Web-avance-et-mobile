<template>
    <v-app id="inspire">
        <v-navigation-drawer
        v-model="drawer"
        app
        clipped
        >
            <v-list dense>
                <v-list-item v-if="!this.$store.state.auth.connected" link>
                    <v-list-item-action>
                        <v-icon>mdi-view-dashboard</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <router-link to="/" class="nav-link">Accueil</router-link>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item v-if="this.$store.state.auth.connected" link>
                    <v-list-item-action>
                        <v-icon>mdi-view-dashboard</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <router-link to="/map">Map</router-link>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item v-if="this.$store.state.auth.connected" link>
                    <v-list-item-action>
                        <v-icon>mdi-cog</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <router-link to="/" class="nav-link" @click.native="disconnect()">Se Deconnecter</router-link>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

        <v-app-bar
        app
        clipped-left
        >
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title></v-toolbar-title>
        </v-app-bar>

        <v-content>
            <router-view></router-view>
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
            this.$vuetify.theme.dark = true
        },
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