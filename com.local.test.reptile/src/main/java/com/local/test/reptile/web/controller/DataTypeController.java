package com.local.test.reptile.web.controller;

import org.springframework.stereotype.Controller;

import com.local.test.reptile.pojo.po.DataType;
import com.local.test.reptile.service.DataTypeService;
import com.shunwang.business.framework.spring.mvc.controller.CrudController;

@Controller
public class DataTypeController extends CrudController<DataType, DataTypeService> {
}
