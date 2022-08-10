package sg.bowling.bowlingapp.filestore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class FileStore {
	private final AmazonS3 s3;
	
	@Autowired
	public FileStore(AmazonS3 s3) {
		this.s3 = s3;
	}
	
	public void save(String path, String filename, String contentType, long contentLength, InputStream inputStream) {
		ObjectMetadata metadata = new ObjectMetadata();
		
		if (! (contentType.isEmpty() | contentLength <=0) ) {
			metadata.setContentType(contentType);
			metadata.setContentLength(contentLength);
		}
		
		try {
			s3.putObject(path, filename, inputStream, metadata);
		} catch(AmazonServiceException e) {
			throw new IllegalStateException("Failed to store file to S3", e);
		}
	}

	public byte[] download(String fullPath, String key) {
		// TODO Auto-generated method stub
		try {
			S3Object object = s3.getObject(fullPath, key);
			S3ObjectInputStream inputStream = object.getObjectContent();
			return IOUtils.toByteArray(inputStream);
			
		} catch(AmazonServiceException | IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
