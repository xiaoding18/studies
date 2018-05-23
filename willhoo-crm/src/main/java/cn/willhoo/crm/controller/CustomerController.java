package cn.willhoo.crm.controller;

import cn.willhoo.crm.domain.BaseDict;
import cn.willhoo.crm.domain.Customer;
import cn.willhoo.crm.domain.QueryVo;
import cn.willhoo.crm.service.BaseDictService;
import cn.willhoo.crm.service.CustomerService;
import cn.willhoo.crm.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月13日 下午4:28:19   
*/
@Controller
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BaseDictService baseDictService;
	
	
	@RequestMapping("findAll")
	public ModelAndView findAll(QueryVo queryVo) {
		ModelAndView model = new ModelAndView();
		
		//得到所有的客户来源
		List<BaseDict> customerSource = baseDictService.findAllBaseDict("002");
		//得到所有的所属行业
		
		List<BaseDict> customerIndustry = baseDictService.findAllBaseDict("001");
		//得到所有的客户级别
		
		List<BaseDict> customerLevel = baseDictService.findAllBaseDict("006");
		//得到查询的数据
		Page<Customer> customers = customerService.findAllByCondition(queryVo);
		model.addObject("page", customers);
		
		model.addObject("fromType",customerSource);
		model.addObject("industryType",customerIndustry);
		model.addObject("levelType",customerLevel);
		
		model.setViewName("customer");
		System.err.println(123);
		return model;
	}
}
