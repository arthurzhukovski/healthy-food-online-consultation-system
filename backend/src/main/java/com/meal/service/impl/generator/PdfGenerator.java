package com.meal.service.impl.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

public class PdfGenerator<In> extends DocumentGeneratorAbstract<In> {
  private final String fontFile = "./arial.ttf";
  private final String templateUrl = "./template.pdf";
  @Override
  public String getDocumentType() {
    return ".pdf";
  }

  @Override
  public String getContentType() {
    return "application/pdf";
  }

  @Override
  public void writeToResponse(List<In> model, HttpServletResponse response) {
    super.writeToResponse(model, response);

    try {
      BaseFont bf = BaseFont.createFont(fontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
      Font font = new Font(bf);
      OutputStream outputStream = response.getOutputStream();
      PdfReader letterhead = new PdfReader(templateUrl);

      Rectangle pageSize = letterhead.getPageSizeWithRotation(1);

      Document document = new Document(pageSize);

      document.setMargins(0, 0, 170, 20);
      document.setMarginMirroringTopBottom(true);

      PdfWriter writer = PdfWriter.getInstance(document, outputStream);
      writer.setPageEvent(new PdfNewPageEventHandler());

      setEncryption(writer);

      document.open();

      addHeader(letterhead, writer);

      addMetadata(document);

      addContent(model, document, font);

      document.close();

      outputStream.flush();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void addContent(List<In> model, Document document, Font font) throws DocumentException {
    Paragraph paragraph =  new Paragraph(" ");
    document.add(paragraph);

    PdfPTable pdfTable = createTable(model, font);

    document.add(pdfTable);
  }

  private PdfPTable createTable(List<In> model, Font font) {
    PdfPTable pdfTable = new PdfPTable(tableModelViewer.getHeaders().size());

    for (String header : tableModelViewer.getHeaders()) {
      PdfPCell headerCell = new PdfPCell(new Phrase(header));
      headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      headerCell.setBackgroundColor(BaseColor.DARK_GRAY);
      pdfTable.addCell(new Paragraph(header, font));
    }

    PdfPCell[] headers = pdfTable.getRow(0).getCells();
    for(PdfPCell cell : headers) {
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    }

    pdfTable.setHeaderRows(1);

    for (List<String> row : tableModelViewer.map(model)) {
      for (String cell : row) {
        pdfTable.addCell(new Paragraph(cell, font));
      }
    }
    return pdfTable;
  }

  private void addMetadata(Document document) {
    document.addAuthor("mpp-team");
    document.addCreationDate();
    document.addTitle("Report");
  }

  private void addHeader(PdfReader letterhead, PdfWriter writer) {
    PdfContentByte content = writer.getDirectContent();
    PdfImportedPage page = writer.getImportedPage(letterhead, 1);
    content.addTemplate(page, 0, 0);
  }

  private void setEncryption(PdfWriter writer) throws DocumentException {
    if(isProtectedFromCopy) {
      writer.setEncryption(
              null,
              null,
              ~(PdfWriter.ALLOW_COPY),
              PdfWriter.STANDARD_ENCRYPTION_128);
    }
  }

  private class PdfNewPageEventHandler extends PdfPageEventHelper {
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
      document.setMargins(0, 0, 20, 20);
    }
  }
}