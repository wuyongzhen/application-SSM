/**
 * @Author By: Wu Yongzhen
 * @Description
 * @Data 17:21 2018/3/27
 * @Modified By:
 **/
package com.ssgm.application.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 下载  测试
 *
 * @author 武永振
 */
@Controller
@RequestMapping("file")
public class UploadController {

    @RequestMapping("skipUploadPage")
    public String skipUploadPage() {
        return "shang";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        File dir = new File(path, fileName);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        file.transferTo(dir);
        return fileName;
    }

    @RequestMapping("/down")
    public void down(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String fileName = request.getSession().getServletContext().getRealPath("upload") + "/101.jpg";

        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));

        String filename = "下载文件.jpg";

        filename = URLEncoder.encode(filename, "UTF-8");

        response.addHeader("Content-Disposition", "attachment;filename=" + filename);

        response.setContentType("multipart/form-data");

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while ((len = bis.read()) != -1) {
            out.write(len);
            out.flush();
        }
        out.close();
    }
}