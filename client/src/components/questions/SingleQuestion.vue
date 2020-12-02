<template>
  <div class="w-full max-w-screen-lg mx-auto text-white">
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Szybkie pytanie</h1>
      <p class="text-sm text-center pb-8">Poćwicz pojedyncze pytania. Od razu poznasz prawidłową idpowiedź,
        co pozwoli na jej łatwiejsze zapamiętanie.</p>
    </header>
    <question v-model="question"></question>
    <button @click="submitAnswer"
            class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-green rounded hover:bg-green hover:text-white">
      Sprawdź
    </button>
  </div>
</template>
<script>
import Question from "@/components/questions/visual/Question";

export default {
  name: 'SingleQuestion',
  components: {
    Question,
  },
  data() {
    return {
      question: {
        id: 439,
        content: 'Zasuszone metodą zielnikową liście Ginkgo biloba należy przechowywać w pomieszczeniu:',
        answerA: 'A. suchym, przewiewnym i w kartonach',
        answerB: 'B. lekko wilgotnym i w workach foliowych',
        answerC: 'C. chłodnym i wyłącznie na stojąco',
        answerD: 'D. ciemnym, wilgotnym i zawieszone',
        selectedAnswer: ''
      }
    }
  },
  methods: {
    submitAnswer() {
      console.log(this.question);
    }
  }
}
</script>