package hibernate;
// Generated 2017-5-17 11:28:28 by Hibernate Tools 5.1.2.Final

/**
 * Fenbao generated by hbm2java
 */
public class Fenbao implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String proNo;
	private String proRange;
	private String cost;
	private String head;
	private String rank;
	private String techHead;
	private String proHead;
	private String proTechHead;
	private String proSaveHead;
	private String proSavePeople;
	private String project;
	private String accessory;
	private String type;

	public Fenbao() {
	}

	public Fenbao(String name, String proNo, String proRange, String cost, String head, String rank, String techHead,
			String proHead, String proTechHead, String proSaveHead, String proSavePeople, String project,
			String accessory, String type) {
		this.name = name;
		this.proNo = proNo;
		this.proRange = proRange;
		this.cost = cost;
		this.head = head;
		this.rank = rank;
		this.techHead = techHead;
		this.proHead = proHead;
		this.proTechHead = proTechHead;
		this.proSaveHead = proSaveHead;
		this.proSavePeople = proSavePeople;
		this.project = project;
		this.accessory = accessory;
		this.type = type;
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

	public String getProNo() {
		return this.proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getProRange() {
		return this.proRange;
	}

	public void setProRange(String proRange) {
		this.proRange = proRange;
	}

	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getHead() {
		return this.head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getTechHead() {
		return this.techHead;
	}

	public void setTechHead(String techHead) {
		this.techHead = techHead;
	}

	public String getProHead() {
		return this.proHead;
	}

	public void setProHead(String proHead) {
		this.proHead = proHead;
	}

	public String getProTechHead() {
		return this.proTechHead;
	}

	public void setProTechHead(String proTechHead) {
		this.proTechHead = proTechHead;
	}

	public String getProSaveHead() {
		return this.proSaveHead;
	}

	public void setProSaveHead(String proSaveHead) {
		this.proSaveHead = proSaveHead;
	}

	public String getProSavePeople() {
		return this.proSavePeople;
	}

	public void setProSavePeople(String proSavePeople) {
		this.proSavePeople = proSavePeople;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
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

}
