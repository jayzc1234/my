package com.hunqingplatform.hunqing.controller.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.exception.CommonException;
import com.hunqingplatform.hunqing.service.CommonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/auth/back/file")
@Api(tags = "文件操作接口")
public class FileController extends CommonUtil {
	private static Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private CommonService service;

    @Autowired
    private CommonUtil commonUtil;
    
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    @ApiOperation(value = "下载文件", notes = "")
    @ApiImplicitParams({
    	  @ApiImplicitParam(name = "fileName", paramType = "query", defaultValue = "64b379cd47c843458378f479a115c322.jpg", value = "文件名", required = true),
		@ApiImplicitParam(name = "thum", paramType = "query", defaultValue = "", value = "是否返回缩略图 默认是，传0返回原图", required = false) }
    )
    public void download(@RequestParam String fileName,@RequestParam(required=false) Integer thum,HttpServletRequest request,HttpServletResponse response) throws Exception {
    	InputStream in=null;
    	try {
    		Integer userId=getUserId(request);
    		String wholeFilePath=commonUtil.getFilePath()+File.separator+"thum"+File.separator+userId;
    		if (null!=thum && thum.intValue()==0) {
    			wholeFilePath=commonUtil.getFilePath()+File.separator+userId;
			}
    		logger.info("文件下载路径："+wholeFilePath);
    		String wholeFileName=wholeFilePath+File.separator+fileName;
    		in=new FileInputStream(wholeFileName);
    		byte[] buf=new byte[1024];
    		int len=0;
    		while((len=in.read(buf))!=-1) {
    			response.getOutputStream().write(buf, 0, len);
    			response.getOutputStream().flush();
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("下载文件错误："+e.getMessage(),500);
		}finally {
			if (null!=in) {
				in.close();
			}
		}
    }
    
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "上传单个文件",notes = "文件唯一id")
	@ApiImplicitParams({		
		@ApiImplicitParam(name = "uploadFile",paramType ="file",required=true)
	})
	public RestResult<String> uploadFile(
			@RequestParam(value="uploadFile") MultipartFile file,HttpServletRequest request) throws Exception{
		RestResult<String> restResult=new RestResult<String>();
	    if (null==file) {
    	  restResult.setState(500);
    	   restResult.setMsg("文件不能为空");
    	   return restResult;
		}
	    restResult.setState(200);
	    String name=service.uploadFile(file,request);
	    restResult.setResults(name);
	    restResult.setMsg("成功");
		return restResult;
	}

	@RequestMapping(value = "/uploads",method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "上传多个个文件",notes = "文件唯一id")
	@ApiImplicitParams({		
		@ApiImplicitParam(name = "uploadFiles",paramType ="file",required=true)
	})
	public RestResult<String[]> uploadFiles(
			@RequestParam(value="uploadFiles") MultipartFile[] files,HttpServletRequest request) throws Exception{
		RestResult<String[]> restResult=new RestResult<String[]>();
	    if (null==files || files.length==0) {
    	  restResult.setState(500);
    	   restResult.setMsg("文件不能为空");
    	   return restResult;
		}
	    restResult.setState(200);
	    String[] names=service.uploadFiles(files,request);
	    restResult.setResults(names);
	    restResult.setMsg("成功");
		return restResult;
	}
}
