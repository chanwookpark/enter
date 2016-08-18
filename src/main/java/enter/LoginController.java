package enter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class LoginController {
    @RequestMapping("/login.page")
    public String loginPage() {
        return "login";
    }

}
