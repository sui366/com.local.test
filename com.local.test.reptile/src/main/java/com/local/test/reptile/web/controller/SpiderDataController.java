package com.local.test.reptile.web.controller;

import org.springframework.stereotype.Controller;

import com.local.test.reptile.pojo.po.SpiderData;
import com.local.test.reptile.service.SpiderDataService;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;

@Controller
public class SpiderDataController extends CrudController<SpiderData, SpiderDataService> {
}
