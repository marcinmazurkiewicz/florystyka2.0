<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Szybkie pytanie</h1>
      <p class="text-sm text-center pb-8">
        Poćwicz pojedyncze pytania. Od razu poznasz prawidłową idpowiedź, co
        pozwoli na jej łatwiejsze zapamiętanie.
      </p>
    </header>
    <single-question-wrapper :question-id="questionId" />
  </div>
</template>
<script lang="ts">
import SingleQuestionWrapper from "@/components/questions/SingleQuestionWrapper.vue";
import { defineComponent } from "vue";
import { useRoute } from "vue-router";

export default defineComponent({
  name: "SingleQuestion",
  components: {
    SingleQuestionWrapper
  },
  setup() {
    const route = useRoute();
    return { questionId: route.params.questionId };
  }
});
</script>
