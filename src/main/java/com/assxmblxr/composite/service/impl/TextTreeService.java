package com.assxmblxr.composite.service.impl;

import com.assxmblxr.composite.component.TextComponent;
import com.assxmblxr.composite.service.TreeService;

public class TextTreeService implements TreeService<TextComponent> {
  private static final String END_REGEX = "[.*(\\?!.)+(\\s)]+";

  @Override
  public TextComponent collect(TextComponent component) {
    return null;
  }

  @Override
  public boolean isSentence(TextComponent component) {
    return component.toString() != null;
  }
}
