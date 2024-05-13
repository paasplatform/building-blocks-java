package org.paasplatform.edas.moduleB.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @RestController annotation in order to simplify the creation of RESTful web services.
 *  It's a convenient annotation that combines @Controller and @ResponseBody,
 *  which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
 */
@RestController
@RequestMapping("/moduleb")
public class BTestController {
    @GetMapping("/test")
    public String test() {
        return "b response.";
    }
}
