package hibernate;
// Generated 2017-9-9 19:26:20 by Hibernate Tools 5.1.4.Final

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.regexp.internal.recompile;

/**
 * PrescribedActionView generated by hbm2java
 */
public class PrescribedActionView implements java.io.Serializable {

	private PrescribedActionViewId id;
	private Date completedDate;

	public PrescribedActionView() {
	}

	public PrescribedActionView(PrescribedActionViewId id) {
		this.id = id;
	}

	public PrescribedActionViewId getId() {
		return this.id;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public void setId(PrescribedActionViewId id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		String string = "未完成";
		if (completedDate != null) {
			string = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(completedDate);
		}
 		String s = "{\"project\":\"" + id.getProject()
				+  "\",\"action_id\":" + id.getActionId()
				+  ",\"prescribed_action\":\"" + id.getPrescribedAction()
				+  "\",\"related_menu\":\"" + id.getRelatedMenu()
				+  "\",\"prompt_role\":\"" + id.getPromptRole()
				+  "\",\"prompt_cycle\":\"" + id.getPromptCycle()
				+  "\",\"completed_date\":\"" + string + "\"}";		
		return s;
	}

}
