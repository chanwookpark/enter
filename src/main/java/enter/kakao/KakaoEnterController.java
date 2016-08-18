package enter.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/kakao/login.page")
    public String loginPage() {

        return "kakao-login";
    }

    @RequestMapping("/kakao/login-request")
    public String loginRequestToKakao() {

        String kakaoRequestUrl = api.createKakaoLoginRequest();

        logger.info("[Kakao Login Request URL]{}", kakaoRequestUrl);

        return "redirect:" + kakaoRequestUrl;
    }

    @RequestMapping("/kakao/login")
    public String loginByKakaoId(@RequestParam("access_token") String accessToken,
                                 @RequestParam("refresh_token") String refreshToken) {
        logger.debug("[User Token] AccessToken: {}, RefreshToken: {}", accessToken, refreshToken);

        // accesstoken으로 누군인지 알아낸다

        // 로그인시킨다

        return "redirect:/main.page";
    }
}
