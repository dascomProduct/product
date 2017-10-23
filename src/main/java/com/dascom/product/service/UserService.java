package com.dascom.product.service;

import com.dascom.product.entity.CpUser;
import com.dascom.product.entity.UpdateInfo;

public interface UserService {

	 CpUser findCpUserByUsername(String username);

	UpdateInfo findUpdateInfo();
}
