<template>
  <div
    class="border rounded-xl border-dark-green p-1 md:p-6 mb-12 max-w-screen-md mx-auto"
  >
    <h2 class="text-lg font-semibold text-center pb-4">
      Twoje obecne ustawienia:
    </h2>
    <div class="grid grid-cols-2 items-center">
      <p class="text-right pr-4 text-sm">Zgoda na używanie Google Analytics</p>
      <switch-checkbox
        v-model="googleAnalyticsCookies"
        id="googleAnalyticsCookies"
      >
        <span v-if="getStatus()">zgadzam się</span>
        <span v-else>nie zgadzam się</span>
      </switch-checkbox>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, ref, onMounted, watch } from "vue";
import SwitchCheckbox from "@/components/custom_inputs/SwitchCheckbox.vue";
import usePrivacyCookies from "@/composables/usePrivacyCookies";

export default defineComponent({
  name: "PrivacySettings",
  components: {
    SwitchCheckbox
  },
  setup() {
    const googleAnalyticsCookies = ref(false);

    const getStatus = (): boolean => {
      return googleAnalyticsCookies.value;
    };

    const {
      getGoogleAnalyticsStatus,
      setGoogleAnalyticsStatus
    } = usePrivacyCookies();

    watch(googleAnalyticsCookies, newValue =>
      setGoogleAnalyticsStatus(newValue)
    );

    onMounted(
      () => (googleAnalyticsCookies.value = getGoogleAnalyticsStatus())
    );

    return {
      googleAnalyticsCookies,
      getStatus
    };
  }
});
</script>
