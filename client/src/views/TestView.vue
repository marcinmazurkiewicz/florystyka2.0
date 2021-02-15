<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <teleport v-if="isDataReturned" to="#countdown">
      <div>Czas do końca: {{ timer.minutes }} min {{ timer.seconds }} sek</div>
    </teleport>
    <header>
      <h1 class="text-center text-3xl py-6 text-red">
        Symulacja
        <span v-if="questions.length > 0"
          >- test {{ questions.length }} pytań</span
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
    <error-info v-if="isError">
      <span>{{ errorMsg }}</span>
    </error-info>
    <div v-if="isDataReturned">
      <span
        v-if="solved"
        :class="[passed ? 'bg-dark-green' : 'bg-red']"
        class="block w-full mt-8 p-3 text-dark-gray text-lg text-center font-semibold"
      >
        <span v-if="passed">Test zaliczony! </span>
        <span v-else>Test niezaliczony :( </span>
        Uzyskałeś {{ points }}/{{ total }} punktów ({{ percentScore }}%)
      </span>
      <question-view
        v-for="(question, index) in questions"
        :key="question.id"
        :question="question"
        :solution="solutions[question.id]"
        v-model="selectedAnswers[question.id].selectedAnswer"
        :number="index"
      ></question-view>
      <button
        v-if="!solved"
        @click="submitAnswer"
        class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded hover:bg-dark-green hover:text-white"
      >
        Sprawdź
      </button>
    </div>
  </div>
</template>
<script lang="ts">
import QuestionView from "@/components/questions/SingleQuestion.vue";
import ErrorInfo from "@/components/ErrorInfo.vue";
import { HTTP } from "@/http";
import { defineComponent, ref, Ref, computed, onMounted } from "vue";
import { Solution, Question, TestAnswer } from "@/types/QuestionTypes";
import {
  getErrorBasedOnResponse,
  getErrorBasedOnErrorType
} from "@/components/ErrorUtils";
import { ErrorType } from "@/types/ErrorTypes";

export default defineComponent({
  name: "Test",
  components: {
    QuestionView,
    ErrorInfo
  },
  setup() {
    const timer = ref({ minutes: 60, seconds: 1 });
    const isDataReturned = ref(false);
    const isError = ref(false);
    const errorMsg = ref("");
    const questions: Ref<Array<Question>> = ref([]);
    const selectedAnswers: Ref<Array<TestAnswer>> = ref([]);
    const points = ref(0);
    const total = ref(0);
    const solved = ref(false);
    const solutions: Ref<Array<Solution>> = ref([]);

    const countdown = (): void => {
      if (timer.value.minutes <= 0 && timer.value.seconds <= 0) {
        callback();
        return;
      }

      if (--timer.value.seconds < 0) {
        timer.value.seconds = 59;
        timer.value.minutes--;
      }

      if (timer.value.minutes >= 0 && timer.value.seconds >= 0) {
        setTimeout(() => {
          countdown();
        }, 1000);
      }
    };

    const percentScore = computed(() => {
      return (points.value / total.value) * 100;
    });

    const passed = computed(() => {
      return percentScore.value >= 70;
    });

    const submitAnswer = (): void => {
      HTTP.post(`/api/v3/solutions/test`, Object.values(selectedAnswers.value))
        .then(response => {
          solved.value = true;
          points.value = response.data.points;
          total.value = response.data.total;
          solutions.value = response.data.solutions;
        })
        .catch(e => {
          isError.value = true;
          errorMsg.value = getErrorBasedOnResponse(e.response.data);
        });
      window.scrollTo({ top: 0, behavior: "smooth" });
    };

    const prepareAnswersMap = (): void => {
      questions.value.forEach(question => {
        selectedAnswers.value[question.id] = {
          questionId: question.id,
          selectedAnswer: ""
        };
      });
    };

    const prepareSolutionsMap = (): void => {
      questions.value.forEach(question => {
        solutions.value[question.id] = { questionId: question.id };
      });
    };

    const getQuestions = () => {
      HTTP.get("/api/v3/questions/test")
        .then(response => {
          isDataReturned.value = true;
          isError.value = false;
          questions.value = response.data;
          prepareAnswersMap();
          prepareSolutionsMap();
          countdown();
        })
        .catch(e => {
          isDataReturned.value = false;
          isError.value = true;
          errorMsg.value = e.response?.data
            ? getErrorBasedOnResponse(e.response.data)
            : getErrorBasedOnErrorType(ErrorType.CONNECT_ERROR);
        });
    };

    onMounted(getQuestions);

    return {
      timer,
      questions,
      points,
      passed,
      total,
      isDataReturned,
      isError,
      solutions,
      selectedAnswers,
      solved,
      percentScore,
      submitAnswer,
      errorMsg
    };
  }
});
</script>
