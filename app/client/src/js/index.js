import Vue from 'vue/dist/vue.js';
import {default as Form} from '../vue/form.vue';

import { mapState } from 'vuex';
import Vuex from 'vuex';
Vue.use(Vuex);

//VueX State
var state = {
  labels: [
      { title: 'Lattitude : ',id: 'lat' , type: 'text'  , value:45.782, name:'lat'},
      { title: 'Longitude : ',id: 'lon' , type: 'text'  , value:4.8656, name:'lon'},
      { title: 'Zoom : ',id: 'zoom', type: 'range' , value:15    , name:'zoom', min:1, max:20}
  ]
};

//VueX Getters
var getters = {
  labels: state => state.labels
};

//VueX Mutations
var mutations = {

};

//VueX Actions
var actions = {

};

//VueX Store
const store = new Vuex.Store({
    state: state,
    getters: getters
});


var h1 = new Vue({ 
    el: '#h1',
    data: {
        title: 'MifMapApp'
    }
});

var form = new Vue({
    store,
    el: '#form',
    computed: mapState({
        labels: state => state.labels
    })
});

var map = new Vue({ 
    el: '#titleMap',
    data: {
        title: 'Carte'
    }
});
