package dev.mazurkiewicz.florystyka.recaptcha;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;


@Service
public class RecaptchaService {

    private final RecaptchaProperties properties;
    private final RestTemplate client;

    public RecaptchaService(RecaptchaProperties properties) {
        this.properties = properties;
        this.client = new RestTemplate();
    }

    public boolean verifyCaptcha(String token, String action) {
        URI url = getVerifyUrl(token);
        RequestEntity<Void> request = RequestEntity.post(url).build();
        ResponseEntity<RecaptchaResponse> exchange = client.exchange(request, RecaptchaResponse.class);
        return isPositivelyVerified(action, exchange);
    }

    private URI getVerifyUrl(String token) {
        return UriComponentsBuilder.fromHttpUrl(properties.getVerifyUrl())
                .queryParam(properties.getKeyParamName(), properties.getSecretKey())
                .queryParam(properties.getTokenParamName(), token)
                .build()
                .encode()
                .toUri();
    }

    private boolean isPositivelyVerified(String action, ResponseEntity<RecaptchaResponse> exchange) {
        return isRequestStatusSuccessful(exchange) && checkCaptchaResponse(Objects.requireNonNull(exchange.getBody()), action);
    }

    private boolean isRequestStatusSuccessful(ResponseEntity<RecaptchaResponse> exchange) {
        return exchange.getStatusCode().is2xxSuccessful();
    }

    private boolean checkCaptchaResponse(RecaptchaResponse recaptchaResponse, String action) {
        return recaptchaResponse.isSuccess()
                && isCorrectHostname(recaptchaResponse)
                && isCorrectScore(recaptchaResponse)
                && isCorrectAction(recaptchaResponse, action);
    }

    private boolean isCorrectHostname(RecaptchaResponse recaptchaResponse) {
        return recaptchaResponse.getHostname().equals(properties.getHostname());
    }

    private boolean isCorrectScore(RecaptchaResponse recaptchaResponse) {
        return recaptchaResponse.getScore() > 0.5f;
    }

    private boolean isCorrectAction(RecaptchaResponse recaptchaResponse, String action) {
        return recaptchaResponse.getAction().equalsIgnoreCase(action);
    }
}
