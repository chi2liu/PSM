package hibernate;
// Generated 2017-4-19 10:22:30 by Hibernate Tools 5.2.0.CR1

import java.util.Date;

/**
 * Safetypromanagementfb generated by hbm2java
 */
public class Safetypromanagementfb implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String fbName;
	private Date time;
	private String person;
	private String sperson;
	private String accessory;
	private String projectName;

	public Safetypromanagementfb() {
	}

	public Safetypromanagementfb(String name, String fbName, Date time, String person, String sperson, String accessory,
			String projectName) {
		this.name = name;
		this.fbName = fbName;
		this.time = time;
		this.person = person;
		this.sperson = sperson;
		this.accessory = accessory;
		this.projectName = projectName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFbName() {
		return this.fbName;
	}

	public void setFbName(String fbName) {
		this.fbName = fbName;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getSperson() {
		return this.sperson;
	}

	public void setSperson(String sperson) {
		this.sperson = sperson;
	}

	public String getAccessory() {
		return this.accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
