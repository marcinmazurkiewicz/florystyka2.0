<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Szybkie pytanie</h1>
      <p class="text-sm text-center pb-8">Poćwicz pojedyncze pytania. Od razu poznasz prawidłową idpowiedź,
        co pozwoli na jej łatwiejsze zapamiętanie.</p>
    </header>
    <question :question="question" v-model="selectedAnswer"></question>
    <button @click="submitAnswer"
            class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded hover:bg-dark-green hover:text-white">
      Sprawdź
    </button>
  </div>
</template>
<script>
import Question from "@/components/questions/visual/Question";
import {HTTP} from '@/http';

export default {
  name: 'RandomQuestion',
  components: {
    Question,
  },
  data() {
    return {
      question: {},
      selectedAnswer: ''
    }
  },
  methods: {
    submitAnswer() {
      console.log(this.selectedAnswer);
    }
  },
  mounted() {
    HTTP.get('api/v3/questions/random')
        .then((response) => {
          this.question = response.data;
        });
  }
}
</script>