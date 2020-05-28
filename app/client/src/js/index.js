import Vue from 'vue/dist/vue.js';
import MapView from '../vue/mapview.vue';
import Login from '../vue/login.vue';
import App from '../vue/app.vue';
import VueRouter from 'vue-router';

import { mapState } from 'vuex';
import store from './store';

Vue.config.productionTip = false;

Vue.use(VueRouter);

const Mymap = { render: h => h(MapView) };
const Home = { render: h => h(Login)};

const routes = [
  { path: '', component: Home },
  { path: '/map', component: Mymap },
]

const router = new VueRouter({
  routes
})

new Vue({
    el: '#app',
    store,
    router,
    render: h => h(App)
})