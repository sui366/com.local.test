package com.local.test.reptile.web.controller;

import org.springframework.stereotype.Controller;

import com.local.test.reptile.pojo.po.SpiderTaskFull;
import com.local.test.reptile.service.SpiderTaskFullService;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;

@Controller
public class SpiderTaskFullController extends CrudController<SpiderTaskFull, SpiderTaskFullService> {
}
