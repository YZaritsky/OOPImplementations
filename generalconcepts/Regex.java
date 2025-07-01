import java.util.regex.*;

public class Regex {
    // a,b,c -> matches the char

    // .at -> matches words that start with anything and end with "at".

    // [cbr]at -> matches words that start with c|b|r followed by "at" straight away
    // -> "cat", "bat", "rat" , NOT: "cmat"

    // [^cb]at -> matches anything that end with "at" except "cb" -> "rat", NOT:
    // "bat", "cat".

    // [a-z]aa -> matches "aaa", "daa", etc.
    // [^a-c]aa -> matches "daa", NOT: "aaa", "baa", etc.

    // * -> 0 or more occurances of whats before it.
    // a*bb -> matches "bb", "abb", "aabb", etc.

    // + -> 1 or more occurances of whats before it.

    // Special Chars:
    // In java we need to do "\" before every special character because its also a
    // special char in java. I.E -> \b in regex == \\b in java.

    // \d -> a digit. [0-9] // \D -> non-digit. [^0-9]
    // \s -> whitespace. [\t\n\v\f\r] // \S -> non-whitespace. [^\s]
    // \w -> a word. [a-zA-Z_0-9] // \W -> non-word. [^\w]
    // \. -> "\" == escape char. -> this will give us "."

    // Regex Location:
    // ^ -> Start of the line. (^abc -> abc at the start of the file)
    // $ -> End of the line. (abc$ -> abc at the end of the file.)
    // \b -> word boundary (begining or end of the word). [\bJava\b -> finds "Java"
    // when its seperate word.

    // \B -> non-word boundary(spaces between letters inside a word).

    // Regex multiplication:
    // X{n} -> X happens n times.
    // X{n,} -> X Happens atleast n times.
    // X{n,m} -> X Happens n-(m-1) times. inclusive n, exclusive m.
    // X? -> X is optional. (1 or 0 times.)
    // X* -> 0 or more.
    // X+ -> 1 or more.

    // Special Examples:
    // Example A:
    // Match 3 consecutive -> ([0-9])\1{2} -> Breakdown:
    // a.([0-9]) matches some digit.
    // b. \1 matches the same text as captured by last group
    // c. {2} finds it again twice.

    // Example B:
    // Match words that have " ' " before and after them.
    // For example, in this case: "This is 'first' and 'second'".
    // We want -> 'first', 'second'.
    // Solution:
    // "(\\')(.*)(\\')" will work, but its Greedy. (i.e captures: "'first' and 'second'");
    // "(\\')(.*?)(\\')" will also seperate them.

    // Capture groups:
    // Relevant for the "Pattern" in the code:
    // 0 is the whole expression caught by a specific Pattern ("[\d*]" for example),
    // 1 is the first big exp, 2 is the first big exp inside 1, etc.
    // Example:
    // Pattern = "a(b(c)(d))(e)"
    // 0 == a(b(c)(d))(e)
    // 1 == (b(c)(d))
    // 2 == (c)
    // 3 == (d)
    // 4 == (e)

    // Useage in java:
    // Matcher:
    // matches() -> true if it matches the entire text.
    // lookingAt() -> true if it matches at the begining of the text.
    // find() -> true if it matches any part of the text. in loops, if called, it
    // would continue from the first found point to the next one.
    // start() -> first index of the match. -> if failed throws
    // IllegalStateException.
    // end() -> last index of the match +1. -> if failed throws
    // IllegalStateException.

    // match.replaceFirst(String replacemnt);
    // match.replaceAll(String replacement);
    // match.reset(); -> start searching from the start of the text again.
    // match.reset(newText); -> restart matcher with new text.

    // if there was a successfull match:
    // match.group() returns match.group(0);

    Pattern pat = Pattern.compile("/w"); // <- Thats the regex we are looking for.
    Matcher match = pat.matcher("this is the text"); // <- Thats the text we are scanning for regex.

}
