package com.hunqingplatform.hunqing.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.dao.ext.HqProjectMapperExt;
import com.hunqingplatform.hunqing.exception.CommonException;
import com.hunqingplatform.hunqing.pojo.HqProject;

import net.coobird.thumbnailator.Thumbnails;

@Component
public class CommonService extends CommonUtil{
	
private static List<String> vido_suffix=new ArrayList<>(Arrays.asList(new String[] {"avi","mp4","wmv"}));
private static List<String> pic_suffix=new ArrayList<>(Arrays.asList(new String[] {"png","jpg","jpeg","gif","bmp"}));
    @Value("${hunqing.file.linuxpath}")
    private String linuxpath;
    
    @Value("${hunqing.file.winpath}")
    private String winpath;
    
	@Autowired
	private HqProjectMapperExt dao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	private static ExecutorService executors=Executors.newFixedThreadPool(5);
	
	public String uploadFile(MultipartFile file, HttpServletRequest request)  {
		InputStream inputstream=null;
		FileOutputStream fileOutputStream=null;
		try {
			inputstream=file.getInputStream();
			String name=file.getOriginalFilename();
			int suffixlen=name.lastIndexOf(".");
			String suffix=name.substring(suffixlen+1, name.length());
			String prefix =getUUID();
			String newName=prefix+"."+suffix;
            int userId=getBackUserId(request);
			String wholeFilePath=commonUtil.getFilePath()+File.separator+userId;
			
			File dir=new File(wholeFilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String wholeName=wholeFilePath+File.separator+newName;
			fileOutputStream=new FileOutputStream(wholeName);
			byte[]buf=new byte[1024];
			int length=0;
			while((length=inputstream.read(buf))>0) {
				fileOutputStream.write(buf, 0, length);
			}
			fileOutputStream.flush();
			fileOutputStream.close();
			executors.execute(new Runnable() {
				
				@Override
				public void run() {
                    	File fromPic=new File(wholeName);
                    	
                    	File toPicdir=new File(commonUtil.getFilePath()+File.separator+"thum"+File.separator+userId);
                    	if (!toPicdir.exists()) {
                    		toPicdir.mkdirs();
						}
                    	File toPic=new File(toPicdir, newName);
                    	if (fromPic.exists() && fromPic.isFile()) {
                    		//按指定大小把图片进行缩和放（会遵循原图高宽比例） 
                            try {
                            	Thumbnails.of(fromPic).scale(1f).outputQuality(0.25f).toFile(toPic);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
				}
			});
			return newName;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if (null!=inputstream) {
					inputstream.close();
					}
					if (null!=fileOutputStream) {
						fileOutputStream.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return null;
	}

	public String[] uploadFiles(MultipartFile[] files, HttpServletRequest request) throws CommonException {
		String names[]=new String[files.length];
		int i=0;
		StringBuffer picbuf=new StringBuffer();
		StringBuffer vidobuf=new StringBuffer();
		HqProject pro=new HqProject();
        for (MultipartFile multipartFile : files) {
        	String name=uploadFile(multipartFile, request);	
        	if (pic_suffix.contains(name.split("\\.")[1])) {
        		picbuf.append(name).append(",");
			}else {
				vidobuf.append(name).append(",");
			}
        	
        	names[i++]=name;
		}
        if (picbuf.length()>0) {
			
        	pro.setPicUrls(picbuf.subSequence(0,picbuf.length()-1).toString());
		}
        if (vidobuf.length()>0) {
			
        	pro.setVideoUrls(vidobuf.subSequence(0,vidobuf.length()-1).toString());
		}
        pro.setUserId(commonUtil.getBackUserId(request));
        dao.insert(pro);	
		return names;
	}

}
