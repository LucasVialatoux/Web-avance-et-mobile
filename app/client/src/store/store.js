import Vue from 'vue/dist/vue.js';
import Vuex from 'vuex';

Vue.use(Vuex);

//VueX State
const state = {
  labels: [
      { title: 'Lattitude : ',id: 'lat' , type: 'text'  , value:45.782, name:'lat'},
      { title: 'Longitude : ',id: 'lon' , type: 'text'  , value:4.8656, name:'lon'},
      { title: 'Zoom : ',id: 'zoom', type: 'range' , value:15    , name:'zoom', min:1, max:20}
  ],
  title : 'Position'
};

//VueX Getters
const getters = {
  getLabels(state){
  	return state.labels;
  },
  getTitle(state){
  	return state.title;
  }
};

//VueX Mutations
const mutations = {
	UPDATE_LABEL: (state, label) => {
		state.labels[0].value = label[0].value
		state.labels[1].value = label[1].value
    }  
};

//VueX Actions
const actions = {
	loadLabels: (context, label) => {
            context.commit('UPDATE_LABEL', label);
        }
};

//VueX Store
export default new Vuex.Store({  
  state,  
  mutations,  
  actions,  
  getters  
});

