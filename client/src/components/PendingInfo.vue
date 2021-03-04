<template>
  <p class="text-center border border-white rounded-xl p-6 text-white text-lg">
    <slot>Trwa ładowanie...</slot>
  </p>
</template>
<script>
import { defineComponent } from "vue";

export default defineComponent({
  name: "PendingInfo"
});
</script>
