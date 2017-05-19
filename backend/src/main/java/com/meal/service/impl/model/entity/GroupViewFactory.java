package com.meal.service.impl.model.entity;

import com.meal.entity.GroupEntity;
import com.meal.entity.GroupView;
import com.meal.entity.UserEntity;
import com.meal.service.impl.model.TableModelViewer;
import com.meal.service.impl.model.TableModelViewerInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupViewFactory implements ViewerFactoryInterface {
  @Override
  public TableModelViewerInterface create() {
    TableModelViewerInterface<GroupView> tableModelViewer
            = new TableModelViewer<>();

    List<String> headers = Arrays.asList(
            "№",
            "Кол-во участников",
            "Кол-во отчетов",
           // "Кол-во оценок",
            "Тренер"
    );

    tableModelViewer.setHeaders(headers);

    tableModelViewer.setMapper((group) -> {
      List<String> fields = new ArrayList<>();

      fields.add(String.valueOf(group.getId()));
      fields.add(group.getUsers());
      fields.add(group.getReports());
      fields.add(group.getCoach());


      return fields;
    });

    return tableModelViewer;
  }
}
