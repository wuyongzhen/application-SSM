/**
 * @Author By: Wu Yongzhen
 * @Description 文件上传下载
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.Page;
import com.ssgm.application.entity.Announcement;
import com.ssgm.application.entity.CustomerFeedback;
import com.ssgm.application.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 下载  测试
 *
 * @author 武永振
 */
@Controller
@RequestMapping("announcement")
public class UploadController {
    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping("skipUploadPage")
    public String skipUploadPage() {
        return "upload";
    }

    @RequestMapping("skipAnnouncementPage")
    public String skipAnnouncementPage() {
        return "announcement";
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 查询公告列表
     * @Date 17:14 2018/3/28
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> findList(
            @RequestParam(value = "parameter", defaultValue = "") String parameter,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Announcement> page = announcementService.findList(parameter, pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageData", page);
        map.put("number", page.getTotal());
        return map;
    }

    /**
     * @Author By:Wu Yongzhen
     * @Description 添加公告
     * @Date 11:28 2018/3/28
     */
    @RequestMapping(value = "/addAnnouncement", method = RequestMethod.POST)
    @ResponseBody
    public int addAnnouncement(@RequestBody Announcement Announcement) throws IOException {
        Announcement.setUploadTime(new Date());
        int i = announcementService.insertAnnouncement(Announcement);
        return i;
    }

    @RequestMapping("upload")
    public boolean upload(HttpServletRequest request, MultipartFile file) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("UI/announcement");
        String fileName = file.getOriginalFilename();
        File dir = new File(path, fileName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);
        return true;
    }

    @RequestMapping(value = "/delAnnouncement", method = RequestMethod.GET)
    public int delAnnouncement(Announcement announcement) {
        return announcementService.delAnnouncement(announcement);
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