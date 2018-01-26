package PSM.SMS;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import com.qcloud.sms.SmsMultiSender;
import com.qcloud.sms.SmsMultiSenderResult;

import PSM.DAO.BasicInfoDAO;
import hibernate.Persondb;

public class SendTest {
	
	
	private static BasicInfoDAO basicInfoDAO = new BasicInfoDAO();
	
	
	String content;
	String tel;
	String appkey;
	int appid;
	int tmplId;
	public SendTest(String name, String content, String tel,int appid, String appkey,int tmplId) throws Exception {
		this.content = content;
		this.tel = tel;
		this.appid = appid;
		this.appkey =appkey;
		this.tmplId = tmplId;
		
		
		//初始化单发
    	SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
    	SmsSingleSenderResult singleSenderResult;
    	ArrayList<String> params = new ArrayList<>();
    	params.add(name);
    	params.add(content);
    	//任务模版：亲爱的｛1｝，您收到任务｛2｝，祝好！
    	singleSenderResult = singleSender.sendWithParam("86", tel, tmplId, params, "", "", "");
		
    	System.out.println(singleSenderResult);
	}
	
	public static void sendMessage(String name, String content, String phoneNumber1 ) {
		try {
			int appid = 1400045042;
    		String appkey = "4137e296481ae55a3697842fa0004ff8";
    		int tmplId = 49821; //尊敬的{1}{2}

    		
    		SendTest test = new SendTest(name, content, phoneNumber1,appid,appkey,tmplId);
    		
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendMessage2(String content, String phoneNumber1 ) {
		try {
			int appid = 1400045042;
    		String appkey = "4137e296481ae55a3697842fa0004ff8";
    		int tmplId = 49820; //湖北院任务提醒

    		//初始化单发
        	SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
        	SmsSingleSenderResult singleSenderResult;
        	ArrayList<String> params = new ArrayList<>();
        	params.add(content);
        	singleSenderResult = singleSender.sendWithParam("86", phoneNumber1, tmplId, params, "", "", "");
    		
        	System.out.println(singleSenderResult);
    		
    		
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void sendMessageByType( String content, String type ) {
		try {
			int appid = 1400045042;
    		String appkey = "4137e296481ae55a3697842fa0004ff8";
    		int tmplId = 49820; //湖北院任务提醒
    		
    		List<Persondb> list = basicInfoDAO.getPersondbByType(type);
    		
    		
    		/*for(int i=0;i<list.size();i++) {
    			System.out.println(content + list.get(i).getName());
    			SendTest test = new SendTest(content, list.get(i).getPhone(),appid,appkey);
    		}*/
	    	
	    	// 初始化群发
	    	SmsMultiSender multiSender = new SmsMultiSender(appid, appkey);
	    	SmsMultiSenderResult multiSenderResult;
	
	    	ArrayList<String> params = new ArrayList<>();
	    	params.add(content);
	    	
	    	ArrayList<String> phoneNumbers = new ArrayList<>();
	    	
	    	for(int i=0;i<list.size();i++) {
    			System.out.println(content + list.get(i).getName());
    			phoneNumbers.add(list.get(i).getPhone());
	    	}

	    	multiSenderResult = multiSender.sendWithParam("86", phoneNumbers, tmplId, params, "", "", "");
	    	System.out.println(multiSenderResult);
	    	
    		
    		
    		
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
