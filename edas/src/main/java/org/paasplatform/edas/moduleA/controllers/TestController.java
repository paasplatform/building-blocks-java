package org.paasplatform.edas.moduleA.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

/**
 *  @RestController annotation in order to simplify the creation of RESTful web services.
 *  It's a convenient annotation that combines @Controller and @ResponseBody,
 *  which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
 */
@RestController
@RequestMapping("/modulea")
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "a response.";
    }

    /**
     * Spring Boot Async异步执行任务
     * @return
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public WebAsyncTask<String> longTimeTask(){
        System.out.println("/login被调用 thread id is : " + Thread.currentThread().getName());
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(1000); //模拟长时间任务
                String result = "login/index";
                System.out.println("执行成功 thread id is : " + Thread.currentThread().getName());
                return result;
            }
        };
        return new WebAsyncTask<String>(callable);
    }
}
