package com.sigwalt.microservice.imageProcessing.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import com.sigwalt.microservice.imageProcessing.dto.ThumbnailDto;
import com.sigwalt.microservice.imageProcessing.form.ThumbnailForm;



@Service
public class CreateThumbnailService {

	public ThumbnailDto execute(ThumbnailForm imageForm) throws IOException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(imageForm.getB64Image()));
		BufferedImage bi = ImageIO.read(byteArrayInputStream);
		String mimeType = new Tika().detect(byteArrayInputStream);
		int originalWidth = bi.getWidth();
		int originalHeight = bi.getHeight();
		int maxDimension = imageForm.getMaxDimension();
		int scaledHeight;
		int scaledWidth;
		//TODO extract image scaling rules
		if(originalHeight < originalWidth) {
			scaledWidth = maxDimension;
			scaledHeight = (int) Math.round(originalHeight*(maxDimension/ (1.0d * originalWidth)));
		}
		else if(originalHeight > originalWidth) {
			scaledWidth = (int) Math.round(originalWidth*(maxDimension/ (1.0d* originalHeight)));
			scaledHeight = maxDimension;
		}
		else {
			scaledWidth = maxDimension;
			scaledHeight = maxDimension;
		}
		
		Image scaledImage = bi.getScaledInstance(scaledWidth,  scaledHeight, BufferedImage.SCALE_SMOOTH);
		BufferedImage scaledBi = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
		scaledBi.createGraphics().drawImage(scaledImage, 0, 0, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(scaledBi,"jpg", baos);
		String b64 = Base64.getEncoder().encodeToString(baos.toByteArray());
		return new ThumbnailDto(b64, mimeType);
	}
}
