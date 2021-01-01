<template>
  <div class="w-full h-full bg-deep-cyan font-lato overflow-y-auto">
    <Topbar/>
    <router-view class="min-h-4/5"/>
    <privacy-policy-banner/>
    <footer
        class="w-full bg-deep-cyan text-white flex flex-col md:flex-row md:justify-between text-sm px-6 mt-6 mx-0 py-4">
      <router-link :to="{name: 'PrivacyPolicy'}" class="hover:text-red pb-3 md:pb-0">Polityka prywatności</router-link>
      <p>Florystyka {{ appVer }} &copy; 2020 <a class="text-red pl-2" href="https://mazurkiewicz.dev">Marcin Mazurkiewicz</a>
      </p>
    </footer>
  </div>
</template>

<script>
import Topbar from '@/components/Topbar'
import PrivacyPolicyBanner from "@/components/privacy/PrivacyPolicyChecker";

export default {
  name: 'App',
  components: {
    Topbar,
    PrivacyPolicyBanner
  },
  data() {
    return {
      appVer: process.env.VUE_APP_VERSION
    }
  }
}
</script>
