package com.test.zookeeper.logback;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String logbackLevel() throws Exception {
        Logger logger = (Logger) LoggerFactory.getLogger("root");
        String levelStr = logger.getLevel().levelStr;
        return levelStr;
    }

}