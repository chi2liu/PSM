package hibernate;
// Generated 2017-3-10 17:33:04 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * Feemanagement generated by hbm2java
 */
public class Feemanagement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String typei;
	private String unit;
	private String fee;
	private String usea;
	private String jperson;
	private String dperson;
	private Date time;
	private String content;
	private String title;
	private String accessory;

	public Feemanagement() {
	}

	public Feemanagement(String typei, String unit, String fee, String usea, String jperson, String dperson, Date time,
			String content, String title, String accessory) {
		this.typei = typei;
		this.unit = unit;
		this.fee = fee;
		this.usea = usea;
		this.jperson = jperson;
		this.dperson = dperson;
		this.time = time;
		this.content = content;
		this.title = title;
		this.accessory = accessory;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypei() {
		return this.typei;
	}

	public void setTypei(String typei) {
		this.typei = typei;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFee() {
		return this.fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getUsea() {
		return this.usea;
	}

	public void setUsea(String usea) {
		this.usea = usea;
	}

	public String getJperson() {
		return this.jperson;
	}

	public void setJperson(String jperson) {
		this.jperson = jperson;
	}

	public String getDperson() {
		return this.dperson;
	}

	public void setDperson(String dperson) {
		this.dperson = dperson;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAccessory() {
		return this.accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

}
