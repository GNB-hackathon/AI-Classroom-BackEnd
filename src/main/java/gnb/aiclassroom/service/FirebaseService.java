package gnb.aiclassroom.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;


@Service
public class FirebaseService {
    private String firebaseBucket = "ai-classroom-50619.appspot.com";

    public String uploadVidio (MultipartFile file, String warehouseName) throws IOException {

       //Logger logger = LoggerFactory.logger(FirebaseService.class);

        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);

        // InputStream으로 파일 내용 읽기
        try (InputStream content = file.getInputStream()) {
            Blob blob = bucket.create(warehouseName, content, file.getContentType());

            String url = blob.signUrl(365, TimeUnit.DAYS).toString();
            //logger.info(url);

            return url;
        }

    }

}
