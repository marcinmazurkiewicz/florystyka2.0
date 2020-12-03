<template>
  <div>
    <label :for="id" :class="color"
           class="block w-full py-3 px-5 mb-1 bg-gray border">
      <slot/>
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
<script>

export default {
  name: 'AnswerRadio',
  props: ['name', 'id', 'value', 'modelValue', 'solution'],
  computed: {
    isChecked() {
      return this.value === this.modelValue;
    },
    color() {
      const defaultStyle = 'text-white border-dark-gray hover:text-azure hover:border-azure';
      const checked = 'text-azure border-azure';
      const correct = 'text-light-green border-light-green';
      const incorrect = 'text-red border-red';
      if (this.solution.correct != null) {
        if (this.isCorrect) {
          return correct;
        } else if (this.isChecked) {
          return incorrect;
        }
      } else if (this.isChecked) {
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
