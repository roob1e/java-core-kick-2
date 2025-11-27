package com.assxmblxr.composite.comparator.impl;

import com.assxmblxr.composite.comparator.Comparator;
import com.assxmblxr.composite.component.impl.TextComposite;

public class TextCompositeComparator implements Comparator<TextComposite> {
  private static final String REGEX = "[\\s.,!?\\n\\t]+";

  @Override
  public boolean compare(TextComposite comparing, TextComposite compared) {
    String comparingText = comparing.toString();
    String comparedText = compared.toString();

    String comparingNormalized = normalize(comparingText);
    String comparedNormalize = normalize(comparedText);
    return comparedNormalize.equals(comparingNormalized);
  }

  private String normalize(String text) {
    return text.replaceAll(REGEX, "").toLowerCase();
  }
}
