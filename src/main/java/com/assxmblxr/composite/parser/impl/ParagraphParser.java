package com.assxmblxr.composite.parser.impl;

import com.assxmblxr.composite.composite.TextComponent;
import com.assxmblxr.composite.composite.impl.TextComposite;
import com.assxmblxr.composite.composite.impl.TextLeaf;
import com.assxmblxr.composite.parser.TextParser;

import java.util.List;

public class ParagraphParser implements TextParser {
  private final TextParser successor;
  private static final String REGEX = "\n\\s+|\n";

  public ParagraphParser(TextParser successor) {
    this.successor = successor;
  }

  @Override
  public TextComponent parse(String text) {
    TextComponent document = new TextComposite();
    List<String> parts = List.of(text.split(REGEX));

    if (parts.isEmpty()) {
      return document;
    }

    for (String part : parts) {
      document.addChild(successor.parse(part));
      document.addChild(new TextLeaf('\n'));
      document.addChild(new TextLeaf('\t'));
    }
    return document;
  }
}
