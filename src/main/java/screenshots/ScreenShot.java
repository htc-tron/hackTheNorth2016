package screenshots;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.google.api.services.vision.v1.model.EntityAnnotation;

import imageSearching.DetectImage;


public class ScreenShot {

	public static void main(String[] args) throws IOException, GeneralSecurityException {

		// get default webcam and open it
		DetectImage detect=new DetectImage(DetectImage.getVisionService());
		Webcam webcam = Webcam.getDefault();
		webcam.open();

		BufferedImage image = webcam.getImage();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( image, "jpg", baos );
		
		 List<EntityAnnotation> labels = detect.identifyLabel(baos.toByteArray());

		// save image to PNG file
		 if(labels!=null){
		 System.out.printf("Found %d label%s\n", labels.size(), labels.size() == 1 ? "" : "s");
		    for (EntityAnnotation annotation : labels) {
		      System.out.printf("\t%s\n", annotation.getDescription());
		    }  
		 }
		
		webcam.close();
	}
}
