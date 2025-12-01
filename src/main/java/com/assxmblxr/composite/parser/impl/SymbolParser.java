package com.assxmblxr.composite.parser.impl;

import com.assxmblxr.composite.composite.TextComponent;
import com.assxmblxr.composite.composite.impl.TextComposite;
import com.assxmblxr.composite.composite.impl.TextLeaf;
import com.assxmblxr.composite.parser.TextParser;

public class SymbolParser implements TextParser {
  public SymbolParser() {}

  @Override
  public TextComponent parse(String text) {
    TextComponent lexeme = new TextComposite();
    for (int i = 0; i < text.length(); i++) {
      lexeme.addChild(new TextLeaf(text.charAt(i)));
    }
    return lexeme;
  }
}
