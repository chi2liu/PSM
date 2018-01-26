package hibernate;
// Generated 2017-10-14 15:05:16 by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * Monthplan generated by hbm2java
 */
public class Monthplan implements java.io.Serializable {

	private Integer id;
	private String no;
	private String year;
	private String month;
	private String workload;
	private String completed;
	private String content;
	private Integer contentNum;
	private String manager;
	private Date planDate;
	private String realDate;
	private String accessory;
	private String unit;
	private String completedsm;
	private String projectName;

	public Monthplan() {
	}

	public Monthplan(String no, String year, String month, String workload, String completed, String content,
			Integer contentNum, String manager, Date planDate, String realDate, String accessory, String unit,
			String completedsm, String projectName) {
		this.no = no;
		this.year = year;
		this.month = month;
		this.workload = workload;
		this.completed = completed;
		this.content = content;
		this.contentNum = contentNum;
		this.manager = manager;
		this.planDate = planDate;
		this.realDate = realDate;
		this.accessory = accessory;
		this.unit = unit;
		this.completedsm = completedsm;
		this.projectName = projectName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getWorkload() {
		return this.workload;
	}

	public void setWorkload(String workload) {
		this.workload = workload;
	}

	public String getCompleted() {
		return this.completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getContentNum() {
		return this.contentNum;
	}

	public void setContentNum(Integer contentNum) {
		this.contentNum = contentNum;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Date getPlanDate() {
		return this.planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public String getRealDate() {
		return this.realDate;
	}

	public void setRealDate(String realDate) {
		this.realDate = realDate;
	}

	public String getAccessory() {
		return this.accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCompletedsm() {
		return this.completedsm;
	}

	public void setCompletedsm(String completedsm) {
		this.completedsm = completedsm;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
