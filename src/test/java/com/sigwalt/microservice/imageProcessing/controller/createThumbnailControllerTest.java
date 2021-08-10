package com.sigwalt.microservice.imageProcessing.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.sigwalt.microservice.imageProcessing.dto.ThumbnailDto;
import com.sigwalt.microservice.imageProcessing.form.ThumbnailForm;
import com.sigwalt.microservice.imageProcessing.service.CreateThumbnailService;

@SpringBootTest
public class createThumbnailControllerTest {
	
	String pathToPictures = "/test-pictures/";
	ThumbnailForm thumbnailForm;

	@Mock
	CreateThumbnailService createThumbnailService;
	
	@InjectMocks
	ThumbnailGeneratorController thumbnailGenerator;
	
	@BeforeEach
	public void before() {
		thumbnailForm = new ThumbnailForm();
	}
	
	@Test
	public void responseIs200WhenCreatingThumbnail() throws IOException {
		Assertions.assertNotNull(createThumbnailService);
		String pictureName = "widePicture.jpg";
		InputStream picture = this.getClass().getResourceAsStream(pathToPictures + pictureName);
		String pictureB64 = Base64.getEncoder().encodeToString(picture.readAllBytes());
		thumbnailForm.setB64Image(pictureB64);
		thumbnailForm.setMaxDimension(640);
		ResponseEntity<ThumbnailDto> responseEntity = thumbnailGenerator.generateThumbnail(thumbnailForm);
		
		Assertions.assertEquals(200, responseEntity.getStatusCode().value());   
		
		
	}
}
