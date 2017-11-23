package com.dascom.product.realm;










import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.dascom.product.entity.CpUser;
import com.dascom.product.service.UserService;








public class CustomRealm  extends AuthorizingRealm {
	
	@Autowired
	private UserService userServiceImpl;
	
	// 设置realm的名称
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		CpUser  cpUser=(CpUser) principals.getPrimaryPrincipal();
		if(cpUser==null) return null;
		//获取该所拥有的权限
		List<String> permissions= new ArrayList<>();
		//测试数据
		permissions .add("hah");
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}



	
	
	//realm的认证方法，从数据库查询用户信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		// token是用户输入的用户名和密码 
		// 第一步从token中取出用户名
		String username = (String) token.getPrincipal();
		// 第二步：根据用户输入的userCode从数据库查询
		CpUser cpUser=null;
		try {
			cpUser =userServiceImpl.findCpUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cpUser==null) return null;
		// 从数据库查询到密码
		String password = cpUser.getPassword();
		//将activeUser设置simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(cpUser,password,this.getName());
		return simpleAuthenticationInfo;
	}
	
	

	// 用于授权
	
	/*protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
		Emp emp =  (Emp) principals.getPrimaryPrincipal();
		List<Rolejurisdiction> permissionList = null;
		try {
			permissionList = systemServiceImpl.serlecRolejurisdictionBySn(emp.geteSn());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//单独定一个集合对象 
		List<String> permissions = new ArrayList<String>();
		//测试数据
		if(permissionList!=null){
			for(Rolejurisdiction item:permissionList){
				//将数据库中的权限标签 符放入集合
				permissions.add(item.getJurisdiction().getName());
			}
		}
		
		//查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}*/
	
	//清除缓存
	/*public void clearCached() { 
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}*/
}
