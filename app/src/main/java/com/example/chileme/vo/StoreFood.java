package com.example.chileme.vo;

public class StoreFood {

	private Integer foodId;
	private String foodName;
	private float price;
	private String photoSource;
	private float grade;
	private int peopleBuy;
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPhotoSource() {
		return photoSource;
	}
	public void setPhotoSource(String photoSource) {
		this.photoSource = photoSource;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	public int getPeopleBuy() {
		return peopleBuy;
	}
	public void setPeopleBuy(int peopleBuy) {
		this.peopleBuy = peopleBuy;
	}

}
