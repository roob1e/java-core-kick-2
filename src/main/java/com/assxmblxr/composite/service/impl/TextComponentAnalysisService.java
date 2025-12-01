package com.assxmblxr.composite.service.impl;

import com.assxmblxr.composite.composite.TextComponent;
import com.assxmblxr.composite.composite.impl.TextComposite;
import com.assxmblxr.composite.parser.impl.LexemeParser;
import com.assxmblxr.composite.parser.impl.SentenceParser;
import com.assxmblxr.composite.parser.impl.SymbolParser;
import com.assxmblxr.composite.service.AnalysisService;

import java.util.Comparator;
import java.util.List;

public class TextComponentAnalysisService implements AnalysisService {
  private static final SymbolParser symbolParser = new SymbolParser();
  private static final LexemeParser lexemeParser = new LexemeParser(symbolParser);
  private static final SentenceParser sentenceParser = new SentenceParser(lexemeParser);


  @Override
  public int findMaxSharedWordSentences(TextComponent component) {
    throw new UnsupportedOperationException("Under construction.");
  }

  @Override
  public List<String> sortSentences(String text) {
    TextComponent component = sentenceParser.parse(text);
    TextComponent[] children = component.getChildren();
    List<TextComponent> sentences = List.of(children);

    return sentences.stream()
            .map(TextComponent::toString)
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .sorted(Comparator.comparingInt(String::length))
            .toList();
  }

  @Override
  public String swapLexemes(String text) {
    TextComponent sentencesComponent = sentenceParser.parse(text);
    TextComponent[] sentences = sentencesComponent.getChildren();
    StringBuilder sb = new StringBuilder("\t");

    for (TextComponent sentence : sentences) {
      if (sentence instanceof TextComposite) {
        TextComponent[] words = sentence.getChildren();
        TextComponent temp = words[0];
        words[0] = words[words.length - 1];
        words[words.length - 1] = temp;
        for (TextComponent word : words) {
          sb.append(word.toString());
        }
      } else {
        sb.append("\n\t");
      }
    }
    return sb.toString();
  }

}
