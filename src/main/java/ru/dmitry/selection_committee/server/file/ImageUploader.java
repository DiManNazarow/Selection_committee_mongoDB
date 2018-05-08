package ru.dmitry.selection_committee.server.file;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;

import java.io.*;

public class ImageUploader implements Upload.Receiver, Upload.SucceededListener {

    public File file;

    private Image image;

    public ImageUploader(Image image){
        this.image = image;
    }

    @Override
    public OutputStream receiveUpload(String fileName, String mimeType) {
        FileOutputStream fos = null;
        try {
            file = new File("G:/JavaProject/Selection_Committee/src/main/resources/temp/" + fileName);
            fos = new FileOutputStream(file);
        } catch (Exception e){
            new Notification(e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
            return null;
        }
        return fos;
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        try {
            FtpClient.uploadFile(file, "tftdfg", new FtpClient.UploadCallback() {
                @Override
                public void onSuccessUpload(String fileName) {
                    try {
                        FtpClient.downloadFile(fileName, new FtpClient.DownloadCallback() {
                            @Override
                            public void onDownloadSuccess(byte[] imageS) {
                                image.setVisible(true);
                                image.setSource(new StreamResource(getStream(imageS), "streamedSourceFromByteArray"));
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
//        image.setVisible(true);
//        image.setSource(new FileResource(file));
    }

    public static StreamResource.StreamSource getStream(byte[] image){
        return (StreamResource.StreamSource) () -> new ByteArrayInputStream(image);
    }

}

