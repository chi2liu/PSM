package hibernate;
// Generated 2017-3-31 14:34:12 by Hibernate Tools 5.2.0.CR1

import java.util.Date;

/**
 * Fenbaoyinhuanpczlgzfa generated by hbm2java
 */
public class Fenbaoyinhuanpczlgzfa implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String year;
	private String fenbaoname;
	private String workname;
	private String filename;
	private Date uploadtime;
	private String accessory;
	private String projectName;

	public Fenbaoyinhuanpczlgzfa() {
	}

	public Fenbaoyinhuanpczlgzfa(String year, String fenbaoname, String workname, String filename, Date uploadtime,
			String accessory, String projectName) {
		this.year = year;
		this.fenbaoname = fenbaoname;
		this.workname = workname;
		this.filename = filename;
		this.uploadtime = uploadtime;
		this.accessory = accessory;
		this.projectName = projectName;
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

	public String getFenbaoname() {
		return this.fenbaoname;
	}

	public void setFenbaoname(String fenbaoname) {
		this.fenbaoname = fenbaoname;
	}

	public String getWorkname() {
		return this.workname;
	}

	public void setWorkname(String workname) {
		this.workname = workname;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
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
