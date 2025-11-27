package com.assxmblxr.composite.parser.impl;

import com.assxmblxr.composite.component.TextComponent;
import com.assxmblxr.composite.component.impl.TextComposite;
import com.assxmblxr.composite.parser.TextParser;

import java.util.List;

public class LexemeParser implements TextParser {
  private final TextParser successor;
  private static final String REGEX = "(?<= )|(?= )";

  public LexemeParser(TextParser successor) {
    this.successor = successor;
  }

  @Override
  public TextComponent parse(String text) {
    TextComponent sentence = new TextComposite();
    List<String> parts = List.of(text.split(REGEX));

    if (parts.isEmpty()) {
      return sentence;
    }

    for (String part : parts) {
      sentence.addChild(successor.parse(part));
    }
    return sentence;
  }
}
