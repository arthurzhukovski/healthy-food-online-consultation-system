package com.meal.service.impl.model;


import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TableModelViewer<In> implements TableModelViewerInterface<In> {

  private Function<In, List<String>> mapper;
  private List<String> headers;

  @Override
  public List<String> getHeaders() {
    return headers;
  }

  public void setHeaders(List<String> headers) {
    this.headers = headers;
  }

  @Override
  public List<List<String>> map(List<In> in) {
    if(mapper != null) {
      return in
              .stream()
              .map((item)->mapper.apply(item))
              .collect(Collectors.toList());
    }

    return null;
  }

  public void setMapper(Function<In, List<String>> mapper) {
    this.mapper = mapper;
  }
}