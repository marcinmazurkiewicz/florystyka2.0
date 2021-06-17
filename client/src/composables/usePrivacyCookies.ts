import { useState } from "vue-gtag-next";
import { VueCookieNext } from "vue-cookie-next";

const googleAnalyticsCookie = "privacy_analytics_cookies";

export default function usePrivacyCookies() {
  const setGoogleAnalyticsGtag = (enable: boolean) => {
    const { isEnabled } = useState();
    if (isEnabled) {
      isEnabled.value = enable;
    }
  };
  const setGoogleAnalyticsStatus = (enable: boolean): void => {
    VueCookieNext.setCookie(googleAnalyticsCookie, String(enable));
    setGoogleAnalyticsGtag(enable);
  };

  const getGoogleAnalyticsStatus = (): boolean => {
    return "true" === VueCookieNext.getCookie(googleAnalyticsCookie);
  };

  const presetGoogleAnalyticsGtag = (): void => {
    const { isEnabled } = useState();
    if (isEnabled) {
      isEnabled.value = getGoogleAnalyticsStatus();
    }
  };

  const checkIsGoogleAnalyticsCookieIsAvailable = (): boolean => {
    return VueCookieNext.isCookieAvailable(googleAnalyticsCookie);
  };

  return {
    setGoogleAnalyticsStatus,
    setGoogleAnalyticsGtag,
    getGoogleAnalyticsStatus,
    presetGoogleAnalyticsGtag,
    checkIsGoogleAnalyticsCookieIsAvailable
  };
}
