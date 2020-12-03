<template>
  <div :id="id" :class="color"
       class="block w-full py-3 px-5 mb-1 bg-gray border">
    <slot/>
  </div>
</template>
<script>

export default {
  name: 'AnswerSpan',
  props: ['id', 'value', 'checked', 'solution'],
  computed: {
    color() {
      const defaultStyle = 'text-white border-dark-gray';
      const checked = 'text-azure border-azure';
      const correct = 'text-light-green border-light-green';
      const incorrect = 'text-red border-red';
      if (this.solution.correct != null) {
        if (this.isCorrect) {
          return correct;
        } else if (this.checked) {
          return incorrect;
        }
      } else if (this.checked) {
        return checked;
      }
      return defaultStyle;
    },
    isCorrect() {
      return this.solution.correct === this.value;
    }
  }
}
</script>
