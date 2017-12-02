package PSM.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import PSM.DAO.GoalDutyDAO;
import PSM.Service.GoalDutyService;
import PSM.Service.HiddenTroubleSolutionService;
import hibernate.Anweihui;
import hibernate.Fenbao;
import hibernate.Flownode;
import hibernate.Goaldecom;
import hibernate.Project;
import hibernate.Saveculture;
import hibernate.Saveproduct;
import hibernate.Securityplan;
import hibernate.Taizhang;
import hibernate.Weixianyuan;
import hibernate.Yinhuanpaicha;

public class HiddenTroubleSolutionAction extends ActionSupport {
	private int start; // 分页查询参
	private int limit; // 分页查询参数
	private HiddenTroubleSolutionService hiddenTroubleSolutionService;
	private String type;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public HiddenTroubleSolutionService getHiddenTroubleSolutionService() {
		return hiddenTroubleSolutionService;
	}

	public void setHiddenTroubleSolutionService(HiddenTroubleSolutionService hiddenTroubleSolutionService) {
		this.hiddenTroubleSolutionService = hiddenTroubleSolutionService;
	}

	public HiddenTroubleSolutionAction() {

	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	public void getFileInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String path = request.getParameter("path");
		String fileName = request.getParameter("name");
		try {
			String filePath = request.getRealPath("/") + "upload\\" + path + "\\" + fileName;
			String fileLength = hiddenTroubleSolutionService.getFileInfo(filePath);

			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(fileLength);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;

	}

	public void deleteAllFile() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String fileName = request.getParameter("fileName");
		String ppID = request.getParameter("id");
		String rootPath = request.getRealPath("/") + "upload\\";
		try {
			String jsonStr = hiddenTroubleSolutionService.deleteAllFile(ppID, fileName, rootPath);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public void deleteOneFile() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String fileName = request.getParameter("name");
		String ppID = request.getParameter("id");
		String rootPath = request.getRealPath("/") + "upload\\";
		try {
			String jsonStr = hiddenTroubleSolutionService.deleteOneFile(ppID, fileName, rootPath);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public void getWeixianyuanListDef() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String projectName = request.getParameter("projectName");
			String jsonStr = hiddenTroubleSolutionService.getWeixianyuanList("", start, limit, projectName);

			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public void getWeixianyuanListSearch() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String findstr = request.getParameter("findStr");
			String projectName = request.getParameter("projectName");
			String jsonStr = hiddenTroubleSolutionService.getWeixianyuanList(findstr, start, limit, projectName);
			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public Weixianyuan getWeixianyuan() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// java.sql.Date solveTime =
		// java.sql.Date.valueOf(request.getParameter("solveTime"));

		Weixianyuan pro = new Weixianyuan();
		pro.setJobActivity(request.getParameter("JobActivity"));
		pro.setBadFactor(request.getParameter("BadFactor"));
		pro.setBadEvent(request.getParameter("BadEvent"));

		pro.setRank(request.getParameter("Rank"));

		pro.setControlAction(request.getParameter("ControlAction").replace("\n", ""));
		pro.setYijian(request.getParameter("Yijian"));
		if (pro.getYijian().equals("不超过500个字符")) {
			pro.setYijian("");
		}
		pro.setProjectName(request.getParameter("ProjectName"));

		return pro;
	}

	public void addWeixianyuan() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = request.getParameter("fileName");
		String rootPath = request.getRealPath("/") + "upload\\";
		try {
			Weixianyuan pro = getWeixianyuan();

			String jsonStr = hiddenTroubleSolutionService.addWeixianyuan(pro, fileName, rootPath);
			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public void editWeixianyuan() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = request.getParameter("fileName");
		String rootPath = request.getRealPath("/") + "upload\\";
		try {
			Weixianyuan pro = getWeixianyuan();
			pro.setId(Integer.parseInt(request.getParameter("ID")));
			pro.setAccessory(request.getParameter("Accessory"));

			String jsonStr = hiddenTroubleSolutionService.editWeixianyuan(pro, fileName, rootPath);
			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public void deleteWeixianyuan() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String ID = request.getParameter("id");
		String rootPath = request.getRealPath("/") + "upload\\";
		try {

			String jsonStr = hiddenTroubleSolutionService.deleteWeixianyuan(ID, rootPath);
			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public void getYinhuanpaichaListDef() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String jsonStr = hiddenTroubleSolutionService.getYinhuanpaichaList("", start, limit);

			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public void getYinhuanpaichaListSearch() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String findstr = request.getParameter("findStr");
			String jsonStr = hiddenTroubleSolutionService.getYinhuanpaichaList(findstr, start, limit);
			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public Yinhuanpaicha getYinhuanpaicha() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Yinhuanpaicha pro = new Yinhuanpaicha();

		return pro;
	}

	public void addYinhuanpaicha() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = request.getParameter("fileName");
		String rootPath = request.getRealPath("/") + "upload\\";
		try {
			Yinhuanpaicha pro = getYinhuanpaicha();

			String jsonStr = hiddenTroubleSolutionService.addYinhuanpaicha(pro, fileName, rootPath);
			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public void editYinhuanpaicha() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String fileName = request.getParameter("fileName");
		String rootPath = request.getRealPath("/") + "upload\\";
		try {
			Yinhuanpaicha pro = getYinhuanpaicha();
			pro.setId(Integer.parseInt(request.getParameter("ID")));

			String jsonStr = hiddenTroubleSolutionService.editYinhuanpaicha(pro, fileName, rootPath);
			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}

	public void deleteYinhuanpaicha() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String ID = request.getParameter("id");
		String rootPath = request.getRealPath("/") + "upload\\";
		try {

			String jsonStr = hiddenTroubleSolutionService.deleteYinhuanpaicha(ID, rootPath);
			System.out.println(jsonStr);
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			// return ERROR;
		}
		// return SUCCESS;
	}
	
	public void importExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			String type = request.getParameter("type");
			String fileName = request.getParameter("fileName");
			String projectName = request.getParameter("projectName");
			String rootPath = request.getRealPath("/")+"upload\\";
			int total = hiddenTroubleSolutionService.importExcel(type, rootPath, fileName, projectName);
			outputJSON(response, "{\"result\":\"success\",\"total\":"+total+"}");
		}
		catch(Exception e) {
			e.printStackTrace();
			outputJSON(response, "{\"result\":\"failed\"}");
		}
	}
	
	private void outputJSON(HttpServletResponse response, String jsonStr) throws Exception {
		System.out.println(jsonStr);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonStr);
		out.flush();
		out.close();
	}
}
