package com.dascom.product.serviceImpl;

import org.springframework.stereotype.Service;

import com.dascom.product.entity.UpdateInfo;
import com.dascom.product.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	
	@Override
	public UpdateInfo findUpdateInfo(){
		UpdateInfo  info =new UpdateInfo();
		return info;
	}

}
