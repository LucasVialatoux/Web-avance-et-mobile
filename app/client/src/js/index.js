import Vue from 'vue/dist/vue.js';
import {default as MapView} from '../vue/mapview.vue';
import {default as MyForm} from '../vue/form.vue';
import {default as Header} from '../vue/header.vue';
import VueRouter from 'vue-router';

import { mapState } from 'vuex';
import store from './store';

Vue.config.productionTip = false;

Vue.use(VueRouter);

const Form = { render: h => h(MyForm) };
const Mymap = { render: h => h(MapView) };
const Home = { render: h => h(Header)};

const routes = [
  { path: '/map', component: Mymap },
  { path: '', component: Home }
]

const router = new VueRouter({
  routes
})

new Vue({
    el: '#app',
    store,
    router
    //render: h => h(MapView)
})