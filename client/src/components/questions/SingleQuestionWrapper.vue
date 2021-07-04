<template>
  <view-wrapper :response-status="responseStatus">
    <span
      v-if="solved"
      :class="[correct ? 'bg-dark-green' : 'bg-red']"
      class="block w-full mt-8 p-3 text-dark-gray text-lg text-center font-semibold"
    >
      <span v-if="correct">Dobrze :)</span>
      <span v-else>Błędna odpowiedź :(</span>
    </span>
    <question :questionUnit="question" @selected="select(question, $event)" />
    <button
      v-if="!solved"
      @click="checkAnswer"
      class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded-xl hover:bg-dark-green hover:text-white"
    >
      Sprawdź
    </button>

    <button
      v-if="solved && allowNext"
      @click="getRandomQuestion"
      class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded-xl hover:bg-dark-green hover:text-white"
    >
      Następne pytanie
    </button>
  </view-wrapper>
</template>
<script lang="ts">
import { defineComponent, computed, onMounted } from "vue";
import Question from "@/components/questions/Question.vue";
import { useQuestion } from "@/composables/useQuestions";
import ViewWrapper from "@/components/ViewWrapper.vue";
import { QuestionUnit, UserChoice } from "@/types/QuestionTypes";

export default defineComponent({
  name: "SingleQuestionWrapper",
  props: {
    questionId: {
      type: String,
      required: false
    },
    allowNext: {
      type: Boolean,
      default: false
    }
  },
  emits: ["solved"],
  components: {
    Question,
    ViewWrapper
  },
  setup(props) {
    const {
      question,
      responseStatus,
      getRandomQuestion,
      getSingleQuestion,
      solved,
      submitAnswer,
      selectedAnswer
    } = useQuestion();

    const select = (question: QuestionUnit, selectedAnswer: UserChoice) => {
      question.selectedAnswer = selectedAnswer;
    };

    const correct = computed(
      () => question.value?.selectedAnswer === question.value?.correctAnswer
    );

    const checkAnswer = () => {
      if (question.value != null) {
        submitAnswer(question.value);
      }
    };

    onMounted(() => {
      props.questionId == null
        ? getRandomQuestion()
        : getSingleQuestion(props.questionId);
    });

    return {
      question,
      responseStatus,
      solved,
      selectedAnswer,
      correct,
      checkAnswer,
      select,
      getRandomQuestion
    };
  }
});
</script>
