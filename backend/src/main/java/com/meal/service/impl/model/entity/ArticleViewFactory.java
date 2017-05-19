package com.meal.service.impl.model.entity;

import com.meal.entity.ArticleEntity;
import com.meal.entity.ArticleView;
import com.meal.entity.UserEntity;
import com.meal.service.impl.model.TableModelViewer;
import com.meal.service.impl.model.TableModelViewerInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleViewFactory implements ViewerFactoryInterface {
  @Override
  public TableModelViewerInterface create() {
    TableModelViewerInterface<ArticleView> tableModelViewer
            = new TableModelViewer<>();

    List<String> headers = Arrays.asList(
            "Автор",
            "Кол-во статей",
            "Кол-во статей за месяц",
            "Дата последней публикации"
    );

    tableModelViewer.setHeaders(headers);

    tableModelViewer.setMapper((article) -> {
      List<String> fields = new ArrayList<>();


      fields.add(article.getCoach());
      fields.add(article.getCount());
      fields.add(article.getLastPub());

      return fields;
    });

    return tableModelViewer;
  }
}
