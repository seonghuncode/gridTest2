package com.ysh.jqGri.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysh.jqGri.dto.TestDto;
import com.ysh.jqGri.dto.UserResponse;
import com.ysh.jqGri.service.TestService;

@Controller
public class TestContrller {

	@Autowired
	private TestService testService;

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	// @ResponseBody
	public String getTestData(Model model) {
		System.out.println(testService.getTestData());
		List<TestDto> result = testService.getTestData();
		// model.addAttribute("testData", result);

		// jqgrid를 local로 사용하는 방법-----------------
		// 데이터를 문자열 json형식으로 변환해서 클라이언트 부분으로 넘기게 되면
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(result);
			System.out.println("result : " + jsonStr);
			model.addAttribute("testData", jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// ---------------------------------------------------

		return "jqGridTest";
	}

	// jqgrid를 local로 사용하는
	// 방법--------------------------------------------------------------------------------------
	@RequestMapping(value = "/showGridByLocal", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	public String showGridByLocal(Model model) {

		List<TestDto> result = testService.getTestData();

		// jqgrid를 local로 사용하는 방법-----------------
		// 데이터를 문자열 json형식으로 변환해서 클라이언트 부분으로 넘기게 되면
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(result);
			System.out.println("result : " + jsonStr);
			model.addAttribute("testData", jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "showGridByLocal";
	}

	// ------------------------------------------------------------------------------------------------------------------------

	// jqGrid를 페이징 없이 서버에 요청해서 하는 경우
	@ResponseBody
	@RequestMapping(value = "/showGridFromserverWithoutPaging", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public List<TestDto> showGridFromserverWithoutPaging(HttpServletRequest req, HttpServletResponse res,
			ModelMap model, @RequestParam Map<String, Object> param) throws Exception {
		List<TestDto> result = testService.getTestData();
		return result;

	}

	// jqGrid를 페이징을 사용해서 서버에 요청해서 하는
	// 경우---------------------------------------------------------------------------------------
	@RequestMapping(value = "/showGridFromserverWithPaging", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody UserResponse showGridFromserverWithPaging(HttpServletRequest request,
			@RequestParam Map<String, String> table,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam int rows,
			@RequestParam String sidx, @RequestParam String sord) throws Exception {

		Enumeration<?> param = request.getParameterNames();
		System.out.println("==========================================");
		while (param.hasMoreElements()) {
			String name = (String) param.nextElement();
			System.out.println(name + " : " + request.getParameter(name));
		} // page : 요청한 페이지, rows : 페이지 당 몇개의 행이 보여질지, sidx : 소팅하는 기준이 되는 인덱스, sord : 내림차순
			// 도는 오름차순
		System.out.println("==========================================");

		// DB에 있는 전체 데이터를 가지고 오는 로직(페이징 기능 없음)
		List<TestDto> result = testService.getTestData();
		System.out.println("result : " + result);

		// UtilMap utilMap = new UtilMap(table);
		// UtilMap listParam = setListParam(utilMap, page, rows);
		UserResponse response = new UserResponse();

		// 전체 데이터 갯수와 전체 페이지를 가지고 오는 부분
		Map<String, Object> totalData = new HashMap<String, Object>();
		totalData = testService.getTotalData();
//		System.out.println("totalData : " + totalData);
		// Object totalCnt = totalData.get("totalCnt");
		// Object totalPage = totalData.get("totalPage");
		int totalCnt = Integer.parseInt(String.valueOf(totalData.get("totalCnt")));
		int totalPage = Integer.parseInt(String.valueOf(totalData.get("totalPage")));
//		System.out.println("totalCnt : " + totalCnt);
//		System.out.println("totalPage : " + totalPage);

		// DB에서 페이징 데이터만 가지고 오는 로직
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("rows", rows);
		List<TestDto> dataByPaging = testService.getDataByPaging(map);
		System.out.println("dataByPaging : " + dataByPaging);

		response.setRows(dataByPaging); // 그리드에 보여줄 전체 데이터

		// response.setTotal(listParam.getInt("total_pages"));
		response.setTotal(totalPage); //전체 페이지 수 (한 테이블에 5개식 보여준다는 가정)

		// response.setRecords(listParam.getInt("count"));
		response.setRecords(totalCnt); // 전체 데이터 갯수
		// response.setPage(listParam.getInt("page"));
		response.setPage(page); //현재 페이지 (화면 페이지에 보여줄 데이텨)

		System.out.println("response : " + response.getRows());
		System.out.println("response : " + response.getPage());
		System.out.println("response : " + response.getRecords());
		System.out.println("response : " + response.getTotal());

		return response;

	}

	@RequestMapping(value = "/test2", produces = "application/json; charset=utf8", method = { RequestMethod.POST })
	@ResponseBody
	public String getAllDataFromDB(HttpServletRequest request, Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") String page,
			@RequestParam(value = "rows", required = false, defaultValue = "") String rows) throws Exception {

		Enumeration<?> param = request.getParameterNames();
		System.out.println("==========================================");
		while (param.hasMoreElements()) {
			String name = (String) param.nextElement();
			System.out.println(name + " : " + request.getParameter(name));
		}
		System.out.println("==========================================");
		List<TestDto> result = testService.getTestData();
		model.addAttribute("list", result);
		System.out.println("/test2요청 들어옴");

		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(result);
			System.out.println("result : " + jsonStr);
			return jsonStr;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "";

	}

	@ResponseBody
	@RequestMapping(value = "/test3", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public JSONArray testUrl(HttpServletRequest req, HttpServletResponse res, ModelMap model,
			@RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();

		map1.put("id", "1");
		map1.put("test_name", "Test_Title_1");
		map1.put("test_email", "Se_Yeon");
		map1.put("test_date", "2020");

		map2.put("id", "2");
		map2.put("test_name", "Test_Title_2");
		map2.put("test_email", "Hwang Se_Yeon");
		map2.put("test_date", "2020");

		map3.put("id", "3");
		map3.put("test_name", "Test_Title_3");
		map3.put("test_email", "seyeon1491");
		map3.put("test_date", "2020");

		list.add(map1);
		list.add(map2);
		list.add(map3);

//			result.put("page", "1");
//			result.put("total", 2);
//			result.put("records", "13");
		// result.put("rows", list);

		JSONObject jsonObject = new JSONObject(result);

		JSONArray jsonArr = new JSONArray();
		for (Map<String, Object> map : list) {
			jsonArr.add(convertMapToJson(map));
		}

		System.out.println("===============");
		System.out.println(jsonArr);
		System.out.println("===============");

		return jsonArr;
	}

	// json으로 변경
	public static JSONObject convertMapToJson(Map<String, Object> map) {

		JSONObject json = new JSONObject();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			json.put(key, value);
		}
		return json;
	}

}
