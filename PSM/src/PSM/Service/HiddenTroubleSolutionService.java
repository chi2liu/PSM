package PSM.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import PSM.DAO.HiddenTroubleSolutionDAO;
import PSM.Tool.ExcelToPdf;
import PSM.Tool.ReadExcel;
import PSM.Tool.WordToPdf;
import hibernate.ReadilyShoot;
import hibernate.Weixianyuan;
import hibernate.Yinhuanpaicha;

public class HiddenTroubleSolutionService {

	private HiddenTroubleSolutionDAO hiddenTroubleSolutionDAO;

	public HiddenTroubleSolutionDAO getHiddenTroubleSolutionDAO() {
		return hiddenTroubleSolutionDAO;
	}

	public void setHiddenTroubleSolutionDAO(HiddenTroubleSolutionDAO hiddenTroubleSolutionDAO) {
		this.hiddenTroubleSolutionDAO = hiddenTroubleSolutionDAO;
	}

	public String getFileInfo(String filePath) {
		String fileLength = "0";
		File file = new File(filePath);
		System.out.println(filePath);
		if (file.exists()) {
			fileLength = Long.toString(file.length());
		}
		return fileLength;
	}

	public String deleteAllFile(String ppID, String fileName, String rootPath) {
		System.out.println("--in delete all--" + ppID + "  " + fileName + " " + rootPath);
		String[] newFile = fileName.split("\\*");
		String json = "{\"success\":\"true\"}";

		if (ppID == null || ppID.length() <= 0)// 没有ID 说明是添加 直接去temp全部删除
		{
			for (String existFileInList : newFile) {
				deleteFile(rootPath + "temp\\" + existFileInList);
			}
		}
		return json;
	}

	public String deleteOneFile(String ppID, String fileName, String rootPath) {
		String json = "{\"success\":\"true\"}";
		String deletePath = rootPath + "temp" + "\\" + fileName;
		if (ppID == null || ppID.length() <= 0)// 如果是新增才删除
		{
			deleteFile(deletePath);
		}
		return json;
	}

	public boolean cutGeneralFile(String srcPath, String destDir) {
		if (!copyGeneralFile(srcPath, destDir)) {
			System.out.println("复制失败导致剪切失败!");
			return false;
		}
		if (!deleteFile(srcPath)) {
			System.out.println("删除源文件(文件夹)失败导致剪切失败!");
			return false;
		}

		System.out.println("剪切成功!");
		return true;
	}

	public boolean copyGeneralFile(String srcPath, String destDir) {
		boolean flag = false;
		File file = new File(srcPath);
		if (!file.exists()) {
			System.out.println("源文件或源文件夹不存在!");
			return false;
		}
		if (file.isFile()) { // 源文件
			System.out.println("下面进行文件复制!");
			flag = copyFile(srcPath, destDir);
		}

		return flag;
	}

	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		if (!file.exists())
			return false;
		if (sPath.endsWith(".doc")) {
			String strpdf = sPath.substring(0, sPath.length() - 4) + ".pdf";
			File filepdf = new File(strpdf);
			if (filepdf.isFile() && filepdf.exists()) {
				filepdf.delete();
			}
		}
		if (sPath.endsWith(".docx")) {
			String strpdf = sPath.substring(0, sPath.length() - 5) + ".pdf";
			File filepdf = new File(strpdf);
			if (filepdf.isFile() && filepdf.exists()) {
				filepdf.delete();
			}
		}
		// 路径为文件且不为空则删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);

		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;

