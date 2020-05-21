import Vue from 'vue/dist/vue.js';
import {default as MapView} from '../vue/mapview.vue';

import { mapState } from 'vuex';
import store from './store';

Vue.config.productionTip = false

new Vue({
    el: '#app',
    store,
    render: h => h(MapView)
})
