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
      <student-menu v-if="isLoggedUser()" />
      <default-menu v-if="!isLoggedUser()" />
    </nav>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";
import { isLoggedUser } from "@/utils/authUtils";
import DefaultMenu from "@/components/DefaultMenu.vue";
import StudentMenu from "@/components/StudentMenu.vue";

export default defineComponent({
  name: "TopNav",
  components: {
    DefaultMenu,
    StudentMenu
  },
  setup() {
    return { isLoggedUser };
  }
});
</script>
