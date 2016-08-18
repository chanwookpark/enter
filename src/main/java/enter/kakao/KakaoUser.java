package enter.kakao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author chanwook
 */
@Getter
@Setter
@ToString
public class KakaoUser extends SocialUser {

    private final Map<String, String> properties;

    public KakaoUser(KakaoUserInfo userInfo) {
        super(userInfo.getId());
        this.properties = userInfo.getProperties();
    }
}
