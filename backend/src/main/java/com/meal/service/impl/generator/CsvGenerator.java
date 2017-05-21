package com.meal.service.impl.generator;


import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CsvGenerator<In> extends DocumentGeneratorAbstract<In> {

  @Override
  public String getDocumentType() {
    return ".csv";
  }

  @Override
  public String getContentType() {
    return "text/csv; charset=utf-8";
  }

  @Override
  public void writeToResponse(List<In> model, HttpServletResponse response) {
    super.writeToResponse(model, response);

    try {
      ICsvListWriter listWriter = new CsvListWriter(response.getWriter(),
              CsvPreference.STANDARD_PREFERENCE);
      addContent(model, listWriter);
      listWriter.flush();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void addContent(List<In> model, ICsvListWriter listWriter) throws IOException {
//    listWriter
//            .writeHeader(
//                    tableModelViewer
//                            .getHeaders()
//                            .toArray(
//                                    new String[tableModelViewer
//                                            .getHeaders()
//                                            .size()]));


    List<List<String>> modelList =  tableModelViewer.map(model);
    for (List<String> row : modelList) {
      listWriter.write(row);
    }
  }
}