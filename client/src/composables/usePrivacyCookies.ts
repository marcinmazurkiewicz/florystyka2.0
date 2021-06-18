import { useState } from "vue-gtag-next";
import { useCookie } from "@vue-composable/cookie";
import { Ref } from "vue";
const googleAnalyticsCookie = "privacy_analytics_cookies";

const isCookie = (cookie: Ref<string | null | undefined>): boolean =>
  cookie.value !== undefined && cookie.value !== null;

export default function usePrivacyCookies() {
  const setGoogleAnalyticsGtag = (enable: boolean) => {
    const { isEnabled } = useState();
    if (isEnabled) {
      isEnabled.value = enable;
    }
  };
  const setGoogleAnalyticsStatus = (enable: boolean): void => {
    const { cookie } = useCookie(googleAnalyticsCookie);
    cookie.value = String(enable);
    setGoogleAnalyticsGtag(enable);
  };

  const getGoogleAnalyticsStatus = (): boolean => {
    const { cookie } = useCookie(googleAnalyticsCookie);
    return isCookie(cookie) && cookie.value === "true";
  };

  const presetGoogleAnalyticsGtag = (): void => {
    const { isEnabled } = useState();
    if (isEnabled) {
      isEnabled.value = getGoogleAnalyticsStatus();
    }
  };

  const checkIsGoogleAnalyticsCookieIsAvailable = (): boolean => {
    const { cookie } = useCookie(googleAnalyticsCookie);
    return isCookie(cookie);
  };

  return {
    setGoogleAnalyticsStatus,
    setGoogleAnalyticsGtag,
    getGoogleAnalyticsStatus,
    presetGoogleAnalyticsGtag,
    checkIsGoogleAnalyticsCookieIsAvailable
  };
}
