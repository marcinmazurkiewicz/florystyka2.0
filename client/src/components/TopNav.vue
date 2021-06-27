<template>
  <div
    class="w-full sticky top-0 bg-light-green flex flex-col-reverse md:flex-row justify-between items-stretch border-b-2 border-dark-green"
  >
    <p
      v-if="this.$route.path === '/test'"
      class="p-4 pl-6 text-xl"
      id="countdown"
    ></p>
    <p
      class="hidden md:block p-4 pl-6 text-xl"
      v-if="this.$route.path !== '/test'"
    >
      Testy florystyczne
    </p>
    <nav class="self-stretch max-w-screen-lg text-center md:text-right md:pr-6">
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
        <li v-if="isLoggedUser()" class="inline-block h-full">
          <div
            class="block py-3 px-2 md:px-4 md:py-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
            @click="logoutUser"
          >
            <span class="block self-center">Wyloguj</span>
          </div>
        </li>
        <li v-else class="inline-block h-full">
          <router-link
            :to="{ name: 'Login' }"
            class="block py-3 px-2 md:px-4 md:py-4 hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
            exact-active-class="bg-dark-green"
          >
            <span class="block self-center">Zaloguj</span>
          </router-link>
        </li>
      </ul>
    </nav>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import { isLoggedUser, logout } from "@/utils/authUtils";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "TopNav",
  setup() {
    const router = useRouter();

    const logoutUser = function() {
      logout().then(() => router.push({ name: "Home" }));
    };

    return { isLoggedUser, logoutUser };
  }
});
</script>
