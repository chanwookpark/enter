package enter.kakao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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
        logger.debug("[Kakao User Token] AccessToken: {}, RefreshToken: {}", accessToken, refreshToken);

        // accesstoken으로 사용자 식별 정보를 받아온다
        KakaoUserInfo kakaoUserInfo = api.getUserInfo(accessToken);

        // 로그인시킨다
        final ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        authorities.add(new SimpleGrantedAuthority("SOCIAL_KAKAO"));
        
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("test", "test00", authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/main.page";
    }
}
