package enter.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author chanwook
 */
@Service
public class KakaoApi {

    private final Logger logger = LoggerFactory.getLogger(KakaoApi.class);

    private final RestTemplate template = new RestTemplate();

    @Value("${enter.kakao.host.auth}")
    private String authHost;

    @Value("${enter.kakao.host.api}")
    private String apiHost;

    @Value("${enter.kakao.restAppKey}")
    private String restAppKey;

    @Value("${enter.kakao.authorize.redirectUri}")
    private String requestUri;

    public String createKakaoLoginRequest() {
        getAuthHostUriBuilder("/oauth/authorize")
                .queryParam("client_id", restAppKey)
                .queryParam("redirect_uri", requestUri)
                .queryParam("response_type", "code");

        return UriComponentsBuilder.newInstance().toUriString();
    }

    public KakaoUserInfo getUserInfo(String accessToken) {
        final String url = getApiHostUriBuilder("/v1/user/me").toUriString();

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        final ResponseEntity<KakaoUserInfo> response =
                template.exchange(url, HttpMethod.POST, new HttpEntity(headers), KakaoUserInfo.class);

        logger.debug("[Kakao GetUserInfo] URL: {}, accessToken: {}, response: {}", url, accessToken, response);
        // response -> user dto

        return response.getBody();
    }

    private UriComponentsBuilder getAuthHostUriBuilder(String urlPath) {
        return UriComponentsBuilder.newInstance().host(authHost).scheme("https").path(urlPath);
    }

    private UriComponentsBuilder getApiHostUriBuilder(String urlPath) {
        return UriComponentsBuilder.newInstance().host(apiHost).scheme("https").path(urlPath);
    }
}
