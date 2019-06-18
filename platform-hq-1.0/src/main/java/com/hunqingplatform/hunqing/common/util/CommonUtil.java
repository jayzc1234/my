package com.hunqingplatform.hunqing.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hunqingplatform.hunqing.common.page.DaoSearch;
import com.hunqingplatform.hunqing.common.page.Page;
import com.hunqingplatform.hunqing.common.properties.NormalProperties;
import com.hunqingplatform.hunqing.dao.ext.HqUserMapperExt;
import com.hunqingplatform.hunqing.exception.CommonException;
import com.hunqingplatform.hunqing.pojo.HqUser;

/**
 * 基础工具类
 *
 * @author zcy
 */
@Component
public class CommonUtil {
	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    @Value("${hunqing.file.linuxpath}")
    private String linuxpath;
    
    @Value("${hunqing.file.winpath}")
    private String winpath;
    
    @Autowired
    private HqUserMapperExt userDao;
    
    public  String getFilePath() {
		String os = System.getProperty("os.name");  
		String filePath=null;
		if(os.toLowerCase().startsWith("win")){  
			filePath=winpath;
		}else {
			filePath=linuxpath;
		}
		logger.info("文件路径："+filePath);
		return filePath;
    }
    public void validateRequestParamAndValueNotNull(Map<String, Object> params, String... args) throws CommonException {

        if (params.size() == 0) {
            throw new CommonException("业务参数不能为空");
        }

        if (args.length == 0) {
            return;
        }

        List<String> list1 = new ArrayList<>();
        list1.addAll(params.keySet());

        for (String key : list1) {
            if (params.get(key) == null || params.get(key).toString().equals("") || params.get(key).toString().toLowerCase().equals("null")) {
                params.remove(key);
                continue;
            }
            if (params.get(key) instanceof Map) {
                Map map = (Map) params.get(key);
                if (map.size() == 0) {
                    params.remove(key);
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (String arg : args) {
            if (!params.containsKey(arg)) {
                list.add(arg);
            }
        }

        if (list.size() > 0) {
            throw new CommonException("Missing parameter {" + list.toString() + "}");
        }
    }
    /**
     * 根据入参和总记录数，返回页码实体类
     * @param params
     * @param total
     * @return
     * @throws Exception
     * @author liujianqiang
     * @data 2018年10月11日
     */
    public Page getPage(Map<String, Object> params, int total) throws CommonException {
        Object pageCountObj = params.get("pageSize");//每页记录数
        Object currentPageObj = params.get("current");//当前页
        int pageCount;
        int currentPage;
        if (isNull(pageCountObj) || Integer.parseInt(pageCountObj.toString()) == 0) {//假如每页记录数为空,默认为10条
            pageCount = NormalProperties.DEFAULT_PAGE_COUNT;
        } else {
            pageCount = Integer.parseInt(pageCountObj.toString());
        }
        if (isNull(currentPageObj) || Integer.parseInt(currentPageObj.toString()) == 0) {//假如当前页为空,则当前页设置为默认值1
            currentPage = NormalProperties.DEFAULT_CURRENT_PAGE;
        } else {
            currentPage = Integer.parseInt(currentPageObj.toString());
        }
        return new Page(pageCount, currentPage, total);
    }
    /**
     * 小写md5返回值
     * @param str
     * @return
     * @throws CommonException
     */
    public static String md5ToLower(String str) throws CommonException {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new CommonException("获取md5对象失败");
        }
        try {
            md5.update((str).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new CommonException("待转字符串转字节数组失败");
        }
        byte b[] = md5.digest();

        int i;
        StringBuffer buf = new StringBuffer("");

        for(int offset=0; offset<b.length; offset++){
            i = b[offset];
            if(i<0){
                i+=256;
            }
            if(i<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }
    public HqUser getBackUser(HttpServletRequest request) throws CommonException {
    	HqUser user=(HqUser) request.getSession().getAttribute("backUser");
    	if (null==user) {
			throw new CommonException("后台用户未登录，请先登录", 500);
		}
    	return user;
    }
    
    public int getBackUserId(HttpServletRequest request) throws CommonException {
    	HqUser user=(HqUser) request.getSession().getAttribute("backUser");
    	if (null==user) {
    		String userId=request.getHeader("userId");
    		logger.info("userId="+userId);
    		if (StringUtils.isNotBlank(userId)) {
    			user=userDao.selectByPrimaryKey(Integer.parseInt(userId));
    			if (null==user) {
    				throw new CommonException("获取用户id方法根据用户id:"+userId+"无法获取用户信息", 500);
    			}
			}
			if (null==user) {
				throw new CommonException("后台用户未登录", 500);
			}
		}
    	return user.getId() ;
    }

    public HqUser getFrontUser(HttpServletRequest request) throws CommonException {
    	HqUser user=(HqUser) request.getSession().getAttribute("frontUser");
    	if (null==user) {
    		String userId=request.getHeader("userId");
    		logger.info("userId="+userId);
    		if (StringUtils.isNotBlank(userId)) {
    			user=userDao.selectByPrimaryKey(Integer.parseInt(userId));
    			if (null==user) {
    				throw new CommonException("获取用户id方法根据用户id:"+userId+"无法获取用户信息", 500);
    			}
			}
    		if (null==user) {
    			throw new CommonException("前台用户未登录，请先登录", 500);
			}
		}
    	return user;
    }

    public int getFrontUserId(HttpServletRequest request) throws CommonException {
    	HqUser user=(HqUser) request.getSession().getAttribute("frontUser");
    	if (null==user) {
    		String userId=request.getHeader("userId");
    		logger.info("userId="+userId);
    		if (StringUtils.isNotBlank(userId)) {
    			user=userDao.selectByPrimaryKey(Integer.parseInt(userId));
    			if (null==user) {
    				throw new CommonException("获取用户id方法根据用户id:"+userId+"无法获取用户信息", 500);
    			}
			}
    		if (null==user) {
    			throw new CommonException("前台用户未登录，请先登录", 500);
			}
		}
    	return user.getId();
    }
    
    public int getUserId(HttpServletRequest request) throws CommonException {
    	HqUser user=(HqUser) request.getSession().getAttribute("backUser");
    	logger.info("后台登录用户="+user);
    	if (null==user) {
            user=(HqUser) request.getSession().getAttribute("frontUser");
            logger.info("前台登录用户="+user);
			if (null==user) {
				throw new CommonException("用户未登录", 500);
			}
		}
    	return user.getId() ;
    }
    /**
     * 验证参数是否为空,为空返回true
     *
     * @param obj
     * @return
     * @author liujianqiang
     * @data 2018年10月11日
     */
    public boolean isNull(Object obj) {
        if (obj == null || "".equals(obj.toString())) {
            return true;
        } else {
            return false;
        }
    }



    /**
     * 功能描述：对象转换成对应的对象
     *
     * @return T
     * @Author corbett
     * @Description //TODO
     * @Date 14:07 2018/10/12
     * @Param [o-需要转换的对象, c--需要转换成的对象类型]
     **/
    public <T> T toJavaBean(Object o, Class<T> c) throws CommonException {

        if (isNull(o) || isNull(c)) {
            throw new CommonException("转换的对象或者class类都不能为null");
        }
        return JSONObject.parseObject(o instanceof String ? o.toString() : JSON.toJSONString(o), c);
    }



    /**
     * @return
     * @Author corbett
     * @Description //TODO 校验手机号格式是否正确
     * @Date 9:17 2018/12/20
     * @Param
     **/
    public void checkPhoneFormat(String phone) throws CommonException {
        if (!PhoneFormatCheckUtils.isPhoneLegal(phone)) {
            throw new CommonException("手机号格式不正确", 500);
        }
    }


    /**
     * 获取32位UUID，去掉中间的-
     *
     * @return
     * @author corbett
     * @data 2018年9月4日
     */
    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    /**
     * 产生6位随机数
     *
     * @return
     * @throws Exception
     * @author liujianqiang
     * @data 2018年9月5日
     */
    public String getSixRandom() throws Exception {
        Random random = new Random();
        int sixRandom = random.nextInt(899999) + 100000;
        String result = "" + sixRandom;
        if (result.length() != 6) {
            throw new Exception("不是六位随机数,number= " + result);
        }
        return result;
    }

    
    
    public static String replaceSpicialChactar(String data) {
    	String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？_-]";
    	//可以在中括号内加上任何想要替换的字符
    	String aa= "";//这里是将特殊字符换为aa字符串,""代表直接去掉
    	Pattern p = Pattern.compile(regEx);
    	Matcher m = p.matcher(data);//这里把想要替换的字符串传进来
    	String newString= m.replaceAll(aa).trim();
		return newString;
    }
    
}
