<template>
  <div class="mb-4 w-full h-full max-w-screen-lg mx-auto box-border lg:px-4 xl:px-10">
    <div class=" w-full table p-4 box-border lg:my-10 xl:p-8">
      <h4 class="text-3xl text-red pb-8 text-center font-semibold">Dodaj nowe pytanie</h4>

      <section class="border p-6 rounded-xl my-4 border-light-gray">

        <text-area-input type="textarea" v-model="content" id="content" :error="errors['content']">Treść pytania
        </text-area-input>

        <file-input id="image"
                    accept="image/png, image/jpeg"
                    :error="errors['image']"
                    :required="false"
                    @handle-file="handleFile">
          Obrazek do pytania
        </file-input>
      </section>
      <section class="border p-6 rounded-xl my-4" :class="isCorrect('A') ? 'border-light-green' : 'border-light-gray'">
        <text-input v-model="answerA" id="answerA" :error="errors['answerA']">Odpowiedź A</text-input>
        <switch-radio value="A" id="correctA" :name='correct'
                      v-model="correct" :error="errors['correct']">
          <span v-if="isCorrect('A')">prawidłowa odpowiedź</span>
          <span v-else>nieprawidłowa odpowiedź</span>
        </switch-radio>
      </section>

      <section class="border p-6 rounded-xl my-4" :class="isCorrect('B') ? 'border-light-green' : 'border-light-gray'">
        <text-input v-model="answerB" id="answerB" :error="errors['answerB']">Odpowiedź B</text-input>
        <switch-radio value="B" id="correctB" :name='correct'
                      v-model="correct" :error="errors['correct']">
          <span v-if="isCorrect('B')">prawidłowa odpowiedź</span>
          <span v-else>nieprawidłowa odpowiedź</span>
        </switch-radio>
      </section>
      <section class="border p-6 rounded-xl my-4"
               :class="isCorrect('C') ? 'border-light-green' : 'border-light-gray'">
        <text-input v-model="answerC" id="answerC" :error="errors['answerC']">Odpowiedź C</text-input>
        <switch-radio value="C" id="correctC" :name='correct'
                      v-model="correct" :error="errors['correct']">
          <span v-if="isCorrect('C')">prawidłowa odpowiedź</span>
          <span v-else>nieprawidłowa odpowiedź</span>
        </switch-radio>
      </section>
      <section class="border p-6 rounded-xl my-4"
               :class="isCorrect('D') ? 'border-light-green' : 'border-light-gray'">
        <text-input v-model="answerD" id="answerD" :error="errors['answerD']">Odpowiedź D</text-input>
        <switch-radio value="D" id="correctD" :name='correct'
                      v-model="correct" :error="errors['correct']">
          <span v-if="isCorrect('D')">prawidłowa odpowiedź</span>
          <span v-else>nieprawidłowa odpowiedź</span>
        </switch-radio>
      </section>

      <section class="border p-6 rounded-xl my-4 border-light-gray">
        <h4 class="text-xl text-red pb-8 text-center">Informacje o egzaminie, z którego pochodzi pytanie</h4>
        <text-input v-model="year" type="number" id="year" :error="errors['year']" min="2000">Rok egzaminu</text-input>
        <text-input v-model="month" type="number" id="month" :error="errors['month']" min="1" max="12">Miesiąc
          egzaminu
        </text-input>
      </section>

      <button @click="saveQuestion"
              class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded-xl hover:bg-dark-green hover:text-white">
        Zapisz
      </button>
    </div>
  </div>
</template>
<script>
import {authHTTP} from "@/http";
import TextInput from "@/components/visual/TextInput";
import TextAreaInput from "@/components/visual/TextAreaInput";
import SwitchRadio from "@/components/visual/SwitchRadio";
import FileInput from "@/components/visual/FileInput";

export default {
  name: 'AddEditQuestion',
  components: {TextInput, TextAreaInput, SwitchRadio, FileInput},
  data() {
    return {
      content: '',
      answerA: '',
      answerB: '',
      answerC: '',
      answerD: '',
      correct: '',
      image: null,
      month: 0,
      year: 0,
      errors: {},
    }
  },
  methods: {
    isCorrect(answer) {
      return this.correct === answer;
    },
    handleFile(file) {
      this.image = file;
    },
    saveQuestion() {
      const formData = new FormData();
      formData.append('content', this.content);
      formData.append('answerA', this.answerA);
      formData.append('answerB', this.answerB);
      formData.append('answerC', this.answerC);
      formData.append('answerD', this.answerD);
      formData.append('correct', this.correct);
      formData.append('image', this.image);
      formData.append('month', this.month);
      formData.append('year', this.year);

      authHTTP.post(`/api/v3/admin/questions`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
          .then(() => {
            this.$router.go(-1);

          })
          .catch((error) => {
            this.errors = error.response.data.errors;
          });
    }
  }
}
</script>