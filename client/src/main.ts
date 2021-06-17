import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { VueCookieNext } from "vue-cookie-next";
import "@/assets/index.css";
import VueSmoothScroll from "vue3-smooth-scroll";

createApp(App)
  .use(router)
  .use(VueCookieNext)
  .use(VueSmoothScroll, {
    updateHistory: false,
    duration: 300
  })
  .mount("#app");
