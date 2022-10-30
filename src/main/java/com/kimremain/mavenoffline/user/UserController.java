package com.kimremain.mavenoffline.user;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimremain.mavenoffline.common.base.WebBaseController;
import com.kimremain.mavenoffline.common.constant.URIConstant;

@RestController
public class UserController extends WebBaseController{

	@GetMapping(URIConstant.URI_USER)
	public String getUserList(Model model) {
		model.addAttribute("name", "jay");
		return URIConstant.URI_USER;
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName(URIConstant.URI_MAIN + "pages/main");
//		String ogUrl = HttpUtil.getRequestUrl(request);
//		String ogImage = HttpUtil.getHostUrl(request) + URIConstant.URI_OG_IMAGE;	
//		modelAndView.addObject("_OG_URL", ogUrl);
//		modelAndView.addObject("_OG_IMAGE", ogImage);
//		
//		LogUtil.debugHighlightObject(request);
//		return modelAndView;
	}

	
}
