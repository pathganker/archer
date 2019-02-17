/**
 * 
 */
package cn.com.qingqfeng.archer.webapi.controller.user;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.qingqfeng.archer.enumEntity.ApiCodeEnum;
import cn.com.qingqfeng.archer.pojo.Result;
import cn.com.qingqfeng.archer.utils.VerifyCodeUtils;

/**   
 * <p> 类名：  LoginController   </p>
 * <p> 描述：	TODO  </p>
 * <p> 创建时间： 2019年2月17日 下午5:24:22   </p>
 * @author lijunliang 
 * @version 1.0   
 */
@RestController
@RequestMapping("users")
public class UserController {
	
	@RequestMapping("snsLogins")
	public Result requestSnsLogins() {
		Result result = new Result();
		//test
		result.setCode(ApiCodeEnum.SUCCESS);
		List<String> code = new ArrayList<>();
		code.add("test");
		result.setData(code);
		return result;
	}
	
	@RequestMapping("getCaptcha")
	public void requestCaptcha(HttpServletRequest req, HttpServletResponse res) {
		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = req.getSession(true);
		// 删除以前的
		session.removeAttribute("verCode");
		session.removeAttribute("codeTime");
		session.setAttribute("verCode", verifyCode.toLowerCase());
		session.setAttribute("codeTime", LocalDateTime.now());
		// 生成图片
		int w = 200, h = 40;
		try {
			OutputStream out = res.getOutputStream();
			VerifyCodeUtils.outputImage(w, h, out, verifyCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
