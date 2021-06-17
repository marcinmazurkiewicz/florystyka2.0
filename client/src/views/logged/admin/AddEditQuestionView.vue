<template>
  <div
    class="mb-4 w-full h-full max-w-screen-lg mx-auto box-border lg:px-4 xl:px-10"
  >
    <div class=" w-full table p-4 box-border lg:my-10 xl:p-8">
      <h4 class="text-3xl text-red pb-8 text-center font-semibold">
        Dodaj nowe pytanie
      </h4>

      <section class="border p-6 rounded-xl my-4 border-light-gray">
        <text-area-input
          type="textarea"
          v-model="question.content"
          id="content"
          :error="errors['content']"
          >Treść pytania
        </text-area-input>

        <file-input
          id="image"
          accept="image/png, image/jpeg"
          :error="errors['image']"
          :required="false"
          @handle-file="handleFile"
        >
          Obrazek do pytania
        </file-input>
      </section>
      <section
        class="border p-6 rounded-xl my-4"
        :class="isCorrect('A') ? 'border-light-green' : 'border-light-gray'"
      >
        <text-input
          v-model="question.answerA"
          id="answerA"
          :error="errors['answerA']"
          >Odpowiedź A</text-input
        >
        <switch-radio
          value="A"
          id="correctA"
          name="correct"
          v-model="question.correct"
          :error="errors['correct']"
        >
          <span v-if="isCorrect('A')">prawidłowa odpowiedź</span>
          <span v-else>nieprawidłowa odpowiedź</span>
        </switch-radio>
      </section>

      <section
        class="border p-6 rounded-xl my-4"
        :class="isCorrect('B') ? 'border-light-green' : 'border-light-gray'"
      >
        <text-input
          v-model="question.answerB"
          id="answerB"
          :error="errors['answerB']"
          >Odpowiedź B</text-input
        >
        <switch-radio
          value="B"
          id="correctB"
          name="correct"
          v-model="question.correct"
          :error="errors['correct']"
        >
          <span v-if="isCorrect('B')">prawidłowa odpowiedź</span>
          <span v-else>nieprawidłowa odpowiedź</span>
        </switch-radio>
      </section>
      <section
        class="border p-6 rounded-xl my-4"
        :class="isCorrect('C') ? 'border-light-green' : 'border-light-gray'"
      >
        <text-input
          v-model="question.answerC"
          id="answerC"
          :error="errors['answerC']"
          >Odpowiedź C</text-input
        >
        <switch-radio
          value="C"
          id="correctC"
          name="correct"
          v-model="question.correct"
          :error="errors['correct']"
        >
          <span v-if="isCorrect('C')">prawidłowa odpowiedź</span>
          <span v-else>nieprawidłowa odpowiedź</span>
        </switch-radio>
      </section>
      <section
        class="border p-6 rounded-xl my-4"
        :class="isCorrect('D') ? 'border-light-green' : 'border-light-gray'"
      >
        <text-input
          v-model="question.answerD"
          id="answerD"
          :error="errors['answerD']"
          >Odpowiedź D</text-input
        >
        <switch-radio
          value="D"
          id="correctD"
          name="correct"
          v-model="question.correct"
          :error="errors['correct']"
        >
          <span v-if="isCorrect('D')">prawidłowa odpowiedź</span>
          <span v-else>nieprawidłowa odpowiedź</span>
        </switch-radio>
      </section>

      <section class="border p-6 rounded-xl my-4 border-light-gray">
        <h4 class="text-xl text-red pb-8 text-center">
          Informacje o egzaminie, z którego pochodzi pytanie
        </h4>
        <text-input
          v-model="question.year"
          type="number"
          id="year"
          :error="errors['year']"
          min="2000"
          >Rok egzaminu
        </text-input>
        <text-input
          v-model="question.month"
          type="number"
          id="month"
          :error="errors['month']"
          min="1"
          max="12"
          >Miesiąc egzaminu
        </text-input>
      </section>

      <button
        @click="saveQuestion"
        class="w-full bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded-xl hover:bg-dark-green hover:text-white"
      >
        Zapisz
      </button>
    </div>
  </div>
</template>
<script lang="ts">
import TextInput from "@/components/custom_inputs/TextInput.vue";
import TextAreaInput from "@/components/custom_inputs/TextAreaInput.vue";
import SwitchRadio from "@/components/custom_inputs/SwitchRadio.vue";
import FileInput from "@/components/custom_inputs/FileInput.vue";
import { defineComponent, ref, Ref } from "vue";
import { NewQuestion } from "@/types/QuestionTypes";
import { useQuestionsAsAdmin } from "@/composables/useQuestions";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "AddEditQuestion",
  components: { TextInput, TextAreaInput, SwitchRadio, FileInput },
  setup() {
    const question: Ref<NewQuestion> = ref({
      content: "",
      answerA: "",
      answerB: "",
      answerC: "",
      answerD: "",
      correct: "",
      month: 0,
      year: 0
    });

    const errors = ref({});
    const router = useRouter();

    const isCorrect = function(answer: string) {
      return question.value.correct === answer;
    };

    const handleFile = function(file: Blob) {
      question.value.image = file;
    };

    const { addQuestion } = useQuestionsAsAdmin();

    const prepareFormData = (question: NewQuestion): FormData => {
      const formData = new FormData();
      formData.append("content", question.content);
      formData.append("answerA", question.answerA);
      formData.append("answerB", question.answerB);
      formData.append("answerC", question.answerC);
      formData.append("answerD", question.answerD);
      formData.append("correct", question.correct);
      if (question.image) {
        formData.append("image", question.image);
      }
      formData.append("month", question.month?.toString() ?? "");
      formData.append("year", question.year?.toString() ?? "");
      return formData;
    };

    const saveQuestion = function(): void {
      const formData = prepareFormData(question.value);
      addQuestion(formData)
        .then(() => {
          router.go(-1);
        })
        .catch(response => {
          errors.value = response.responseStatus.errors;
        });
    };

    return { question, isCorrect, handleFile, saveQuestion, errors };
  }
});
</script>
