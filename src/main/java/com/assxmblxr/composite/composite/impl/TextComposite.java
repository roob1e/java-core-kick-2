package com.assxmblxr.composite.composite.impl;

import com.assxmblxr.composite.composite.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
  private static final Logger logger = LogManager.getLogger(TextComposite.class);
  private final List<TextComponent> children;

  public TextComposite() {
    children = new ArrayList<>();
    logger.info("TextComposite created");
  }

  public TextComponent[] getChildren() {
    return children.toArray(new TextComponent[0]);
  }

  @Override
  public void addChild(TextComponent child) {
    logger.trace("TextComposite: addChild");
    children.add(child);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (TextComponent child : children) {
      builder.append(child.toString());
    }
    return builder.toString();
  }

  @Override
  public int count() {
    return children.stream()
            .mapToInt(TextComponent::count)
            .sum();
  }
}