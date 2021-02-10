<template>
  <div>
    <banner/>
    <main class="w-full max-w-screen-lg m-auto text-white" :class="isGenerating ? 'cursor-wait' : 'cursor-default'">
      <section>
        <header class="text-center text-4xl text-red pt-16 pb-8 px-3 md:px-6 tracking-wider leading-relaxed">
          Test w PDF
        </header>

        <p class="text-center tracking-wide leading-loose px-6">
          Gotowy do druku test do rozwiązania na zajęciach lub do zabrania tam, gdzie nie ma sieci.
        </p>


        <p class="text-center tracking-wide leading-loose pb-8 px-6">
          Na ostatniej stronie znajduje się klucz odpowiedzi - zajrzyj do niego dopiero po rozwiązaniu testu ;)
        </p>

        <connect-error-info v-if="isConnectError"/>

        <button v-else @click="generatePdf"
                :class="isGenerating ? 'bg-gray-600 text-gray-700 border-gray-700 cursor-wait'
                : 'bg-light-green text-dark-gray border-dark-green cursor-pointer hover:bg-dark-green hover:text-white'"
                class="block w-1/2 mx-auto  mt-8 p-3  text-lg font-semibold border rounded-xl"
                :disabled="isGenerating">
          Generuj i pobierz test
        </button>
      </section>

    </main>
  </div>
</template>
<script>
import Banner from "@/components/visual/Banner";
import ConnectErrorInfo from "@/components/visual/ConnectErrorInfo"
import {HTTP} from "@/http";

export default {
  name: 'PdfView',
  components: {
    Banner,
    ConnectErrorInfo
  },
  data() {
    return {
      isConnectError: false,
      isGenerating: false,
      questionNumber: 0,
      earliestQuestionYear: 0,
      latestQuestionYear: 0
    }
  },
  methods: {
    generatePdf() {
      if(this.isGenerating) {
        return;
      }

      this.isGenerating = true;
      HTTP.get(`/api/v3/questions/test/pdf`, {responseType: "blob"})
          .then((response) => {
            this.downloadFile(response);
            this.isGenerating = false;
          })
          .catch(() => {
            this.isConnectError = true;
            this.isGenerating = false;
          });
    },
    downloadFile(response) {
      const filename = this.getFilenameFromHeader(response.headers['content-disposition']) ?? `test.pdf`;

      const file = new Blob([response.data], {type: response.headers['content-type']});
      if (window.navigator && window.navigator.msSaveOrOpenBlob) {
        window.navigator.msSaveOrOpenBlob(file);
        return;
      }

      const data = window.URL.createObjectURL(file);
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', filename);
      document.body.appendChild(link);
      link.click();
      setTimeout(function () {
        window.URL.revokeObjectURL(data)
      }, 100)
    },
    getFilenameFromHeader(header) {
      const filenameRegex = /filename[^;\n]*=(UTF-\d['"]*)?((['"]).*?[.]$\2|[^;\n]*)?/;
      let filename;
      if (header) {
        const contentDispositionHeaderSplit = filenameRegex.exec(header);
        if (contentDispositionHeaderSplit[2])
          filename = decodeURIComponent(contentDispositionHeaderSplit[2]);
      }
      return filename;
    }
  },
}
</script>