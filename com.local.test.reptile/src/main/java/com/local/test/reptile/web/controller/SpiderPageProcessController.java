package com.local.test.reptile.web.controller;

import org.springframework.stereotype.Controller;

import com.local.test.reptile.pojo.po.SpiderPageProcess;
import com.local.test.reptile.service.SpiderPageProcessService;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;

@Controller
public class SpiderPageProcessController extends CrudController<SpiderPageProcess, SpiderPageProcessService> {
}
