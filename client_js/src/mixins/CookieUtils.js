import {useState} from "vue-gtag-next";

const googleAnalyticsCookie = "privacy_analytics_cookies";

export default {
    methods: {
        setGoogleAnalyticsStatus(enable) {
            this.$cookie.setCookie(googleAnalyticsCookie, enable);
            this.setGoogleAnalyticsGtag(enable);
        },
        getGoogleAnalyticsStatus() {
            return 'true' === this.$cookie.getCookie(googleAnalyticsCookie);
        },
        setGoogleAnalyticsGtag(enable) {
            const {isEnabled} = useState();
            isEnabled.value = enable;
        },
        presetGoogleAnalyticsGtag() {
            const {isEnabled} = useState();
            isEnabled.value = this.getGoogleAnalyticsStatus();
        },
        checkIsGoogleAnalyticsCookieIsAvailable() {
            return this.$cookie.isCookieAvailable(googleAnalyticsCookie);
        }
    }
}
