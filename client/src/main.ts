import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "@/assets/index.css";
import VueSmoothScroll from "vue3-smooth-scroll";
import { VueReCaptcha } from "vue-recaptcha-v3";

createApp(App)
  .use(router)
  .use(VueSmoothScroll, {
    updateHistory: false,
    duration: 300
  })
  .use(VueReCaptcha, {
    siteKey: process.env.VUE_APP_RECAPTCHA,
    loaderOptions: {
      autoHideBadge: true
    }
  })
  .mount("#app");
