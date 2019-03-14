/**   */
package cn.com.qingqfeng.archer.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	private final static String COVERDIR = "\\\\upload/picture/cover";
	
	public static String handleCover(MultipartFile file, String articleId) throws RuntimeException{
		if(null == file || file.getSize() < 1){
			return null;
		}
		//重命名
		String filename = file.getOriginalFilename();
    	String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    	String newFilename =time+"_"+filename;
    	//创建文件
    	File newFile = new File(COVERDIR+"/"+articleId, newFilename);
    	if(!newFile.exists()){
    		newFile.mkdirs();
    	}
    	//拷贝
    	try{
    		file.transferTo(newFile);
    	}catch(IllegalStateException | IOException e){
    		throw new RuntimeException("复制文件错误:"+e);
    	}
    	return  COVERDIR+"/"+articleId+"/"+newFilename;
	}
}
