import {createApp} from 'vue'
import App from './App.vue'
import '@/assets/index.css'
import router from './router'
import VueGtag from "vue-gtag-next";
import {VueCookieNext} from "vue-cookie-next"

createApp(App)
    .use(router)
    .use(VueGtag, {
        isEnabled: false,
        property: {
            id: process.env.VUE_APP_GOOGLE_ANALYTICS_ID
        }
    })
    .use(VueCookieNext)
    .mount('#app')

VueCookieNext.config({
    expire: '30d'
})