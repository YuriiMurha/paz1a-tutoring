# Topic 11 · Strings: lecture

Text is everywhere: names, commands, file names, messages, whole documents. In Java, text is a `String`.
Strings look simple, but they hide some of the most important ideas in the language: objects and references,
immutability, `==` versus `equals`, characters as numbers and the cost of building text. This lecture explains all
of it, from what a `String` is in memory to the problem-solving patterns you need for harder puzzles.

**You need:** loops (topic 03), methods (04), arrays (05), objects (07) and references (08).
**Practise with:** recap 11 `StringBasics` (warm-up), then exercise 11 `StringPuzzles` (harder).
**Picture:** [diagram 11](../diagrams/README.md#11--strings) shows strings in memory.
**Stuck on an exercise?** The [topic 11 guide](../diagrams/topic-11-guide.md) has step-by-step hints.

**How to read this:** type the small examples into IntelliJ (a `main` method or the *jshell* console) and predict
the output **before** you run them. When your prediction is wrong, you have just found something worth learning.

## Contents

1. [What a String really is](#1-what-a-string-really-is)
2. [`char`: a character is a number](#2-char-a-character-is-a-number)
3. [Memory: immutability, the string pool and `==` vs `equals`](#3-memory-immutability-the-string-pool-and--vs-equals)
4. [The String methods, one group at a time](#4-the-string-methods-one-group-at-a-time)
5. [Gluing strings with `+` and its hidden cost](#5-gluing-strings-with--and-its-hidden-cost)
6. [`StringBuilder`: a string you can change](#6-stringbuilder-a-string-you-can-change)
7. [Strings in decisions: `switch`, `null` and safe comparisons](#7-strings-in-decisions-switch-null-and-safe-comparisons)
8. [Problem-solving patterns, with worked examples](#8-problem-solving-patterns-with-worked-examples)
9. [Going deeper: Unicode, locales and what is inside a String](#9-going-deeper-unicode-locales-and-what-is-inside-a-string)
10. [Common mistakes](#10-common-mistakes)
11. [Cheat sheet: speed of the common operations](#11-cheat-sheet-speed-of-the-common-operations)
12. [Check yourself](#12-check-yourself)
13. [Going further](#13-going-further)

---

## 1. What a String really is

A `String` is a **sequence of characters** (`char` values) with positions `0, 1, 2, …`, just like an array.
But a `String` is **an object**, not a primitive:

```java
String greeting = "Hello";
```

- `"Hello"` is a **String object** on the Heap.
- `greeting` is a variable on the Stack that holds the **address** of that object (an arrow), exactly like an
  array variable or a `Person` variable in topic 08.
- `String` is a class (`java.lang.String`), which is why it starts with a capital letter and why you call methods
  on it: `greeting.length()`, `greeting.charAt(0)`.

```
STACK                        HEAP
┌──────────────────────┐     ┌───────────────────────────────┐
│ greeting  [ 0x51 ]───┼────►│ String @0x51                  │
└──────────────────────┘     │  'H'  'e'  'l'  'l'  'o'      │
                             │  [0]  [1]  [2]  [3]  [4]      │
                             └───────────────────────────────┘
```

### Positions work like array indexes

| | array `int[] a` | `String s` | `StringBuilder sb` |
|---|---|---|---|
| number of elements | `a.length` (a field, **no** parentheses) | `s.length()` (a method) | `sb.length()` |
| element at position i | `a[i]` | `s.charAt(i)` | `sb.charAt(i)` |
| change element i | `a[i] = x;` | impossible (immutable) | `sb.setCharAt(i, x);` |
| valid positions | `0 … a.length - 1` | `0 … s.length() - 1` | `0 … sb.length() - 1` |
| outside the range | `ArrayIndexOutOfBoundsException` | `StringIndexOutOfBoundsException` | `StringIndexOutOfBoundsException` |

The classic loop over every character is the array loop with `charAt`:

```java
for (int i = 0; i < text.length(); i++) {
    char c = text.charAt(i);
    // ... do something with c
}
```

### Three kinds of "nothing": `null`, `""` and `" "`

```java
String a = null;   // no object at all: the variable has no arrow
String b = "";     // a real String object with length 0 (the empty string)
String c = " ";    // a String of length 1: one space character
```

| | `a = null` | `b = ""` | `c = " "` |
|---|---|---|---|
| is there an object? | no | yes | yes |
| `x.length()` | **NullPointerException** | `0` | `1` |
| `x.isEmpty()` | **NullPointerException** | `true` | `false` |
| `x.isBlank()` (only spaces?) | **NullPointerException** | `true` | `true` |

Calling **any** method on `null` crashes, because there is no object to ask. The exercises reject `null` arguments
for this reason, but treat `""` as a normal input with a natural answer (zero words, an empty middle, …).

### Creating strings

```java
String s1 = "text";                              // a literal - the usual way
String s2 = new String("text");                  // forces a NEW object (almost never needed, see section 3)
String s3 = String.valueOf(42);                  // "42" - valueOf works for every type
String s4 = String.valueOf(new char[]{'h', 'i'}); // "hi" - from a char array
String s5 = "" + 3.5;                            // "3.5" - a quick (lazy) conversion
String s6 = """
        Text blocks (Java 15+) can span
        several lines.
        """;                                     // "Text blocks (Java 15+) can span\nseveral lines.\n"
```

---

## 2. `char`: a character is a number

A `char` is **one** character, written in **single** quotes: `'a'`, `'7'`, `' '`, `'\n'`. A `String` uses **double**
quotes: `"a"` is a String of length 1, not a char. They are different types:

```java
char c = 'a';      // OK
char d = "a";      // does not compile: a String is not a char
String e = 'a';    // does not compile either
```

### Every char has a number code

Inside the computer, a `char` is a whole number from 0 to 65 535 (its Unicode code). The ones you will meet most
often are the ASCII codes:

| characters | codes | remember |
|---|---|---|
| `' '` (space) | 32 | smaller than every letter and digit |
| `'0'` … `'9'` | 48 … 57 | digits are in order |
| `'A'` … `'Z'` | 65 … 90 | upper case comes **first** |
| `'a'` … `'z'` | 97 … 122 | lower case = upper case + 32 |

You never need to memorise the numbers: Java lets you calculate with the characters themselves. Because letters and
digits are **consecutive**, these tricks always work:

```java
char c = 'e';
int position = c - 'a';             // 4: 'e' is the 5th letter, so 4 steps after 'a'
char next = (char) (c + 1);         // 'f': a char + an int is an int, so cast back to char
char upper = (char) (c - 'a' + 'A'); // 'E'  (but Character.toUpperCase(c) is clearer)

char digit = '7';
int value = digit - '0';            // 7  ('7' is 55, '0' is 48)
char back = (char) ('0' + 3);       // '3'

boolean isLowerLetter = c >= 'a' && c <= 'z';   // compare chars like numbers
boolean isDigit = digit >= '0' && digit <= '9';
```

`c - 'a'` turns a letter into a number 0–25, which is perfect as an **array index**: `counts[c - 'a']++` counts
letters (pattern 8.3). `'0' + n` and `'a' + n` go the other way.

### The traps of char arithmetic

```java
System.out.println('a' + 'b');        // 195 !  char + char is int + int (97 + 98)
System.out.println("" + 'a' + 'b');   // ab     String + char glues
System.out.println('a' + 1);          // 98     an int
System.out.println((char) ('a' + 1)); // b

char c = 'a';
c++;          // OK: c is now 'b' (++ and += cast for you)
c += 2;       // OK: c is now 'd'
c = c + 1;    // does NOT compile: c + 1 is an int, and an int does not fit into a char without a cast
```

Comparing two `char` values with `==` is correct, because they are numbers: `text.charAt(i) == 'x'`.
(Comparing two **Strings** with `==` is a bug, see section 3.)

### The `Character` helper class

`Character` has static methods that answer questions about one char:

| method | example | result |
|---|---|---|
| `Character.isLetter(c)` | `isLetter('x')`, `isLetter('é')`, `isLetter('3')` | `true`, `true`, `false` |
| `Character.isDigit(c)` | `isDigit('7')` | `true` |
| `Character.isLetterOrDigit(c)` | `isLetterOrDigit('_')` | `false` |
| `Character.isWhitespace(c)` | `isWhitespace(' ')`, `isWhitespace('\t')` | `true`, `true` |
| `Character.isUpperCase(c)` / `isLowerCase(c)` | `isUpperCase('Q')` | `true` |
| `Character.toUpperCase(c)` / `toLowerCase(c)` | `toUpperCase('q')`, `toUpperCase('!')` | `'Q'`, `'!'` |
| `Character.getNumericValue(c)` | `getNumericValue('7')`, `getNumericValue('a')` | `7`, `10` (!) |
| `Character.toString(c)` / `String.valueOf(c)` | `String.valueOf('x')` | `"x"` |

Two details matter in the exercises:

- `isLetter` and `isDigit` know **all** of Unicode: `isLetter('ž')` and even `isDigit('٣')` (Arabic-Indic three) are
  `true`. When a task says "the letters a–z" or "the digits 0–9", the precise check is `c >= 'a' && c <= 'z'`
  or `c >= '0' && c <= '9'`.
- `getNumericValue('a')` is 10, because it also reads letters as digits of bigger bases (like hexadecimal). For a
  digit character, `c - '0'` is the simple, predictable choice.

---

## 3. Memory: immutability, the string pool and `==` vs `equals`

[Diagram 11](../diagrams/README.md#11--strings) draws everything in this section.

### Strings never change (they are immutable)

**No String method changes the String.** Methods like `toUpperCase`, `trim`, `replace` or `substring` build a
**new** String and return it. The original stays exactly as it was:

```java
String s = "java";
s.toUpperCase();              // creates "JAVA" ... and throws it away
System.out.println(s);        // java

s = s.toUpperCase();          // keep the result: s now points at the new object
System.out.println(s);        // JAVA
```

In memory, the second line does **not** change the object `"java"`. It creates a new object `"JAVA"` and moves the
arrow of `s` to it. If nothing else points at `"java"`, the garbage collector removes it later.

```
before:  s ──► "java"
after:   s ──► "JAVA"        "java"  (unchanged, now unused)
```

Why did the designers of Java make strings immutable?

1. **Safe sharing.** You can pass a String to any method (topic 08: the method gets a copy of the arrow) and be sure
   it comes back unchanged. Nobody can change the name inside your `Player` object behind your back.
2. **The string pool** (below) can let many variables share one object, because nobody can change it.
3. **Speed for maps and sets.** A String computes its `hashCode()` once and remembers it.
4. **Security and threads.** A file name or password checked once cannot be changed afterwards by other code,
   and several threads can read the same String without locks.

The price: every "change" creates a new object. Usually that is cheap, but in a loop it can become very slow
(section 5), which is why `StringBuilder` exists (section 6).

### The string pool

String **literals** with the same content are stored only **once**, in a special area of the Heap called the
*string pool*. Every literal `"hi"` in your program points at that same object:

```java
String a = "hi";
String b = "hi";              // the same pooled object as a
String c = new String("hi");  // new ALWAYS creates a new object, outside the pool
```

```
STACK            HEAP
a [0x20] ──┐     ┌─ string pool ──────┐
           ├───► │  "hi" @0x20        │
b [0x20] ──┘     └────────────────────┘
c [0x7C] ──────►    "hi" @0x7C   (a second object with the same content)
```

The compiler also puts the result of **constant** expressions into the pool: `"hel" + "lo"` is computed while
compiling, so it is the same object as `"hello"`. Strings made **while the program runs** (from user input, from
`substring`, from `+` with a variable, from a `StringBuilder`, read from a file) are new objects outside the pool.
`s.intern()` returns the pooled object with the same content, but you will rarely need it.

### `==` compares arrows, `equals` compares content

| expression | result | why |
|---|---|---|
| `a == b` | `true` | the same object (both point into the pool) |
| `a == c` | **`false`** | two different objects, so two different addresses |
| `a.equals(c)` | `true` | the same characters in the same order |
| `"hel" + "lo" == "hello"` | `true` | computed by the compiler, so pooled |
| `part + "lo" == "hello"` (with `String part = "hel";`) | **`false`** | built while the program runs |
| `"Hi".equals("hi")` | `false` | `'H'` and `'h'` are different characters |
| `"Hi".equalsIgnoreCase("hi")` | `true` | upper/lower case ignored |

> **Rule: compare Strings with `equals`, never with `==`.**

The dangerous part is that `==` often *seems* to work: in a small test with literals, everything is pooled and `==`
says `true`. Then the same code gets a String from the keyboard, a file or `trim()`, and suddenly says `false`.
(The test `comparesContentNotReferences` in recap 11 does exactly that to your `isCommand`.)

`==` is still right for `char` values and for checking `s == null`.

### Ordering strings: `compareTo`

`a.compareTo(b)` tells you which string comes first in **lexicographic** (dictionary-like) order:

- a **negative** number if `a` comes before `b`,
- `0` if they are equal,
- a **positive** number if `a` comes after `b`.

How it decides: compare the characters from the left. At the **first difference**, the answer is the difference of
the two char codes. If one string runs out first (it is a prefix of the other), the shorter one comes first and the
answer is the difference of the lengths.

```java
"apple".compareTo("banana")    // 'a' - 'b' = -1   apple comes first
"apple".compareTo("apricot")   // 'p' - 'r' = -2   first difference at index 2
"car".compareTo("cart")        // 3 - 4 = -1      "car" is a prefix, so it comes first
"pear".compareTo("pear")       // 0
"Zebra".compareTo("apple")     // 'Z' - 'a' = 90 - 97 = -7   (!)
"Zebra".compareToIgnoreCase("apple")  // positive: z comes after a
```

Two warnings:

1. **Upper case comes before all lower case**, because `'Z'` (90) is smaller than `'a'` (97). For "real" alphabetical
   order use `compareToIgnoreCase`.
2. Only the **sign** of the result is promised. Write `if (a.compareTo(b) < 0)`, never `if (a.compareTo(b) == -1)`.

Digits are compared as characters too: `"10".compareTo("9")` is negative, because `'1'` < `'9'`. That is why file
lists sometimes show `file10` before `file9`.

---

## 4. The String methods, one group at a time

Every method below **returns** something and leaves the original String unchanged. Examples use
`String s = "Hello, World";` unless they say otherwise.

```
 H   e   l   l   o   ,       W   o   r   l   d
[0] [1] [2] [3] [4] [5] [6] [7] [8] [9] [10][11]      s.length() == 12
```

### 4.1 Looking at the string

| method | example | result | notes |
|---|---|---|---|
| `length()` | `s.length()` | `12` | the space and the comma count |
| `charAt(i)` | `s.charAt(4)`, `s.charAt(12)` | `'o'`, **exception** | the last index is `length() - 1` |
| `isEmpty()` | `"".isEmpty()`, `" ".isEmpty()` | `true`, `false` | length is 0 |
| `isBlank()` | `"  ".isBlank()` | `true` | empty or only whitespace (Java 11+) |

### 4.2 Searching

| method | example | result |
|---|---|---|
| `indexOf(char or String)` | `s.indexOf('o')`, `s.indexOf("World")` | `4`, `7` |
| `indexOf(x, fromIndex)` | `s.indexOf('o', 5)` | `8` (searching starts at index 5) |
| `lastIndexOf(x)` | `s.lastIndexOf('o')` | `8` |
| `contains(String)` | `s.contains("lo, W")` | `true` |
| `startsWith(String)` / `endsWith(String)` | `s.startsWith("Hell")`, `s.endsWith("d")` | `true`, `true` |

All the `indexOf` methods return **-1** when nothing is found. Always check for -1 before you use the result as an
index, otherwise `substring(-1 + …)` and friends will surprise you.

`indexOf(x, fromIndex)` lets you find **every** occurrence. Counting how often `"an"` occurs in `"banana"`:

```java
String text = "banana";
int count = 0;
int at = text.indexOf("an");
while (at >= 0) {
    count++;
    at = text.indexOf("an", at + 1);   // continue AFTER the last match
}
// "banana": matches at 1 and 3 -> count == 2
```

(With `at + "an".length()` instead of `at + 1` you count only non-overlapping matches: `"aaaa"` contains `"aa"` three
times overlapping, but only twice without overlap.)

### 4.3 Cutting out a piece: `substring`

| method | example | result |
|---|---|---|
| `substring(begin)` | `s.substring(7)` | `"World"` (from begin to the end) |
| `substring(begin, end)` | `s.substring(0, 5)`, `s.substring(7, 12)` | `"Hello"`, `"World"` |

The most important rule of this whole lecture after `equals`:

> **`substring(begin, end)` includes `begin` but NOT `end`.** The piece has `end - begin` characters.

```
s.substring(7, 12)
 H   e   l   l   o   ,       W   o   r   l   d
                            [7] [8] [9] [10][11] | 12 = end, not included
                             └─── 12 - 7 = 5 characters ───┘
```

Why "end not included"? Because then everything fits together without `+ 1` and `- 1`:

- `s.substring(0, k) + s.substring(k)` is always the whole `s`.
- The first `k` characters are `s.substring(0, k)`, the last `k` are `s.substring(s.length() - k)`.
- `s.substring(i, i + 1)` is the one character at `i`, as a String.

Edge cases: `begin == end` gives `""` (legal), and `s.substring(s.length())` is `""` (legal, nothing is left).
But `begin > end`, a negative index or an index above `length()` throws `StringIndexOutOfBoundsException`.

### 4.4 Comparing

| method | example | result |
|---|---|---|
| `equals(other)` | `"Java".equals("Java")` | `true` |
| `equalsIgnoreCase(other)` | `"JAVA".equalsIgnoreCase("java")` | `true` |
| `compareTo(other)` | `"a".compareTo("b")` | negative (section 3) |
| `compareToIgnoreCase(other)` | `"B".compareToIgnoreCase("a")` | positive |

### 4.5 Making changed copies

| method | example | result |
|---|---|---|
| `toUpperCase()` / `toLowerCase()` | `"Java 17".toUpperCase()` | `"JAVA 17"` |
| `trim()` | `"  hi \n".trim()` | `"hi"` (removes spaces, tabs, newlines at both ends) |
| `strip()`, `stripLeading()`, `stripTrailing()` | `"  hi  ".stripLeading()` | `"hi  "` |
| `replace(char, char)` | `"banana".replace('a', 'o')` | `"bonono"` (**every** occurrence) |
| `replace(String, String)` | `"a-b-c".replace("-", ", ")` | `"a, b, c"` |
| `repeat(n)` | `"ab".repeat(3)`, `"-".repeat(0)` | `"ababab"`, `""` |
| `concat(String)` | `"Hi".concat("!")` | `"Hi!"` (same as `+`) |

`strip` is the modern version of `trim`: `trim` removes every character with a code up to 32 (space and control
characters), `strip` removes everything Unicode calls whitespace (for example the wide "em space"). For ordinary
spaces, tabs and newlines they do the same.

Be careful with `replaceAll` and `replaceFirst`: their first argument is a **regular expression**, a mini pattern
language where `.` means "any character". `"1.2.3".replaceAll(".", "-")` gives `"-----"`. If you mean plain text,
use `replace`.

### 4.6 Splitting and joining

| method | example | result |
|---|---|---|
| `split(regex)` | `"a,b,c".split(",")` | `{"a", "b", "c"}` |
| `split(" +")` | `"one   two".split(" +")` | `{"one", "two"}` (one or more spaces) |
| `split("\\s+")` | `"one\ttwo\nthree".split("\\s+")` | `{"one", "two", "three"}` (any whitespace) |
| `String.join(sep, parts...)` | `String.join(", ", "a", "b", "c")` | `"a, b, c"` |

`split` has the most surprises of any String method, so learn its rules:

| call | result | rule |
|---|---|---|
| `"a,b,,c".split(",")` | `{"a", "b", "", "c"}` | two separators in a row give an empty piece |
| `"a,b,,".split(",")` | `{"a", "b"}` | empty pieces at the **end** are dropped |
| `",a".split(",")` | `{"", "a"}` | an empty piece at the **start** is kept |
| `"".split(",")` | `{""}` | **one** piece: the empty string (length 1, not 0!) |
| `"a.b.c".split(".")` | `{}` | `.` is a regex meaning "any character"; write `split("\\.")` |
| `"a\|b".split("\\\|")` | `{"a", "b"}` | `\|` is special in regex too, so it needs `\\` as well |

The usual safe recipe for "words separated by spaces" is: `trim()` first (no empty piece at the start), handle the
empty string separately (no `{""}`), then `split(" +")`.

### 4.7 Converting

| method | example | result |
|---|---|---|
| `toCharArray()` | `"abc".toCharArray()` | `{'a', 'b', 'c'}`: a **new** array, changing it does not change the String |
| `new String(chars)` / `String.valueOf(chars)` | `new String(new char[]{'h', 'i'})` | `"hi"` |
| `new String(chars, offset, count)` | `new String(chars, 1, 2)` with `{'a','b','c','d'}` | `"bc"` |
| `String.valueOf(x)` | `String.valueOf(3.0)`, `String.valueOf(true)` | `"3.0"`, `"true"` |
| `Integer.parseInt(s)` | `Integer.parseInt("-42")`, `Integer.parseInt("+7")` | `-42`, `7` |
| `Double.parseDouble(s)` | `Double.parseDouble("2.5")` | `2.5` |
| `Integer.toString(n)`, `Integer.toBinaryString(n)` | `Integer.toBinaryString(10)` | `"1010"` |

`Integer.parseInt` throws a `NumberFormatException` for anything that is not exactly a number: `"12a"`, `""`,
`" 42"` (yes, the space!), and numbers too big for an `int` such as `"3000000000"`.

### 4.8 Formatting

`String.format` (and `System.out.printf`, and since Java 15 `"...".formatted(...)`) fill placeholders:

```java
String line = String.format("%-8s|%5d|%8.2f", "milk", 3, 1.5);
// "milk    |    3|    1.50"
```

| placeholder | meaning | example → result |
|---|---|---|
| `%s` | any value as text | `format("%s!", "hi")` → `"hi!"` |
| `%d` | whole number | `format("%d", 42)` → `"42"` |
| `%.2f` | decimal number, 2 places (rounded) | `format("%.2f", 3.14159)` → `"3.14"` |
| `%5d`, `%-8s` | minimum width 5 (right-aligned), 8 (`-` = left-aligned) | `format("[%5d]", 42)` → `"[   42]"` |
| `%05d` | pad with zeros | `format("%05d", 42)` → `"00042"` |
| `%c` | one char | `format("%c", 'x')` → `"x"` |
| `%n`, `%%` | a line break, a literal `%` | `format("100%%")` → `"100%"` |

Careful: `%.2f` may use a decimal **comma** on a computer with Slovak or German settings. Pass a locale
(`String.format(Locale.ROOT, ...)`) when the output must be the same everywhere.

---

## 5. Gluing strings with `+` and its hidden cost

### How `+` decides between adding and gluing

`+` **glues** (concatenates) as soon as one of its two operands is a String. It is evaluated **from left to right**,
one `+` at a time:

```java
System.out.println(1 + 2 + "3");     // "33"   (1 + 2 = 3, then 3 + "3")
System.out.println("1" + 2 + 3);     // "123"  ("1" + 2 = "12", then "12" + 3)
System.out.println("1" + (2 + 3));   // "15"   parentheses first
System.out.println('a' + 'b' + "c"); // "195c" two chars add up as numbers first
System.out.println("x" + null);      // "xnull"
```

Any object can be glued: Java calls its `toString()` (topic 07), which is why
`"Account: " + account` prints whatever `BankAccount.toString()` returns.

### Why `+=` in a loop is slow

Because a String can never change, every `+` builds a **new** String and copies **all** characters of both
parts into it. In a loop that adds a little at a time, each round copies everything built so far:

```java
String list = "";
for (int i = 1; i <= n; i++) {
    list += i + ", ";          // copies the WHOLE list built so far, every single round
}
```

If the list grows by about 7 characters per round, round 1 copies 7 characters, round 2 copies 14, round 3 copies
21, …, round n copies 7n. In total that is 7 · (1 + 2 + … + n) ≈ 3.5 · n² characters: the work grows with the
**square** of n. Double n, and it takes four times as long.

Measured on an ordinary laptop, building the list `"1, 2, 3, …, n"`:

| n | `+=` in a loop | `StringBuilder` |
|---|---|---|
| 50 000 | 2.4 seconds | 5 ms |
| 100 000 | 13.8 seconds | 9 ms |

The `StringBuilder` version is about **1 500 times faster** at n = 100 000, and the gap keeps growing. (The test
`fastEvenForAHugeList` in recap 11 gives your `numberList` 2 seconds for n = 100 000.)

A single expression like `"Name: " + name + ", age " + age` is fine: Java builds it in one step. Only repeated `+=`
on the same growing String in a loop is the problem.

---

## 6. `StringBuilder`: a string you can change

A `StringBuilder` holds characters in an internal array that is **bigger than needed**. Appending writes into the free
space at the end: nothing is copied. Only when the array is full does it get a new one, twice as big. Because the
size doubles, copying happens so rarely that an `append` costs about the same on average, no matter how long the
text already is.

```
StringBuilder sb = new StringBuilder();   // internal array of 16 free places
sb.append("Hi");                          // [H][i][ ][ ][ ][ ] ... length 2, capacity 16
sb.append('!');                           // [H][i][!][ ][ ][ ] ... length 3: written in place, no copy
String result = sb.toString();            // "Hi!" - a normal String again
```

### The methods

| method | example (each on a fresh `new StringBuilder("hello")`) | content afterwards |
|---|---|---|
| `append(x)` (any type) | `sb.append(' ').append(42)` | `"hello 42"` |
| `insert(index, x)` | `sb.insert(0, ">> ")` | `">> hello"` |
| `setCharAt(index, c)` | `sb.setCharAt(0, 'J')` | `"Jello"` |
| `deleteCharAt(index)` | `sb.deleteCharAt(4)` | `"hell"` |
| `delete(begin, end)` | `sb.delete(1, 3)` | `"hlo"` (end not included, like `substring`) |
| `replace(begin, end, str)` | `sb.replace(0, 1, "J")` | `"Jello"` |
| `reverse()` | `sb.reverse()` | `"olleh"` |
| `setLength(n)` | `sb.setLength(2)` | `"he"` (cuts off the rest; `setLength(0)` empties it) |
| `length()`, `charAt(i)`, `indexOf(str)` | `sb.indexOf("l")` | (nothing changes) → `2` |
| `toString()` | `String s = sb.toString();` | (nothing changes) → `"hello"` |

`append`, `insert`, `delete` and `reverse` return the builder itself, so calls can be **chained**:

```java
String line = new StringBuilder()
        .append("x = ").append(3)
        .append(", ok = ").append(true)
        .toString();                      // "x = 3, ok = true"
```

### Building a list with separators (the "fence post" problem)

A list of n items has only n - 1 separators, like a fence with n posts and n - 1 rails:

```
 post  rail  post  rail  post
  a     |     b     |     c        3 items, 2 separators: "a | b | c"
```

So "append an item, append a separator" in every round gives one separator too many (`"a | b | c | "`). Two
common ways to get it right:

1. **Separator before every item except the first.** The first round is the special one: only from the second
   item on does a separator go in front. (How can the loop tell that it is not in the first round?)
2. **Separator after every item, then cut the last one off** with `setLength(length() - …)`. Careful: when there are
   no items at all, there is nothing to cut off.

When you already have the items in an array of Strings, `String.join(" | ", items)` does it in one call.

### `StringBuilder` traps

```java
StringBuilder a = new StringBuilder("hi");
StringBuilder b = new StringBuilder("hi");
a.equals(b);                          // false !  StringBuilder does NOT compare content
a.toString().equals(b.toString());    // true
"hi".contentEquals(a);                // true: compares a String with a builder's content

new StringBuilder('x');               // EMPTY builder! 'x' is turned into the int 120 = the capacity
new StringBuilder("x");               // what you meant

sb.append('a' + 'b');                 // appends "195": the chars were added as numbers first
```

### Which one when?

| situation | use |
|---|---|
| a fixed text, a key, a name, a value to compare | `String` |
| a few pieces glued once: `"Hi " + name + "!"` | `String` with `+` |
| text built piece by piece in a loop | `StringBuilder` |
| text edited in place (insert, delete, reverse, set characters) | `StringBuilder` |
| an array of pieces with one separator | `String.join` |

(`StringBuffer` is an older twin of `StringBuilder` that is safe to share between threads but slower. You do not
need it in this course.)

---

## 7. Strings in decisions: `switch`, `null` and safe comparisons

### `switch` on a String

A `switch` can choose by String content (inside, it compares with `equals`, not `==`). Since Java 14 a `switch`
can also be an **expression** that produces a value:

```java
static String dayType(String day) {
    return switch (day.toLowerCase()) {
        case "saturday", "sunday" -> "weekend";
        case "monday", "tuesday", "wednesday", "thursday", "friday" -> "weekday";
        default -> "unknown";
    };
}
// dayType("Sunday") == "weekend", dayType("Holiday") == "unknown"
```

The classic form `case "saturday": … break;` works with Strings as well. A `switch` on a `null` String throws a
`NullPointerException`, so check for `null` first.

### Comparing when one side might be `null`

```java
if (answer.equals("yes"))      // crashes if answer is null
if ("yes".equals(answer))      // safe: the literal is never null, and equals(null) is simply false
if (answer != null && answer.equals("yes"))   // also safe: && stops early (topic 02)
```

### Keep the result of a "change"

Because Strings are immutable, a line like `name.trim();` does nothing useful. IntelliJ even underlines it
("Result of 'String.trim()' is ignored"). Keep the result:

```java
name = name.trim();                 // reuse the variable
String clean = name.trim();         // or give it a new name
```

---

## 8. Problem-solving patterns, with worked examples

Almost every string puzzle is built from a handful of patterns. Each one below is shown with a complete,
worked example. **None of these examples is an exercise**, but every exercise uses one or more of the patterns, so
make sure you understand each example line by line, then trace it on paper with a short input.

### 8.1 Walk the characters

Loop over the indexes, look at `charAt(i)`, decide something for each character.

*Example: count the vowels.*

```java
static int countVowels(String text) {
    int count = 0;
    for (int i = 0; i < text.length(); i++) {
        char c = Character.toLowerCase(text.charAt(i));
        if ("aeiou".indexOf(c) >= 0) {   // a small trick: "is c one of these characters?"
            count++;
        }
    }
    return count;
}
// countVowels("Programming in Java") == 6
```

The `"aeiou".indexOf(c) >= 0` trick replaces five comparisons (`c == 'a' || c == 'e' || …`).

### 8.2 Build a new string

Create a `StringBuilder`, append to it while walking the input, return `toString()`.

*Example: swap upper and lower case.*

```java
static String toggleCase(String text) {
    StringBuilder result = new StringBuilder(text.length()); // we know the final length
    for (int i = 0; i < text.length(); i++) {
        char c = text.charAt(i);
        if (Character.isUpperCase(c)) {
            result.append(Character.toLowerCase(c));
        } else if (Character.isLowerCase(c)) {
            result.append(Character.toUpperCase(c));
        } else {
            result.append(c);                            // digits, spaces, ... unchanged
        }
    }
    return result.toString();
}
// toggleCase("Hello World 2") == "hELLO wORLD 2"
```

### 8.3 Count with an array (a frequency table)

When you need "how many of each character", use an `int` array with one counter per possible character and
`c - 'a'` as the index. No maps needed.

*Example: the most frequent letter (on a tie, the one earlier in the alphabet).*

```java
static char mostFrequentLetter(String text) {
    int[] counts = new int[26];                // counts[0] for 'a', ..., counts[25] for 'z'
    for (int i = 0; i < text.length(); i++) {
        char c = Character.toLowerCase(text.charAt(i));
        if (c >= 'a' && c <= 'z') {
            counts[c - 'a']++;
        }
    }
    int best = 0;
    for (int letter = 1; letter < 26; letter++) {
        if (counts[letter] > counts[best]) {   // > (not >=) keeps the earlier letter on a tie
            best = letter;
        }
    }
    return (char) ('a' + best);                // index back to a letter
}
// mostFrequentLetter("Mississippi") == 'i'   (i and s both appear 4 times; i comes first)
```

```
"Mississippi" →  counts:  i=4  m=1  p=2  s=4  (all others 0)
```

For any character (not only a–z) use `new int[128]` (ASCII) and the char itself as the index: `counts[c]++`.

### 8.4 Two pointers

Two indexes move through the text(s), each at its own speed, often from both ends towards the middle or through
two strings at once.

*Example: is `small` a subsequence of `big`? (Its characters appear in `big` in the same order, not necessarily next
to each other: `"ace"` is a subsequence of `"abcde"`, `"aec"` is not.)*

```java
static boolean isSubsequence(String small, String big) {
    int i = 0;                                          // the next char of small we are looking for
    for (int j = 0; j < big.length() && i < small.length(); j++) {
        if (big.charAt(j) == small.charAt(i)) {
            i++;                                        // found it: look for the next one
        }
    }
    return i == small.length();                         // did we find all of them?
}
```

```
small = "ace", big = "abcde"
j: 0 'a' = 'a' → i = 1 | 1 'b' ≠ 'c' | 2 'c' = 'c' → i = 2 | 3 'd' ≠ 'e' | 4 'e' = 'e' → i = 3 = length ✔
```

### 8.5 Sliding window

Keep a "window" `text[start … end]` that always satisfies a rule. Move `end` forward one step at a time; when the
window breaks the rule, move `start` forward until it is valid again. Every character enters the window once and
leaves it at most once, so the whole thing is fast (linear), even though there are two loops.

*Example: the length of the longest piece without a repeated character.* `"abcabcbb"` → 3 (`"abc"`),
`"bbbbb"` → 1, `"pwwkew"` → 3 (`"wke"`).

```java
static int longestWithoutRepeats(String text) {
    boolean[] inWindow = new boolean[128];     // which ASCII characters are in the window right now
    int best = 0;
    int start = 0;
    for (int end = 0; end < text.length(); end++) {
        char c = text.charAt(end);
        while (inWindow[c]) {                  // c is already inside: shrink from the left
            inWindow[text.charAt(start)] = false;
            start++;
        }
        inWindow[c] = true;                    // now c can join
        best = Math.max(best, end - start + 1);
    }
    return best;
}
```

| end | char | window before | action | window after | best |
|---|---|---|---|---|---|
| 0 | a | `""` | add | `a` | 1 |
| 1 | b | `a` | add | `ab` | 2 |
| 2 | c | `ab` | add | `abc` | 3 |
| 3 | a | `abc` | `a` is inside → drop `a` | `bca` | 3 |
| 4 | b | `bca` | drop `b` | `cab` | 3 |
| 5 | c | `cab` | drop `c` | `abc` | 3 |
| 6 | b | `abc` | drop `a`, drop `b` | `cb` | 3 |
| 7 | b | `cb` | drop `c`, drop `b` | `b` | 3 |

### 8.6 Read a number digit by digit (parsing)

Reading `"4096"` as the number 4096: start with 0 and, for every digit, **shift what you have one place to the left
and add the new digit**. This is how `Integer.parseInt` works inside.

```java
static int parseDigits(String digits) {
    int value = 0;
    for (int i = 0; i < digits.length(); i++) {
        char c = digits.charAt(i);
        if (c < '0' || c > '9') {
            throw new NumberFormatException("Not a digit: " + c);
        }
        value = value * 10 + (c - '0');
    }
    return value;
}
```

```
"4096":  0 → 0·10 + 4 = 4 → 4·10 + 0 = 40 → 40·10 + 9 = 409 → 409·10 + 6 = 4096
```

Scanning with an index that **you** move (a `while` loop instead of a `for`) lets you read "a piece at a time": a
word, then a number, then a word…, which is how compilers read your Java code.

### 8.7 Look for the "aha": a trick that makes it short

Some problems become easy once you see them from the right side.

*Example: is `b` a rotation of `a`?* (`"erbottlewat"` is `"waterbottle"` with `"wat"` moved from the front to the
back.) Instead of trying every rotation: every rotation of `a` appears **inside `a + a`**.

```java
static boolean isRotation(String a, String b) {
    return a.length() == b.length() && (a + a).contains(b);
}
// a + a = "waterbottlewaterbottle" contains "erbottlewat" → true
```

The length check matters: `"wat"` is inside `a + a` too, but it is not a rotation.

### 8.8 A stack: "the last one in is the first one out"

A stack is like a pile of plates: you only ever put a plate on top (**push**) or take the top plate off (**pop**).
With a `char[]` and a counter `size`, a stack takes three lines: the top element is `stack[size - 1]`, push is
`stack[size] = c; size++;`, pop is `size--;`. Stacks solve everything where the **most recent** unfinished thing
matters first: undo, nested structures, going back.

*Example: apply backspaces. In the typed text, `#` deletes the character before it:* `"abc#d##e"` → `"ae"`.

```java
static String applyBackspaces(String typed) {
    char[] stack = new char[typed.length()];   // never more characters than were typed
    int size = 0;
    for (int i = 0; i < typed.length(); i++) {
        char c = typed.charAt(i);
        if (c == '#') {
            if (size > 0) {
                size--;                         // pop: forget the most recent character
            }
        } else {
            stack[size] = c;                    // push
            size++;
        }
    }
    return new String(stack, 0, size);          // the first size characters of the array
}
```

```
a → [a]   b → [a b]   c → [a b c]   # → [a b]   d → [a b d]   # → [a b]   # → [a]   e → [a e]   →  "ae"
```

### Which pattern do I need?

| the task says… | try |
|---|---|
| "for every character…", "count …", "is there a …" | 8.1 walk the characters |
| "return a new string where…" | 8.2 build with a `StringBuilder` |
| "same letters", "how many of each", "most frequent" | 8.3 counting array |
| "palindrome", "compare from both ends", "merge two texts" | 8.4 two pointers |
| "longest / shortest piece that…" | 8.5 sliding window, or "grow from every possible centre" |
| "numbers inside text", "a format like `a3b12`" | 8.6 parse with your own index |
| "nested", "matching pairs", "undo" | 8.8 stack |
| a very short solution feels possible | 8.7 look for the trick, then check the edge cases |

Then, for **every** puzzle, test these inputs in your head before you run anything: the empty string, one character,
everything the same (`"aaaa"`), the interesting part at the very start and at the very end, and upper/lower case.

---

## 9. Going deeper: Unicode, locales and what is inside a String

You can pass the exercises without this section. Read it to understand what real programs run into.

### A `char` is not always a whole character

`char` has 16 bits, enough for 65 536 codes. Unicode has more than a million possible characters (emoji, historic
scripts, …). Characters beyond the first 65 536 are stored as **two** `char` values, a *surrogate pair*:

```java
String smile = "😀";
smile.length();                               // 2 !  two chars
smile.codePointCount(0, smile.length());      // 1    one real character (code point)
smile.charAt(0);                              // half of the emoji, not a printable character
```

So reversing `"a😀"` char by char breaks the emoji. Programs that must handle any text work with *code points*
(`codePointAt`, `codePoints()`) instead of chars. In this course, every input is plain ASCII, so `char` is enough.

### Upper and lower case depend on the language

```java
"straße".toUpperCase();     // "STRASSE": one ß becomes two letters, so the length changes!
"TITLE".toLowerCase();      // "title" ... except on a computer set to Turkish: "tıtle" (dotless ı)
```

When the result must not depend on the computer's language settings (file names, commands, keys), write
`toLowerCase(Locale.ROOT)`. For comparing words, `equalsIgnoreCase` is usually the simpler choice.

### What is inside a String object

Since Java 9, a String stores its characters in a `byte[]` plus a flag called the *coder*. If all characters fit into
one byte (Latin-1, which covers English and most Western European text), each takes 1 byte; otherwise every
character takes 2 bytes (UTF-16). This *compact strings* trick halves the memory for most text. It is invisible to
your code: `charAt` and `length` hide it completely.

Two more internals worth knowing:

- `substring` **copies** the characters it returns (it costs time proportional to the length of the piece).
  Very old Java versions shared the array instead, which sometimes kept huge texts alive in memory by accident.
- `hashCode()` is computed from all characters (`s[0]·31^(n-1) + s[1]·31^(n-2) + … + s[n-1]`) the first time you
  ask, then remembered inside the object. Immutability is what makes remembering it safe.

---

## 10. Common mistakes

| symptom | cause | fix |
|---|---|---|
| a comparison is `false` although both texts look identical | `a == b` compares addresses | `a.equals(b)` |
| a method "does nothing" | `s.toUpperCase();` result ignored (Strings are immutable) | `s = s.toUpperCase();` |
| `cannot find symbol: length` | `s.length` without parentheses (that is the array version) | `s.length()` |
| `StringIndexOutOfBoundsException` at the end | `charAt(s.length())` or a loop with `i <= s.length()` | the last index is `length() - 1` |
| a substring is one character too short | forgot that `end` is not included | `substring(begin, begin + count)` |
| prints `195` instead of `ab` | `'a' + 'b'` adds codes | start with a String: `"" + 'a' + 'b'` |
| a digit counts as 55 | added the char code of `'7'` | `c - '0'` |
| a word count of 1 for `""` | `"".split(" ")` is `{""}` | handle empty / blank input first |
| an empty first word | leading spaces before `split` | `trim()` first |
| `split(".")` returns an empty array | `.` is a regular expression | `split("\\.")` |
| `NullPointerException` on `x.equals("…")` | `x` is `null` | `"…".equals(x)` or check `x != null` first |
| very slow with large inputs | `+=` on a String in a loop | `StringBuilder` |
| two equal builders are not `equals` | `StringBuilder` does not override `equals` | compare `toString()` results |
| `new StringBuilder('x')` is empty | the char became the capacity (an int) | `new StringBuilder("x")` |
| changing `chars[i]` did not change the String | `toCharArray()` returns a copy | build a new String: `new String(chars)` |
| `"Zebra"` sorted before `"apple"` | `compareTo` puts upper case first | `compareToIgnoreCase` |

---

## 11. Cheat sheet: speed of the common operations

n is the length of the string (m the length of the other string).

| operation | cost | comment |
|---|---|---|
| `length()`, `charAt(i)` | instant (O(1)) | the characters are in an array inside |
| `isEmpty()` | instant | |
| `equals`, `compareTo` | up to O(n) | stops at the first difference; different lengths → `equals` is instant |
| `indexOf`, `contains` (a String) | up to O(n · m) | in practice usually close to O(n) |
| `substring(b, e)` | O(e - b) | copies the piece |
| `toUpperCase`, `trim`, `replace`, `+` | O(n) | build a new String |
| `split` | O(n) | plus one new String per piece |
| `s += x` in a loop of n rounds | **O(n²)** | copies everything every round |
| `StringBuilder.append` | O(1) on average | the buffer doubles when full |
| `StringBuilder.insert(0, …)`, `deleteCharAt(0)` | O(n) | everything after the position shifts |
| `StringBuilder.toString()` | O(n) | copies the characters into a new String |

---

## 12. Check yourself

Answer on paper first, then check with the answers (and with IntelliJ when you disagree).

1. What does this print?
   ```java
   String s = "java";
   s.toUpperCase();
   System.out.println(s);
   ```
2. `"hel" + "lo" == "hello"` is `true`, but with `String part = "hel";` the expression `part + "lo" == "hello"` is
   `false`. Why? And what should you write instead?
3. What do `System.out.println('a' + 'b' + "c");` and `System.out.println("c" + 'a' + 'b');` print?
4. What is `"banana".indexOf("na", 3)`?
5. What is `"Hello".substring(1, 3)`, and how long is it?
6. How many elements has `"a,,b,".split(",")`? And `"".split(",")`?
7. Is `"Zoo".compareTo("apple")` negative or positive? And `"Zoo".compareToIgnoreCase("apple")`?
8. What is `(char) ('a' + 25)`? What is `'7' - '0'`? What is `'7' + 0`?
9. Which of these throws an exception: `"abc".substring(3)` or `"abc".charAt(3)`?
10. Why does `s += i;` in a loop of n rounds take time proportional to n², while `sb.append(i);` does not?
11. What is `new StringBuilder("ab").equals(new StringBuilder("ab"))`?
12. What is `"😀".length()`?
13. `String a = new String("x"); String b = a; a = a + "y";` What is `b` now? How many String objects with
    content `"x"` exist (not counting the pool)?
14. Write one line that tests whether the String `word` (never `null`) starts and ends with the same character.
    What must you check first?

<details><summary>Answers</summary>

1. `java`. `toUpperCase` returned a new String, which was thrown away.
2. `"hel" + "lo"` is a constant expression: the compiler computes it and uses the pooled `"hello"`, so `==` finds the
   same object. `part + "lo"` is computed while the program runs and creates a new object, so `==` (which compares
   addresses) is `false`. Write `(part + "lo").equals("hello")`.
3. `195c` (97 + 98 = 195 first, then glued to `"c"`) and `cab` (the String comes first, so everything is glued).
4. `4`. The search starts at index 3 (`'a'`) and finds `"na"` at 4.
5. `"el"` (indexes 1 and 2; 3 is not included), length 3 - 1 = 2.
6. `{"a", "", "b"}`: 3 elements (the empty piece at the end is dropped, the one in the middle is kept).
   `"".split(",")` is `{""}`: 1 element.
7. Negative (`'Z'` is 90, `'a'` is 97, so `"Zoo"` comes first). Ignoring case it is positive: z comes after a.
8. `'z'`; `7`; `55` (an int: the code of `'7'`).
9. `"abc".charAt(3)` throws. `"abc".substring(3)` is `""`, because `substring(length())` is allowed.
10. Each `+=` creates a new String and copies everything built so far: 1 + 2 + … + n pieces ≈ n²/2 copies. A
    `StringBuilder` writes into free space at the end of its array and only rarely (when it doubles) copies.
11. `false`. `StringBuilder` inherits `equals` from `Object`, which compares addresses.
12. `2`: the emoji is stored as a surrogate pair of two chars.
13. `b` is still `"x"`: `a + "y"` created a new object `"xy"` and moved only `a`'s arrow. One object `"x"` (the one
    created with `new`, still referenced by `b`).
14. `word.length() > 0 && word.charAt(0) == word.charAt(word.length() - 1)`. The empty string has no first character,
    so check the length first (the `&&` stops before `charAt` runs).
</details>

---

## 13. Going further

Optional challenges for when exercise 11 is green. No tests: make up your own examples and check them.

1. **Write `indexOf` yourself.** `static int find(String text, String pattern)` without using `indexOf`, `contains`
   or `regionMatches`. The simple solution tries every starting position (O(n · m)). Then read about the
   *Knuth–Morris–Pratt* algorithm, which never looks at a character of `text` twice.
2. **Title case.** `"the lord of the rings"` → `"The Lord of the Rings"`: capitalise every word except short ones
   (`of`, `the`, `a`, `and`), but always capitalise the first word.
3. **Count distinct substrings** of a short word (`"aba"` has 5: `a, b, ab, ba, aba`). How fast does your solution get
   for a word of length 1 000?
4. **Roman numerals.** Convert `1994` to `"MCMXCIV"` and back. Which pattern from section 8 does the "back" direction
   use?
5. **Regular expressions.** Look up `java.util.regex.Pattern` and write one pattern that checks whether a String is a
   valid Slovak car plate such as `KE123AB`. (Regular expressions are what `split` and `replaceAll` understand.)
6. **Unicode-safe reverse.** Reverse `"a😀b"` correctly to `"b😀a"` using `codePointAt` and `Character.charCount`.

---

**Next step:** do recap 11 (`StringBasics`), then exercise 11 (`StringPuzzles`). The
[topic 11 guide](../diagrams/topic-11-guide.md) walks you through every method with hints, without giving away the
code.
