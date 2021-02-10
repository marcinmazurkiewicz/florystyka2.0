<template>
  <div class="w-full max-w-screen-lg mx-auto text-white p-4">
    <header>
      <h1 class="text-center text-3xl py-6 text-red">Szybkie pytanie</h1>
      <p class="text-sm text-center pb-8">Poćwicz pojedyncze pytania. Od razu poznasz prawidłową idpowiedź,
        co pozwoli na jej łatwiejsze zapamiętanie.</p>
    </header>
    <single-question-wrapper v-if="isDataReturned" :question="question"></single-question-wrapper>
    <connect-error-info v-if="isConnectError">
      <span v-if="is404">Pytanie nr {{ id }} nie istnieje</span>
      <span v-else>Nie udało się nawiązać połączenia z serwerem.</span>
    </connect-error-info>
  </div>
</template>
<script>
import SingleQuestionWrapper from "@/components/questions/SingleQuestionWrapper";
import ConnectErrorInfo from "@/components/visual/ConnectErrorInfo";
import {HTTP} from '@/http';
import {useRoute} from "vue-router";

export default {
  name: 'RandomQuestion',
  components: {
    SingleQuestionWrapper,
    ConnectErrorInfo
  },
  data() {
    return {
      isDataReturned: false,
      isConnectError: false,
      is404: false,
      id: 0,
      question: {},
    }
  },
  methods: {
    getQuestion(id) {
      HTTP.get(`/api/v3/questions/${id}`)
          .then((response) => {
            this.isDataReturned = true;
            this.isConnectError = false;
            this.question = response.data;
          })
          .catch((error) => {
            if (error.response) {
              this.is404 = error.response.status === 404;
            }
            this.isDataReturned = false;
            this.isConnectError = true;
          });
    }
  },
  mounted() {
    const {
      params: {questionId}
    } = useRoute();
    this.id = questionId;
    this.getQuestion(questionId);
  }
}
</script>