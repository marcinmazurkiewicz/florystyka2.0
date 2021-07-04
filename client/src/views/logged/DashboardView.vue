<template>
  <div class=" grid grid-cols-dashboard">
    <nav id="admin-nav" class="bg-light-green">
      <ol>
        <li>
          <router-link
            :to="{ name: 'AdminQuestionsView' }"
            class="block py-3 px-2 md:px-4 md:py-4 md:py4hover:bg-black-01 cursor-pointer h-full flex items-stretch content-center"
            active-class="bg-dark-green"
            >Pytania</router-link
          >
        </li>
        <li><a href="#">Pytania</a></li>
        <li><a href="#">Użytkownicy</a></li>
        <li><a href="#">Ustawienia</a></li>
        <li><a href="#" @click="logout">Wyloguj</a></li>
      </ol>
    </nav>
    <div class="w-full m-auto text-white">
      <router-view></router-view>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, ref, Ref } from "vue";

export default defineComponent({
  name: "Dashboard",

  setup() {
    const isDataReturned: Ref<boolean> = ref(true);
    const questionNumber: Ref<number> = ref(0);
    const earliestQuestionYear: Ref<number> = ref(0);
    const latestQuestionYear: Ref<number> = ref(0);
    return {
      isDataReturned,
      questionNumber,
      earliestQuestionYear,
      latestQuestionYear
    };
  }
});
</script>
