package com.assxmblxr.composite.component;

import java.util.List;

public interface TextComponent {
  @Override
  String toString();

  void addChild(TextComponent child);

  List<TextComponent> getChildren();
}
