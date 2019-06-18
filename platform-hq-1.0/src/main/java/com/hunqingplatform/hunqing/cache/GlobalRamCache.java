package com.hunqingplatform.hunqing.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hunqingplatform.hunqing.common.model.mo.PhoneCodeMO;
import com.hunqingplatform.hunqing.exception.CommonException;
@Component
public class GlobalRamCache {
	private static Logger log = LoggerFactory.getLogger(GlobalRamCache.class);
	static long fiveMinutesSeconds=5*60*1000L;
	public static Map<String, PhoneCodeMO> phoneCodeMap=new HashMap<String, PhoneCodeMO>();
	
	public static void  chekPhoneCode(String phone,String code) throws CommonException {
		log.info("接收到验证码验证数据phone="+phone+",code="+code);
		PhoneCodeMO phoneCodeMO=phoneCodeMap.get(phone);
		if (null==phoneCodeMO) {
			throw new CommonException("验证码不存在", 500);
		}
		String ecode=phoneCodeMO.getCode();
		log.info("根据phone="+phone+"查出的code="+code);
		if (!ecode.equals(code)) {
			throw new CommonException("验证码不正确", 500);
		}
		long createtime=phoneCodeMO.getSendTime();
		long nowtime=System.currentTimeMillis();
		
		long sub=nowtime-createtime;
		if (sub>fiveMinutesSeconds) {
			throw new CommonException("验证码已过期", 500);
		}
	}
}
