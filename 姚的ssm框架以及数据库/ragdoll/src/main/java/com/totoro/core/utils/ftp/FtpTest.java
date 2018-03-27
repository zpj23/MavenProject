package com.totoro.core.utils.ftp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpTest {
	
	
	public static void main(String[] args) {
		System.out.println("--------begin-----");
		FtpTest a = new FtpTest();
		a.uploadFile();
		System.out.println("-------end------");
	}
	
	
    /** 
     * 向ftp写文件(数据) 
     */  
      
    public void uploadFile() {  
   
        // 要写入的文件内容  
        String fileContent = "hello world，你好世界";  
        // ftp登录用户名  
        String userName = "root";  
        // ftp登录密码  
        String userPassword = "111111";  
        // ftp地址  
        String server = "192.168.11.60";//直接ip地址  
        // 创建的文件  
        String fileName = "ftp.txt";  
        // 指定写入的目录  
        String path = "cc";  
   
        FTPClient ftpClient = new FTPClient();  
        try {  
            InputStream is = null;  
            // 1.输入流  
            is = new ByteArrayInputStream(fileContent.getBytes());  
            // 2.连接服务器  
            ftpClient.connect(server);  
            // 3.登录ftp  
            ftpClient.login(userName, userPassword);  
            // 4.指定写入的目录  
            ftpClient.changeWorkingDirectory(path);  
            // 5.写操作  
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  
            ftpClient.storeFile(new String(fileName.getBytes("utf-8"),  
                    "iso-8859-1"), is);  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (ftpClient.isConnected()) {  
                try {  
                    ftpClient.disconnect();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
      
    /** 
     * ftp下载数据 
     */  
      
    public void downFile() {  
        // ftp登录用户名  
        String userName = "admin";  
        // ftp登录密码  
        String userPassword = "xxxx";  
        // ftp地址:直接IP地址  
        String server = "xxxx";  
        // 创建的文件  
        String fileName = "ftp.txt";  
        // 指定写入的目录  
        String path = "wd";  
        // 指定本地写入文件  
        String localPath="D:\\";  
          
        FTPClient ftp = new FTPClient();  
        try {  
            int reply;  
            //1.连接服务器  
            ftp.connect(server);  
            //2.登录服务器 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
            ftp.login(userName, userPassword);  
            //3.判断登陆是否成功  
            reply = ftp.getReplyCode();  
            if (!FTPReply.isPositiveCompletion(reply)) {  
                ftp.disconnect();  
            }  
            //4.指定要下载的目录  
            ftp.changeWorkingDirectory(path);// 转移到FTP服务器目录  
            //5.遍历下载的目录  
            FTPFile[] fs = ftp.listFiles();  
            for (FTPFile ff : fs) {  
                //解决中文乱码问题，两次解码  
                byte[] bytes=ff.getName().getBytes("iso-8859-1");  
                String fn=new String(bytes,"utf8");  
                if (fn.equals(fileName)) {  
                    //6.写操作，将其写入到本地文件中  
                    File localFile = new File(localPath + ff.getName());  
                    OutputStream is = new FileOutputStream(localFile);  
                    ftp.retrieveFile(ff.getName(), is);  
                    is.close();  
                }  
            }  
            ftp.logout();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (ftp.isConnected()) {  
                try {  
                    ftp.disconnect();  
                } catch (IOException ioe) {  
                }  
            }  
        }  
    }  
 }  