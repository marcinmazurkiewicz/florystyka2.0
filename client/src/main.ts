import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import "@/assets/index.css";
import VueSmoothScroll from "vue3-smooth-scroll";

createApp(App)
  .use(router)
  .use(VueSmoothScroll, {
    updateHistory: false,
    duration: 300
  })
  .mount("#app");
