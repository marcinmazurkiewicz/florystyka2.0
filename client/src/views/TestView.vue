<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <teleport v-if="responseStatus.isDataReturned" to="#countdown">
      <div v-if="test?.timer">
        Czas do końca: {{ test.timer.minutes }} min {{ test.timer.seconds }} sek
      </div>
    </teleport>
    <header>
      <h1 class="text-center text-3xl py-6 text-red">
        Symulacja
        <span v-if="test?.questions?.length">
          - test {{ test.questions.length }} pytań</span
        >
      </h1>
      <p class="text-sm text-center pb-6">
        Sprawdź się w dokładnie takim samym trybie, jaki obowiązuje podczas
        rzeczywistego egzaminu.
      </p>
      <p class="text-sm text-center pb-8">
        Na rozwiązanie całej części pisemnej otrzymujesz maksymalnie 60 minut.
        To całkiem sporo czasu, więc zachowaj spokój :)
      </p>
    </header>
    <view-wrapper :response-status="responseStatus">
      <span
        v-if="solved"
        :class="[passed ? 'bg-dark-green' : 'bg-red']"
        class="block w-full mt-8 p-3 text-dark-gray text-lg text-center font-semibold"
      >
        <span v-if="passed">Test zaliczony! </span>
        <span v-else>Test niezaliczony :( </span>
        Uzyskałeś {{ points }}/{{ total }} punktów ({{ percentScore }}%)
      </span>
      <div v-if="test != null">
        <question-view
          v-for="(question, index) in test.questions"
          :key="question.id"
          :questionUnit="question"
          :number="index"
          @selected="select(question, $event)"
        ></question-view>
      </div>
      <button
        v-if="!solved"
        @click="submitAnswer"
        class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded hover:bg-dark-green hover:text-white"
      >
        Sprawdź
      </button>
    </view-wrapper>
  </div>
</template>
<script lang="ts">
import QuestionView from "@/components/questions/Question.vue";
import ViewWrapper from "@/components/ViewWrapper.vue";
import { defineComponent, computed, onMounted, watch } from "vue";
import { countdownInterval } from "@/composables/useCountdown";
import { useTest } from "@/composables/useQuestions";
import { isTest, QuestionUnit, UserChoice } from "@/types/QuestionTypes";

export default defineComponent({
  name: "Test",
  components: {
    QuestionView,
    ViewWrapper
  },
  setup() {
    let countdown = 0;
    const {
      points,
      total,
      test,
      responseStatus,
      solved,
      getQuestions,
      sendAnswers
    } = useTest();

    const percentScore = computed(() => {
      return (points.value / total.value) * 100;
    });

    const passed = computed(() => {
      return percentScore.value >= 70;
    });

    const select = (question: QuestionUnit, selectedAnswer: UserChoice) => {
      question.selectedAnswer = selectedAnswer;
    };

    const submitAnswer = (): void => {
      clearInterval(countdown);
      if (isTest(test.value)) {
        sendAnswers(test.value.questions);
        window.scrollTo({ top: 0, behavior: "smooth" });
      }
    };

    watch(test, () => {
      if (isTest(test.value)) {
        countdown = countdownInterval(test.value.timer, submitAnswer);
      }
    });

    onMounted(() => {
      getQuestions();
    });

    return {
      test,
      points,
      passed,
      total,
      solved,
      percentScore,
      submitAnswer,
      select,
      responseStatus
    };
  }
});
</script>
