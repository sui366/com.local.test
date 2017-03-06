package com.local.test.reptile.web.controller;

import org.springframework.stereotype.Controller;

import com.local.test.reptile.pojo.po.SpiderPlatform;
import com.local.test.reptile.service.SpiderPlatformService;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;

@Controller
public class SpiderPlatformController extends CrudController<SpiderPlatform, SpiderPlatformService> {
}
