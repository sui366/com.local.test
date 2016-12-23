package com.local.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.local.test.exception.MyException;
import com.local.test.pojo.MonitorObjects;
import com.local.test.service.MonitorObjectsService;

@Controller
@RequestMapping("monitorObjects")
public class MonitorObjectsController {

	@Autowired
	MonitorObjectsService monitorObjectsService;

    @RequestMapping("/findMonitorObjects")
    public ModelAndView findMonitorObjects() throws MyException{
    	
//    	MonitorObjects obj = monitorObjectsService.get(200l);
    	MonitorObjects obj = monitorObjectsService.findById(200l);
//    	System.out.println(obj.getProductType());
//        map.addAttribute("data", obj);
    	
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("monitor-objects");
        modelAndView.addObject("data", obj);
        return modelAndView;
    }

}