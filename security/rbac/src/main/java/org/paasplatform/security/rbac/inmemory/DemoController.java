package org.paasplatform.security.rbac.inmemory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class DemoController {

    private static final String template = "Hello, %s!";

    public DemoController() {

    }

    @GetMapping("/admin/index")
    @ResponseBody
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        return String.format(template, name);
    }

    @GetMapping("/authentication/login")
    public String authenticationLogin() throws IOException {
        return "login";
    }

    @GetMapping("/user/index")
    @ResponseBody
    public String home() {
        return "欢迎来到主页 ~";
    }
}