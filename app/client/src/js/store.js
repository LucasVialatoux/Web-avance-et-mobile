import Vue from 'vue/dist/vue.js';
import Vuex from 'vuex';

Vue.use(Vuex);

//VueX State
const state = {
    form: {
        title : 'Position',
        labels: [
            { title: 'Lattitude : ',id: 'lat' , type: 'text'  , value:45.782, name:'lat'},
            { title: 'Longitude : ',id: 'lon' , type: 'text'  , value:4.8656, name:'lon'},
            { title: 'Zoom : ',id: 'zoom', type: 'range' , value:15    , name:'zoom', min:1, max:20}
        ]
    }
};

//VueX Getters
const getters = {
    getLabels(state){
        return state.form.labels;
    },
    getTitle(state){
        return state.form.title;
    },
    getLabelById: (state) => (id) => {
        return state.form.labels.find(label => label.id === id);
    }
};

//VueX Mutations
const mutations = {
    /*changeLabel: (state, payload) => {
        const {id, value} = payload
        const label = state.form.labels.find(label => label.id === id)
        label.value = value

        var map = document.getElementById('map')._leaflet_map
        map.setView([state.form.labels[0].value, state.form.labels[1].value], state.form.labels[2].value);
    }*/
};

//VueX Actions
const actions = {
};

//VueX Store
export default new Vuex.Store({  
    state,  
    mutations,  
    actions,  
    getters
});
