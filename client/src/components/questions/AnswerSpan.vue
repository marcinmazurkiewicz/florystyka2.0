<template>
  <div
    :id="id"
    :class="color"
    class="block w-full py-3 px-5 mb-1 bg-gray border flex justify-between items-center"
  >
    <slot class="text-gr" />
    <div v-if="checked" class="w-6 h-6 flex-shrink-0">
      <img
        v-if="isCorrect"
        src="https://s.svgbox.net/hero-outline.svg?ic=check&fill=8bc34a"
        class="w-full"
        alt="correct"
      />
      <img
        v-else
        src="https://s.svgbox.net/hero-outline.svg?ic=x&fill=ff5252"
        class="w-full"
        alt="incorrect"
      />
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "AnswerSpan",
  props: ["id", "value", "checked", "solution"],
  computed: {
    color(): string {
      const uncheckedFont = "italic";
      const checkedFont = "font-semibold not-italic";
      const uncheckedColor = "text-gray-400 border-dark-gray";
      const correctColor = "text-light-green border-light-green";
      const incorrectColor = "text-red border-red";

      let style = this.checked ? checkedFont : uncheckedFont;
      if (this.isCorrect) {
        style += ` ${correctColor}`;
      } else if (this.checked) {
        style += ` ${incorrectColor}`;
      } else {
        style += ` ${uncheckedColor}`;
      }
      return style;
    },
    isCorrect(): boolean {
      return this.solution.correct === this.value;
    }
  }
});
</script>
