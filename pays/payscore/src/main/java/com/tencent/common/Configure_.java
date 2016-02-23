package com.tencent.common;

/**
 * User: rizenguo Date: 2014/10/29 Time: 14:40 这里放置各种配置数据
 */
public class Configure_ {
	// 这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
	// 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
	// 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改

	private static String key = "51c56b886b5be869567dd389b3e5d1d6";
	// 公众号里的私钥
	private static String appsecret = "1937e6b47786c86fac37303f97d5ed91";
	// 微信分配的公众号ID（开通公众号之后可以获取到）
	// private static String appID = "wxb89ebba3cec98a8c";//第一版
	private static String appID = "wx5505d0e3b58607a6";

	// 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	// private static String mchID = "1243442302";//第一版
	private static String mchID = "1291102901";

	// 受理模式下给子商户分配的子商户号
	private static String subMchID = "";

	// HTTPS证书的本地路径
	private static String certLocalPath = "G:\\project\\e代送众包版\\Eds.SuperMan\\SuperManWebApi\\cert\\apiclient_cert.p12";

	// HTTPS证书密码，默认密码等于商户号MCHID
	// private static String certPassword = "1233410002";//第一版
	private static String certPassword = "1291102901";

	// 是否使用异步线程的方式来上报API测速，默认为异步模式
	private static boolean useThreadToDoReport = true;

	public static String getAppsecret() {
		return appsecret;
	}

	public static void setAppsecret(String appsecret) {
		Configure_.appsecret = appsecret;
	}

	// 机器IP
	private static String ip = "127.0.0.1";

	// 以下是几个API的路径：
	// 1）被扫支付API
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

	// 2）被扫支付查询API
	public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	// 3）退款API
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	// 4）退款查询API
	public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	// 5）撤销API
	public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	// 6）下载对账单API
	public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	// 7) 统计上报API
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		Configure_.useThreadToDoReport = useThreadToDoReport;
	}

	public static String HttpsRequestClassName = "com.tencent.common.HttpsRequest";

	public static void setKey(String key) {
		Configure_.key = key;
	}

	public static void setAppID(String appID) {
		Configure_.appID = appID;
	}

	public static void setMchID(String mchID) {
		Configure_.mchID = mchID;
	}

	public static void setSubMchID(String subMchID) {
		Configure_.subMchID = subMchID;
	}

	public static void setCertLocalPath(String certLocalPath) {
		Configure_.certLocalPath = certLocalPath;
	}

	public static void setCertPassword(String certPassword) {
		Configure_.certPassword = certPassword;
	}

	public static void setIp(String ip) {
		Configure_.ip = ip;
	}

	public static String getKey() {
		return key;
	}

	public static String getAppid() {
		return appID;
	}

	public static String getMchid() {
		return mchID;
	}

	public static String getSubMchid() {
		return subMchID;
	}

	public static String getCertLocalPath() {
		return certLocalPath;
	}

	public static String getCertPassword() {
		return certPassword;
	}

	public static String getIP() {
		return ip;
	}

	public static void setHttpsRequestClassName(String name) {
		HttpsRequestClassName = name;
	}

}