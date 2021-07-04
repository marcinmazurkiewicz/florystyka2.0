<template>
  <ul class="uppercase h-full">
    <!--    <li class="inline-block h-full">-->
    <!--      <router-link-->
    <!--        :to="{ name: 'Home' }"-->
    <!--        class="block py-3 px-2 md:px-4 md:py-4 md:py4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"-->
    <!--        exact-active-class="bg-dark-green"-->
    <!--      >-->
    <!--        <span class="block self-center">Start</span>-->
    <!--      </router-link>-->
    <!--    </li>-->
    <li class="inline-block h-full">
      <router-link
        :to="{ name: 'StudentTest' }"
        class="block py-3 px-2 md:px-4 md:py-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
        exact-active-class="bg-dark-green"
      >
        <span class="block self-center">Test</span>
      </router-link>
    </li>
    <li class="inline-block h-full">
      <router-link
        :to="{ name: 'StudentRandomQuestion' }"
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
      <div
        class="block py-3 px-2 md:px-4 md:py-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
        @click="logoutUser"
      >
        <span class="block self-center">Wyloguj</span>
      </div>
    </li>
  </ul>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import { useRouter } from "vue-router";
import { logout } from "@/utils/authUtils";

export default defineComponent({
  name: "StudentMenu",
  setup() {
    const router = useRouter();

    const logoutUser = function() {
      logout().then(() => router.push({ name: "Home" }));
    };

    return { logoutUser };
  }
});
</script>
