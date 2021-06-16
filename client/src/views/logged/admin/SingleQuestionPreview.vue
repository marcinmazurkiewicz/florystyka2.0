<template>
  <div class="mb-4 w-full h-full box-border lg:px-4 xl:px-10">
    <div class=" w-full table p-4 box-border lg:my-10 xl:p-8 text-white">
      <section v-if="question">
        <h4 class="text-center text-red text-3xl">Pytanie {{ question.id }}</h4>
        <p class="py-4 px-5 mt-5 mb-1 bg-dark-gray text-right"
           style="word-spacing: 0.25rem"
           v-if="question.year">Egzamin: {{ question.month }}.{{ question.year }}</p>
        <p class="py-4 px-5 mt-5 mb-1 bg-dark-gray font-bold">
          <span v-html="question.content"></span>
          <img v-if="question.image" :src="imgUrl" class="pt-4 mx-auto" alt="obrazek do pytania"/>
        </p>
        <div class="block w-full py-3 px-5 mb-1 bg-light-gray flex justify-between items-center">
          <p>A. <span v-html="question.answerA"></span></p>
        </div>
        <div class="block w-full py-3 px-5 mb-1 bg-light-gray flex justify-between items-center">
          <p>B. <span v-html="question.answerB"></span></p>
        </div>
        <div class="block w-full py-3 px-5 mb-1 bg-light-gray flex justify-between items-center">
          <p>C. <span v-html="question.answerC"></span></p>
        </div>
        <div class="block w-full py-3 px-5 mb-1 bg-light-gray flex justify-between items-center">
          <p>D. <span v-html="question.answerD"></span></p>
        </div>
        <p class="py-4 px-5 mt-5 mb-1 bg-light-green text-black font-bold">
          Prawidłowa odpowiedź: {{ question.correct }}
        </p>

      </section>
    </div>
  </div>
</template>
<script lang="ts">
import {useRoute} from "vue-router";
import {computed, defineComponent, onMounted, Ref, ref} from "vue";
import {ResponseStatus} from "@/types/ResponseStatus";
import {useQuestionsAsAdmin} from "@/composables/useQuestions";
import {AdminQuestion} from "@/types/QuestionTypes";

export default defineComponent({
  name: 'AdminQuestionView',
  setup() {
    const route = useRoute();
    const questionId = typeof route.params.questionId === 'string' ? parseInt(route.params.questionId): 0;
    const {getQuestionPreview} = useQuestionsAsAdmin();

    const question: Ref<AdminQuestion | null> = ref(null);
    const responseStatus: Ref<ResponseStatus> = ref(ResponseStatus.pending());
    onMounted(() => {
      getQuestionPreview(questionId)
          .then(response => {
            responseStatus.value = response.responseStatus;
            if (response.data) {
              question.value = response.data;
            }
          })
          .catch(errorStatus => {
            responseStatus.value = errorStatus;
          })
    });

    const imgUrl = computed(() => {
      return question.value?.image ? `${process.env.VUE_APP_API_URL}${question.value.image}` : "";
    });

    return {question, imgUrl, responseStatus}
  }
})
</script>
