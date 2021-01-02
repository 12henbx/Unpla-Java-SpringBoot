package com.unpla.service.cloud;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadFileGCP {
    public static void uploadObject(
            String projectId, String bucketName, String objectName, byte[] file) throws IOException {
        // The ID of your GCP project
        // String projectId = "your-project-id" "unpla-vision-api";

        // The ID of your GCS bucket
        // String bucketName = "your-unique-bucket-name" "unpla-photo-storage";

        // The ID of your GCS object
        // String objectName = "your-object-name";

        // The path to your file to upload
        // String filePath = "path/to/your/file"

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("E:\\Project Bootcamp Blibli Future batch 4\\Project Phase 3\\unpla-command-pattern\\unpla-command-pattern-properties\\src\\main\\resources\\unpla-vision-api-6f93f59fe705.json")))
                .build().getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//        storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));
        storage.create(blobInfo, file);

        System.out.println(
                "uploaded to bucket " + bucketName + " as " + objectName);
    }

}
