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

    private Map<String, String> properties = new HashMap<>();

}
