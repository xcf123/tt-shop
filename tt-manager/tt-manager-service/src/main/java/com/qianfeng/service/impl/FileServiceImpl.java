package com.qianfeng.service.impl;

import com.qianfeng.service.FileService;
import com.qianfeng.utils.FtpUtils;
import com.qianfeng.utils.IDUtils;
import com.qianfeng.utils.Prop;
import com.qianfeng.utils.PropKit;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Map<String, Object> uploadImages(MultipartFile upfile) {
        Map<String,Object> map=new HashMap<>();
        try {
            Prop prop=PropKit.use("managerwebftp.properties");

            String host = prop.get("ftp.host");
            int port = prop.getInt("ftp.port");
            String username = prop.get("ftp.username");
            String password = prop.get("ftp.password");
            String bashPath = prop.get("ftp.basePath");
            String filePath =new DateTime().toString("/yyyy/MM/dd");
            String filename=upfile.getOriginalFilename();
            String type=filename.substring(filename.lastIndexOf("."));
            filename = IDUtils.genImageName()+type;

            boolean b=FtpUtils.uploadFile(host,port,username,password,bashPath,filePath,filename,upfile.getInputStream());
           if(b){
               map.clear();
               map.put("state","SUCCESS");
               map.put("title",filename);
               map.put("original",upfile.getOriginalFilename());
               map.put("type",type);
               map.put("url","/images"+filePath+"/"+filename);
               map.put("size",upfile.getSize());
           }

        } catch (Exception e) {
            e.printStackTrace();
        }




        return map;
    }
}
