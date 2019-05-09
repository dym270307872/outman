package cn.dym.outman.springmvc.controller;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dyaoming.outman.entitys.SysUser;


@Controller
@Scope("prototype")
public class HelloWorldController {

	@RequestMapping("/hello")
	public String hello(Model model) {

		model.addAttribute("greeting", "Hello Spring MVC");
		model.addAttribute("text", "通用模板展示");
		model.addAttribute("d_iv",
				"<!DOCTYPE html>  <html>  <head><title>业务受理单</title><style type=\"text/css\">@page{size:210mm 100mm; margin:auto auto 5px 5px;}body {      margin-left: 45px;      margin-right: 45px;      font-family: \"SimSun\";      font-size: 12px;  } table {      margin: auto;      width: 100%;      border-collapse: collapse;      border: 1px solid #444;  }   th,td {      border: 1px solid #444;      padding:5px;	}  .print_title { font-size:20px; font-color:black;  text-align:center;position:relative; margin-bottom:5px;}.print_title span { position:absolute; font-size:12px; right:0; bottom:0;}.column_title{ text-align:center;}.hr { height:1px; border-top:1px dashed #666; margin:60px 0;}@media print{　　.noprint{  　　display:none　　}}</style></head><body><div id=\"container\" name=\"container\" style=\"margin:0px 0px 0px 5px;\"> <!-- <table width=\"960px\"> --> 	<!-- <tr> --> 		<!-- <td class=\"print_title\" align=\"center\">洛阳市社会保险事业管理局业务受理单</td> --> 	<!-- </tr> --> 	<!-- <tr> --> 		<!-- <td align=\"right\">办件编号：0000001</td> --> 	<!-- </tr> --> <!-- </table> --> <div class=\"print_title\">    <span>办件编号：0000001</span>    洛阳市社会保险事业管理局业务受理单 </div> <table width=\"960px\" border=\"1\" class=\"print_table\">     <!--<thead>        <tr><th colspan=\"6\" class=\"print_title\">洛阳市社会保险事业管理局业务受理单</th></tr>	    <tr><th align=\"right\" colspan=\"6\" class=\"sub_title\">办件编号：0000001</th></tr>     </thead>-->     <tbody>         <tr> 		<td class=\"column_title\" align=\"center\" width=\"70px\">申请事项</td> 		<td colspan=\"3\">申报登记-机关事业在职人员恢复缴费</td>		<td class=\"column_title\" align=\"center\">医疗费用总额</td> 		<td >62302.50</td> 	</tr> 	<tr> 		<td width=\"\" class=\"column_title\" align=\"center\">申办人</td> 		<td width=\"\">董耀明</td>		<td width=\"\" class=\"column_title\" align=\"center\">申办人身份证号</td> 		<td width=\"\">0000001</td> 		<td width=\"\" class=\"column_title\" align=\"center\">联系方式</td> 		<td width=\"\">13111111111</td> 	</tr> 	<tr> 		<td class=\"column_title\" align=\"center\">受理人</td> 		<td>张书卷</td>		<td width=\"\" class=\"column_title\" align=\"center\">受理时间</td> 		<td width=\"\" colspan=\"2\">2018-04-12</td> 				<td align=\"center\" rowspan=\"3\" ><img alt=\"扫描二维码\" src=\"sbewm.png\" width=\"120px\"/></td> 	</tr> 	<tr> 		<td class=\"column_title\" align=\"center\">经办科室</td> 		<td >医疗费用审核科</td>		<td class=\"column_title\" align=\"center\">承诺时间</td>		<td colspan=\"2\">7工作日</td> 	</tr> 	<tr> 		<td class=\"column_title\" align=\"center\">材料名称</td> 		<td colspan=\"4\"> 			1、住院费用总清单、住院病历复印件（全部加盖公章）、住院发票原件、出院证或诊断证明原件 ；<br/> 			2、出生医学证明复印件、生育证复印件、结婚证复印件 ；<br/> 			3、女工生育保险生育待遇申报表（加盖单位印章）、身份证复印件两份（本人联系方式） ；<br/> 			4、男方所在单位出具的女方无业证明、女方所在的村（居）民委员会出具的无工作单位的证明 （加盖公章）；<br/> 		</td> 	</tr> 	<tr> 		<td class=\"column_title\" align=\"center\">备注</td> 		<td colspan=\"5\"></td> 	</tr>     </tbody> </table> <div style=\"float:right;width:300px; margin-top:10px;\">申请人（签字）：</div> <div class=\"hr\"></div></div></body></html>");
		return "helloworld";

	}



	@RequestMapping("/html")
	public String hello2(Model model) {

		// model.addAttribute("greeting", "Hello Spring MVC");

		return "hellohtml";

	}



	@RequestMapping("/ftl")
	public String hello3(Model model) {

		model.addAttribute("greeting", "Hello Spring MVC");

		return "helloftl";

	}



	@RequestMapping("/login")
	@ResponseBody
	public Object login(@RequestHeader HttpHeaders httpHeaders) {

		Object result = null;
		try {
			UserTemp userTemp = obtainUserFormHeader(httpHeaders);

			System.out.println(userTemp.username + ":" + userTemp.password);
			// 尝试查找用户库是否存在
			SysUser user = new SysUser();
			user.setUsername(userTemp.username);

			result = user;
			if (userTemp.username != null) {
				if ("admin".equals(userTemp.username)) {
					// 密码不匹配
					result = new ResponseEntity(HttpStatus.BAD_REQUEST);
				}
				if ("dyaoming".equals(userTemp.username)) {
					// 用户已锁定
					result = new ResponseEntity(HttpStatus.LOCKED);
				}
			} else {
				// 不存在 404
				result = new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		} catch(UnsupportedEncodingException e) {
			// logger.error("用户认证错误", e);
			result = new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		// 成功返回json
		return result;

	}



	/**
	 * This allows the CAS server to reach to a remote REST endpoint via a POST
	 * for verification of credentials.
	 * Credentials are passed via an Authorization header whose value is Basic
	 * XYZ where XYZ is a Base64 encoded version of the credentials.
	 * 
	 * @param httpHeaders
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private UserTemp obtainUserFormHeader(HttpHeaders httpHeaders)
			throws UnsupportedEncodingException {

		// cas服务端会通过把用户信息放在请求头authorization中，并且通过Basic认证方式加密
		String authorization = httpHeaders.getFirst("authorization");
		if (StringUtils.isEmpty(authorization)) { return null; }

		String baseCredentials = authorization.split(" ")[1];
		// 用户名:密码
		String usernamePassword = new String(Base64Utils.decodeFromString(baseCredentials),
				"UTF-8");
		String[] credentials = usernamePassword.split(":");

		return new UserTemp(credentials[0], credentials[1]);
	}

	/**
	 * 从请求头中获取用户名和密码
	 */
	private class UserTemp {
		private String	username;
		private String	password;



		public UserTemp(String username, String password) {
			this.username = username;
			this.password = password;
		}
	}

}
