<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <teleport v-if="isDataReturned" to="#countdown">
      <div>Czas do końca: {{ timer.minutes }} min {{ timer.seconds }} sek</div>
    </teleport>
    <header>
      <h1 class="text-center text-3xl py-6 text-red">
        Symulacja - test {{ questions.length }} pytań
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
    <connect-error-info v-if="isConnectError" />
  </div>
</template>
<script lang="ts">
import QuestionView from "@/components/questions/SingleQuestion.vue";
import ConnectErrorInfo from "@/components/ConnectErrorInfo.vue";
import { HTTP } from "@/http";
import { defineComponent } from "vue";
import { Solution, Question, TestAnswer } from "@/types/QuestionTypes";

export default defineComponent({
  name: "Test",
  components: {
    QuestionView,
    ConnectErrorInfo
  },
  data() {
    return {
      timer: {
        minutes: 60,
        seconds: 1
      },
      isDataReturned: false,
      isConnectError: false,
      questions: [] as Array<Question>,
      selectedAnswers: {} as Array<TestAnswer>,
      points: 0,
      total: 0,
      solved: false,
      solutions: {} as Array<Solution>
    };
  },
  computed: {
    percentScore(): number {
      return (this.points / this.total) * 100;
    },
    passed(): boolean {
      return this.percentScore >= 70;
    }
  },
  methods: {
    submitAnswer(): void {
      HTTP.post(
        `/api/v3/solutions/test`,
        Object.values(this.selectedAnswers)
      ).then(response => {
        this.solved = true;
        this.points = response.data.points;
        this.total = response.data.total;
        this.solutions = response.data.solutions;
      });
      window.scrollTo({ top: 0, behavior: "smooth" });
    },
    countdown(): void {
      if (this.timer.minutes <= 0 && this.timer.seconds <= 0) {
        this.submitAnswer();
        return;
      }

      if (--this.timer.seconds < 0) {
        this.timer.seconds = 59;
        this.timer.minutes--;
      }

      if (this.timer.minutes >= 0 && this.timer.seconds >= 0) {
        setTimeout(() => {
          this.countdown();
        }, 1000);
      }
    },
    prepareAnswersMap(): void {
      this.questions.forEach(question => {
        this.selectedAnswers[question.id] = {
          questionId: question.id,
          selectedAnswer: ""
        };
      });
    },
    prepareSolutionsMap(): void {
      this.questions.forEach(question => {
        this.solutions[question.id] = { questionId: question.id };
      });
    }
  },
  mounted() {
    HTTP.get("/api/v3/questions/test")
      .then(response => {
        this.isDataReturned = true;
        this.isConnectError = false;
        this.questions = response.data;
        this.prepareAnswersMap();
        this.prepareSolutionsMap();
        this.countdown();
      })
      .catch(() => {
        this.isDataReturned = false;
        this.isConnectError = true;
      });
  }
});
</script>
