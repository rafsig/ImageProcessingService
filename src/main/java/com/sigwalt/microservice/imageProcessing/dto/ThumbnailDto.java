package com.sigwalt.microservice.imageProcessing.dto;

public class ThumbnailDto {
	
	private String b64;
	private String fileFormat;
	
	public ThumbnailDto(String b64, String fileFormat) {
		this.fileFormat = fileFormat;
		this.b64 = b64;
	}

	public String getB64() {
		return b64;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}	
}
