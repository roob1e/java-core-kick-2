package com.assxmblxr.composite.composite;

public interface TextComponent {
  @Override
  String toString();

  void addChild(TextComponent child);

  TextComponent[] getChildren();

  int count();
}
