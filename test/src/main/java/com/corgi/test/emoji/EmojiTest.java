package com.corgi.test.emoji;

import com.github.binarywang.java.emoji.EmojiConverter;

/**
 * @title: EmojiTest
 * @description: 当Emoji表情字符存储有问题，或者遇到保存字符串到数据库里出现\xF0\x9F\x92\x94类似问题时
 * @author: dengmiao
 * @create: 2019-08-01 15:33
 **/
public class EmojiTest {

    static EmojiConverter emojiConverter = EmojiConverter.getInstance();

    public static void main(String[] args) {
        System.out.println("\uD83C\uDF31");
        testToAlias();
        testToHtml();
        testToUnicode();
    }

    public static void testToAlias() {
        String str = "  An 😃😀awesome 😃😃string with a few 😃😉emojis!";
        String alias = emojiConverter.toAlias(str);
        System.out.println(str);
        System.out.println("EmojiConverterTest.testToAlias()=====>");
        System.out.println(alias);
        /*Assert.assertEquals(
                ":no_good: :ok_woman: :couple_with_heart:An :smiley::grinning:awesome :smiley::smiley:string with a few :smiley::wink:emojis!",
                alias);*/
    }

    public static void testToHtml() {
        String str = "  An 😀😃awesome 😃😃string with a few 😉😃emojis!";
        String result = emojiConverter.toHtml(str);
        System.out.println(str);
        System.out.println("EmojiConverterTest.testToHtml()=====>");
        System.out.println(result);
        /*Assert.assertEquals(
                "&#128581; &#128582; &#128145;An &#128512;&#128515;awesome &#128515;&#128515;string with a few &#128521;&#128515;emojis!",
                result);*/
    }

    public static void testToUnicode() {
        String str = "   :smiley: :grinning: :wink:";
        String result = emojiConverter.toUnicode(str);
        System.err.println(str);
        System.err.println("EmojiConverterTest.testToUnicode()=====>");
        System.err.println(result);
        /*Assert.assertEquals("🙅 🙆 💑 😃 😀 😉", result);*/
    }
}
