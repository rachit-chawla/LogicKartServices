package org.gl.project.Models;

import javax.persistence.Entity;

@Entity
public class OpticalMedia extends Product
{
	private String opticalMediaType;
	
	private String opticalMediaCategory;
	
	private String opticalMediaDescription;

	public String getOpticalMediaType() {
		return opticalMediaType;
	}

	public void setOpticalMediaType(String opticalMediaType) {
		this.opticalMediaType = opticalMediaType;
	}

	public String getOpticalMediaCategory() {
		return opticalMediaCategory;
	}

	public void setOpticalMediaCategory(String opticalMediaCategory) {
		this.opticalMediaCategory = opticalMediaCategory;
	}

	public String getOpticalMediaDescription() {
		return opticalMediaDescription;
	}

	public void setOpticalMediaDescription(String opticalMediaDescription) {
		this.opticalMediaDescription = opticalMediaDescription;
	}
}