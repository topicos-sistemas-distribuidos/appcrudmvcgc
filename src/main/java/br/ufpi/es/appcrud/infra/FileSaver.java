package br.ufpi.es.appcrud.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobTargetOption;
import com.google.cloud.storage.Storage.PredefinedAcl;
import com.google.cloud.storage.StorageOptions;

@Component
public class FileSaver {
	@Autowired 
	private HttpServletRequest request;
	private Storage storage;
	private StorageOptions storageOptions;
	
	private void init() {
		try {
			storageOptions = StorageOptions.newBuilder()
					.setProjectId("my-spring-mvc")
					.setCredentials(GoogleCredentials.fromStream(new FileInputStream("/Users/armandosoaressousa/Desktop/my-spring-mvc-3fc1f59f9751.json"))).build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		storage = storageOptions.getService();
	}
	
	//private static Storage storage = StorageOptions.getDefaultInstance().getService();
	
	public String write(String baseFolder, MultipartFile file) {
        try {
        	String realPath = request.getServletContext().getRealPath("/" + baseFolder);
        	System.out.println("realPath" + realPath);
            String path = realPath + "/" + file.getOriginalFilename();
            System.out.println("path: " + path);
            file.transferTo(new File(path));
            System.out.println("Caminho gravado no banco: " + baseFolder + "/" + file.getOriginalFilename());
            return baseFolder + "/" + file.getOriginalFilename();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
		
	public String write(MultipartFile file) {
		BlobInfo blobInfo = null;
		
		init();
		try {
			blobInfo = storage.create(BlobInfo.newBuilder("meu-bucket-files", file.getOriginalFilename()).build(), file.getBytes(),
																BlobTargetOption.predefinedAcl(PredefinedAcl.PUBLIC_READ));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return blobInfo.getMediaLink();
	}
	
}
