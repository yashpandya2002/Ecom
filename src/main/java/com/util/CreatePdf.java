package com.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class CreatePdf
 */
public class CreatePdf extends HttpServlet {
	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//    	PDDocument document = new PDDocument();
//    	PDPage page = new PDPage();
//    	document.addPage(page);
//
//    	PDPageContentStream contentStream = new PDPageContentStream(document, page);
//    	
//    	contentStream.setFont(PDType1Font.HELVETICA, 12);
//    	contentStream.beginText();
//    	contentStream.showText("Hello World");
//    	contentStream.endText();
//    	contentStream.close();
//
//    	document.save("pdfBoxHelloWorld.pdf");
//    	document.close();
		
		Document doc = new Document();
		try {
			
				HttpSession session=request.getSession();
				int userId=(Integer)(session.getAttribute("userId"));
			Connection con=DbConnection.getConnection();
			PreparedStatement preparedStatement=con.prepareStatement("select * from products join carts where carts.userId=? and carts.productId=products.productId;");
			preparedStatement.setInt(1, userId);
			ResultSet result=preparedStatement.executeQuery();
			
			preparedStatement=con.prepareStatement
					("delete from carts where userId=?");
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
			// generate a PDF at the specified location
			PdfWriter writer = PdfWriter.getInstance(doc,
					new FileOutputStream("D:\\Innovative_AJ\\EComBill.pdf"));
			System.out.println("PDF created.");
			// opens the PDF
			doc.open();
			int total=0;
			// adds paragraph to the PDF file
			doc.add(new Paragraph("Item Name                     Price             Quantity               Total Price"));
			while(result.next()) {
				total+=(result.getInt("quantity")*result.getInt("price"));
				String msg=result.getString("name")+"              "+result.getInt("price")+"            "+result.getInt("quantity")+"              "+(result.getInt("quantity")*result.getInt("price"))+"\n";
			doc.add(new Paragraph(msg));
			}
			
			String msg="Total price:                                                   "+total;
			doc.add(new Paragraph(msg));
			// close the PDF file
			doc.close();
			// closes the writer
			writer.close();
			SendMail.sendPDF(userId);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		RequestDispatcher rd = request.getRequestDispatcher("ProductController");

		rd.forward(request, response);
	}

}
