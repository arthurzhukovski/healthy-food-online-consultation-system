package com.meal.service.impl.model.entity;

import com.meal.entity.ReportEntity;
import com.meal.entity.ReportView;
import com.meal.entity.UserEntity;
import com.meal.service.impl.model.TableModelViewer;
import com.meal.service.impl.model.TableModelViewerInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReportViewFactory implements ViewerFactoryInterface{

  @Override
  public TableModelViewerInterface create() {
    TableModelViewerInterface<ReportView> tableModelViewer
            = new TableModelViewer<>();

    List<String> headers = Arrays.asList(
            "Кол-во отчетов",
            "Кол-во не оцененных отчетов",
            "Отчетов с оценкой \"Хорошо\"",
            "Отчетов с оценкой \"Нормально\"",
            "Отчетов с оценкой \"Плохо\""
    );

    tableModelViewer.setHeaders(headers);

    tableModelViewer.setMapper((report) -> {
      List<String> fields = new ArrayList<>();

      fields.add(report.getCount());
      fields.add(report.getMarkedCount());
      fields.add(report.getGood());
      fields.add(report.getNeutral());
      fields.add(report.getBad());

      return fields;
    });

    return tableModelViewer;
  }

}
