<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <teleport to="#countdown">
      <div> Czas do końca: {{ timer.minutes }} min {{ timer.seconds }} sek</div>
    </teleport>
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Symulacja - test 40 pytań</h1>
      <p class="text-sm text-center pb-6">Sprawdź się w dokładnie takim samym trybie, jaki obowiązuje podczas
        rzeczywistego egzaminu.</p>
      <p class="text-sm text-center pb-8">Na rozwiązanie całej części pisemnej otrzymujesz maksymalnie 60 minut.
        To całkiem sporo czasu, więc zachowaj spokój :)</p>
    </header>
    <question-view v-for="(question, index) in questions" :key="question.id" :question="question" :solution="solution"
                   v-model="selectedAnswers[question.id]"
                   :number="index"></question-view>
    <button @click="submitAnswer"
            class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded hover:bg-dark-green hover:text-white">
      Sprawdź
    </button>
  </div>
</template>
<script>
import QuestionView from "@/components/questions/visual/QuestionView";
import {HTTP} from "@/http";

export default {
  name: 'Test',
  components: {
    QuestionView,
  },
  data() {
    return {
      timer: {
        minutes: 60,
        seconds: 1
      },
      questions: [],
      selectedAnswers: {},
      solution: {}
    }
  },
  methods: {
    submitAnswer() {
      console.log(this.selectedAnswers);
    },
    countdown() {
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
    prepareAnswersMap() {
      this.questions.forEach(question => {
        this.selectedAnswers[question.id] = '';
      })
    }
  },
  mounted() {
    HTTP.get('api/v3/questions/test')
        .then((response) => {
          this.questions = response.data;
          this.prepareAnswersMap();
          this.countdown();
        });
  }
}
</script>