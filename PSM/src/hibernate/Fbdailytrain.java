package hibernate;
// Generated 2017-3-31 17:15:04 by Hibernate Tools 5.1.0.Final

import java.util.Date;

/**
 * Fbdailytrain generated by hbm2java
 */
public class Fbdailytrain implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String fenbao;
	private Date startDate;
	private Date endDate;
	private Integer time;
	private Date registDate;
	private String record;
	private String accessory;
	private String projectName;

	public Fbdailytrain() {
	}

	public Fbdailytrain(String fenbao, Date startDate, Date endDate, Integer time, Date registDate, String record,
			String accessory, String projectName) {
		this.fenbao = fenbao;
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.registDate = registDate;
		this.record = record;
		this.accessory = accessory;
		this.projectName = projectName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFenbao() {
		return this.fenbao;
	}

	public void setFenbao(String fenbao) {
		this.fenbao = fenbao;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Date getRegistDate() {
		return this.registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getRecord() {
		return this.record;
	}

	public void setRecord(String record) {
		this.record = record;
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
