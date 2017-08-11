package com.bah91;

import com.bah91.domain.ImageContainer;
import com.bah91.services.FileService;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by bah91 on 10/08/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTests {

    @Autowired
    FileService fileService;

    @Test
    public void test_Can_Authenticate() throws DbxException {
        DbxClientV2 client = fileService.getClient();
        System.out.println(client.users().getCurrentAccount());
        assertNotNull(client.users().getCurrentAccount());
    }

    @Test
    public void test_Can_Get_FileList() {
        ArrayList<ImageContainer> images = fileService.getFiles();
        System.out.println(images);
        assertNotNull(images);
    }

    @Test
    public void test_Can_Upload_File() {
        String filename = UUID.randomUUID().toString() + ".jpg";
        FileMetadata metadata =
                fileService.uploadFile(filename, "/tmp/wedding/test.jpg");
        assertEquals(filename, metadata.getName());
    }

    @Test
    public void test_Can_Upload_File_From_URL() {
        fileService.processImage("http://dreamatico.com/data_images/bear/bear-5.jpg");
    }

}
