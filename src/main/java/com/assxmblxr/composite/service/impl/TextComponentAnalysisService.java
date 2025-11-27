package com.assxmblxr.composite.service.impl;

import com.assxmblxr.composite.component.TextComponent;
import com.assxmblxr.composite.service.AnalysisService;

import java.util.ArrayList;
import java.util.List;

public class TextComponentAnalysisService implements AnalysisService {
  @Override
  public int findMaxSharedWordSentences(TextComponent component) {
    List<String> sentences = new ArrayList<>();
    for (TextComponent paragraphs : component.getChildren()) {
      for (TextComponent sentence : paragraphs.getChildren()) {
        sentences.add(sentence.toString());
      }
    }

    return 0;
  }

  @Override
  public String sortSentences(TextComponent component) {
    return "";
  }
}
