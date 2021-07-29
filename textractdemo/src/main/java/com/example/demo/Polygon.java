package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Polygon{
    @JsonProperty("X") 
    public double x;
    @JsonProperty("Y") 
    public double y;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
    
    
}
