package enter.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chanwook
 */
@Controller
public class KakaoEnterController {

    private final Logger logger = LoggerFactory.getLogger(KakaoEnterController.class);

    @Autowired
    private KakaoApi api;

    @RequestMapping("/kakao/login-request")
    public String loginRequestToKakao() {

        String kakaoRequestUrl = api.createKakaoLoginRequest();

        logger.info("[Kakao Login Request URL]{}", kakaoRequestUrl);

        return "redirect:" + kakaoRequestUrl;
    }

    @RequestMapping("/kakao/login")
    public String loginByKakaoId(@RequestParam("access_token") String accessToken,
                                 @RequestParam("refresh_token") String refreshToken) {

        logger.debug("[Kakao User Token] AccessToken: {}, RefreshToken: {}", accessToken, refreshToken);

        // access token으로 사용자 식별 정보를 받아온다
        KakaoUserInfo kakaoUserInfo = api.getUserInfo(accessToken);
        kakaoUserInfo.saveToken(accessToken, refreshToken);

        // 로그인시킨다
        final Authentication authentication = new KakaoUser(kakaoUserInfo);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/main.page";
    }
}
