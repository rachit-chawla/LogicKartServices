package org.gl.project.Models;

import javax.persistence.Entity;

@Entity
public class Magazine extends Product {

	private String magazine_Author;
	
	private String magazine_Description;
	
	private String magazine_Publisher;
	
	private Integer magazine_Publishing_Year;

	public String getMagazine_Author() {
		return magazine_Author;
	}

	public void setMagazine_Author(String magazine_Author) {
		this.magazine_Author = magazine_Author;
	}

	public String getMagazine_Description() {
		return magazine_Description;
	}

	public void setMagazine_Description(String magazine_Description) {
		this.magazine_Description = magazine_Description;
	}

	public String getMagazine_Publisher() {
		return magazine_Publisher;
	}

	public void setMagazine_Publisher(String magazine_Publisher) {
		this.magazine_Publisher = magazine_Publisher;
	}

	public Integer getMagazine_Publishing_Year() {
		return magazine_Publishing_Year;
	}

	public void setMagazine_Publishing_Year(Integer magazine_Publishing_Year) {
		this.magazine_Publishing_Year = magazine_Publishing_Year;
	}
}