package com.assxmblxr.composite.composite.impl;

import com.assxmblxr.composite.composite.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TextLeaf implements TextComponent {
  private static final Logger logger = LogManager.getLogger();
  private final char character;

  public TextLeaf(char character) {
    this.character = character;
    logger.trace("TextLeaf({}) initialized", character);
  }

  @Override
  public TextComponent[] getChildren() {
    logger.warn("TextLeaf can't have children");
    throw new UnsupportedOperationException("TextLeaf can't have children");
  }

  @Override
  public void addChild(TextComponent child) {
    logger.warn("TextLeaf can't have children");
    throw new UnsupportedOperationException("TextLeaf can't have children");
  }

  @Override
  public String toString() {
    return String.valueOf(character);
  }

  @Override
  public int count() {
    if (Character.isLetter(character)) {
      return 1;
    } else if (character == '\t' || character == '\n') {
      return 0;
    } else {
      return 0;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TextLeaf that = (TextLeaf) o;
    return character == that.character;
  }
}