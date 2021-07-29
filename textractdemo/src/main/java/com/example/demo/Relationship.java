package com.example.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Relationship{
    @JsonProperty("Type") 
    public String type;
    @JsonProperty("Ids") 
    public List<String> ids;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
    
    
}