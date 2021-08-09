package com.sigwalt.microservice.imageProcessing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sigwalt.microservice.imageProcessing.dto.ThumbnailDto;
import com.sigwalt.microservice.imageProcessing.form.ThumbnailForm;
import com.sigwalt.microservice.imageProcessing.service.CreateThumbnailService;

@SpringBootTest
public class CreateThumbnailServiceTest {
	

	@Autowired
	CreateThumbnailService thumbnailGeneratorService;
	
	ThumbnailForm thumbnailForm;
	
	String pathToPictures = "/test-pictures/";
	
	@BeforeEach
	public void before() {
		thumbnailForm = new ThumbnailForm();
	}
	
	@Test
	public void thumbnailWidthOnWidePictureExpectedToBe640() throws IOException {
		String pictureName = "widePicture.jpg";
		InputStream picture = this.getClass().getResourceAsStream(pathToPictures + pictureName);
		String pictureB64 = Base64.getEncoder().encodeToString(picture.readAllBytes());
		thumbnailForm.setB64Image(pictureB64);
		thumbnailForm.setMaxDimension(640);
		ThumbnailDto thumbnailDto = thumbnailGeneratorService.execute(thumbnailForm);
		String thumbnailB64 = thumbnailDto.getB64();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(thumbnailB64));
		BufferedImage thumbnail = ImageIO.read(byteArrayInputStream);
		int width = thumbnail.getWidth();
		int height = thumbnail.getHeight();
		Assertions.assertEquals(640, width);
		Assertions.assertEquals(true, width > height);
	}
	
	@Test
	public void thumbnailHeightOnTallPictureExpectedToBe640() throws IOException {
		InputStream picture = this.getClass().getResourceAsStream("/test-pictures/tallPicture.jpg");
		String pictureB64 = Base64.getEncoder().encodeToString(picture.readAllBytes());
		thumbnailForm.setB64Image(pictureB64);
		thumbnailForm.setMaxDimension(640);
		ThumbnailDto thumbnailDto = thumbnailGeneratorService.execute(thumbnailForm);
		String thumbnailB64 = thumbnailDto.getB64();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(thumbnailB64));
		BufferedImage thumbnail = ImageIO.read(byteArrayInputStream);
		int width = thumbnail.getWidth();
		int height = thumbnail.getHeight();
		Assertions.assertEquals(640, height);
		Assertions.assertEquals(true, width < height);
	}
}
