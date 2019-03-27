/**   */
package cn.com.qingqfeng.archer.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**   
 * <p>类名称: PictureUtils </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年3月21日 下午4:36:41 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
public class PictureUtils {
	
	private final static String BASEDIR = "/ROOT";
	
	
	public static String getPreview(String imageUrl, int wid, int heig) throws IOException{
		File image = new File(BASEDIR+imageUrl);
		String[] name = imageUrl.split("\\.");
		String targetUrl = BASEDIR+name[0]+"_pre"+".png";
		File target = new File(targetUrl);
		if(!target.exists()){
			target.mkdirs();
		}
		BufferedImage bufImage = ImageIO.read(image);
		final Integer height = bufImage.getHeight();
		final Integer width = bufImage.getWidth();
		Integer x = 0,y=0,w=0,h=0;
		if(height>= width){
			w=width;
			h=width;
			y=(height-width)/2;
		}else{
			w=height;
			h=height;
			x=(width-height)/2;
		}
		BufferedImage temp = bufImage.getSubimage(x, y, w, h);
		Image  preview = temp.getScaledInstance(wid, heig, Image.SCALE_SMOOTH);
		BufferedImage bufprev = new BufferedImage(wid, heig, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufprev.createGraphics();
		g2.drawImage(preview, 0, 0,wid, heig, null);
		g2.dispose();
		ImageIO.write(bufprev, "png", target);
		return name[0]+"_pre"+".png";
	}
}
