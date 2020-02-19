package br.com.verde.blue.model;

import java.io.Serializable;

public class PrintScreen implements Serializable {

	private static final long serialVersionUID = 1L;
	private int clientId;
	private int questionId;
	private int imageId;
	private String comments;
	private String imagePath;
	private String imageName;
	private Double latitude;
	private Double longitude;
	
	public PrintScreen() {

	}

	public PrintScreen(int imageId, int clientId, int questionId, String comments,
			String imagePath, String imageName) {
		this.imageId = imageId;
		this.clientId = clientId;
		this.questionId = questionId;
		this.comments = comments;
		this.imageName = imageName;
		this.imagePath = imagePath;
	}

	public int getPictureId() {
		return imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imagename) {
		this.imageName = imagename;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	public int getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}