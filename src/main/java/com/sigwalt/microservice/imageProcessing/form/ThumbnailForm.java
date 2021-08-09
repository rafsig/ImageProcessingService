package com.sigwalt.microservice.imageProcessing.form;

public class ThumbnailForm {
	
	String b64Image;
	int maxDimension;

	public String getB64Image() {
		return b64Image;
	}
	public void setB64Image(String b64Image) {
		this.b64Image = b64Image;
	}
	public int getMaxDimension() {
		return maxDimension;
	}
	public void setMaxDimension(int scaleWidth) {
		this.maxDimension = scaleWidth;
	}
	
	
}
