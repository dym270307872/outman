package cn.dym.outman.freemaker.utils;

import java.io.File;  
import java.io.IOException;  
import java.io.StringWriter;  
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;

import freemarker.core.ParseException;  
import freemarker.template.Configuration;  
import freemarker.template.MalformedTemplateNameException;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;  
import freemarker.template.TemplateNotFoundException;

/** 
 * PDF生成辅助类 
 * 
 * 
 */  
@SuppressWarnings("deprecation")  
public class PdfHelper {
	 public static ITextRenderer getRender(String path) throws DocumentException, IOException {  
		   
	        ITextRenderer render = new ITextRenderer();  
	    
	        //添加字体，以支持中文  
	        render.getFontResolver().addFont(path + "/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
	        render.getFontResolver().addFont(path + "/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
	   
	        return render;  
	    }  
	   
	    //获取要写入PDF的内容  
	    public static String getPdfContent(String ftlPath, String ftlName, Object o) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {  
	        return useTemplate(ftlPath, ftlName, o);  
	    }  
	   
	    //使用freemarker得到html内容  
	    public static String useTemplate(String ftlPath, String ftlName, Object o) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {  
	   
	        String html = null;  
	   
	        Template tpl = getFreemarkerConfig(ftlPath+"").getTemplate(ftlName);  
	        tpl.setEncoding("UTF-8");  
	   
	        StringWriter writer = new StringWriter();  
	        tpl.process(o, writer);  
	        writer.flush();  
	        html = writer.toString();  
	        return html;  
	    }  
	   
	    /** 
	     * 获取Freemarker配置 
	     * @param templatePath 
	     * @return 
	     * @throws IOException 
	     */  
	    private static Configuration getFreemarkerConfig(String templatePath) throws IOException {  
	        freemarker.template.Version version = new freemarker.template.Version("2.3.22");  
	        Configuration config = new Configuration(version);  
	        config.setDirectoryForTemplateLoading(new File(templatePath));  
	        config.setEncoding(Locale.CHINA, "utf-8");  
	        return config;  
	    }  
	       
	    /** 
	     * 获取类web目录路径
	     * @return 
	     */  
	    public static String getPath(HttpServletRequest request){  
	        return request.getSession().getServletContext().getRealPath("");  
	    }  
}
