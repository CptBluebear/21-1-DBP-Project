package org.corodiak.library.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.library.mapper.ArticleMapper;
import org.corodiak.library.mapper.BookMapper;
import org.corodiak.library.mapper.DigitalMapper;
import org.corodiak.library.mapper.MaterialMapper;
import org.corodiak.library.mapper.MediaTypeMapper;
import org.corodiak.library.mapper.MemberMapper;
import org.corodiak.library.mapper.MultiMediaMapper;
import org.corodiak.library.mapper.RentalMapper;
import org.corodiak.library.mapper.ReservationMapper;
import org.corodiak.library.model.Article;
import org.corodiak.library.model.Book;
import org.corodiak.library.model.Digital;
import org.corodiak.library.model.Material;
import org.corodiak.library.model.MaterialImpl;
import org.corodiak.library.model.MediaType;
import org.corodiak.library.model.Member;
import org.corodiak.library.model.MultiMedia;
import org.corodiak.library.model.Rental;
import org.corodiak.library.model.Reservation;
import org.corodiak.library.service.ArticleService;
import org.corodiak.library.service.BookService;
import org.corodiak.library.service.DigitalService;
import org.corodiak.library.service.MaterialService;
import org.corodiak.library.service.MemberService;
import org.corodiak.library.service.MultiMediaService;
import org.corodiak.library.service.RentalService;
import org.corodiak.library.service.ReservationService;
import org.corodiak.library.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RestController
public class TestController {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	MaterialMapper materialMapper;
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	ArticleMapper articleMapper;
	
	@Autowired
	DigitalMapper digitalMapper;
	
	@Autowired
	MediaTypeMapper mediaTypeMapper;
	
	@Autowired
	MultiMediaMapper multiMediaMapper;
	
	@Autowired
	RentalMapper rentalMapper;
	
	@Autowired
	ReservationMapper reservationMapper;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	RentalService rentalService;
	
	@Autowired
	MaterialService materialService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	DigitalService digitalService;
	
