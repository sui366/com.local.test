package com.local.test.reptile.web.controller;

import org.springframework.stereotype.Controller;

import com.local.test.reptile.pojo.po.SpiderType;
import com.local.test.reptile.service.SpiderTypeService;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;

@Controller
public class SpiderTypeController extends CrudController<SpiderType, SpiderTypeService> {
}
