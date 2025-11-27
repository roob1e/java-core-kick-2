package com.assxmblxr.composite.component.impl;

import com.assxmblxr.composite.component.TextComponent;
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

  public List<TextComponent> getChildren() {
    return List.copyOf(children);
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
}
