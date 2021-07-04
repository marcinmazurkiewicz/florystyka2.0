<template>
  <main
    class="w-full max-w-screen-lg m-auto my-6 text-white border rounded-lg border-gray-700 bg-dark-gray"
  >
    <section>
      <header
        class="text-center text-4xl text-red pt-16 pb-8 px-3 md:px-6 tracking-wider leading-relaxed"
      >
        Witaj {{ username }}
      </header>

      <div class="py-6 w-full grid grid-col-1 md:grid-cols-2">
        <div class="text-center py-6 mx-12 border rounded-lg border-dark-green">
          <h5 class="text-red text-2xl">Rozwiązane testy</h5>
          <i class="material-icons text-7xl text-light-green py-6">receipt</i>
          <p class="text-center">
            Historia rozwiązywanych testów.
          </p>
        </div>
        <div class="text-center p-6">
          <h5 class="text-red text-2xl">Rozwiązane szybkie pytania</h5>
          <i class="material-icons text-7xl text-light-green py-6">av_timer</i>
          <p class="text-center">
            Historia szybkich pytań.
          </p>
        </div>
      </div>
    </section>
  </main>
</template>
<script lang="ts">
import { defineComponent, ref, Ref } from "vue";
import { getUsername } from "@/utils/authUtils";

export default defineComponent({
  name: "Dashboard",

  setup() {
    const isDataReturned: Ref<boolean> = ref(true);
    const questionNumber: Ref<number> = ref(0);
    const earliestQuestionYear: Ref<number> = ref(0);
    const latestQuestionYear: Ref<number> = ref(0);
    const username: Ref<string> = ref(getUsername());

    return {
      isDataReturned,
      questionNumber,
      earliestQuestionYear,
      latestQuestionYear,
      username
    };
  }
});
</script>
