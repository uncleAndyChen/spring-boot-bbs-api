package io.github.uncleandychen.bbs.api.controller;

import bbs.api.biz.service.ApiAdapterService;
import bbs.api.common.lib.ConfigProperties;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ApiController {
    @RequestMapping("/bbsApi")
    public ApiResponse getResponse(BaseRequest baseRequest, HttpServletRequest request, HttpServletResponse response) {
        //允许跨域访问的代码，开发环境时开启，仅为了配合前端测试
        if ( ConfigProperties.getValue("evn").equals("localDev")) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }

        return ApiAdapterService.getApiResponse(baseRequest, request, response);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello bbs api!";
    }
}
