package prasad.assign3;
/**
 * 
 * @author Swathi
 *
 */
import java.sql.Date;
import java.util.Comparator;

public class Picture implements Comparator<Picture> {
	
	private String caption;
	private String path;
	private String picName;
	private String photographer;
	private String location;
	private java.util.Date date;
	private String description;
	private String category;
	
	/**
	 * Class Constructors
	 */
	public Picture() {
		super();
	}
	public Picture(String caption, String path, String picName,
			String description, java.util.Date date, String category, String photographer,String location) {
		super();
		this.caption = caption;
		this.path = path;
		this.picName = picName;
		this.description = description;
		this.date = date;
		this.category = category;
		this.photographer = photographer;
		this.location = location;
	}
	
	public String getPhotographer() {
		return photographer;
	}
	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date theDate) {
		this.date = theDate;
	}
	@Override
	public int compare(Picture o1, Picture o2) {
		
		return o1.getDate().compareTo(o2.getDate());
	}
	
}
