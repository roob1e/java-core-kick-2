package com.assxmblxr.composite.service;

import com.assxmblxr.composite.composite.TextComponent;

import java.util.List;

public interface AnalysisService {
  @SuppressWarnings("unused")
  int findMaxSharedWordSentences(TextComponent textComponent);
  List<String> sortSentences(String text);
  String swapLexemes(String text);

}
