package com.assxmblxr.composite.component.impl;

import com.assxmblxr.composite.component.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextLeaf implements TextComponent {
  private static final Logger logger = LogManager.getLogger();
  private final char character;

  public TextLeaf(char character) {
    this.character = character;
    logger.trace("TextLeaf({}) initialized", character);
  }

  @Override
  public List<TextComponent> getChildren() {
    logger.warn("TextLeaf can't have children");
    return new ArrayList<>();
  }

  @Override
  public void addChild(TextComponent child) {
    logger.warn("TextLeaf can't have children");
  }

  @Override
  public String toString() {
    return String.valueOf(character);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TextLeaf that = (TextLeaf) o;
    return character == that.character;
  }
}