package lol.gilliard.mayden.components;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/{uuid:[a-zA-Z0-9-]{36}}")
    public String redirectUuidUrlToIndex() {
        // This serves index.html to all requests of the form /{some-uuid}
        // (it is automatically served on / and /index.html)
        return "index.html";
    }
}
