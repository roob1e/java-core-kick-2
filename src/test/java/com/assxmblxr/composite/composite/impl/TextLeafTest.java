package com.assxmblxr.composite.composite.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class TextLeafTest {
  TextLeaf leaf;

  @AfterEach
  void tearDown() {
    leaf = null;
  }

  @Test
  void getChildren() {
    Class<UnsupportedOperationException> expected = UnsupportedOperationException.class;

    leaf = new TextLeaf('c');

    assertThrows(expected, () -> leaf.getChildren());
  }

  @Test
  void addChild() {
    Class<UnsupportedOperationException> expected = UnsupportedOperationException.class;

    leaf = new TextLeaf('c');

    assertThrows(expected, () -> leaf.addChild(new TextLeaf('c')));
  }

  @Test
  void testToString() {
    String expected = "c";

    leaf = new TextLeaf('c');
    String actual = leaf.toString();

    assertEquals(expected, actual);
  }

  @Test
  void count() {
    int expected = 1;

    leaf = new TextLeaf('c');
    int actual = leaf.count();

    assertEquals(expected, actual);
  }

  @Test
  void extractText() {
    String expected = "c";

    leaf = new TextLeaf('c');
    String actual = leaf.extractText();

    assertEquals(expected, actual);
  }
}