package com.meal.service.impl.model;

import java.util.List;
import java.util.function.Function;

public interface TableModelViewerInterface<In> {
  void setHeaders(List<String> headers);
  List<String> getHeaders();
  void setMapper(Function<In, List<String>> mapper);

  List<List<String>> map(List<In> in);
}