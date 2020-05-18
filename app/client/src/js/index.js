import Vue from 'vue';
import {default as Form} from '../vue/form.vue';

var h1 = new Vue({ 
    el: '#h1',
    data: {
        title: 'MifMapApp'
    }
});

var form = new Vue({
    el: '#form',
    render: h => h(Form)
});

var map = new Vue({ 
    el: '#titleMap',
    data: {
        title: 'Carte'
    }
});
