package hibernate;
// Generated 2017-3-9 20:18:50 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * Floodworksum generated by hbm2java
 */
public class Floodworksum implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String year;
	private String bzperson;
	private Date bztime;
	private String spperson;
	private Date sptime;
	private String accessory;

	public Floodworksum() {
	}

	public Floodworksum(String year, String bzperson, Date bztime, String spperson, Date sptime, String accessory) {
		this.year = year;
		this.bzperson = bzperson;
		this.bztime = bztime;
		this.spperson = spperson;
		this.sptime = sptime;
		this.accessory = accessory;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBzperson() {
		return this.bzperson;
	}

	public void setBzperson(String bzperson) {
		this.bzperson = bzperson;
	}

	public Date getBztime() {
		return this.bztime;
	}

	public void setBztime(Date bztime) {
		this.bztime = bztime;
	}

	public String getSpperson() {
		return this.spperson;
	}

	public void setSpperson(String spperson) {
		this.spperson = spperson;
	}

	public Date getSptime() {
		return this.sptime;
	}

	public void setSptime(Date sptime) {
		this.sptime = sptime;
	}

	public String getAccessory() {
		return this.accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

}
