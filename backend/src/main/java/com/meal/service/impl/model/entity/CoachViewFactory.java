package com.meal.service.impl.model.entity;

import com.meal.entity.UserEntity;
import com.meal.entity.UserView;
import com.meal.service.impl.model.TableModelViewer;
import com.meal.service.impl.model.TableModelViewerInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoachViewFactory implements ViewerFactoryInterface{

  @Override
  public TableModelViewerInterface create() {
    TableModelViewerInterface<UserView> tableModelViewer
            = new TableModelViewer<>();

    List<String> headers = Arrays.asList(
            "Логин",
            "Проставлено оценок",
            "Проставлено положительных оценок",
            "Кол-во курируемых групп",
            "Кол-во курируемых участников"
    );

    tableModelViewer.setHeaders(headers);

    tableModelViewer.setMapper((coach) -> {
      List<String> fields = new ArrayList<>();


//        String fio = client.getLastName()
//                + " "
//                + client.getFirstName().substring(0,1)
//                + "."
//                + client.getPatronymic().substring(0,1)
//                +".";

      fields.add(coach.getLogin());
      fields.add(coach.getMarksCount());
      fields.add(coach.GoodMark());
      fields.add(coach.getGroupsCount());
      fields.add(coach.getUsersCount());

      return fields;
    });

    return tableModelViewer;
  }

}
