<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Szybkie pytanie</h1>
      <p class="text-sm text-center pb-8">Poćwicz pojedyncze pytania. Od razu poznasz prawidłową idpowiedź,
        co pozwoli na jej łatwiejsze zapamiętanie.</p>
    </header>
    <single-question-wrapper :question="question"></single-question-wrapper>
  </div>
</template>
<script>
import SingleQuestionWrapper from "@/components/questions/SingleQuestionWrapper";
import {HTTP} from '@/http';
import {useRoute} from "vue-router";

export default {
  name: 'RandomQuestion',
  components: {
    SingleQuestionWrapper,
  },
  data() {
    return {
      question: {},
    }
  },
  methods: {
    getQuestion(id) {
      HTTP.get(`/api/v3/questions/${id}`)
          .then((response) => {
            this.question = response.data;
          });
    }
  },
  mounted() {
    const {
      params: {questionId}
    } = useRoute();
    this.getQuestion(questionId);
  }
}
</script>