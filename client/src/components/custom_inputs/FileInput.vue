<template>
  <div class="w-full">
    <span class="block w-full text-right text-sm text-red" v-if="error">
      {{ getErrorBasedOnErrorInfo(error) }}
    </span>
    <div class="flex">
      <input
        :id="id"
        :name="id"
        :required="required"
        type="file"
        :accept="accept"
        ref="fileInputRef"
        class="opacity-0 absolute block w-3/5 pl-4 pr-2 py-2.5 mt-2 mb-7 max-w-screen-sm border"
        @change="handleFile"
      />
      <span
        :class="
          error
            ? 'bg-red border-dark-red focus:border-light-gray'
            : 'bg-white border-white focus:border-light-green'
        "
        class="input-file w-2/3 focus:shadow-none pl-4 pr-2 py-3 text-black"
        >{{ filename }}</span
      >
      <label
        :for="id"
        class="file-label w-1/3 overflow-hidden pl-4 pr-2 py-4 text-sm text-center lowercase bg-light-green text-black border-0 rounded-r-xl mt-2 mb-7"
      >
        <slot />
      </label>
    </div>
  </div>
</template>
<script lang="ts">
import { getErrorBasedOnErrorInfo } from "@/utils/errorUtils";
import { defineComponent, PropType, ref, Ref } from "vue";
import { ErrorInfo } from "@/types/ErrorTypes";

export default defineComponent({
  name: "FileInput",

  props: {
    id: {
      type: String,
      required: true
    },
    error: Object as PropType<ErrorInfo>,
    required: {
      type: Boolean,
      default: true
    },
    accept: {
      type: String,
      default: "*"
    }
  },
  setup(props, { emit }) {
    const filename: Ref<string> = ref("");
    const fileInputRef = ref();
    const handleFile = function() {
      if (fileInputRef.value.files[0]) {
        filename.value = fileInputRef.value.files[0].name;
      }
      emit("handle-file", fileInputRef.value.files[0]);
    };
    return { filename, fileInputRef, handleFile, getErrorBasedOnErrorInfo };
  }
});
</script>
<style>
.file-label:before {
  content: "\21EA";
  margin-right: 0.25rem;
}

.input-file {
  @apply appearance-none border-3 py-2 block pl-4 text-sm leading-5 mt-2 mb-7;
}
</style>
