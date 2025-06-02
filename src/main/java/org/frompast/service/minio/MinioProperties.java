package org.frompast.service.minio;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    String url;
    String bucket;
    String publicFolder = "public";
    String loadAttachmentBufferFolder = "load_attachment_buffer";
}
