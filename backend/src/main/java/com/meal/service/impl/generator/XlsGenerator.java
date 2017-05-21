package com.meal.service.impl.generator;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class XlsGenerator<In> extends DocumentGeneratorAbstract<In> {
  @Override
  public String getDocumentType() {
    return ".xls";
  }

  @Override
  public String getContentType() {
    return "application/vnd.ms-excel";
  }

  private XSSFWorkbook getWorkBook(List<In> model) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Test");
    sheet.setDefaultColumnWidth(30);
    PrintSetup ps = sheet.getPrintSetup();

    sheet.setAutobreaks(true);

    ps.setFitHeight((short)1);
    ps.setFitWidth((short)1);
    sheet.setFitToPage(true);

    CellStyle style = getCellStyle(workbook);
    CellStyle headerStyle = getCellStyle(workbook);
    headerStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
    headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
    addContent(model, sheet, style, headerStyle);

    return workbook;
  }

  private void addContent(List<In> model, Sheet sheet, CellStyle style, CellStyle headerStyle) {
    Row header = sheet.createRow(0);
 //   header.setRowStyle(headerStyle);


    for (int i = 0; i < tableModelViewer.getHeaders().size(); i++) {
      String caption = tableModelViewer.getHeaders().get(i);
      header.createCell(i).setCellValue(caption);
      header.getCell(i).setCellStyle(headerStyle);
    }


    int rowCount = 1;

    for (List<String> row : tableModelViewer.map(model)) {
      Row tableRow = sheet.createRow(rowCount++);

      for (int i = 0; i < row.size(); i++) {
        String item = row.get(i);
        tableRow
                .createCell(i)
                .setCellValue(item);
        tableRow.getCell(i).setCellStyle(style);
      }
    }

    for (int i = 0; i < tableModelViewer.getHeaders().size(); i++) {
      sheet.autoSizeColumn(i);
    }
  }

  private CellStyle getCellStyle(XSSFWorkbook workbook) {
    CellStyle style = workbook.createCellStyle();
    Font font = workbook.createFont();
    font.setFontName("Arial");
    style.setWrapText(true);
    font.setBoldweight((short) 10);
    font.setColor(HSSFColor.BLACK.index);
    style.setFont(font);
    return style;
  }


  @Override
  public void writeToResponse(List<In> model, HttpServletResponse response) {
    super.writeToResponse(model, response);

    XSSFWorkbook workbook = getWorkBook(model);

    try {
      workbook.write(response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}