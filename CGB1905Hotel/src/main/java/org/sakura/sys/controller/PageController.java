package org.sakura.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {


    @RequestMapping("/{moduleName}")
    public String toHtml(@PathVariable String moduleName) {

        return moduleName;
    }
}
