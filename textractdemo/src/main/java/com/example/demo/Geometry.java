package com.example.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Geometry{
    @JsonProperty("BoundingBox") 
    public BoundingBox boundingBox;
    @JsonProperty("Polygon") 
    public List<Polygon> polygon;
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	public List<Polygon> getPolygon() {
		return polygon;
	}
	public void setPolygon(List<Polygon> polygon) {
		this.polygon = polygon;
	}
    
    
}
