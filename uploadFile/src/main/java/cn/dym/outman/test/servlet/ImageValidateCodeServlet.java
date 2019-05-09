/*
 * @(#)ImageValidateCodeServlet.java 创建于2016-08-21
 * 
 * Copyright (c) 2016-2018 by Hnzh. 
 *
 */
package cn.dym.outman.test.servlet;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * 
 * 类名称：ImageValidateCodeServlet
 * <P/>
 * 类描述： 生成图片验证码类。
 * <P/>
 * 创建时间：2016-08-21
 * <P/>
 * 创建人： DYM
 * <P/>
 * 修改人：无
 * <P/>
 * 修改时间：无
 * <P/>
 * 修改备注：无
 * <P/>
 * 版本：v1.0
 *
 */
@WebServlet(name="imageValidateCodeServlet", urlPatterns="/validateCode/imageValidateCodeServlet") 
public class ImageValidateCodeServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private static final String CONTENT_TYPE = "image/jpeg; charset=utf-8";

	@Override
	public void init() throws ServletException
	{
		
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType(CONTENT_TYPE);
		// PrintWriter out = response.getWriter();
		try {
			HttpSession session = request.getSession();
			// 在内存中创建图象
			int width = 108, height = 40;
			BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			// 获取图形上下文
			Graphics g = image.getGraphics();
			// 生成随机类
			Random random = new Random();
			// 设定背景色
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);
			// 设定字体
			g.setFont(new Font("Times New Roman", Font.PLAIN, 32));
			// 画边框
			g.setColor(new Color(200,200,200));
			g.drawRect(0,0,width-1,height-1);
			// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 155; i++)
			{
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}
			
			// 取随机产生的认证码(6位数字)
			String sRand = "";
			for(int i = 0; i < 6; i++)
			{
				String rand = String.valueOf(random.nextInt(10));
				sRand += rand;
				// 将认证码显示到图象中
				g.setColor(new Color(20 + random.nextInt(60), 20 + random.nextInt(120), 20 + random.nextInt(180)));
				// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
				g.drawString(rand, 16 * i + 6, 33);
			}

			// 将认证码存入SESSION
			session.setAttribute("IMAGEVALIDATECODE", sRand.toLowerCase());
			// 图象生效
			g.dispose();
			// 输出图象到页面
			ImageIO.write(image, "JPEG", response.getOutputStream());
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request,response);
	}
	
	@Override
	public void destroy()
	{
		
	}
	
	
	// 给定范围获得随机颜色
	public Color getRandColor(int fc, int bc)
	{ 
		Random random = new Random();
		if(fc > 255)
		{
			fc = 255;
		}
		
		if(bc > 255)
		{
			bc = 255;
		}
		
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
}
