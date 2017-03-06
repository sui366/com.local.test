package com.local.test.reptile.web.controller;

import org.springframework.stereotype.Controller;

import com.local.test.reptile.pojo.po.DataContent;
import com.local.test.reptile.service.DataContentService;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;

@Controller
public class DataContentController extends CrudController<DataContent, DataContentService> {
}
