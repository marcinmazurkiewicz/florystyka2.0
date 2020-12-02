<template>
  <div class="w-full max-w-screen-lg mx-auto text-white">
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
    <question v-for="(question, index) in questions" :key="question.id" v-model="questions[index]"
              :number="index"></question>
    <button @click="submitAnswer"
            class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-green rounded hover:bg-green hover:text-white">
      Sprawdź
    </button>
  </div>
</template>
<script>
import Question from "@/components/questions/visual/Question";

export default {
  name: 'Test',
  components: {
    Question,
  },
  data() {
    return {
      timer: {
        minutes: 60,
        seconds: 1
      },
      questions: [
        {
          id: 439,
          content: 'Zasuszone metodą zielnikową liście Ginkgo biloba należy przechowywać w pomieszczeniu:',
          answerA: 'A. suchym, przewiewnym i w kartonach',
          answerB: 'B. lekko wilgotnym i w workach foliowych',
          answerC: 'C. chłodnym i wyłącznie na stojąco',
          answerD: 'D. ciemnym, wilgotnym i zawieszone',
          selectedAnswer: ''
        },
        {
          id: 440,
          content: 'Zasuszone metodą zielnikową liście Ginkgo biloba należy przechowywać w pomieszczeniu:',
          answerA: 'A. suchym, przewiewnym i w kartonach',
          answerB: 'B. lekko wilgotnym i w workach foliowych',
          answerC: 'C. chłodnym i wyłącznie na stojąco',
          answerD: 'D. ciemnym, wilgotnym i zawieszone',
          selectedAnswer: ''
        },
        {
          id: 441,
          content: 'Zasuszone metodą zielnikową liście Ginkgo biloba należy przechowywać w pomieszczeniu:',
          answerA: 'A. suchym, przewiewnym i w kartonach',
          answerB: 'B. lekko wilgotnym i w workach foliowych',
          answerC: 'C. chłodnym i wyłącznie na stojąco',
          answerD: 'D. ciemnym, wilgotnym i zawieszone',
          selectedAnswer: ''
        },
        {
          id: 442,
          content: 'Zasuszone metodą zielnikową liście Ginkgo biloba należy przechowywać w pomieszczeniu:',
          answerA: 'A. suchym, przewiewnym i w kartonach',
          answerB: 'B. lekko wilgotnym i w workach foliowych',
          answerC: 'C. chłodnym i wyłącznie na stojąco',
          answerD: 'D. ciemnym, wilgotnym i zawieszone',
          selectedAnswer: ''
        },
        {
          id: 443,
          content: 'Zasuszone metodą zielnikową liście Ginkgo biloba należy przechowywać w pomieszczeniu:',
          answerA: 'A. suchym, przewiewnym i w kartonach',
          answerB: 'B. lekko wilgotnym i w workach foliowych',
          answerC: 'C. chłodnym i wyłącznie na stojąco',
          answerD: 'D. ciemnym, wilgotnym i zawieszone',
          selectedAnswer: ''
        }
      ]
    }
  },
  methods: {
    submitAnswer() {
      console.log(this.questions);
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
    }
  },
  mounted() {
    this.countdown();
  }
}
</script>