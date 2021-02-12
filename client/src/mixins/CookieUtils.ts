import { useState } from "vue-gtag-next";
import { VueCookieNext } from "vue-cookie-next";

const googleAnalyticsCookie = "privacy_analytics_cookies";

export default {
  methods: {
    setGoogleAnalyticsStatus(enable: boolean): void {
      VueCookieNext.setCookie(googleAnalyticsCookie, String(enable));
      this.setGoogleAnalyticsGtag(enable);
    },
    getGoogleAnalyticsStatus(): boolean {
      return "true" === VueCookieNext.getCookie(googleAnalyticsCookie);
    },
    setGoogleAnalyticsGtag(enable: boolean) {
      const { isEnabled } = useState();
      if (isEnabled) {
        isEnabled.value = enable;
      }
    },
    presetGoogleAnalyticsGtag() {
      const { isEnabled } = useState();
      if (isEnabled) {
        isEnabled.value = this.getGoogleAnalyticsStatus();
      }
    },
    checkIsGoogleAnalyticsCookieIsAvailable(): boolean {
      return VueCookieNext.isCookieAvailable(googleAnalyticsCookie);
    }
  }
};
