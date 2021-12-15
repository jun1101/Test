package com.audio.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.audio.VO.FileVO;



public class FTPUpLoader {
	//File revFile = new File("C:\\Users\\J\\Downloads\\eclipse.zip");
	//"192.168.204.4", 21, "������", "123123", revFile
	public static String host = "192.168.0.73";
	public static int port = 21;
	public static String user ="tester";
	public static String pwd = "123123";
	
	public static String byteCalculation(long bytes) {
        String retFormat = "0";
       long size = bytes;

        String[] s = { "bytes", "KB", "MB", "GB", "TB", "PB" };
        

        if (bytes != 0) {
              int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
              DecimalFormat df = new DecimalFormat("#,###.##");
              double ret = ((size / Math.pow(1024, Math.floor(idx))));
              retFormat = df.format(ret) + " " + s[idx];
         } else {
              retFormat += " " + s[0];
         }

         return retFormat;
}
	
	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if(replies != null && replies.length>0) {
			for(String aReply : replies) {
				System.out.println("Server : "+aReply);
			}
		}
	}
	
	public static String FtpUpLoad(File revFile, FileInputStream fis,String idx, String userrole) throws SocketException, IOException {
		
		FTPClient ftp = new FTPClient();
		boolean isSuccess = false;
		String[] folderidx = revFile.getName().split("_",3);
		ftp.setConnectTimeout(10000*60*60);
		
		try {
			ftp.setControlEncoding("UTF-8");
			
			int reply = 0;
			
			ftp.connect(host,port);
			
			reply = ftp.getReplyCode();
			
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return"error";
			}
			else {
				Date from = new Date();
				
				SimpleDateFormat nowDateHHmmss = new SimpleDateFormat("HHmmss");
				SimpleDateFormat nowDateymd = new SimpleDateFormat("yyMMdd");
				
				String nowHHmmss = nowDateHHmmss.format(from);
				String nowymd = nowDateymd.format(from);
				System.out.println("�����"+nowymd+nowHHmmss);
				ftp.login(user, pwd);
				showServerReply(ftp);
				
				ftp.enterLocalPassiveMode();
				//ftp.enterLocalActiveMode();
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
				if(userrole.equals("admin")) {
					ftp.makeDirectory("/Util/TEST/"+nowymd+"_"+folderidx[1]);
					showServerReply(ftp);
					
					ftp.changeWorkingDirectory("/Util/TEST/"+nowymd+"_"+folderidx[1]);
					showServerReply(ftp);
					
					isSuccess = ftp.storeFile(revFile.getName(), fis);
				}
				else {
					ftp.makeDirectory("/Util/TEST/"+nowymd+"_"+idx);
					showServerReply(ftp);
					
					ftp.changeWorkingDirectory("/Util/TEST/"+nowymd+"_"+idx);
					showServerReply(ftp);
					
					isSuccess = ftp.storeFile(idx+'_'+revFile.getName(), fis);
				}
				
				String filepath="/Util/TEST/"+nowymd+"_"+idx;
				//FileInputStream fis = new FileInputStream(revFile);
				
				showServerReply(ftp);
				if(isSuccess) {
					System.out.println("���ε� �Ϸ�");
				}
				
				ftp.disconnect();
				showServerReply(ftp);
				return filepath;
			}
		}
		catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			return"error";
		}
		catch (SocketException e) {
			// TODO: handle exception
			e.printStackTrace();
			return"error";
		}
		
	}	

	public static boolean download(FileVO param, HttpServletResponse response) throws SocketException, IOException, Exception {
        FileInputStream fis = null;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(10000*60*60);
        try {
            ftpClient.setControlEncoding("utf-8");
            ftpClient.connect(host, port);
            int reply = ftpClient.getReplyCode();
            
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new Exception(host+" FTP ���� ���� ����");
            }
            
            
            ftpClient.setSoTimeout(10000*60*60);
            ftpClient.login(user, pwd);
            showServerReply(ftpClient);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalActiveMode();
            
            String ftpPath = param.getFilepath()+"/"+param.getFilename();
            boolean success  = false;
            OutputStream outputStream2 = new BufferedOutputStream(response.getOutputStream());
            InputStream inputStream = ftpClient.retrieveFileStream(ftpPath);
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream2.write(bytesArray, 0, bytesRead);
            }
 
            success = ftpClient.completePendingCommand();
            outputStream2.close();
            inputStream.close();
            
            return success;
            
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }
	
}
