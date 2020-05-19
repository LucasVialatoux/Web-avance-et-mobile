import Vue from 'vue/dist/vue.js';
import {default as Form} from '../vue/form.vue';

import { mapState } from 'vuex';
import store from '../store/store'

Vue.config.productionTip = false

var h1 = new Vue({ 
    el: '#h1',
    data: {
        title: 'MifMapApp'
    }
});

var form = new Vue({
    el: '#form',
    store,
    render: h => h(Form)
});

/*
var form = new Vue({
    el: '#form',
    store,
    computed: mapState({
        labels: state => state.labels
    })
});
*/

var map = new Vue({ 
    el: '#titleMap',
    data: {
        title: 'Carte'
    }
});
