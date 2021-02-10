import {createApp} from 'vue'
import App from './App.vue'
import '@/assets/index.css'
import router from './router'
import VueGtag from "vue-gtag-next";
import {VueCookieNext} from "vue-cookie-next"
import store from "@/store/store";

createApp(App)
    .use(router)
    .use(VueGtag, {
        isEnabled: false,
        property: {
            id: process.env.VUE_APP_GOOGLE_ANALYTICS_ID
        }
    })
    .use(VueCookieNext)
    .use(store)
    .mount('#app')

VueCookieNext.config({
    expire: '30d'
})