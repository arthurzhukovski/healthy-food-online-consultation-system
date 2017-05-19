package com.meal.service.impl.generator;

import com.meal.service.impl.model.TableModelViewerInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

public abstract class DocumentGeneratorAbstract<In> {
  protected TableModelViewerInterface<In> tableModelViewer;
  protected boolean isProtectedFromCopy;

  public DocumentGeneratorAbstract() {
    isProtectedFromCopy = false;
  }

  public void setProtectedFromCopy(boolean value) {
    this.isProtectedFromCopy = value;
  }
  public abstract String getDocumentType();

  public String createDocumentName() {
    String filename = java.util.UUID.randomUUID().toString();
    return filename + getDocumentType();
  }
  public abstract String getContentType();

  public void setModelViewer(TableModelViewerInterface<In> tableModelViewer) {
    this.tableModelViewer = tableModelViewer;
  }

  public void writeToResponse(List<In> doctorDtoList, HttpServletResponse response) {
    response.setHeader("Content-disposition",
            "attachment;filename=" + createDocumentName());

    response.setHeader("Content-type", getContentType());
    response.setStatus(SC_CREATED);
  }
}
