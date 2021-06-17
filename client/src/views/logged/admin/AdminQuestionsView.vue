<template>
  <div class="w-full m-auto text-white p-4">
    <div class="w-full flex my-5">
      <router-link :to="{ name: 'AdminAddQuestion' }"
        >Dodaj pytanie</router-link
      >
      <ul class="w-full flex justify-center">
        <li class="p-4 inline-block cursor-pointer" @click="getPage(1)">
          &#10092;&#10092;
        </li>
        <li
          :class="n === currentPage ? 'bg-light-green' : 'bg-light-gray'"
          class="p-4 inline-block cursor-pointer"
          v-for="n in pages()"
          :key="n"
          @click="getPage(n)"
        >
          {{ n }}
        </li>
        <li
          class="p-4 inline-block cursor-pointer"
          @click="getPage(this.totalPages)"
        >
          &#10093;&#10093;
        </li>
      </ul>
    </div>

    <div class="w-full table mb-4">
      <table class="w-full text-center text-sm break-words table-auto">
        <thead>
          <tr class="border-b-2 border-gray-300">
            <th class="pt-4 pb-3 px-2 pl-4">Lp</th>
            <th class="cell">ID</th>
            <th class="cell">Treść pytania</th>
            <th class="cell">Odpowiedź A</th>
            <th class="cell">Odpowiedź B</th>
            <th class="cell">Odpowiedź C</th>
            <th class="cell">Odpowiedź D</th>
            <th class="cell">Poprawna odpowiedź</th>
            <th class="cell">Rok egzaminu</th>
            <th class="cell">Miesiąc egzaminu</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="border-b border-gray-300 odd:bg-honey-100"
            v-for="(question, index) in questions"
            :key="question.id"
          >
            <td class="cell pl-4" @click="goToDetails(question.id)">
              {{ index + 1 }}.
            </td>
            <td class="cell" @click="goToDetails(question.id)">
              {{ question.id }}
            </td>
            <td class="cell" @click="goToDetails(question.id)">
              <span v-html="question.content"></span>
            </td>
            <td class="cell" @click="goToDetails(question.id)">
              <span v-html="question.answerA"></span>
            </td>
            <td class="cell" @click="goToDetails(question.id)">
              <span v-html="question.answerB"></span>
            </td>
            <td class="cell" @click="goToDetails(question.id)">
              <span v-html="question.answerC"></span>
            </td>
            <td class="cell" @click="goToDetails(question.id)">
              <span v-html="question.answerD"></span>
            </td>
            <td class="cell" @click="goToDetails(question.id)">
              {{ question.correct }}
            </td>
            <td class="cell" @click="goToDetails(question.id)">
              {{ question.year }}
            </td>
            <td class="cell" @click="goToDetails(question.id)">
              {{ question.month }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, Ref, ref } from "vue";
import { useQuestionsAsAdmin } from "@/composables/useQuestions";
import { AdminQuestion } from "@/types/QuestionTypes";

export default defineComponent({
  name: "AdminQuestionsView",
  setup() {
    const questions: Ref<AdminQuestion[] | undefined> = ref([]);
    const totalPages: Ref<number> = ref(0);
    const currentPage: Ref<number> = ref(0);
    const { nearbyPages, goToPage, goToDetails } = useQuestionsAsAdmin();

    const pages = function() {
      return nearbyPages(totalPages.value, currentPage.value);
    };

    const getPage = function(pageNo: number): void {
      goToPage(pageNo).then(result => {
        if (result.data) {
          totalPages.value = result.data.totalPages;
          currentPage.value = result.data.number + 1;
          questions.value = result.data.content;
        }
      });
    };

    onMounted(() => getPage(1));

    return {
      questions,
      totalPages,
      currentPage,
      getPage,
      goToDetails,
      pages
    };
  }
});
</script>
<style>
.cell {
  @apply pt-4 pb-3 px-2 cursor-pointer;
}
</style>
