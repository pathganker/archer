/**   */
package cn.com.qingqfeng.archer.webapi.controller.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enums.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.utils.VerifyCodeUtils;

/**   
 * <p>类名称: AuthController </p> 
 * <p>描述: TODO  </p>
 * <p>创建时间 : 2019年2月22日 上午10:38:21 </p>
 * @author lijunliang
 * @version 1.0
 * 
 */
@RestController
@RequestMapping("auth")
public class AuthController {
	/**
	 * 
	 * <p>方法名:  getCaptcha </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月22日下午1:04:10 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param req
	 * @param res  
	 * void
	 */
	@RequestMapping(value="captcha", method={RequestMethod.GET})
	public void getCaptcha(HttpServletRequest req, HttpServletResponse res) {
		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		Subject user = SecurityUtils.getSubject();
		user.getSession().setAttribute("verCode", verifyCode.toLowerCase());
//		// 存入会话session
//		HttpSession session = req.getSession(true);
//		// 删除以前的
//		session.removeAttribute("verCode");
//		session.removeAttribute("codeTime");
//		session.setAttribute("verCode", verifyCode.toLowerCase());
//		session.setAttribute("codeTime", LocalDateTime.now());
		// 生成图片
		int w = 200, h = 40;
		try {
			OutputStream out = res.getOutputStream();
			VerifyCodeUtils.outputImage(w, h, out, verifyCode);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * <p>方法名:  validateCaptcha </p> 
	 * <p>描述:    TODO </p>
	 * <p>创建时间:  2019年2月22日下午1:04:07 </p>
	 * @version 1.0
	 * @author lijunliang
	 * @param req
	 * @param res
	 * @return  
	 * Result
	 */
	@RequestMapping(value="vercode", method={RequestMethod.GET})
	public Result validateCaptcha(HttpServletRequest req, HttpServletResponse res) {
		Result rs = new Result();
		Subject user = SecurityUtils.getSubject();	
		String captcha = (String) req.getParameter("captcha");
		//大写转小写
		if(null != captcha){
			captcha=captcha.toLowerCase();
		}
		String verCode = (String) req.getSession().getAttribute("verCode");
		if (null != verCode && verCode.equals(captcha)){
			rs.setCode(ApiCodeEnum.SUCCESS);
		}else{
			rs.setCode(ApiCodeEnum.CAPTCHA_WRONG);
		}
		return rs;
	}
}
