import '@mdi/font/css/materialdesignicons.css'
import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import App from '../vue/App.vue'

import store from './store'
import router from './router'

Vue.use(Vuetify)

Vue.config.productionTip = false

const opts = {
    icons: {
        iconfont: 'mdi'
    }
}

new Vue({
    store,
    router,
    vuetify: new Vuetify(opts),
    render: h => h(App)
}).$mount('#app')