		// 删除文件夹下所有的文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;

		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断文件或目录是否存在
		if (!file.exists()) {
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) {
				return deleteFile(sPath); // 为文件时调用删除文件方法
			} else {
				return deleteDirectory(sPath); // 为目录时调用删除目录方法
			}
		}
	}

	private boolean copyFile(String srcPath, String destDir) {
		boolean flag = false;

		File srcFile = new File(srcPath);
		if (!srcFile.exists()) { // 源文件不存在
			System.out.println("源文件不存在");
			return false;
		}
		// 获取待复制文件的文件名
		String fileName = srcPath.substring(srcPath.lastIndexOf(File.separator));
		String destPath = destDir + fileName;
		if (destPath.equals(srcPath)) { // 源文件路径和目标文件路径重复
			System.out.println("源文件路径和目标文件路径重复!");
			return false;
		}
		File destFile = new File(destPath);
		if (destFile.exists() && destFile.isFile()) { // 该路径下已经有一个同名文件
			System.out.println("目标目录下已有同名文件!");
			return false;
		}

		File destFileDir = new File(destDir);
		destFileDir.mkdirs();
		try {
			FileInputStream fis = new FileInputStream(srcPath);
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int c;
			while ((c = fis.read(buf)) != -1) {
				fos.write(buf, 0, c);
			}
			fis.close();
			fos.close();

			flag = true;
		} catch (IOException e) {
			//
		}
		if (flag) {
			System.out.println("复制文件成功!");
		}

		return flag;
	}

	public String getWeixianyuanList(String findstr, int start, int limit, String projectName) {

		List<Weixianyuan> list = hiddenTroubleSolutionDAO.getWeixianyuanList(findstr, start, limit, projectName);
		int total = hiddenTroubleSolutionDAO.datacount;
		String jsonStr = "{\"total\":\"" + total + "\",\"rows\":[";
		for (int i = 0; i < list.size(); ++i) {
			if (i > 0) {
				jsonStr += ",";
			}
			Weixianyuan p = list.get(i);
			jsonStr += "{\"ID\":\"" + p.getId() + "\",\"JobActivity\":\"" + p.getJobActivity() + "\",\"BadFactor\":\""
					+ p.getBadFactor() + "\",\"BadEvent\":\"" + p.getBadEvent() + "\",\"Rank\":\"" + p.getRank() + "\"";
			jsonStr += ",\"ControlAction\":\"" + p.getControlAction() + "\",\"Yijian\":\"" + p.getYijian()
					+ "\",\"ProjectName\":\"" + p.getProjectName() + "\",\"Accessory\":\"" + p.getAccessory() + "\"}";
		}
		jsonStr += "]}";
		System.out.println(jsonStr);
		return jsonStr;
	}

	public String addWeixianyuan(Weixianyuan p, String fileName, String rootPath) {
		String json = "{\"success\":\"true\"}";
		// 有可能需要用到当前时间作为编号
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
		String mytime = df.format(new Date());

		// 分别获取当前年月日
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		String sy = String.valueOf(year);
		String sm = String.valueOf(month);
		if (month < 10) {
			sm = "0" + sm;
		}
		String sd = String.valueOf(day);
		if (day < 10) {
			sd = "0" + sd;
		}

		// 建立月份和项目名文件夹
		String ym = rootPath + sy + sm + "\\";
		String ympname = ym + p.getJobActivity() + "\\";
		File directory1 = new File(ym); // 根目录是否存在
		if (!directory1.exists())
			directory1.mkdir();
		File directory2 = new File(ympname); // 根目录是否存在
		if (!directory2.exists())
			directory2.mkdir();

		// 拷贝temp中的文件到正确的文件夹
		String[] newFile = fileName.split("\\*");
		for (String copyFile : newFile) {
			cutGeneralFile(rootPath + "temp\\" + copyFile, ym + p.getJobActivity());
		}

		// 存入数据库的内容
		if (fileName != null && fileName.length() > 0) {
			if (fileName.charAt(fileName.length() - 1) == '*')
				fileName = fileName.substring(0, fileName.length() - 1);
		}

		String accessory = sy + sm + "*" + p.getJobActivity() + "*" + fileName;

		// 打包
		/*
		 * byte[] buffer = new byte[1024]; if(fileName.length()>0) { String
		 * strZipName = ympname + sy + sm + sd + p.getJobActivity()+".zip";
		 * System.out.println("压缩包名称:"+strZipName); try { ZipOutputStream out =
		 * new ZipOutputStream(new FileOutputStream(strZipName)); String[]
		 * allfile = fileName.split("\\*");
		 * System.out.println(Arrays.toString(allfile)); for(int i
		 * =0;i<allfile.length;i++) { File tempfile = new
		 * File(ympname+allfile[i]); if(tempfile.exists()) { FileInputStream fis
		 * = new FileInputStream(tempfile); out.putNextEntry(new
		 * ZipEntry(tempfile.getName())); int len; while((len =
		 * fis.read(buffer))>0) { out.write(buffer,0,len); } out.closeEntry();
		 * fis.close(); } } accessory =
		 * accessory+sy+sm+sd+p.getJobActivity()+".zip"; out.close(); } catch
		 * (Exception e) { e.printStackTrace(); } }
		 */

		// doc转pdf
		if (fileName.length() != 0) {
			String[] allFile = fileName.split("\\*");
			for (String temp : allFile) {
				if (temp.endsWith(".doc")) {
					System.out.println(ympname + temp);
					WordToPdf.translateThread(ympname + temp, ympname + temp.substring(0, temp.length() - 4) + ".pdf");
				} else if (temp.endsWith(".docx")) {
					System.out.println(ympname + temp);
					WordToPdf.translateThread(ympname + temp, ympname + temp.substring(0, temp.length() - 5) + ".pdf");
				} else if (temp.endsWith(".xlsx")) {
					System.out.println("-----------xlsx");
					ExcelToPdf.excel2pdf(ympname + temp, ympname + temp.substring(0, temp.length() - 5) + ".pdf");
				} else if (temp.endsWith("xls"))

				{
					System.out.println("----------xls");
					ExcelToPdf.excel2pdf(ympname + temp, ympname + temp.substring(0, temp.length() - 4) + ".pdf");
				}
			}
		}

		if (fileName == null || fileName.length() <= 0) {
			System.out.println("----is null---");
			p.setAccessory(fileName);
		} else {
			p.setAccessory(accessory);
		}
		hiddenTroubleSolutionDAO.insertWeixianyuan(p);
		return json;
	}

	public String editWeixianyuan(Weixianyuan p, String fileName, String rootPath) {
		int oldAccessoryNone = 0;
		if (p.getAccessory() == null || p.getAccessory().length() <= 0) {
			oldAccessoryNone = 1;
		}
		String json = "{\"success\":\"true\"}";
		// 有可能需要用到当前时间作为编号
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
		String mytime = df.format(new Date());

		// 分别获取当前年月日
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		String sy = String.valueOf(year);
		String sm = String.valueOf(month);
		if (month < 10) {
			sm = "0" + sm;
		}
		String sd = String.valueOf(day);
		if (day < 10) {
			sd = "0" + sd;
		}

		if ((p.getAccessory() == null || p.getAccessory().length() <= 0)
				&& (fileName != null && fileName.length() > 0)) {
			// 建立月份和项目名文件夹
			String ym = rootPath + sy + sm + "\\";
			String ympname = ym + p.getJobActivity() + "\\";
			File directory1 = new File(ym); // 根目录是否存在
			if (!directory1.exists())
				directory1.mkdir();
			File directory2 = new File(ympname); // 根目录是否存在
			if (!directory2.exists())
				directory2.mkdir();
		}

		String oldAccessory = p.getAccessory();
		String[] oldFile = oldAccessory.split("\\*");
		int oldFileLength = oldFile.length;
		String oldFoldName;
		if (oldAccessoryNone == 0) {
			oldFoldName = oldFile[0] + "\\" + oldFile[1];
		} else {
			oldFoldName = sy + sm + "\\" + p.getJobActivity();
		}
		String[] newFile = fileName.split("\\*");
		String newAccessory;
		if (oldAccessoryNone == 0) {
			newAccessory = oldFile[0] + "*" + oldFile[1] + "*" + fileName;
		} else {
			newAccessory = sy + sm + "*" + p.getJobActivity() + "*" + fileName;
		}

		// ------------------------删除列表中不存在的文件---------------------------//
		for (int i = 2; i < oldFileLength - 1; i++) {
			String existFileInDB = oldFile[i];
			// 列表中是否存在该文件
			boolean hasFileInList = false;
			for (String existFileInList : newFile) {
				if (existFileInDB.equals(existFileInList)) {
					hasFileInList = true;
					break;
				}
			}
			if (!hasFileInList) {
				deleteFile(rootPath + oldFoldName + "\\" + existFileInDB);
			}
		}
		// -----------------------end----------------------------------//
		// 拷贝temp文件夹的新文件到对应文件夹
		for (String oneFile : newFile) {
			boolean hasFileInList = false;
			for (int i = 2; i < oldFileLength - 1; i++) {
				if (oneFile.equals(oldFile[i])) {
					hasFileInList = true;
					break;
				}
			}
			if (!hasFileInList) {
				cutGeneralFile(rootPath + "temp\\" + oneFile, rootPath + oldFoldName);
			}

		}
		if (oldAccessoryNone == 0) {
			// deleteFile(rootPath + oldFoldName
			// +"\\"+oldFile[oldFile.length-1]);
		}
		// ----------打包------------------//
		/*
		 * byte[] buffer = new byte[1024]; if(fileName.length()>0) { String
		 * strZipName = rootPath+oldFoldName
		 * +"\\"+ sy+sm+sd+p.getJobActivity()+".zip"; try { ZipOutputStream out
		 * = new ZipOutputStream(new FileOutputStream(strZipName)); String[]
		 * allfile = fileName.split("\\*"); for(int i =0;i<allfile.length;i++) {
		 * File tempfile = new File(rootPath+ oldFoldName +"\\"+ allfile[i]);
		 * if(tempfile.exists()) { FileInputStream fis = new
		 * FileInputStream(tempfile); out.putNextEntry(new
		 * ZipEntry(tempfile.getName())); int len; while((len =
		 * fis.read(buffer))>0) { out.write(buffer,0,len); } out.closeEntry();
		 * fis.close(); } } newAccessory =
		 * newAccessory+sy+sm+sd+p.getJobActivity()+".zip"; out.close(); } catch
		 * (Exception e) { e.printStackTrace(); } }
		 */

		newAccessory = newAccessory.substring(0, newAccessory.length() - 1);

		if (fileName.length() != 0) {
			String[] allFile = fileName.split("\\*");
			for (String tempFile : allFile) {
				if (tempFile.endsWith(".doc")) {
					System.out.println(rootPath + oldFoldName + "\\" + tempFile);
					WordToPdf.translateThread(rootPath + oldFoldName + "\\" + tempFile,
							rootPath + oldFoldName + "\\" + tempFile.substring(0, tempFile.length() - 4) + ".pdf");
				} else if (tempFile.endsWith(".docx")) {
					System.out.println(rootPath + oldFoldName + "\\" + tempFile);
					WordToPdf.translateThread(rootPath + oldFoldName + "\\" + tempFile,
							rootPath + oldFoldName + "\\" + tempFile.substring(0, tempFile.length() - 5) + ".pdf");
				} else if (tempFile.endsWith(".xlsx")) {
					System.out.println(rootPath + oldFoldName + "\\" + tempFile);
					ExcelToPdf.excel2pdf(rootPath + oldFoldName + "\\" + tempFile,
							rootPath + oldFoldName + "\\" + tempFile.substring(0, tempFile.length() - 5) + ".pdf");
				} else if (tempFile.endsWith(".xls")) {
					System.out.println(rootPath + oldFoldName + "\\" + tempFile);
					ExcelToPdf.excel2pdf(rootPath + oldFoldName + "\\" + tempFile,
							rootPath + oldFoldName + "\\" + tempFile.substring(0, tempFile.length() - 4) + ".pdf");
				}
			}
		}

		if (fileName == null || fileName.length() <= 0) {
			System.out.println("----is null---");
			p.setAccessory(fileName);
		} else {
			p.setAccessory(newAccessory);
		}

		hiddenTroubleSolutionDAO.updateWeixianyuan(p);
		return json;
	}

	public String deleteWeixianyuan(String ID, String rootPath) {
		Weixianyuan pro = new Weixianyuan();
		String json = "{\"success\":\"true\"}";
		String[] temp = ID.split(",");
		for (int i = 0; i < temp.length; i++) {
			List<Weixianyuan> list = hiddenTroubleSolutionDAO.checkWeixianyuanID((Integer.parseInt(temp[i])));
			// 删除附件
			if (list != null) {
				Weixianyuan p = list.get(0);
				String fileName = p.getAccessory();
				System.out.println(fileName + "length" + fileName.length());
				if (fileName != null && fileName.length() != 0) {
					String[] name = fileName.split("\\*");
					String folder = name[0] + "//" + name[1];
					String path = rootPath + folder;
					System.out.println("--path:" + path);
					for (int j = 2; j < name.length; j++) {
						deleteFile(path + "//" + name[j]);
					}
					DeleteFolder(path);
				}
			}
			pro.setId(Integer.parseInt(temp[i]));
			hiddenTroubleSolutionDAO.deleteWeixianyuan(pro);
		}
		return json;
	}

	public String getYinhuanpaichaList(String findstr, int start, int limit) {

		List<Yinhuanpaicha> list = hiddenTroubleSolutionDAO.getYinhuanpaichaList(findstr, start, limit);
		int total = hiddenTroubleSolutionDAO.datacount;
		String jsonStr = "{\"total\":\"" + total + "\",\"rows\":[";
		for (int i = 0; i < list.size(); ++i) {
			if (i > 0) {
				jsonStr += ",";
			}
			Yinhuanpaicha p = list.get(i);
			jsonStr += "{\"ID\":\"" + p.getId() + "\",\"HiddenTrouble\":\"" + p.getHiddenTrouble() + "\",\"Place\":\""
					+ p.getPlace() + "\",\"FindMan\":\"" + p.getFindMan() + "\",\"Rank\":\"" + p.getRank() + "\"";
			jsonStr += ",\"Action\":\"" + p.getAction() + "\",\"Unit\":\"" + p.getUnit() + "\",\"InChargeMan\":\""
					+ p.getInChargeMan() + "\",\"PlanTime\":\"" + p.getPlanTime() + "\",\"Cost\":\"" + p.getCost()
					+ "\"";
			jsonStr += ",\"RealTime\":\"" + p.getRealTime() + "\",\"Supervisor\":\"" + p.getSupervisor()
					+ "\",\"PreventAction\":\"" + p.getPreventAction() + "\",\"FileNo\":\"" + p.getFileNo() + "\"";
			jsonStr += ",\"Accessory\":\"" + p.getAccessory() + "\"}";
		}
		jsonStr += "]}";
		System.out.println(jsonStr);
		return jsonStr;
	}

	public String addYinhuanpaicha(Yinhuanpaicha p, String fileName, String rootPath) {
		String json = "{\"success\":\"true\"}";
		// 有可能需要用到当前时间作为编号
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
		String mytime = df.format(new Date());

		// 分别获取当前年月日
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		String sy = String.valueOf(year);
		String sm = String.valueOf(month);
		if (month < 10) {
			sm = "0" + sm;
		}
		String sd = String.valueOf(day);
		if (day < 10) {
			sd = "0" + sd;
		}

		// 建立月份和项目名文件夹
		String ym = rootPath + sy + sm + "\\";
		String ympname = ym + p.getSupervisor() + "\\";
		File directory1 = new File(ym); // 根目录是否存在
		if (!directory1.exists())
			directory1.mkdir();
		File directory2 = new File(ympname); // 根目录是否存在
		if (!directory2.exists())
			directory2.mkdir();

		// 拷贝temp中的文件到正确的文件夹
		String[] newFile = fileName.split("\\*");
		for (String copyFile : newFile) {
			cutGeneralFile(rootPath + "temp\\" + copyFile, ym + p.getSupervisor());
		}

		// 存入数据库的内容
		if (fileName != null && fileName.length() > 0) {
			if (fileName.charAt(fileName.length() - 1) == '*')
				fileName = fileName.substring(0, fileName.length() - 1);
		}

		String accessory = sy + sm + "*" + p.getSupervisor() + "*" + fileName;

		// 打包
		/*
		 * byte[] buffer = new byte[1024]; if(fileName.length()>0) { String
		 * strZipName = ympname + sy + sm + sd + p.getSupervisor()+".zip";
		 * System.out.println("压缩包名称:"+strZipName); try { ZipOutputStream out =
		 * new ZipOutputStream(new FileOutputStream(strZipName)); String[]
		 * allfile = fileName.split("\\*");
		 * System.out.println(Arrays.toString(allfile)); for(int i
		 * =0;i<allfile.length;i++) { File tempfile = new
		 * File(ympname+allfile[i]); if(tempfile.exists()) { FileInputStream fis
		 * = new FileInputStream(tempfile); out.putNextEntry(new
		 * ZipEntry(tempfile.getName())); int len; while((len =
		 * fis.read(buffer))>0) { out.write(buffer,0,len); } out.closeEntry();
		 * fis.close(); } } accessory =
		 * accessory+sy+sm+sd+p.getSupervisor()+".zip"; out.close(); } catch
		 * (Exception e) { e.printStackTrace(); } }
		 */

		// doc转pdf
		if (fileName.length() != 0) {
			String[] allFile = fileName.split("\\*");
			for (String temp : allFile) {
				if (temp.endsWith(".doc")) {
					System.out.println(ympname + temp);
					WordToPdf.translateThread(ympname + temp, ympname + temp.substring(0, temp.length() - 4) + ".pdf");
				} else if (temp.endsWith(".docx")) {
					System.out.println(ympname + temp);
					WordToPdf.translateThread(ympname + temp, ympname + temp.substring(0, temp.length() - 5) + ".pdf");
				} else if (temp.endsWith(".xlsx")) {
					System.out.println("-----------xlsx");
					ExcelToPdf.excel2pdf(ympname + temp, ympname + temp.substring(0, temp.length() - 5) + ".pdf");
				} else if (temp.endsWith("xls"))

				{
					System.out.println("----------xls");
					ExcelToPdf.excel2pdf(ympname + temp, ympname + temp.substring(0, temp.length() - 4) + ".pdf");
				}
			}
		}

		if (fileName == null || fileName.length() <= 0) {
			System.out.println("----is null---");
			p.setAccessory(fileName);
		} else {
			p.setAccessory(accessory);
		}
		hiddenTroubleSolutionDAO.insertYinhuanpaicha(p);
		return json;
	}

	public String editYinhuanpaicha(Yinhuanpaicha p, String fileName, String rootPath) {
		int oldAccessoryNone = 0;
		if (p.getAccessory() == null || p.getAccessory().length() <= 0) {
			oldAccessoryNone = 1;
		}
		String json = "{\"success\":\"true\"}";
		// 有可能需要用到当前时间作为编号
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
		String mytime = df.format(new Date());

		// 分别获取当前年月日
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		String sy = String.valueOf(year);
		String sm = String.valueOf(month);
		if (month < 10) {
			sm = "0" + sm;
		}
		String sd = String.valueOf(day);
		if (day < 10) {
			sd = "0" + sd;
		}

		if ((p.getAccessory() == null || p.getAccessory().length() <= 0)
				&& (fileName != null && fileName.length() > 0)) {
			// 建立月份和项目名文件夹
			String ym = rootPath + sy + sm + "\\";
			String ympname = ym + p.getSupervisor() + "\\";
			File directory1 = new File(ym); // 根目录是否存在
			if (!directory1.exists())
				directory1.mkdir();
			File directory2 = new File(ympname); // 根目录是否存在
			if (!directory2.exists())
				directory2.mkdir();
		}

		String oldAccessory = p.getAccessory();
		String[] oldFile = oldAccessory.split("\\*");
		int oldFileLength = oldFile.length;
		String oldFoldName;
		if (oldAccessoryNone == 0) {
			oldFoldName = oldFile[0] + "\\" + oldFile[1];
		} else {
			oldFoldName = sy + sm + "\\" + p.getSupervisor();
		}
		String[] newFile = fileName.split("\\*");
		String newAccessory;
		if (oldAccessoryNone == 0) {
			newAccessory = oldFile[0] + "*" + oldFile[1] + "*" + fileName;
		} else {
			newAccessory = sy + sm + "*" + p.getSupervisor() + "*" + fileName;
		}

		// ------------------------删除列表中不存在的文件---------------------------//
		for (int i = 2; i < oldFileLength - 1; i++) {
			String existFileInDB = oldFile[i];
			// 列表中是否存在该文件
			boolean hasFileInList = false;
			for (String existFileInList : newFile) {
				if (existFileInDB.equals(existFileInList)) {
					hasFileInList = true;
					break;
				}
			}
			if (!hasFileInList) {
				deleteFile(rootPath + oldFoldName + "\\" + existFileInDB);
			}
		}
		// -----------------------end----------------------------------//
		// 拷贝temp文件夹的新文件到对应文件夹
		for (String oneFile : newFile) {
			boolean hasFileInList = false;
			for (int i = 2; i < oldFileLength - 1; i++) {
				if (oneFile.equals(oldFile[i])) {
					hasFileInList = true;
					break;
				}
			}
			if (!hasFileInList) {
				cutGeneralFile(rootPath + "temp\\" + oneFile, rootPath + oldFoldName);
			}

		}
		if (oldAccessoryNone == 0) {
			// deleteFile(rootPath + oldFoldName
			// +"\\"+oldFile[oldFile.length-1]);
		}
		// ----------打包------------------//
		/*
		 * byte[] buffer = new byte[1024]; if(fileName.length()>0) { String
		 * strZipName = rootPath+oldFoldName
		 * +"\\"+ sy+sm+sd+p.getSupervisor()+".zip"; try { ZipOutputStream out =
		 * new ZipOutputStream(new FileOutputStream(strZipName)); String[]
		 * allfile = fileName.split("\\*"); for(int i =0;i<allfile.length;i++) {
		 * File tempfile = new File(rootPath+ oldFoldName +"\\"+ allfile[i]);
		 * if(tempfile.exists()) { FileInputStream fis = new
		 * FileInputStream(tempfile); out.putNextEntry(new
		 * ZipEntry(tempfile.getName())); int len; while((len =
		 * fis.read(buffer))>0) { out.write(buffer,0,len); } out.closeEntry();
		 * fis.close(); } } newAccessory =
		 * newAccessory+sy+sm+sd+p.getSupervisor()+".zip"; out.close(); } catch
		 * (Exception e) { e.printStackTrace(); } }
		 */

		newAccessory = newAccessory.substring(0, newAccessory.length() - 1);

		if (fileName.length() != 0) {
			String[] allFile = fileName.split("\\*");
			for (String tempFile : allFile) {
				if (tempFile.endsWith(".doc")) {
					System.out.println(rootPath + oldFoldName + "\\" + tempFile);
					WordToPdf.translateThread(rootPath + oldFoldName + "\\" + tempFile,
							rootPath + oldFoldName + "\\" + tempFile.substring(0, tempFile.length() - 4) + ".pdf");
				} else if (tempFile.endsWith(".docx")) {
					System.out.println(rootPath + oldFoldName + "\\" + tempFile);
					WordToPdf.translateThread(rootPath + oldFoldName + "\\" + tempFile,
							rootPath + oldFoldName + "\\" + tempFile.substring(0, tempFile.length() - 5) + ".pdf");
				} else if (tempFile.endsWith(".xlsx")) {
					System.out.println(rootPath + oldFoldName + "\\" + tempFile);
					ExcelToPdf.excel2pdf(rootPath + oldFoldName + "\\" + tempFile,
							rootPath + oldFoldName + "\\" + tempFile.substring(0, tempFile.length() - 5) + ".pdf");
				} else if (tempFile.endsWith(".xls")) {
					System.out.println(rootPath + oldFoldName + "\\" + tempFile);
					ExcelToPdf.excel2pdf(rootPath + oldFoldName + "\\" + tempFile,
							rootPath + oldFoldName + "\\" + tempFile.substring(0, tempFile.length() - 4) + ".pdf");
				}
			}
		}

		if (fileName == null || fileName.length() <= 0) {
			System.out.println("----is null---");
			p.setAccessory(fileName);
		} else {
			p.setAccessory(newAccessory);
		}

		hiddenTroubleSolutionDAO.updateYinhuanpaicha(p);
		return json;
	}

	public String deleteYinhuanpaicha(String ID, String rootPath) {
		Yinhuanpaicha pro = new Yinhuanpaicha();
		String json = "{\"success\":\"true\"}";
		String[] temp = ID.split(",");
		for (int i = 0; i < temp.length; i++) {
			List<Yinhuanpaicha> list = hiddenTroubleSolutionDAO.checkYinhuanpaichaID((Integer.parseInt(temp[i])));
			// 删除附件
			if (list != null) {
				Yinhuanpaicha p = list.get(0);
				String fileName = p.getAccessory();
				System.out.println(fileName + "length" + fileName.length());
				if (fileName != null && fileName.length() != 0) {
					String[] name = fileName.split("\\*");
					String folder = name[0] + "//" + name[1];
					String path = rootPath + folder;
					System.out.println("--path:" + path);
					for (int j = 2; j < name.length; j++) {
						deleteFile(path + "//" + name[j]);
					}
					DeleteFolder(path);
				}
			}
			pro.setId(Integer.parseInt(temp[i]));
			hiddenTroubleSolutionDAO.deleteYinhuanpaicha(pro);
		}
		return json;
	}
	
	public int importExcel(String type, String rootPath, String fileName, String projectName) {
		String path = rootPath + "temp\\" + fileName;
		int total = 0;
		System.out.println("type : " + type);
		switch (type) {
		case "危险源动态管控":
			List<Weixianyuan> items = ReadExcel.readWeixianyuanExcel(path);
			for (Weixianyuan item : items) {
				item.setProjectName(projectName);
				hiddenTroubleSolutionDAO.insertWeixianyuan(item);
			}
			total = items.size();
			break;
		default:
			break;
		}
		return total;
	}
	
	public String getReadilyShootList(String projectName, String findStr, int limit, int start) {
		List<ReadilyShoot> list = hiddenTroubleSolutionDAO.getReadilyShootList(projectName, findStr, limit, start);
		int total = list.size();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String jsonStr = "{\"total\":" + total + ",\"rows\":[";
		for (int i = 0; i < list.size(); ++i) {
			if (i > 0) jsonStr += ",";
			ReadilyShoot p = list.get(i);
			String prefix = sdf.format(p.getTakeTime()) + "," + p.getUploadedBy() + "于" + p.getPosition() + "拍摄上传。";
			String commnet = "隐患问题为" + p.getComment();
			jsonStr += "{\"ID\":" + p.getId() 
					+ ",\"url\":\"" + p.getUrl().replaceAll("\\\\", "/")
					+ "\",\"prefix\":\"" + prefix
					+ "\",\"comment\":\"" + commnet 
					+ "\",\"project\":\"" + p.getProject() + "\"}";
			}
		jsonStr += "]}";
		System.out.println(jsonStr);
		return jsonStr;
	}
	
	public String deleteReadilyShoot(String ID, String rootPath) {
		ReadilyShoot pro = new ReadilyShoot();
		String json = "{\"success\":\"true\"}";
		String[] temp = ID.split(",");
		for(int i = 0; i < temp.length; i++) {
			ReadilyShoot p = hiddenTroubleSolutionDAO.getReadilyShoot(Integer.parseInt(temp[i]));
			hiddenTroubleSolutionDAO.deleteReadilyShoot(p);
		} 
		return json;
	}

}
