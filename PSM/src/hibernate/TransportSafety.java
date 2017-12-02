package hibernate;
// Generated 2017-4-18 10:04:40 by Hibernate Tools 5.2.0.CR1

/**
 * Transportsafety generated by hbm2java
 */
public class TransportSafety implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String carNum;
	private String carName;
	private String department;
	private String license;
	private String driver;
	private String driverNum;
	private String maintenance;
	private String projectName;

	public TransportSafety() {
	}

	public TransportSafety(String carNum, String carName, String department, String license, String driver,
			String driverNum, String maintenance, String projectName) {
		this.carNum = carNum;
		this.carName = carName;
		this.department = department;
		this.license = license;
		this.driver = driver;
		this.driverNum = driverNum;
		this.maintenance = maintenance;
		this.projectName = projectName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarNum() {
		return this.carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getCarName() {
		return this.carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLicense() {
		return this.license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriverNum() {
		return this.driverNum;
	}

	public void setDriverNum(String driverNum) {
		this.driverNum = driverNum;
	}

	public String getMaintenance() {
		return this.maintenance;
	}

	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
