package com.local.test.reptile.web.controller;

import org.springframework.stereotype.Controller;

import com.local.test.reptile.pojo.po.SpiderTask;
import com.local.test.reptile.service.SpiderTaskService;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;

@Controller
public class SpiderTaskController extends CrudController<SpiderTask, SpiderTaskService> {
}
