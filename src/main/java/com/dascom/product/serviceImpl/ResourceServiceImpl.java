package com.dascom.product.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dascom.product.dao.ProductVideoMapper;
import com.dascom.product.dao.SoftwareMapper;
import com.dascom.product.entity.Product_Video;
import com.dascom.product.entity.Software;
import com.dascom.product.entity.Software_Type;
import com.dascom.product.service.ResourceService;
import com.dascom.product.util.PageBean;
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired 
	private ProductVideoMapper productVideoMapper;
	@Autowired 
	private SoftwareMapper softwareMapper;
	
	@Override
	public PageBean<Product_Video> findVideoPname(String keyword, int page) {
		PageBean<Product_Video> pageBean = new PageBean<Product_Video>();
		//设置总页数
		pageBean.setPage(page);
		//设置当前每页显示的记录数
		int limit = 6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productVideoMapper.findCountPname(keyword);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		if(totalPage==0){
			totalPage=1;
		}
		pageBean.setTotalPage(totalPage);
		//从哪页开始
		int begin = (page - 1) * limit;
		//每页显示的数据集合
		List<Product_Video> list = productVideoMapper.findVideoPname(keyword, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public PageBean<Product_Video> findVideoAll(int page) {
		PageBean<Product_Video> pageBean = new PageBean<Product_Video>();
		pageBean.setPage(page);
		int limit = 6;
		pageBean.setLimit(limit);
		int totalCount = 0;
		totalCount = productVideoMapper.findCountAll();
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		if(totalPage==0){
			totalPage=1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;
		List<Product_Video> list = productVideoMapper.findVideoAll(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public boolean updateVideo(Product_Video product_Video) {
		boolean result=false;
		try{
			productVideoMapper.update(product_Video);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean insertVideo(Product_Video product_Video) {
		boolean result=false;
		int  i=productVideoMapper.insert(product_Video);
		if(i>0){
			result=true;
		}
		return result;
	}

	@Override
	public boolean deleteVideoById(Product_Video product_Video) {
		boolean result=false;
		try{
			productVideoMapper.delete(product_Video);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Product_Video findByVid(int vid) {
		Product_Video video=new Product_Video();
		video=productVideoMapper.findByVid(vid);
		if(video != null){
			return video;
		}
		return null;
	}

	@Override
	public PageBean<Software> findSoftwareAll(int page) {
		PageBean<Software> pageBean=new PageBean<Software>();
		pageBean.setPage(page);
		int limit = 6;
		pageBean.setLimit(limit);
		int totalCount = 0;
		totalCount = softwareMapper.findCountAll();
		pageBean.setTotalCount(totalCount);
		int totalPage = 0;
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		if(totalPage==0){
			totalPage=1;
		}
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * limit;
		List<Software> softList=softwareMapper.getAllSoftware(begin, limit);
		pageBean.setList(softList);
		return pageBean;
	}

	@Override
	public Software findSoftById(int id) {
		Software software=softwareMapper.getSoftwareById(id);
		return software;
	}

	@Override
	public PageBean<Software> findSoftwareByTypeAndName(String keyword,
			Integer type, int page) {
		PageBean<Software> pageBean=new PageBean<Software>();
		pageBean.setPage(page);
		int limit = 6;
		pageBean.setLimit(limit);
		int totalCount = 0;
		if(type == 0){
			totalCount=softwareMapper.findCountByKey(keyword);
			pageBean.setTotalCount(totalCount);
			int totalPage = 0;
			if(totalCount % limit == 0)
			{
				totalPage = totalCount / limit;
			}else{
				totalPage = totalCount / limit + 1;
			}
			if(totalPage==0){
				totalPage=1;
			}
			pageBean.setTotalPage(totalPage);
			int begin = (page - 1) * limit;
			List<Software> list=softwareMapper.findSoftwareByKey(keyword, begin, limit);
			pageBean.setList(list);
		}else{
			totalCount=softwareMapper.findCountByKeyAndType(keyword, type);
			pageBean.setTotalCount(totalCount);
			int totalPage = 0;
			if(totalCount % limit == 0)
			{
				totalPage = totalCount / limit;
			}else{
				totalPage = totalCount / limit + 1;
			}
			if(totalPage==0){
				totalPage=1;
			}
			pageBean.setTotalPage(totalPage);
			int begin = (page - 1) * limit;
			List<Software> list=softwareMapper.findSoftwareByTypeAndKey(keyword, type, begin, limit);
			pageBean.setList(list);
		}
		return pageBean;
	}

	@Override
	public boolean insertSoftware(Software software) {
		boolean result=false;
		int i=softwareMapper.addSoftware(software);
		if(i>0){
			result=true;
		}
		return result;
	}

	@Override
	public boolean updateSoftware(Software software) {
		boolean result=false;
		try{
			softwareMapper.updateSoftware(software);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteSoftware(Software software) {
		boolean result=false;
		try{
			softwareMapper.delSoftware(software);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Software_Type> findAllSoftwareType() {
		List<Software_Type> type=softwareMapper.findAllSoftwareType();
		return type;
	}

	@Override
	public Software_Type findSoftwareTypeById(int id) {	
		return softwareMapper.findSoftWareTypeById(id);
	}
}
