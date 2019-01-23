package com.ssm.demo.controller;

import com.ssm.demo.entity.Description;
import com.ssm.demo.service.DescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
//@RequestMapping表示类中的所有响应请求的方法都是以该地址作为父路径
@RequestMapping("/description")
public class DescriptionControler {

    @Autowired
    private DescriptionService descriptionService;

    /**
     * 通过ModelAndView对象获取信息
     */
    @RequestMapping("/infoByMv")
    public ModelAndView infoByMv(){
        Description description = descriptionService.getLastDescription();
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("description",description);
        return new ModelAndView("description",model);
    }

    /**
     * 通过HttpServletRequest对象获取信息
     * @param request
     * @return
     */
    @RequestMapping("/infoByRequest")
    public String infoByRequest(HttpServletRequest request){
        Description lastDescription = descriptionService.getLastDescription();
        request.setAttribute("description",lastDescription);
        return "description";
    }

}
