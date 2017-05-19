package com.meal.service.impl.model.entity;

import com.meal.entity.UserEntity;
import com.meal.entity.UserView;
import com.meal.service.impl.model.TableModelViewer;
import com.meal.service.impl.model.TableModelViewerInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserViewFactory implements ViewerFactoryInterface{

    @Override
    public TableModelViewerInterface create() {
      TableModelViewerInterface<UserView> tableModelViewer
              = new TableModelViewer<>();

      List<String> headers = Arrays.asList(
              "Логин",
       //       "Рейтинг",
              "Последний отчет",
              "Количество положительных оценок",
              "Количество отрицательных оценок"
      );

      tableModelViewer.setHeaders(headers);

      tableModelViewer.setMapper((user) -> {
        List<String> fields = new ArrayList<>();


//        String fio = client.getLastName()
//                + " "
//                + client.getFirstName().substring(0,1)
//                + "."
//                + client.getPatronymic().substring(0,1)
//                +".";

        fields.add(user.getLogin());
    //    fields.add(user.getRaiting());
        fields.add(user.getReportsPerDay());
        fields.add(user.GoodMarksCount());
        fields.add(user.BadMarksCount());

        return fields;
      });

      return tableModelViewer;
    }

}
