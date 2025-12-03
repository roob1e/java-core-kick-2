package com.assxmblxr.composite.service.impl;

import com.assxmblxr.composite.composite.TextComponent;
import com.assxmblxr.composite.composite.impl.TextComposite;
import com.assxmblxr.composite.parser.impl.LexemeParser;
import com.assxmblxr.composite.parser.impl.ParagraphParser;
import com.assxmblxr.composite.parser.impl.SentenceParser;
import com.assxmblxr.composite.parser.impl.SymbolParser;
import com.assxmblxr.composite.service.AnalysisService;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextComponentAnalysisService implements AnalysisService {
  private static final SymbolParser symbolParser = new SymbolParser();
  private static final LexemeParser lexemeParser = new LexemeParser(symbolParser);
  private static final SentenceParser sentenceParser = new SentenceParser(lexemeParser);
  private static final ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

  @Override
  public int findMaxSharedWordSentences(TextComponent textComponent) {
    String text = textComponent.toString();
    TextComponent component = sentenceParser.parse(text);
    TextComponent[] children = component.getChildren();
    List<TextComponent> sentences = List.of(children);

    HashMap<String, Set<Integer>> results = new HashMap<>();
    int sentenceIndex = 0;

    for (TextComponent sentence : sentences) {
      sentenceIndex++;
      List<String> words = new ArrayList<>();
      if (sentence instanceof TextComposite) {
        TextComponent[] candidates = sentence.getChildren();
        for (TextComponent candidate : candidates) {
          if (candidate instanceof TextComposite && !candidate.extractText().isEmpty()) {
            words.add(candidate.extractText().toLowerCase());
          }
        }
      }


      for (String word : words) {
        if (!results.containsKey(word)) {
          HashSet<Integer> set = new HashSet<>();
          set.add(sentenceIndex);
          results.put(word, set);
        } else {
          results.get(word).add(sentenceIndex);
        }
      }
    }
    System.out.println(results);
    return results.values().stream()
            .mapToInt(Set::size)
            .max()
            .orElse(0);
  }

  @Override
  public TextComponent sortSentences(TextComponent textComponent) {
    String text = textComponent.toString();
    TextComponent component = sentenceParser.parse(text);
    TextComponent[] children = component.getChildren();
    List<TextComponent> sentences = List.of(children);

    List<String> sorted = sentences.stream()
            .map(TextComponent::toString)
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .sorted(Comparator.comparingInt(String::length))
            .toList();
    var sortedText = String.join("", sorted);
    return paragraphParser.parse(sortedText);
  }

  @Override
  public String swapLexemes(TextComponent textComponent) {
    String text = textComponent.toString();

    Pattern sentencePattern = Pattern.compile("[^.!?]+[.!?]");
    Matcher sentenceMatcher = sentencePattern.matcher(text);

    StringBuilder result = new StringBuilder();
    int lastEnd = 0;

    while (sentenceMatcher.find()) {
      String sentence = sentenceMatcher.group();
      result.append(text, lastEnd, sentenceMatcher.start());
      result.append(swapWordsInSentence(sentence));
      lastEnd = sentenceMatcher.end();
    }
    if (lastEnd < text.length()) {
      result.append(text.substring(lastEnd));
    }
    return result.toString();
  }

  private String swapWordsInSentence(String sentence) {
    Pattern wordPattern = Pattern.compile("\\p{L}+");
    Matcher wordMatcher = wordPattern.matcher(sentence);

    List<String> words = new ArrayList<>();
    List<int[]> positions = new ArrayList<>();

    while (wordMatcher.find()) {
      words.add(wordMatcher.group());
      positions.add(new int[]{wordMatcher.start(), wordMatcher.end()});
    }

    if (words.size() < 2) {
      return sentence;
    }

    StringBuilder newSentence = new StringBuilder(sentence);
    String firstWord = words.getFirst();
    String lastWord = words.getLast();

    newSentence.replace(positions.getFirst()[0], positions.getFirst()[1], lastWord);
    int lengthDiff = lastWord.length() - firstWord.length();
    newSentence.replace(
            positions.getLast()[0] + lengthDiff,
            positions.getLast()[1] + lengthDiff,
            firstWord
    );

    return newSentence.toString();
  }
}