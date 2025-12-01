package com.assxmblxr.composite.parser.impl;

import com.assxmblxr.composite.composite.TextComponent;
import com.assxmblxr.composite.composite.impl.TextComposite;
import com.assxmblxr.composite.composite.impl.TextLeaf;
import com.assxmblxr.composite.parser.TextParser;

import java.util.List;

public class SentenceParser implements TextParser {
  private final TextParser successor;
  private static final String REGEX = "(?<=[.?!])\\s+(?=[A-ZА-Я])";

  public SentenceParser(TextParser successor) {
    this.successor = successor;
  }

  @Override
  public TextComponent parse(String text) {
    TextComponent paragraph = new TextComposite();
    List<String> parts = List.of(text.split(REGEX));

    if (parts.isEmpty()) {
      return paragraph;
    }

    for (String part : parts) {
      paragraph.addChild(successor.parse(part));
      paragraph.addChild(new TextLeaf(' '));
    }
    return paragraph;
  }
}
