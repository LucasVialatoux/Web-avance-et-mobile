import Vue from 'vue/dist/vue.js'
import App from '../vue/app.vue'

import store from './store'
import router from './router'

Vue.config.productionTip = false

new Vue({
    el: '#app',
    store,
    router,
    render: h => h(App)
})