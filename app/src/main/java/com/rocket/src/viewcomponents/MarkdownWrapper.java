package com.rocket.src.viewcomponents;

import android.content.Context;
import android.widget.TextView;

import org.commonmark.node.Text;

import io.noties.markwon.Markwon;
import io.noties.markwon.syntax.Prism4jTheme;
import io.noties.markwon.syntax.Prism4jThemeDarkula;
import io.noties.markwon.syntax.Prism4jThemeDefault;
import io.noties.markwon.syntax.SyntaxHighlightPlugin;
import io.noties.prism4j.Prism4j;
import io.noties.prism4j.annotations.PrismBundle;

@PrismBundle(include = {"kotlin", "java", "python","cpp"},
        grammarLocatorClassName = ".QuizGrammarLocator")
public class MarkdownWrapper  {
    private static Markwon markwon;
    private static MarkdownWrapper instance = null;

    private MarkdownWrapper(Context context) {
        Prism4j prism4j = new Prism4j(new QuizGrammarLocator());
        Prism4jThemeDefault theme = Prism4jThemeDefault.create();
        SyntaxHighlightPlugin syntaxHighlightPlugin = SyntaxHighlightPlugin.create(prism4j, theme);
        markwon = Markwon.builder(context).usePlugin(syntaxHighlightPlugin).build();
    }
    public static void applyMarkdown(Context context,TextView textView,
                                     String markdown) {
        if(instance==null)
            instance = new MarkdownWrapper(context);
        markwon.setMarkdown(textView, markdown);
    }
}
