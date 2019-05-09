package cn.dym.outman.freemaker.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.dym.outman.freemaker.utils.PdfUtils;

public class TestHbPdf {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String rootPath="f:/test/template/";
		
		List<InputStream>  instream = new ArrayList<InputStream>();
		
		for(int i=0;i<3;i++){
			InputStream inputStream = new FileInputStream("f:/test/template/pdf/20180414/89de336e86e04ce3bdc4347f26acde67.pdf");
			instream.add(inputStream);
		}
		OutputStream outputStream = new FileOutputStream("f:/test/template/pdf/20180414/1.pdf");
		PdfUtils.concatPDFs(instream,outputStream, false,rootPath);
	}

}
