<template>
  <div>
    <div
      id="g-recaptcha"
      class="w-full flex justify-center"
      :data-sitekey="recaptchaSiteKey"
    ></div>
    <span class="inline-block w-1/2 text-right text-sm text-red" v-if="error">
      {{ error }}
    </span>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";

declare global {
  interface Window {
    grecaptcha: any;
  }
}

export default defineComponent({
  name: "Recaptcha",
  props: {
    error: String
  },
  emits: ["verify"],
  setup(props, { emit }) {
    const recaptchaSiteKey = ref(process.env.VUE_APP_RECAPTCHA_SITEKEY);
    const widgetId = ref(0);

    const reset = function() {
      window.grecaptcha.reset(widgetId.value);
    };

    function loadRecaptcha(callback: () => void) {
      if (window && !window.grecaptcha) {
        const recaptchaScript = document.createElement("script");
        document.head.appendChild(recaptchaScript);
        recaptchaScript.onload = () => {
          window.grecaptcha.ready(() => {
            callback();
          });
        };
        recaptchaScript.setAttribute(
          "src",
          "https://www.google.com/recaptcha/api.js"
        );
      } else {
        callback();
      }
    }

    function render() {
      const recaptcha = window.grecaptcha;
      widgetId.value = recaptcha.render("g-recaptcha", {
        sitekey: recaptchaSiteKey.value,
        callback: () => {
          const result: string = recaptcha.getResponse(widgetId.value);
          emit("verify", result);
        },
        "expired-callback": () => reset()
      });
    }

    onMounted(() => loadRecaptcha(() => render()));

    return {
      recaptchaSiteKey,
      reset
    };
  }
});
</script>
