package cn.willhoo.ssh_bos.utils.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月18日 下午3:12:07   
*/
public class AliyunSms {
	
	private static final String VALIDATE_TEMPLATE = "SMS_132385228";
	private static final String ADVICE_TEMPLATE = "SMS_133270880";
	
	/**
	 * 
	 * @param courierPhone 快递员手机号
	 * @param customerPhone 客户手机号
	 * @return
	 */
	public static boolean sendAdviceForCourier(String courierPhone,String customerPhone) {
		String message = "{\"phone\":\""+customerPhone+"\"}";
		return sendSmsByAliyun(courierPhone,message,ADVICE_TEMPLATE);
	}
	
	/**
	 * 
	 * @param phone 需要发送到的手机号
	 * @param code 需要发送的验证码
	 * @return
	 */
	public static boolean sendValiCode(String phone,String code) {
		String message = "{\"code\":\""+code+"\"}";
		return sendSmsByAliyun(phone,message,VALIDATE_TEMPLATE);
	}

	public static boolean sendSmsByAliyun(String phone, String message,String templateId){
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
		
		
		//替换成你的AK
		final String accessKeyId = "LTAIhJlUN7VkRBCE";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "E4zb32oDg3ligib2XXH7vrGMqxC6Va";//你的accessKeySecret，参考本文档步骤2
		
		
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //使用post提交
		 request.setMethod(MethodType.POST);
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		 request.setPhoneNumbers(phone);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName("肖鼎");
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode(templateId);
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		 request.setTemplateParam(message);
		 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		 //request.setSmsUpExtendCode("90997");
		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//				 request.setOutId("yourOutId");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = null;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (ClientException e) {
			e.printStackTrace();
			return false;
		}
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
		//请求成功
			System.out.println("请求成功");
			return true;
		}else {
			System.out.println("请求失败"+sendSmsResponse.getCode());
			return false;
		}
	}
}
