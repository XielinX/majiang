package com.xlx.majiang.mapper;

import com.xlx.majiang.model.Question;

import java.util.List;

/**
 * 问题
 * @author: xielx on 2019/6/23
 */
public interface QuestionExtMapper {


  /**
   * 阅读量
   * @param record
   * @return
   */
  int incView(Question record);

  /**
   * 评论量
   * @param record
   * @return
   */
  int incCommentCount(Question record);

  /**
   * 查询相关问题
   * @param question
   * @return
   */
  List<Question> selectRelated(Question question);
}
