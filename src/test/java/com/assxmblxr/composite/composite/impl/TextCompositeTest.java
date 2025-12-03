package com.assxmblxr.composite.composite.impl;

import com.assxmblxr.composite.composite.TextComponent;
import com.assxmblxr.composite.parser.impl.LexemeParser;
import com.assxmblxr.composite.parser.impl.ParagraphParser;
import com.assxmblxr.composite.parser.impl.SentenceParser;
import com.assxmblxr.composite.parser.impl.SymbolParser;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TextCompositeTest {
  SymbolParser symbolParser;
  LexemeParser lexemeParser;
  SentenceParser sentenceParser;
  ParagraphParser paragraphParser;

  String text1, text2;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    text1 = """
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
    text2 = "Bye.";

    symbolParser = new SymbolParser();
    lexemeParser = new LexemeParser(symbolParser);
    sentenceParser = new SentenceParser(lexemeParser);
    paragraphParser = new ParagraphParser(sentenceParser);
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    text1 = null;
    text2 = null;

    symbolParser = null;
    lexemeParser = null;
    sentenceParser = null;
    paragraphParser = null;
  }

  @org.junit.jupiter.api.Test
  void getChildren() {
    int expected = 4;

    TextComponent component = symbolParser.parse(text2);
    int actual = component.getChildren().length;

    assertEquals(expected, actual);
  }

  @org.junit.jupiter.api.Test
  void addChild() {
    TextComponent[] expected = { new TextLeaf('1') };

    TextComponent component = new TextComposite();
    TextComponent child = new TextLeaf('1');
    component.addChild(child);
    TextComponent[] actual = component.getChildren();

    assertArrayEquals(expected, actual);
  }

  @org.junit.jupiter.api.Test
  void testToString() {
    String text = text1;
    String[] expected = new String[]{Arrays.stream(text.split("\n\t"))
            .map(String::trim)
            .collect(Collectors.joining("\n"))};

    TextComponent component = paragraphParser.parse(text1);
    String actualText = component.toString();
    String[] actual = new String[]{Arrays.stream(actualText.split("\n\t"))
            .map(String::trim)
            .collect(Collectors.joining("\n"))};

    assertArrayEquals(expected, actual);
  }

  @org.junit.jupiter.api.Test
  void count() {
    int expected = 582;

    TextComponent component = paragraphParser.parse(text1);
    int actual = component.count();

    assertEquals(expected, actual);
  }

  @org.junit.jupiter.api.Test
  void extractText() {
    String expected = "Bye ";

    TextComponent component = sentenceParser.parse(text2);
    String actual = component.extractText();

    assertEquals(expected, actual);
  }
}