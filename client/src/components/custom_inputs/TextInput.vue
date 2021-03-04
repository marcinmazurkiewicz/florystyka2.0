<template>
  <div class="w-full">
    <label :for="id"
           :class="error ? 'text-red' : 'text-dark-green'"
           class="inline-block text-md leading-5 font-medium pl-2 w-1/2">
      <slot/>
    </label><span class="inline-block w-1/2 text-right text-sm text-red" v-if="error">
      {{ error }}</span>
    <input :id="id" :class="error ? 'bg-red border-red focus:border-dark-red' : 'focus:border-dark-green'"
           :name="id" :required=required :type="type"
           class="form-input block w-full pl-4 pr-2 py-3 text-black sm:text-sm sm:leading-5 mt-2 mb-7 focus:shadow-none border-3 border-white"
           @input="$emit('update:modelValue', $event.target.value)">
  </div>
</template>
<script lang="ts">

import {getErrorBasedOnErrorType} from "@/utils/errorUtils";
import {defineComponent} from "vue";

export default defineComponent({
  name: 'TextInput',
  emits: ['update:modelValue'],
  props: {
    modelValue: {
      type: [String, Number],
      required: true
    },
    id: {
      type: String,
      required: true
    },
    error: {
      type: String
    },
    type: {
      type: String,
      default: 'text'
    },
    required: {
      type: Boolean,
      default: true
    }
  },
  setup() {
    return {getErrorBasedOnErrorType};
  }
});
</script>
