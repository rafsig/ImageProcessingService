package com.sigwalt.microservice.imageProcessing.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sigwalt.microservice.imageProcessing.dto.ThumbnailDto;
import com.sigwalt.microservice.imageProcessing.form.ThumbnailForm;
import com.sigwalt.microservice.imageProcessing.service.CreateThumbnailService;

@RestController
@RequestMapping("/thumbnail")
public class ThumbnailGeneratorController {
	
	@Autowired
	CreateThumbnailService createThumbnailService;
	
	@PostMapping
	public ResponseEntity<ThumbnailDto> generateThumbnail(@RequestBody ThumbnailForm imageForm) throws IOException{
		return ResponseEntity.ok(createThumbnailService.execute(imageForm));
	}

}
