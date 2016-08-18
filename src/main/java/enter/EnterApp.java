package enter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chanwook
 */
@RestController
@SpringBootApplication
public class EnterApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EnterApp.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "Start~!";
    }

}