<template>
  <div class="w-full min-h-screen bg-deep-cyan font-lato overflow-y-auto">
    <top-nav />
    <router-view class="min-h-80" />
    <privacy-policy-banner />
    <footer
      class="w-full bg-deep-cyan text-white flex flex-col md:flex-row md:justify-between text-sm px-6 mt-6 mx-0 py-4"
    >
      <router-link
        :to="{ name: 'PrivacyPolicy' }"
        class="hover:text-red pb-3 md:pb-0"
        >Polityka prywatności</router-link
      >
      <p>
        Florystyka {{ appVer }} &copy; 2020
        <a class="text-red pl-2" href="https://mazurkiewicz.dev"
          >Marcin Mazurkiewicz</a
        >
      </p>
    </footer>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, Ref } from "vue";
import PrivacyPolicyBanner from "@/components/privacy/PrivacyPolicyChecker.vue";
import TopNav from "@/components/TopNav.vue";

export default defineComponent({
  name: "App",
  components: {
    TopNav,
    PrivacyPolicyBanner
  },
  setup() {
    const appVer: Ref<string> = ref(process.env.VUE_APP_VERSION);
    return { appVer };
  }
});
</script>
<style>
html,
body,
#app {
  min-height: 100vh;
}
</style>
