package enter.kakao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chanwook
 */
@Getter
@Setter
@ToString
public class KakaoUserInfo implements Serializable {

    private long id; //유저 고유 식별자

    private String accessToken;

    private String refreshToken;

    private Map<String, String> properties = new HashMap<>();

    public void saveToken(String accessToken, String refreshToken) {
        this.setAccessToken(accessToken);
        this.setRefreshToken(refreshToken);
    }
}
