package com.assxmblxr.composite.service;

import com.assxmblxr.composite.composite.TextComponent;

public interface AnalysisService {
  int findMaxSharedWordSentences(TextComponent textComponent);

  TextComponent sortSentences(TextComponent textComponent);

  TextComponent swapLexemes(TextComponent textComponent);
}