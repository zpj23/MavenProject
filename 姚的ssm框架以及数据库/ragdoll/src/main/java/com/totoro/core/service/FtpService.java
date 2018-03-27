package com.totoro.core.service;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTPClient;

import com.totoro.core.utils.ReadFtpProperties;

public interface FtpService {

	/*
     * 登录至FTP
     */
    public boolean loginFTP(FTPClient client, ReadFtpProperties rfp);

    /*
     * 退出ftp
     */
    public boolean logout(FTPClient  client);//

    /*
     * 上传文件到remotePath，其在ftp上的名字为inputStream
     */
    public boolean uploadFile(FTPClient client, String remotePath,
            String fileNewName, InputStream inputStream, ReadFtpProperties rfp);

    /*
     * 从目录remotePath，下载文件fileName
     */
    public InputStream downFileByFtp(FTPClient client, String remotePath,
            String fileName);

    /*
     * 删除ftp上的目录为pathName的文件
     */
    public boolean delFile(FTPClient client, String pathName);
}
