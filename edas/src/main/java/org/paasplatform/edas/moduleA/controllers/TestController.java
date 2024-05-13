package org.paasplatform.edas.moduleA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @RestController annotation in order to simplify the creation of RESTful web services.
 *  It's a convenient annotation that combines @Controller and @ResponseBody,
 *  which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
 */
@RestController
@RequestMapping("/modulea")
public class ModuleAController {
    
}
