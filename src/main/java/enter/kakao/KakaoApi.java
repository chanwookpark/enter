package enter.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author chanwook
 */
@Service
public class KakaoApi {

    @Autowired
    Environment env;

    public String createKakaoLoginRequest() {
        final String host = env.getProperty("enter.kakao.host");
        final String restAppKey = env.getProperty("enter.kakao.restAppKey");
        final String requestUri = env.getProperty("enter.kakao.redirectUri");

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        uriBuilder.host(host).scheme("https")
                .queryParam("client_id", restAppKey)
                .queryParam("redirect_uri", requestUri)
                .queryParam("response_type", "code");

        return uriBuilder.toUriString();
    }
}