	@Autowired
	MultiMediaService multiMediaService;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping(value = "/test")
	public @ResponseBody String index(@RequestBody Map<String, Object> param) throws Exception
	{	
		return "DemoProject";
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> login(@RequestBody Map<String, Object> param)
	{
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		response.put("data", data);
		
		String id = (String)param.get("id");
		String password = (String)param.get("password");
		
		String jwt = null;
		jwt = memberService.login(id, password);
		if(jwt == null)
		{
			response.put("status", 401);
			return response;
		}
		
		response.put("status", 200);
		data.put("token", jwt);
		
		return response;
	}
	
	@RequestMapping(value = "/register", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> register(@RequestBody Map<String, Object> param)
	{
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		response.put("data", data);
		
		String id = (String) param.get("id");
		String password = (String) param.get("password");
		String name = (String) param.get("name");
		String phoneNumber = (String) param.get("phoneNumber");
		String address = (String) param.get("address");
		
		if(!memberService.register(id, password, name, phoneNumber, address))
		{
			response.put("status", 400);
			return response;
		}
		else
		{
			response.put("status", 201);
			return response;
		}
	}
	
	@RequestMapping(value = "/rental/{materialOid}", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> rentalMaterial(@RequestBody Map<String, Object> param, @PathVariable("materialOid") String materialOid) throws Exception
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
		
		ArrayList<Reservation> reservationList = reservationService.readReservationList(materialOid);
		Reservation reservation = null;
		if(reservationList.size() > 0) reservation = reservationList.get(0);
		if(reservation != null && reservation.getReservationMember().getMemberOid() != (int)claims.get("memberOid"))
		{
			response.put("status", 400);
			return response;
		}
		if(reservation != null) reservationService.removeReservation(reservation.getReservationOid());
		if(!rentalService.rentalMaterial(materialOid, (int)claims.get("memberOid")))
		{
			response.put("status", 400);
			return response;
		}
		response.put("status", 200);
		
		return response;
	}
	
	@RequestMapping(value = "/reservation/{materialOid}", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> reservationMaterial(@RequestBody Map<String, Object> param, @PathVariable("materialOid") String materialOid) throws Exception
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
		
		int memberOid = (int)claims.get("memberOid");
		
		if(reservationService.addReservation(materialOid, memberOid)) response.put("status", 200);
		else response.put("status", 400);

		return response;
	}
	
	
	@RequestMapping(value = "/return/{materialOid}", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> returnMaterial(@RequestBody Map<String, Object> param, @PathVariable("materialOid") String materialOid) throws Exception
	{
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		response.put("data", data);
		
		if(!rentalService.returnMaterial(materialOid))
		{
			response.put("status", 400);
			return response;
		}
		response.put("status", 200);
		return response;
	}
	
	@RequestMapping(value = "/rental/list", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> rentalList(@RequestBody Map<String, Object> param) throws Exception
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
		
		int memberOid = (int)claims.get("memberOid");
		
		data.put("rentalList", rentalService.rentalListByMemberOid(memberOid));
		
		response.put("status", 200);
		return response;
	}
	
	@RequestMapping(value = "/search/{category}", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> searchMaterial(
			@RequestBody Map<String, Object> param,
			@PathVariable("category")String category
			) throws Exception
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
		String name = null;
		String author = null;
		String ra = null;
		String keyword = null;
		String publisher = null;
		switch (category) {
		case "material":
			name = (String)param.get("materialName");
			int loanable = param.get("materialLoanable") == null ? 0 : (int)param.get("materialLoanable");
			
			Material material = new MaterialImpl();
			material.setMaterialName(name);
			material.setMaterialLoanable(loanable);
			
			data.put("materialList", materialService.searchMaterial(material));
			response.put("status", 200);
			
			break;
			
		case "article":
			name = (String)param.get("materialName");
			author = (String)param.get("articleAuthor");
			ra = (String)param.get("articleRa");
			keyword = (String)param.get("articleKeyword");
			
			System.out.println(name);
			
			Article article = new Article();
			article.setMaterialName(name);
			article.setArticleAuthor(author);
			article.setArticleRa(ra);
			article.setArticleKeyword(keyword);
			
			data.put("articleList", articleService.searchArticle(article));
			response.put("status", 200);
			
			break;
			
		case "book":
			name = (String)param.get("materialName");
			author = (String)param.get("bookAuthor");
			publisher = (String)param.get("bookPublisher");
			
			Book book = new Book();
			book.setMaterialName(name);
			book.setBookAuthor(author);
			book.setBookPublisher(publisher);
			
			data.put("bookList", bookService.searchBook(book));
			response.put("status", 200);
			
			break;
			
		case "digital":
			name = (String)param.get("materialName");
			
			Digital digital = new Digital();
			digital.setMaterialName(name);
			
			data.put("digitalList", digitalService.searchDigital(digital));
			response.put("status", 200);
			
			break;
			
		case "multiMedia":
			name = (String)param.get("materialName");
			
			MultiMedia multiMedia = new MultiMedia();
			multiMedia.setMaterialName(name);
			
			data.put("multiMediaList", multiMediaService.searchMultiMedia(multiMedia));
			response.put("status", 200);
			
			break;
			
		default:
			response.put("status", 400);
		}
		
		return response;
	}
	
	@RequestMapping(value = "/member/modify", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	public @ResponseBody Map<String, Object> modifyMemberInfo(@RequestBody Map<String, Object> param) throws Exception
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
		
		String password = (String)param.get("memberPassword");
		String phoneNumber = (String)param.get("memberPhoneNumber");
		String address = (String)param.get("memberAddress");
		
		int memberOid = (int)claims.get("memberOid");
		
		if(memberService.modifyMember(memberOid, password, phoneNumber, address)) response.put("status", 200);
		else response.put("status", 400);
		
		return response;
	}
}