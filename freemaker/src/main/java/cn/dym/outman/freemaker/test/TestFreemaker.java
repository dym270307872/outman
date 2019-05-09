package cn.dym.outman.freemaker.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.dym.outman.freemaker.bean.PdfData;
import cn.dym.outman.freemaker.bean.PdfDataDto;
import cn.dym.outman.freemaker.utils.PdfHelper;
import cn.dym.outman.freemaker.utils.PdfUtils;

public class TestFreemaker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//这里做签名验证
        //_____________存pdf_____________

        PdfData pdfData = new PdfData();
        
        Map<String, Object> obj=new HashMap<String, Object>();
        obj.put("busia001", "0000001");
        obj.put("busia002", "0000001");
        obj.put("busia003", "0000001");
        obj.put("busia004", "董耀明");
        obj.put("busia005", "13111111111");
        obj.put("busia010", "张书卷");
        obj.put("busia011", "13333333333");
        obj.put("busia020", "张书卷");
        obj.put("busia021", "2018-04-12");        
        pdfData.setObj(obj);
        
        Map<String,Object> biztype=new HashMap<String,Object>();
        biztype.put("name", "申报登记-机关事业在职人员恢复缴费");
        biztype.put("promiseDays", 7);
//        biztype.put("field1", "1、申办人手续齐全、符合规定的，各窗口应予以办理；2、如果申办人对办件受理持有异议，可拨打咨询电话：69933578；3、如果办件在承诺时间内未办结，可拨打咨询电话：69933578；4、办件进度请关注二维码并绑定个人信息进行查询。");     
        pdfData.setBizType(biztype);
        
        Map<String,Object> jbxx = new HashMap<String,Object>();
        jbxx.put("busic017", "经办人");
        jbxx.put("ylfyze", "62302.50");
        pdfData.setJbxx(jbxx);
        
        List<Map<String,Object>> files = new ArrayList<Map<String,Object>>();
        Map<String,Object> file0 = new HashMap<String,Object>();
        file0.put("PARAM008", "住院费用总清单、住院病历复印件（全部加盖公章）、住院发票原件、出院证或诊断证明原件 ");
        files.add(file0); 
        Map<String,Object> file1 = new HashMap<String,Object>();
        file1.put("PARAM008", "出生医学证明复印件、生育证复印件、结婚证复印件 ");
        files.add(file1); 
        Map<String,Object> file2 = new HashMap<String,Object>();
        file2.put("PARAM008", "女工生育保险生育待遇申报表（加盖单位印章）、身份证复印件两份（本人联系方式） ");
        files.add(file2); 
        Map<String,Object> file3 = new HashMap<String,Object>();
        file3.put("PARAM008", "男方所在单位出具的女方无业证明、女方所在的村（居）民委员会出具的无工作单位的证明 （加盖公章）");
        files.add(file3); 
        pdfData.setFiles(files);
        
        String rootPath="f:/test/template/";
//        System.out.println(rootPath);
        //生成文件夹返回文件名
        Map<String,String> createPdfFile=PdfUtils.createPdfFile(rootPath);
        String outfile=createPdfFile.get("fileName");
        System.out.println("单张内容："+createPdfFile.get("urlPath"));

        //生成pdf文件
        try {
//            PdfUtils.generateToFile(rootPath, "sld_ylbx.ftl", rootPath, pdfData,outfile);
            System.out.println(PdfHelper.getPdfContent(rootPath, "sld_ylbx.ftl",pdfData));
//            List<InputStream>  instream = new ArrayList<InputStream>();
//    		
//    		for(int i=0;i<3;i++){
//    			InputStream inputStream = new FileInputStream(rootPath+createPdfFile.get("urlPath"));
//    			instream.add(inputStream);
//    		}
//    		Map<String,String> createPdfFile2=PdfUtils.createPdfFile(rootPath);
//    		OutputStream outputStream = new FileOutputStream(rootPath+createPdfFile2.get("urlPath"));
//    		System.out.println("三张合并："+createPdfFile2.get("urlPath"));
//    		PdfUtils.concatPDFs(instream,outputStream, false,rootPath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}

}
