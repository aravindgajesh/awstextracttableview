package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentMetadata{
    @JsonProperty("Pages") 
    public int pages;

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
    
    
}

