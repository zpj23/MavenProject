package com.totoro.core.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.totoro.core.utils.ReadFtpProperties;
public class FtpServiceImpl {

	
	public boolean loginFTP(FTPClient client, ReadFtpProperties rfp) {
        String ftpIp = rfp.getIp();
        String ftpPort = rfp.getPort();
        String ftpUser = rfp.getUser();
        String ftpPwd = rfp.getPwd();
        // String fgtpRemotePath = rfp.getRemotePath();
        boolean b = false;

        try {
            client.connect(ftpIp, Integer.parseInt(ftpPort));
        } catch (NumberFormatException e) {
            System.out.println("无法连接到ftp");
            return false;
        } catch (SocketException e) {
            System.out.println("无法连接到ftp");
            return false;
        } catch (IOException e) {
            System.out.println("无法连接到ftp");
            return false;
        }
        client.setControlEncoding("uft-8");
        try {
            b = client.login(ftpUser, ftpPwd);
        } catch (IOException e) {
            System.out.println("登录ftp出错");
            logout(client);// 退出/断开FTP服务器链接
            return false;
        }
        return b;

    }

    public boolean logout(FTPClient client) {
        boolean b = false;

        try {
            b = client.logout();// 退出登录
            client.disconnect();// 断开连接
        } catch (IOException e) {
            return false;
        }
        return b;

    }

    public boolean uploadFile(FTPClient client, String remotePath,
            String fileNewName, InputStream inputStream, ReadFtpProperties rfp) {
        boolean b = false;
        try {
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();
            if (remotePath != null && !"".equals(remotePath.trim())) {
                String[] pathes = remotePath.split("/");
                for (String onepath : pathes) {
                    if (onepath == null || "".equals(onepath.trim())) {
                        continue;
                    }

                    onepath = new String(onepath.getBytes("utf-8"),
                            "iso-8859-1");
                    System.out.println("onepath=" + onepath);
                    if (!client.changeWorkingDirectory(onepath)) {
                        client.makeDirectory(onepath);// 创建FTP服务器目录
                        client.changeWorkingDirectory(onepath);// 改变FTP服务器目录
                    } else {
                        System.out.println("文件单路径");
                    }
                }
            }
            b = client.storeFile(new String(fileNewName.getBytes("utf-8"),
                    "iso-8859-1"), inputStream);
        } catch (UnsupportedEncodingException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return b;
    }

    public InputStream downFileByFtp(FTPClient ftpClient, String remotePath,
            String fileName) {

        FTPFile[] fs;
        InputStream is = null;
        try {
            // 设置被动模式
            ftpClient.enterLocalPassiveMode();
            // 设置以二进制流的方式传输
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 设置编辑格式
            ftpClient.setControlEncoding("utf-8");

            remotePath = remotePath.substring(0,
                    remotePath.lastIndexOf(fileName));
            fs = ftpClient.listFiles(remotePath);// 递归目标目录
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {// 查找目标文件
                    is = ftpClient.retrieveFileStream(new String(
                            (remotePath + fileName).getBytes("utf-8"),
                            "iso-8859-1"));
                    break;
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        return is;

    }

    public boolean delFile(FTPClient ftpClient, String pathName) {
        boolean b = false;

        try {
            b = ftpClient.deleteFile(pathName);

            return b;
        } catch (Exception e) {
            return false;
        } finally {
            logout(ftpClient);// 退出/断开FTP服务器链接
        }

    }

	
}
