package com.assxmblxr.composite.service;

import com.assxmblxr.composite.component.TextComponent;

public interface AnalysisService {
  int findMaxSharedWordSentences(TextComponent textComponent);
  String sortSentences(TextComponent textComponent);
}
