package com.bah91.services;

import com.bah91.domain.ImageContainer;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by bah91 on 10/08/17.
 */
@Service
public class FileService {

    private DbxClientV2 client;

    @Value("${dropboxAccessToken}")
    private String ACCESS_TOKEN;

    @Value("${dropboxFilePath}")
    private String DROPBOX_FILE_PATH;

    @Value("${tempFilePath}")
    private String tempFileDir;

    private static final String FORWARD_SLASH = "/";
    private static final String FILE_EXTENSION = ".jpg";
    private static final String FILE_FORMAT = "jpg";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private void createClient(){
        DbxRequestConfig requestConfig = new DbxRequestConfig("twitter-pic-stream");
        client = new DbxClientV2(requestConfig, ACCESS_TOKEN);
    }

    public DbxClientV2 getClient() {
        if(client==null) createClient();
        return client;
    }


    public ArrayList<ImageContainer> getFiles() {
        try {
            ArrayList<ImageContainer> images = new ArrayList<ImageContainer>();

            ListFolderResult folderResult = getClient().files().listFolder(DROPBOX_FILE_PATH);
            List<Metadata> entries =  folderResult.getEntries();

            for(Metadata entry : entries) {
                GetTemporaryLinkResult tempLink = getClient()
                        .files().getTemporaryLink(entry.getPathDisplay());
                ImageContainer image = new ImageContainer(entry.getName(), tempLink.getLink());
                images.add(image);
            }

            return images;

        } catch (DbxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FileMetadata uploadFile(String filename, String path) {
        try (InputStream in = new FileInputStream(path)) {
            return getClient().files()
                    .uploadBuilder(DROPBOX_FILE_PATH
                            + FORWARD_SLASH
                                + filename)
                    .uploadAndFinish(in);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean processImage(String imageUrl) {
        try {
            RenderedImage image = fetchImage(imageUrl);
            String uuid = UUID.randomUUID().toString();
            String filename = uuid + FILE_EXTENSION;
            String path = tempFileDir  + filename;
            writeTempFile(image, path);
            uploadFile(filename, path);
            return true;
        } catch (IOException ex){
            log.error("Could not process image("
                    + imageUrl + "): " + ex.getMessage());
            return false;
        }
    }

    private RenderedImage fetchImage(String imageUrl) throws IOException {
            return ImageIO.read(new URL(imageUrl));
    }

    private void writeTempFile(RenderedImage image, String path) throws IOException {
        File temp = new File(path);
        ImageIO.write(image, FILE_FORMAT, temp);
    }
}
