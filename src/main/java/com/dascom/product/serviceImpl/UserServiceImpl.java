package com.dascom.product.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dascom.product.dao.CpUploadMapper;
import com.dascom.product.dao.CpUserMapper;
import com.dascom.product.entity.CpUser;
import com.dascom.product.entity.UpdateInfo;
import com.dascom.product.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private CpUserMapper cpUserMapper;
	
	public CpUser findCpUserByUsername(String username){
		
		return cpUserMapper.selectCpUserByUsername(username);
	}

	@Override
	public UpdateInfo findUpdateInfo() {
		UpdateInfo info=new UpdateInfo();
		
		/*info.setAll(cpUserMapper.selectByTwoTime());*/
		return null;
	}
}
