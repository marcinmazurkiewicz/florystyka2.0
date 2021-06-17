<template>
  <div
    class="absolute top-0 bg-dark-green p-6 w-full font-medium text-sm text-center leading-6 border-b-2 border-light-green"
    :style="closed ? 'display: none' : 'display: block'"
  >
    <p>
      Strona wykorzystuje pliki cookie do analizowania korzystania z witryny.
    </p>
    <p>
      Kliknięcie w przycisk poniżej pozwala na wyrażenie zgody na przetwarzanie
      danych, zgodnie z polityką prywatności. W każdej chwili można wycofać
      zgodę.
    </p>
    <p>
      W
      <router-link
        :to="{ name: 'PrivacyPolicy' }"
        class="font-semibold underline"
        >polityce prywatności
      </router-link>
      znajduje się więcej informacji.
    </p>
    <a
      class="inline-block px-4 pt-1 pb-2 mt-4 border rounded-xl bg-dark-gray border-dark-gray text-white cursor-pointer mr-4"
      @click="accept(true)"
      >Akceptuję</a
    >
    <a
      class="inline-block px-4 pt-1 pb-2 mt-4 border rounded-xl border-dark-gray cursor-pointer"
      @click="accept(false)"
      >Zamknij</a
    >
  </div>
</template>
<script lang="ts">
import { defineComponent, ref, onBeforeMount } from "vue";
import usePrivacyCookies from "@/composables/usePrivacyCookies.ts";

export default defineComponent({
  name: "PrivacyPolicy",
  setup() {
    const {
      setGoogleAnalyticsStatus,
      checkIsGoogleAnalyticsCookieIsAvailable,
      presetGoogleAnalyticsGtag
    } = usePrivacyCookies();

    const closed = ref(false);

    const close = (): void => {
      closed.value = true;
    };

    const accept = (isAccept: boolean): void => {
      setGoogleAnalyticsStatus(isAccept);
      close();
    };

    const preset = (): void => {
      if (checkIsGoogleAnalyticsCookieIsAvailable()) {
        presetGoogleAnalyticsGtag();
        close();
      }
    };

    onBeforeMount(preset);

    return {
      accept,
      closed
    };
  },
  methods: {}
});
</script>
