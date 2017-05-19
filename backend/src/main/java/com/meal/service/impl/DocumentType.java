package com.meal.service.impl;

import com.meal.service.Exception.ServiceException;

import java.util.HashMap;
import java.util.Map;

public enum  DocumentType {
  CSV,
  PDF,
  XLS;


  private static Map<String, DocumentType> stringRepresentation
          = new HashMap<String, DocumentType>() {{
    put("csv", CSV);
    put("pdf", PDF);
    put("xls", XLS);
  }};

  public static DocumentType of(String type) {
    DocumentType result = stringRepresentation.getOrDefault(type, null);

    if(result == null) {
      throw new ServiceException("can not find " + type + " type");
    }

    return result;
  }
}