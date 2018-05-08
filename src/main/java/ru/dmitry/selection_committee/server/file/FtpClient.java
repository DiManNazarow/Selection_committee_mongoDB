package ru.dmitry.selection_committee.server.file;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class FtpClient {

    private static final String SERVER = "91.144.179.161";

    private static final int PORT = 21;

    private static final String USER = "dimannazarow";

    private static final String PASSWORD = "4815162342";

    public interface UploadCallback {
        void onSuccessUpload(String url);
    }

    public interface DownloadCallback {
        void onDownloadSuccess(byte[] image);
    }

    public static void uploadFile(File file, String userId, UploadCallback uploadCallback) throws Exception {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASSWORD);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String fileName = String.format("file_%s", userId);
            InputStream load = new FileInputStream(file);
            boolean done = ftpClient.storeFile(fileName, load);
            if (done && uploadCallback != null){
                uploadCallback.onSuccessUpload(fileName);
            }
            load.close();

        } catch (Exception e){
            throw new Exception(e);
        } finally {
            try {
                if (ftpClient.isConnected()){
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (Exception e){
                throw new Exception(e);
            }
        }
    }

    public static void deleteFile(String name) throws Exception {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASSWORD);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            boolean delete = ftpClient.deleteFile(name);

        } catch (Exception e){
            throw new Exception(e);
        } finally {
            try {
                if (ftpClient.isConnected()){
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (Exception e){
                throw new Exception(e);
            }
        }
    }

    public static void downloadFile(String url, DownloadCallback downloadCallback) throws Exception {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASSWORD);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File file = new File("G:/JavaProject/Selection_Committee/src/main/resources/temp/" + url);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            boolean success = ftpClient.retrieveFile(url, outputStream);
            if (success && downloadCallback != null){
                byte[] image = new byte[4096];
                outputStream.write(image);
                downloadCallback.onDownloadSuccess(image);
            }
            outputStream.close();

        } catch (Exception e){
            throw new Exception(e);
        } finally {
            try {
                if (ftpClient.isConnected()){
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (Exception e){
                throw new Exception(e);
            }
        }
    }

}
