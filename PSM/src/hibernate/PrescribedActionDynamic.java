package hibernate;
// Generated 2017-9-4 16:20:13 by Hibernate Tools 5.1.4.Final

import java.util.Date;

/**
 * PrescribedActionDynamic generated by hbm2java
 */
public class PrescribedActionDynamic implements java.io.Serializable {

	private int id;
	private String project;
	private Integer actionId;
	private Boolean isDone;
	private Date completedDate;

	public PrescribedActionDynamic() {
	}

	public PrescribedActionDynamic(int id) {
		this.id = id;
	}

	public PrescribedActionDynamic(int id, String project, Integer actionId, Boolean isDone, Date completedDate) {
		this.id = id;
		this.project = project;
		this.actionId = actionId;
		this.isDone = isDone;
		this.completedDate = completedDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Integer getActionId() {
		return this.actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public Boolean getIsDone() {
		return this.isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public Date getCompletedDate() {
		return this.completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

}
