<template>
  <div class="border rounded-xl border-dark-green p-1 md:p-6 mb-12 max-w-screen-md mx-auto">
    <h2 class="text-lg font-semibold text-center pb-4">Twoje obecne ustawienia:</h2>
    <div class="grid grid-cols-2 items-center">
      <p class="text-right pr-4 text-sm">Zgoda na używanie Google Analytics</p>
      <switch-checkbox v-model="googleAnalyticsCookies" id="googleAnalyticsCookies">
        <span v-if="getStatus()">zgadzam się</span>
        <span v-else>nie zgadzam się</span>
      </switch-checkbox>
    </div>
  </div>
</template>
<script>
import SwitchCheckbox from "@/components/visual/SwitchCheckbox";
import CookieUtils from "@/mixins/CookieUtils";

export default {
  name: "PrivacySettings",
  components: {
    SwitchCheckbox
  },
  mixins: [CookieUtils],
  data() {
    return {
      googleAnalyticsCookies: false
    }
  },
  methods: {
    getStatus() {
      return this.googleAnalyticsCookies;
    }
  },
  mounted() {
    this.googleAnalyticsCookies = this.getGoogleAnalyticsStatus();
  },
  watch: {
    googleAnalyticsCookies: function () {
      this.setGoogleAnalyticsStatus(this.googleAnalyticsCookies);
    }
  }
}
</script>