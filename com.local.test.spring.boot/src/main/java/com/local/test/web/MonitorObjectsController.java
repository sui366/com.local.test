package com.local.test.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.local.test.pojo.MonitorObjects;
import com.local.test.service.MonitorObjectsService;

@Controller
@RequestMapping("monitorObjects")
public class MonitorObjectsController {

	@Autowired
	MonitorObjectsService monitorObjectsService;

    @RequestMapping("/findMonitorObjects")
    public ModelAndView findMonitorObjects(){ 
    	
//    	MonitorObjects obj = monitorObjectsService.get(200l);
    	MonitorObjects obj = monitorObjectsService.findById(200l);
//    	System.out.println(obj.getProductType());
//        map.addAttribute("data", obj);
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("monitor-objects");
        modelAndView.addObject("data", obj);
        
        return modelAndView;
    }
    
    @RequestMapping("/testIndex")
    public ModelAndView testIndex(HttpServletResponse response) throws IOException{
    	
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        
//    	response.sendRedirect("index");
    	
    	return modelAndView;
    }

}