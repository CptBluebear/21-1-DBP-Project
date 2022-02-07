package org.corodiak.library.controller;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.corodiak.library.model.Member;
import org.corodiak.library.service.MaterialService;
import org.corodiak.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;

@RestController
@RequestMapping(value = {"/admin"})
public class AdminController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MaterialService materialService;
	
	@RequestMapping(value = "/material/add", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> addMaterial(@RequestBody Map<String, Object> param) throws Exception
	{
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		response.put("data", data);
		
		String token = (String) param.get("token");
		Map<String, Object> claims = memberService.loginCheck(token);
		if(claims == null)
		{
			response.put("status", 403);
			return response;
		}
		if(!memberService.checkAdmin((int)claims.get("memberOid")))
		{
			response.put("status", 403);
			return response;
		}
		
		if(!materialService.addMaterial(param))
		{
			response.put("status", 400);
			return response;
		}
		
		response.put("status", 200);
		return response;
	}
	
	@RequestMapping(value = "/material/remove/{materialOid}", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> removeMaterial(@RequestBody Map<String, Object> param, @PathVariable("materialOid")String materialOid) throws Exception
	{
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		response.put("data", data);
		
		String token = (String) param.get("token");
		Map<String, Object> claims = memberService.loginCheck(token);
		if(claims == null)
		{
			response.put("status", 403);
			return response;
		}
		if(!memberService.checkAdmin((int)claims.get("memberOid")))
		{
			response.put("status", 403);
			return response;
		}
		
		if(!materialService.removeMaterial(materialOid))
		{
			response.put("status", 400);
			return response;
		}
		
		response.put("status", 200);
		return response;
	}
	
	@RequestMapping(value = "/member/list", produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> memberList(@RequestBody Map<String, Object> param) throws Exception
	{
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		response.put("data", data);
		
		String token = (String) param.get("token");
		Map<String, Object> claims = memberService.loginCheck(token);
		if(claims == null)
		{
			response.put("status", 403);
			return response;
		}
		if(!memberService.checkAdmin((int)claims.get("memberOid")))
		{
			response.put("status", 403);
			return response;
		}
		
		List<Member> memberList = memberService.readMemberList();
		
		
		data.put("memberList", memberList);
		
		response.put("status", 200);
		return response;
	}
	
	
	@RequestMapping(value = "/member/remove/{memberOid}", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> removeMember(@RequestBody Map<String, Object> param, @PathVariable("memberOid")int memberOid) throws Exception
	{
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		response.put("data", data);
		
		String token = (String) param.get("token");
		Map<String, Object> claims = memberService.loginCheck(token);
		if(claims == null)
		{
			response.put("status", 403);
			return response;
		}
		if(!memberService.checkAdmin((int)claims.get("memberOid")))
		{
			response.put("status", 403);
			return response;
		}
		
		if(!memberService.removeMember(memberOid))
		{
			response.put("status", 400);
			return response;
		}
		
		response.put("status", 200);
		return response;
	}
	
}
