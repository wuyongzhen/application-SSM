package com.ssgm.application.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Announcement {
    private Integer id;

    private String fileName;

    private String content;

    private Date uploadTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date neeqTime;

    private Long fileSize;

    private Byte del;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getNeeqTime() {
        return neeqTime;
    }

    public void setNeeqTime(Date neeqTime) {
        this.neeqTime = neeqTime;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Byte getDel() {
        return del;
    }

    public void setDel(Byte del) {
        this.del = del;
    }
}