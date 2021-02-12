import { onMounted, ref } from "vue";
import { HTTP } from "@/http";

export default function useQuestionStats() {
  const isDataReturned = ref(false);
  const questionNumber = ref(0);
  const earliestQuestionYear = ref(0);
  const latestQuestionYear = ref(0);

  onMounted(() => {
    HTTP.get("/api/v3/questions/info")
      .then(response => {
        isDataReturned.value = true;
        questionNumber.value = response.data.questionNumber;
        earliestQuestionYear.value = response.data.earliestQuestionYear;
        latestQuestionYear.value = response.data.latestQuestionYear;
      })
      .catch(() => {
        isDataReturned.value = false;
        console.log("Wystąpił błąd połączenia z serwerem");
      });
  });
  return {
    isDataReturned,
    questionNumber,
    earliestQuestionYear,
    latestQuestionYear
  };
}
