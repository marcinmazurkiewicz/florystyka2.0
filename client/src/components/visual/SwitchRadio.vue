<template>
  <div class="mx-1 md:mx-2 my-4">
    <div class="relative inline-block w-10 mr-2 align-middle select-none transition duration-200 ease-in">
      <input type="radio" :name="name" :id="id"
             :value="modelValue"
             @input="$emit('update:modelValue', value)"
             :checked="isChecked"
             class="toggle-checkbox absolute block w-6 h-6 rounded-full bg-white border-4 appearance-none cursor-pointer"/>
      <label :for="id"
             class="toggle-label block overflow-hidden h-6 rounded-full bg-gray-300 cursor-pointer"></label>
    </div>
    <label :for="id" class="text-sm text-white">
      <slot/>
    </label>
    <p v-if="error" class="text-red pt-3">{{ getErrorMsg({errorType: 'CHOOSE_CORRECT'}) }}</p>
  </div>
</template>
<script>

import errorUtils from "@/mixins/errorUtils";

export default {
  name: 'SwitchRadio',
  mixins: [errorUtils],
  props: {
    name: {
      required: true
    },
    id: {
      required: true
    },
    value: {
      required: true
    },
    modelValue: {
      required: true
    },
    error: {
      required: false
    }
  },
  computed: {
    isChecked() {
      return this.value === this.modelValue;
    }
    ,
    isCorrect() {
      return this.solution.correct === this.value;
    }
  }
}
</script>
<style scoped>
.toggle-checkbox:checked {
  @apply right-0 border-dark-green;
}

.toggle-checkbox:checked + .toggle-label {
  @apply bg-dark-green;
}
</style>