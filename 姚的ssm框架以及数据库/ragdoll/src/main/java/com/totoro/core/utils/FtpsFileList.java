package com.totoro.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class FtpsFileList {

	 private static final Logger LOG = LoggerFactory.getLogger(FtpsFileList.class);

	    public static void main(String[] args) {
	        listFileNames("192.168.117.135", 21, "root", "111111", "/opt");
	    }

	    private static List<String> listFileNames(String host, int port, String username, final String password, String dir) {
	        List<String> list = new ArrayList<String>();
	        ChannelSftp sftp = null;
	        Channel channel = null;
	        Session sshSession = null;
	        try {
	            JSch jsch = new JSch();
	            jsch.getSession(username, host, port);
	            sshSession = jsch.getSession(username, host, port);
	            sshSession.setPassword(password);
	            Properties sshConfig = new Properties();
	            sshConfig.put("StrictHostKeyChecking", "no");
	            sshSession.setConfig(sshConfig);
	            sshSession.connect();
	            LOG.debug("Session connected!");
	            channel = sshSession.openChannel("sftp");
	            channel.connect();
	            LOG.debug("Channel connected!");
	            sftp = (ChannelSftp) channel;
	            Vector<?> vector = sftp.ls(dir);
	            for (Object item:vector) {
	                LsEntry entry = (LsEntry) item;
	                System.out.println(entry.getFilename());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            closeChannel(sftp);
	            closeChannel(channel);
	            closeSession(sshSession);
	        }
	        return list;
	    }

	    private static void closeChannel(Channel channel) {
	        if (channel != null) {
	            if (channel.isConnected()) {
	                channel.disconnect();
	            }
	        }
	    }

	    private static void closeSession(Session session) {
	        if (session != null) {
	            if (session.isConnected()) {
	                session.disconnect();
	            }
	        }
	    }
}
