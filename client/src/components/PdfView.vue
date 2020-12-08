<template>
  <div>
    <banner/>
    <main class="w-full max-w-screen-lg m-auto text-white">
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

        <button @click="generatePdf"
                class="block w-1/2 mx-auto bg-light-green mt-8 p-3 text-dark-gray text-lg font-semibold border border-dark-green
            rounded-xl hover:bg-dark-green hover:text-white">
          Generuj i pobierz test
        </button>

      </section>

    </main>
  </div>
</template>
<script>
import Banner from "@/components/visual/Banner";
import {HTTP} from "@/http";

export default {
  name: 'PdfView',
  components: {
    Banner
  },
  data() {
    return {
      questionNumber: 0,
      earliestQuestionYear: 0,
      latestQuestionYear: 0
    }
  },
  methods: {
    generatePdf() {
      HTTP.get(`/api/v3/questions/test/pdf`, {responseType: "blob"})
          .then((response) => {
            this.downloadFile(response);
          })
          .catch((e) => {
            console.log(e);
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