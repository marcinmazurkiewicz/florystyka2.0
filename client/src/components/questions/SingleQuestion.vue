<template>
  <div class="w-full max-w-screen-lg mx-auto text-white">
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Szybkie pytanie</h1>
      <p class="text-sm text-center">Poćwicz pojedyncze pytania. Od razu poznasz prawidłową idpowiedź, co pozwoli na jej
        łatwiejsze zapamiętanie.</p>
    </header>
    <section>
      <p class="py-4 px-5 mt-5 mb-1 bg-dark-gray font-bold">Zasuszone metodą zielnikową liście Ginkgo biloba należy
        przechowywać w pomieszczeniu:</p>
      <answer-radio value="A" id="answerA" name="answer" v-model="answer">A. suchym, przewiewnym i w kartonach
      </answer-radio>
      <answer-radio value="B" id="answerB" name="answer" v-model="answer">B. lekko wilgotnym i w workach foliowych
      </answer-radio>
      <answer-radio value="C" id="answerC" name="answer" v-model="answer">C. chłodnym i wyłącznie na stojąco
      </answer-radio>
      <answer-radio value="D" id="answerD" name="answer" v-model="answer">D. ciemnym, wilgotnym i zawieszone
      </answer-radio>
      <span class="block py-2 px-5 mb-1 bg-dark-gray text-xs text-right ">Pyt. 439</span>
    </section>
    <button
        class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-green rounded hover:bg-green hover:text-white">
      Sprawdź
    </button>
  </div>
</template>
<script>
import AnswerRadio from "@/components/visual/AnswerRadio";

export default {
  components: {
    AnswerRadio
  },
  data() {
    return {
      answer: ''
    }
  }
}
</script>