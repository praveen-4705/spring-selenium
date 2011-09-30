package domain;

import java.io.Serializable;

public class Product implements Serializable{
	
	private String description;
	private Double price;
    private int id;
	
    public void setId(int id) {
		this.id = id;
	}
    
    public int getId() {
		return id;
	}
    
	public String getDescription() {
		return description;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
        buffer.append("Description: " + description + ";");
        buffer.append("Price: " + price);
        return buffer.toString();
	}	
}
