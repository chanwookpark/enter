package enter.kakao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;

/**
 * @author chanwook
 */
@Getter
@Setter
@ToString
public class SocialUser extends UsernamePasswordAuthenticationToken {
    
    public SocialUser(Object principal) {
        super(principal, "", createSocialUser());
    }

    private static List<GrantedAuthority> createSocialUser() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("SOCIAL_USER"));
    }
}
