<template>
  <div>
    <pending-info v-if="responseStatus.isPending" />
    <error-info v-if="responseStatus.isError">
      {{ responseStatus.errorMsg }}
    </error-info>

    <div v-if="responseStatus.isDataReturned">
      <slot></slot>
    </div>
  </div>
</template>
<script lang="ts">
import {defineComponent, PropType} from "vue";
import {ResponseStatus} from "@/types/ResponseStatus";
import ErrorInfo from "@/components/ErrorInfo.vue";
import PendingInfo from "@/components/PendingInfo.vue"

export default defineComponent({
  name: "ViewWrapper",
  components: {
    ErrorInfo,
    PendingInfo
  },
  props: {
    responseStatus: Object as PropType<ResponseStatus>
  }
})
</script>
