package com.dascom.product.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dascom.product.constant.LoginConstant;
import com.dascom.product.entity.CpUser;
import com.dascom.product.entity.UpdateInfo;
import com.dascom.product.service.ProductService;
import com.dascom.product.service.ResourceService;
import com.dascom.product.service.UserService;
import com.dascom.product.util.TimeFilter;
import com.dascom.product.util.ValidateCode;

@Controller
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private ProductService productServiceImpl;
	@Autowired
	private ResourceService resourceServiceImpl;
	
	
	
	
	/**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     * @ValidateCode.generateTextCode(验证码字符类型,验证码长度,需排除的特殊字符)
     * @ValidateCode.generateImageCode(文本验证码,图片宽度,图片高度,干扰线的条数,字符的高低位置是否随机,图片颜色,字体颜色,干扰线颜色)
     */
    @RequestMapping(value = "validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_LOWER, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 5, true, Color.WHITE, Color.BLUE, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }
	
	
	//跳转到登录首页
	@RequestMapping(value="login",produces="application/json;charset=utf-8")
	public String login(){
		return "Login/index";
	}
	
	//跳转到跳转提示
	@RequestMapping("loginHint")
	public String loginHint(HttpServletRequest request,String hint ){
		request.setAttribute("hint", hint);
		return "Login/loginHint";
	}
	//用户登录
	@RequestMapping(value="loginSubmit" ,method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String loginSubmit(HttpServletRequest request, String username,String password){
		//保存用户名
		request.getSession().setAttribute("username", username);
		Session session = SecurityUtils.getSubject().getSession();
		//系统的验证码
        String code = (String) session.getAttribute("validateCode");
        //用户输入的验证码
        String submitCode = WebUtils.getCleanParam(request, "validateCode");
        
        
       if (StringUtils.isEmpty(submitCode)  || !StringUtils.equals(code,submitCode.toLowerCase())) {
            return loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_100000, LoginConstant.LOGIN_ERROR_MESSAGE_VALIDATECODE, null);
        }
        // 想要得到 SecurityUtils.getSubject() 的对象．．访问地址必须跟shiro的拦截地址内．不然后会报空指针 
       
        Subject sub = SecurityUtils.getSubject(); 
        // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
        // 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
        // 当以上认证成功后会向下执行,认证失败会抛出异常
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            sub.login(token);
            //从subject中获取保存在安全管理员中的user对象
            CpUser cpUser = (CpUser) sub.getPrincipal();
            //将用户保存在session中,因为自定义拦截器的原因(如果没有自定义拦截器,将用户存放在session域中的这个步骤可以省略)
            request.getSession().setAttribute("cpUser", cpUser);
            return loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_1000, LoginConstant.LOGIN_SUCCEED_MESSAGE_VALIDATECODE, null);
        } catch (LockedAccountException lae) {
            token.clear();
            return loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_100002, LoginConstant.LOGIN_ERROR_MESSAGE_SYSTEMERROR, null);
        } catch (ExcessiveAttemptsException e) {	
            token.clear();
            return loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_100003, "账号：" + username + LoginConstant.LOGIN_ERROR_MESSAGE_MAXERROR, null);
        } catch (AuthenticationException e) {
            token.clear();
            e.printStackTrace();
            return loginJsonTransform( LoginConstant.LOGIN_ERROR_CODE_100001, LoginConstant.LOGIN_ERROR_MESSAGE_USERERROR, null);
        }
	}
	
	//主页
	@RequestMapping("index")
	public String index(){
		return "Index/index";
	}
	
	//首页欢迎页面
	@RequestMapping("welcome")
	public String welcome( HttpServletRequest request){
		//当前用户的ip地址. 
		String ip= request.getHeader("x-forwarded-for")!=null?request.getHeader("x-forwarded-for"):request.getRemoteAddr();
		//更新信息统计
		UpdateInfo userInfo =userServiceImpl.findUpdateInfo();
		UpdateInfo productInfo =productServiceImpl.findUpdateInfo();
		UpdateInfo resourceInfo =resourceServiceImpl.findUpdateInfo();
		
		
		
		
		
        /*Calendar c = Calendar.getInstance();
       
        Date day =new Date(); //今天  
        
        c=Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-1 );  //昨天 
        Date yesterday =c.getTime();
        
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 1);//本周第一天，以星期日开始        
        Date startWeek=c.getTime();
        
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 7);//本周最后一天
        Date lasttWeek =c.getTime();
        
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);//本月第一天
        Date startMonth =c.getTime();
        
        c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);//本月最后一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date lastMonth=c.getTime();*/
        
        
        
        
        
        //
        
        
        
        // 
       /* List<Date> listDate=new ArrayList<>();
        listDate.add(new Date());
        listDate.add(new Date());
        listDate.add(new Date());*/
        
        //信息更新统计
        //总数 
        
        //管理员
        /*request.setAttribute("user.all", TimeFilter.timeFilter(listDate, startMonth, lastMonth));
        request.setAttribute("user.month", TimeFilter.timeFilter(listDate, startMonth, lastMonth));
        
        request.setAttribute("user.today", TimeFilter.timeFilter(listDate, day, day));
        request.setAttribute("user.yesterday", TimeFilter.timeFilter(listDate, yesterday, yesterday));
        request.setAttribute("user.week", TimeFilter.timeFilter(listDate, startWeek, lasttWeek));
       
        //产品库
        request.setAttribute("prodcut.all", TimeFilter.timeFilter(listDate, startMonth, lastMonth));
        request.setAttribute("prodcut.month", TimeFilter.timeFilter(listDate, startMonth, lastMonth));
        
        request.setAttribute("prodcut.today", TimeFilter.timeFilter(listDate, day, day));
        request.setAttribute("prodcut.yesterday", TimeFilter.timeFilter(listDate, yesterday, yesterday));
        request.setAttribute("prodcut.week", TimeFilter.timeFilter(listDate, startWeek, lasttWeek));
       
        //资源库
        request.setAttribute("res.all", TimeFilter.timeFilter(listDate, startMonth, lastMonth));
        request.setAttribute("res.month", TimeFilter.timeFilter(listDate, startMonth, lastMonth));
        
        request.setAttribute("res.today", TimeFilter.timeFilter(listDate, day, day));
        request.setAttribute("res.yesterday", TimeFilter.timeFilter(listDate, yesterday, yesterday));
        request.setAttribute("res.week", TimeFilter.timeFilter(listDate, startWeek, lasttWeek));*/
       
		//用户ip地址
		request.setAttribute("ip", ip);  
		return "Index/welcome";
	}
	
	
	
	
	
	//判断2个
	
	//帮助转换json数据的方法
	public String loginJsonTransform(String code, String msg ,Object date){
		Map<String, Object> map=new HashMap<String, Object>();
		//用户状态码
		map.put("code", code);
		//登录消息
		map.put("msg", msg);
		//返回的数据.
		map.put("date", date);
		//转换成json数据
		JSONObject json=new JSONObject(map);
		return  json.toString();
	}
	
}
