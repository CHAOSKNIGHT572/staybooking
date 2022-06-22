package com.example.staybooking.service;

import com.example.staybooking.exception.GCSUploadException;
import com.google.api.client.util.Value;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class ImageStorageService {

    @Value("${gcs.bucket")
    private String bucketName;

    private Storage storage;

    @Autowired
    public ImageStorageService(Storage storage) {
        this.storage = storage;
    }

    public String save(MultipartFile file) throws GCSUploadException {
        String filename = UUID.randomUUID().toString();
        BlobInfo blobInfo = null;

        try {
            blobInfo = storage.createFrom(
                    BlobInfo.newBuilder(bucketName, filename)
                            .setContentType("/img/jpeg")
                            .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                            .build(), file.getInputStream()
            );
        } catch (IOException e) {
            throw new GCSUploadException("Failed to upload to GCS!!!");
        }
        return blobInfo.getMediaLink();
    }
}
