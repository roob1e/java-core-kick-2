package com.assxmblxr.composite.service;

public interface TreeService<T> {
  T collect(T current);
  boolean isSentence(T component);
}
