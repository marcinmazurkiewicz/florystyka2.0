<template>
  <div>
    <label
      :for="id"
      :class="color"
      class="block w-full py-3 px-5 mb-1 bg-gray border"
    >
      <slot />
      <input
        :name="name"
        :id="id"
        type="radio"
        :value="value"
        :checked="isChecked"
        @input="$emit('update:modelValue', value)"
        class="hidden"
      />
    </label>
  </div>
</template>
<script lang="ts">
import { defineComponent, computed, toRefs, PropType } from "vue";
import { Solution } from "@/types/QuestionTypes";

export default defineComponent({
  name: "AnswerRadio",
  props: {
    name: {
      type: String
    },
    id: {
      type: String
    },
    value: {
      type: String,
      required: true
    },
    modelValue: {
      type: String,
      required: true
    },
    solution: {
      type: Object as PropType<Solution>,
      required: true
    }
  },
  setup(props) {
    const { solution, value, modelValue } = toRefs(props);

    const isChecked = computed(() => {
      return value.value === modelValue.value;
    });

    const isCorrect = computed(() => {
      return solution.value.correct === value.value;
    });

    const color = computed(() => {
      const defaultStyle =
        "text-white border-dark-gray hover:text-azure hover:border-azure";
      const checked = "text-azure border-azure";
      const correct = "text-light-green border-light-green";
      const incorrect = "text-red border-red";
      if (solution.value != null && solution.value.correct != null) {
        if (isCorrect.value) {
          return correct;
        } else if (isChecked.value) {
          return incorrect;
        }
      } else if (isChecked.value) {
        return checked;
      }
      return defaultStyle;
    });

    return { color, isChecked };
  }
});
</script>
