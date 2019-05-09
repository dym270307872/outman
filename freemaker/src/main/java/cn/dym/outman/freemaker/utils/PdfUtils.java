package cn.dym.outman.freemaker.utils;

import java.io.File;
import java.io.FileOutputStream;  
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;  
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import cn.dym.outman.freemaker.bean.PdfDataDto;
import freemarker.core.ParseException;  
import freemarker.template.MalformedTemplateNameException;  
import freemarker.template.TemplateException;  
import freemarker.template.TemplateNotFoundException;  
   
/** 
 * PDF生成工具类 
 * @author 
 * 
 */ 

public class PdfUtils {
	
    /** 
     * 生成PDF到文件 
     * @param ftlPath 模板文件路径（不含文件名） 
     * @param ftlName 模板文件吗（不含路径） 
     * @param imageDiskPath 图片的磁盘路径 
     * @param data 数据 
     * @param outputFile 目标文件（全路径名称） 
     * @throws Exception 
     */  
    public static void generateToFile(String ftlPath,String ftlName,String imageDiskPath,Object data,String outputFile) throws Exception {  
        String html=PdfHelper.getPdfContent(ftlPath, ftlName, data);
        
//        System.out.println(html);
        
        OutputStream out = null;  
        ITextRenderer render = null;  
        out = new FileOutputStream(outputFile); 
        
        render = PdfHelper.getRender(ftlPath);  
        render.setDocumentFromString(html);  
        if(imageDiskPath!=null&&!imageDiskPath.equals("")){  
            //html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径  
            render.getSharedContext().setBaseURL("file:/"+imageDiskPath);  
        }  
        render.layout();  
        render.createPDF(out);  
        render.finishPDF();  
        render = null;  
        out.close();  
    }  
       
    /** 
     * 生成PDF到输出流中（ServletOutputStream用于下载PDF） 
     * @param ftlPath ftl模板文件的路径（不含文件名） 
     * @param ftlName ftl模板文件的名称（不含路径） 
     * @param imageDiskPath 如果PDF中要求图片，那么需要传入图片所在位置的磁盘路径 
     * @param data 输入到FTL中的数据 
     * @param response HttpServletResponse 
     * @return 
     * @throws TemplateNotFoundException 
     * @throws MalformedTemplateNameException 
     * @throws ParseException 
     * @throws IOException 
     * @throws TemplateException 
     * @throws DocumentException 
     */  
    public static OutputStream generateToServletOutputStream(String ftlPath,String ftlName,String imageDiskPath,Object data,HttpServletResponse response) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException, DocumentException{  
        String html=PdfHelper.getPdfContent(ftlPath+"/", ftlName, data);  
        OutputStream out = null;  
        ITextRenderer render = null;  
        out = response.getOutputStream();  
        render = PdfHelper.getRender(ftlPath);  
        render.setDocumentFromString(html);  
        if(imageDiskPath!=null&&!imageDiskPath.equals("")){  
            //html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径  
            render.getSharedContext().setBaseURL("file:/"+imageDiskPath);  
        }  
        render.layout();  
        render.createPDF(out);  
        render.finishPDF();  
        render = null;  
        return out;  
    }
    /**
     * @param rootPath根目录
     * @return fileName返回pdf文件全名
     * */
    public static Map<String,String> createPdfFile(String rootPath) {
    	Map<String,String> map=new HashMap<String,String>();
    	//拼接文件字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String filePath = rootPath+"/pdf/"+sdf.format(new Date())+"/";
        String tempName = UUID.randomUUID().toString().replace("-", "");
        File dir=new File(filePath);
        if(!dir.exists())
        {
        	dir.mkdirs();
        }
      //输出文件
        String fileName=filePath+"/"+tempName+".pdf";
        map.put("fileName", fileName);
        map.put("urlPath", "/pdf/"+sdf.format(new Date())+"/"+tempName+".pdf");
        return map;
    }
    
    
    
    /***
     * 
     * @param streamOfPDFFiles inputStream的list集合
     * @param outputStream 带有输出地址的输出流
     * @param paginate 是否带页码
      */
    public static void concatPDFs(List<InputStream> streamOfPDFFiles,
            OutputStream outputStream, boolean paginate,String path) {

        Document document = new Document();
        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            // Create Readers for the pdfs.
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }
            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            BaseFont bf = BaseFont.createFont(path + "/arialuni.ttf",
            		BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
            // data

//            PdfImportedPage page;
//            int currentPageNumber = 0;
//            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            // Loop through the PDF files and add to the output.
            int x=0;int y=0;
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();
//                cb.addTemplate(writer.getImportedPage(pdfReader, 1), -44,-y*270+380);
                cb.addTemplate(writer.getImportedPage(pdfReader, 1), 0,y*280);
                x=x+1;y=y+1;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    
    

	public static void main(String[] args) {  

   
		
		
    }  
}
