package io.github.uncleandychen.bbs.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = {"/", "/index", "/home"})
    public String index() {
        return "index";
    }
}
