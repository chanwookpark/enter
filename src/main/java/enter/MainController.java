package enter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class MainController {

    @RequestMapping("/main.page")
    public String main() {

        return "main";
    }
}
