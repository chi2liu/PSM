package hibernate;
// Generated 2017-5-31 16:33:52 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * Saveprodbook generated by hbm2java
 */
public class Saveprodbook implements java.io.Serializable {

	private Integer id;
	private String type;
	private String timeYear;
	private String toTarget;
	private String accessory;
	private String projectName;
	private Date uploadTime;

	public Saveprodbook() {
	}

	public Saveprodbook(String type, String timeYear, String toTarget, String accessory, String projectName,
			Date uploadTime) {
		this.type = type;
		this.timeYear = timeYear;
		this.toTarget = toTarget;
		this.accessory = accessory;
		this.projectName = projectName;
		this.uploadTime = uploadTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimeYear() {
		return this.timeYear;
	}

	public void setTimeYear(String timeYear) {
		this.timeYear = timeYear;
	}

	public String getToTarget() {
		return this.toTarget;
	}

	public void setToTarget(String toTarget) {
		this.toTarget = toTarget;
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

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

}
