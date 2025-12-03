package com.assxmblxr.composite.service;

import com.assxmblxr.composite.composite.TextComponent;

@SuppressWarnings("unused")
public interface AnalysisService {

  int findMaxSharedWordSentences(TextComponent textComponent);

  TextComponent sortSentences(TextComponent textComponent);

  String swapLexemes(TextComponent textComponent);
}