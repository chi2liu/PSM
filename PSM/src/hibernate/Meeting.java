package hibernate;
// Generated 2017-3-29 15:10:44 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;

/**
 * Meeting generated by hbm2java
 */
public class Meeting implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date time;
	private String place;
	private String topic;
	private String host;
	private String record;
	private String participants;
	private String accessory;
	private String type;
	private String projectName;

	public Meeting() {
	}

	public Meeting(Date time, String place, String topic, String host, String record, String participants,
			String accessory, String type, String projectName) {
		this.time = time;
		this.place = place;
		this.topic = topic;
		this.host = host;
		this.record = record;
		this.participants = participants;
		this.accessory = accessory;
		this.type = type;
		this.projectName = projectName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getRecord() {
		return this.record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getParticipants() {
		return this.participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getAccessory() {
		return this.accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
