package com.assxmblxr.composite.service.impl;

import com.assxmblxr.composite.composite.TextComponent;
import com.assxmblxr.composite.parser.impl.LexemeParser;
import com.assxmblxr.composite.parser.impl.ParagraphParser;
import com.assxmblxr.composite.parser.impl.SentenceParser;
import com.assxmblxr.composite.parser.impl.SymbolParser;
import com.assxmblxr.composite.service.AnalysisService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TextComponentAnalysisServiceTest {
  AnalysisService service;
  static String text;
  static int textMaxSharedWords;
  static String textSortedSentences;
  static String textSwappedLexemes;

  SymbolParser symbolParser;
  LexemeParser lexemeParser;
  SentenceParser sentenceParser;
  ParagraphParser paragraphParser;

  @BeforeAll
  static void setUpBeforeClass() {
    textMaxSharedWords = 6;
    text = """
            \tIt has survived not only five centuries, but also the leap into electronic \
            typesetting, remaining essentially unchanged. It was popularised in the with the \
            release of Letraset sheets containing Lorem Ipsum passages, and more recently with \
            desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            \t\
            It is a long established fact that a reader will be distracted by the readable \
            content of a page when looking at its layout. The point of using Ipsum is that it has a \
            more-or-less normal distribution of letters, as opposed to using 'Content here, content \
            here', making it look like readable по английски.
            \t\
            It is a established fact that a reader will be of a page when looking at its \
            layout.
            \t\
            Bye.""";
    textSortedSentences = """
            Bye.\s
            	It is a established fact that a reader will be of a page when looking at its layout.\s
            	It has survived not only five centuries, but also the leap into electronic typesetting, \
            remaining essentially unchanged.\s
            	It is a long established fact that a reader will be distracted by the readable content \
            of a page when looking at its layout.\s
            	The point of using Ipsum is that it has a more-or-less normal distribution of letters, \
            as opposed to using 'Content here, content here', making it look like readable по английски.\s
            	It was popularised in the with the release of Letraset sheets containing Lorem Ipsum \
            passages, and more recently with desktop publishing software like Aldus PageMaker including \
            versions of Lorem Ipsum.\s
            \t""";
    textSwappedLexemes = """
            \tunchanged has survived not only five centuries, but also the leap into electronic \
            typesetting, remaining essentially It. Ipsum was popularised in the with the \
            release of Letraset sheets containing Lorem Ipsum passages, and more recently with \
            desktop publishing software like Aldus PageMaker including versions of Lorem It.\s\s
            \t\
            layout is a long established fact that a reader will be distracted by the readable \
            content of a page when looking at its It. английски point of using Ipsum is that it has a \
            more-or-less normal distribution of letters, as opposed to using 'Content here, content \
            here', making it look like readable по The.\s\s
            \t\
            layout is a established fact that a reader will be of a page when looking at its \
            It.\s\s
            \t\
            Bye.\s\s
            \t""";
  }

  @BeforeEach
  void setUp() {
    service = new TextComponentAnalysisService();
    symbolParser = new SymbolParser();
    lexemeParser = new LexemeParser(symbolParser);
    sentenceParser = new SentenceParser(lexemeParser);
    paragraphParser = new ParagraphParser(sentenceParser);
  }

  @AfterEach
  void tearDown() {
    service = null;
    symbolParser = null;
    lexemeParser = null;
    sentenceParser = null;
    paragraphParser = null;
  }

  @Test
  void findMaxSharedWordSentences() {
    int expected = textMaxSharedWords;

    TextComponent component = paragraphParser.parse(text);
    int actual = service.findMaxSharedWordSentences(component);

    assertEquals(expected, actual);
  }

  @Test
  void sortSentences() {
    String expected = textSortedSentences;

    TextComponent component = paragraphParser.parse(text);
    TextComponent sorted = service.sortSentences(component);
    String actual = sorted.toString();

    assertEquals(expected, actual);
  }

  @Test
  void swapLexemes() {
    String expected = textSwappedLexemes;

    TextComponent component = paragraphParser.parse(text);
    TextComponent swapped = service.swapLexemes(component);
    String actual = swapped.toString();

    assertEquals(expected, actual);
  }
}