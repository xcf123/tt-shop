package com.qianfeng.controller;

import com.qianfeng.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;


@Controller
@Scope("prototype")
public class FileController {
    @Autowired
    private FileService fileService;

    //加载
    @ResponseBody
    @RequestMapping(value = "/file/upload",method = RequestMethod.GET)
    public void load(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("applicaton/json");

            String action=request.getParameter("action");
            if("config".equals(action)){
                PrintWriter out = response.getWriter();
                //读取类路径下的配置文件 resources/config.json
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.json");
                //将inputStream写入到输出流中
                IOUtils.copy(inputStream, out, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    public Map<String,Object> upfile(MultipartFile upfile){
        Map<String,Object> map=null;
        try {
            map=fileService.uploadImages(upfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
