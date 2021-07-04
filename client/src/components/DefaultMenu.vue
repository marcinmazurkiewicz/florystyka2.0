<template>
  <ul class="uppercase h-full">
    <li class="inline-block h-full">
      <router-link
        :to="{ name: 'Home' }"
        class="block py-3 px-2 md:px-4 md:py-4 md:py4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
        exact-active-class="bg-dark-green"
      >
        <span class="block self-center">Start</span>
      </router-link>
    </li>
    <li class="inline-block h-full">
      <router-link
        :to="{ name: 'Test' }"
        class="block py-3 px-2 md:px-4 md:py-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
        exact-active-class="bg-dark-green"
      >
        <span class="block self-center">Test</span>
      </router-link>
    </li>
    <li class="inline-block h-full">
      <router-link
        :to="{ name: 'RandomQuestion' }"
        class="block py-3 px-2 md:px-4 md:py-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
        exact-active-class="bg-dark-green"
      >
        <span class="block self-center">Szybko</span>
      </router-link>
    </li>
    <li class="inline-block h-full">
      <router-link
        :to="{ name: 'Pdf' }"
        class="block py-3 px-2 md:px-4 md:py-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
        exact-active-class="bg-dark-green"
      >
        <span class="block self-center">Pdf</span>
      </router-link>
    </li>
    <li class="inline-block h-full">
      <router-link
        :to="{ name: 'Info' }"
        class="block py-3 px-2 md:px-4 md:py-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
        exact-active-class="bg-dark-green"
      >
        <span class="block self-center">Info</span>
      </router-link>
    </li>
    <li class="inline-block h-full">
      <router-link
        :to="{ name: 'Login' }"
        class="block py-3 px-2 md:px-4 md:py-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
        exact-active-class="bg-dark-green"
      >
        <span class="block self-center">Zaloguj</span>
      </router-link>
    </li>
  </ul>
</template>
<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "DefaultMenu"
});
</script>
