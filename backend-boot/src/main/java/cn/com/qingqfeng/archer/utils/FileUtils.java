/**   */
package cn.com.qingqfeng.archer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


/**   
 * <p>类名称: FileUtils </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月5日 下午5:40:16 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class FileUtils {
	
	private final static  Logger LOGGER = LoggerFactory.getLogger(FileUtils.class); 
	private final static String BASEDIR = "/ROOT";
	private final static String COVERDIR = "/upload/picture/cover";
	
	public static String handleCover(MultipartFile file, String articleId) throws RuntimeException{
		if(null == file || file.getSize() < 1){
			return null;
		}
		//重命名
		String filename = file.getOriginalFilename();
    	String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    	String newFilename =time+"_"+filename;
    	//创建文件
    	File newFile = new File(BASEDIR+COVERDIR+"/"+articleId, newFilename);
    	if(!newFile.exists()){
    		newFile.getParentFile().mkdirs();
    	}
    	//拷贝
    	try{
    		InputStream source = file.getInputStream();
    		OutputStream target = new FileOutputStream(newFile.getAbsoluteFile());
    		byte[] buffer = new byte[1024];
    	    int bytesRead;
    	    while ((bytesRead = source.read(buffer)) != -1) {
    	    	target.write(buffer, 0, bytesRead);
    	    }
    	    target.close();
    	    source.close();
    	}catch(IllegalStateException | IOException e){
    		LOGGER.error("复制文件错误:{}",e);
    		throw new RuntimeException("复制文件错误:"+e);
    	}
    	return  COVERDIR+"/"+articleId+"/"+newFilename;
	}
}
