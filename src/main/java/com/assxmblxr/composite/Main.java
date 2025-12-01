package com.assxmblxr.composite;

import com.assxmblxr.composite.parser.impl.LexemeParser;
import com.assxmblxr.composite.parser.impl.ParagraphParser;
import com.assxmblxr.composite.parser.impl.SentenceParser;
import com.assxmblxr.composite.parser.impl.SymbolParser;
import com.assxmblxr.composite.service.AnalysisService;
import com.assxmblxr.composite.service.impl.TextComponentAnalysisService;

public class Main {
  public static void main(String[] args) {
    AnalysisService service = new TextComponentAnalysisService();

    SymbolParser symbolParser = new SymbolParser();
    LexemeParser lexemeParser = new LexemeParser(symbolParser);
    SentenceParser sentenceParser = new SentenceParser(lexemeParser);
    ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

    String text = """
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
    System.out.println(paragraphParser.parse(text).toString());
    System.out.println(service.sortSentences(text));
    System.out.println(service.swapLexemes(text));
  }
}