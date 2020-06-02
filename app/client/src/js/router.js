import Vue from 'vue'
import VueRouter from 'vue-router'
import store from './store'

import MapView from '../vue/mapview.vue'
import Login from '../vue/login.vue'

Vue.use(VueRouter)

const Mymap = { render: h => h(MapView) }
const Home = { render: h => h(Login)}

const routes = [
    { path: '', component: Home },
    { path: '/map', component: Mymap, meta: {requiresAuth: true} },
]

const router = new VueRouter({
    routes
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.
        if (!store.state.auth.connected) {
            next({
            path: '/',
            query: { redirect: to.fullPath }
            })
        } else {
            next()
        }
    } else {
        next() // make sure to always call next()!
    }
})

export default router