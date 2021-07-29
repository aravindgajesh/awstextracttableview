package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
public class BoundingBox{
    @JsonProperty("Width") 
    public double width;
    @JsonProperty("Height") 
    public double height;
    @JsonProperty("Left") 
    public double left;
    @JsonProperty("Top") 
    public double top;
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getTop() {
		return top;
	}
	public void setTop(double top) {
		this.top = top;
	}
    
    
}