package com.meal.service.impl;

import com.meal.service.impl.generator.CsvGenerator;
import com.meal.service.impl.generator.DocumentGeneratorAbstract;
import com.meal.service.impl.generator.PdfGenerator;
import com.meal.service.impl.generator.XlsGenerator;
import com.meal.service.impl.model.TableModelViewerInterface;
import io.jsonwebtoken.lang.Assert;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentBuilder<In> {
  private DocumentGeneratorAbstract<In> documentGenerator;
  private TableModelViewerInterface<In> modelViewer;

  private final Map<DocumentType, DocumentGeneratorAbstract> defaultGenerators
          = new HashMap<DocumentType, DocumentGeneratorAbstract>() {{
    put(DocumentType.CSV, new CsvGenerator<In>());
    put(DocumentType.PDF, new PdfGenerator<In>());
    put(DocumentType.XLS, new XlsGenerator<In>());
  }};

  private boolean isProtectedFromCopy = false;


  public DocumentBuilder setProtectedFromCopy(boolean value) {
    this.isProtectedFromCopy = value;
    return this;
  }

  public DocumentBuilder setDocumentType(DocumentType documentType) {
    Assert.notNull(documentType, "documentType is null");
    setDocumentGenerator(defaultGenerators.get(documentType));
    return this;
  }

  public DocumentBuilder setDocumentGenerator(DocumentGeneratorAbstract<In> documentGenerator) {
    Assert.notNull(documentGenerator, "documentGenerator is null");

    this.documentGenerator = documentGenerator;
    return this;
  }

  public DocumentBuilder setModelViewer(TableModelViewerInterface<In> modelViewer) {
    Assert.notNull(modelViewer, "modelViewer is null");

    this.modelViewer = modelViewer;
    return this;
  }

  public void writeToResponse(List<In> entityList, HttpServletResponse response) {
    Assert.notNull(documentGenerator, "documentGenerator is null");
    Assert.notNull(modelViewer, "modelViewer is null");

    documentGenerator.setProtectedFromCopy(isProtectedFromCopy);
    documentGenerator.setModelViewer(modelViewer);

    documentGenerator.writeToResponse(entityList, response);
  }
}