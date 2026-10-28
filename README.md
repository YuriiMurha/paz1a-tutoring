# PAZ1a Tutoring

A practice project for **PAZ1a** at UPJŠ. Each exercise is a Java class whose methods you implement
(look for `// TODO: Implement this`), plus a JUnit 5 test class that tells you when your solution works.

**Stuck on a concept?** The [diagrams](diagrams/README.md) show each topic as a picture of what happens in memory.

**Recap exercises** are warm-ups for every topic (package `sk.upjs.paz1a.tutoring.recap`):

| #  | Topic | You implement (in `src/main/java/sk/upjs/paz1a/tutoring/recap/`) | Tests |
|----|-------|-------------------------------------------------------------------|-------|
| 01 | Variables, primitives & operators | `exercise01/BasicExpressions` | `BasicExpressionsTest` |
| 02 | Conditionals & branching | `exercise02/Conditionals` | `ConditionalsTest` |
| 03 | Loops & iteration | `exercise03/LoopPractice` | `LoopPracticeTest` |
| 04 | Methods & scope | `exercise04/MethodDecomposition` | `MethodDecompositionTest` |
| 05 | 1D array fundamentals | `exercise05/ArrayBasics`, `exercise05/ArrayChallenges` | `ArrayBasicsTest`, `ArrayChallengesTest` |
| 06 | 2D arrays | `exercise06/GridBasics` | `GridBasicsTest` |
| 07 | Classes & encapsulation | `exercise07/Rectangle` | `RectangleTest` |
| 08 | References & pass-by-value | `exercise08/Point`, `exercise08/PointTools` | `PointToolsTest` |
| 09 | Inheritance & dynamic dispatch | `exercise09/Shape`, `Circle`, `Square`, `RightTriangle`, `ShapeTools` | `ShapesTest` |
| 10 | Recursion & linked lists | `exercise10/RecursionBasics` (uses `Node`) | `RecursionBasicsTest` |

**Main exercises** are the harder tasks for topics 05–10 (package `sk.upjs.paz1a.tutoring`):

| #  | Topic | You implement (in `src/main/java/sk/upjs/paz1a/tutoring/`) | Tests |
|----|-------|------------------------------------------------------------|-------|
| 05 | 1D arrays | `exercise05/ArrayStats` | `ArrayStatsTest` |
| 06 | 2D arrays & matrices | `exercise06/MatrixOps` | `MatrixOpsTest` |
| 07 | Classes & encapsulation | `exercise07/BankAccount` | `BankAccountTest` |
| 08 | References & objects | `exercise08/Player`, `exercise08/TeamRoster` | `TeamRosterTest` |
| 09 | Inheritance & polymorphism | `exercise09/Device`, `Light`, `Thermostat`, `SmartHomeHub` | `SmartHomeTest` |
| 10 | Recursion | `exercise10/RecursiveUtils` | `RecursiveUtilsTest` |

## 1. Open the project in IntelliJ IDEA

1. **File → Open…**, select the `paz1a-tutoring` folder (the one containing `pom.xml`) and click
   **Trust Project**. IntelliJ recognises Maven and downloads JUnit by itself.
2. The project needs **JDK 17 or newer**. If the code is red everywhere or IntelliJ asks for an SDK, go to
   **File → Project Structure → Project → SDK** and pick one, or choose **Download JDK…**.
3. If the `org.junit` imports stay red, open the **Maven** tool window (right edge) and click
   **Reload All Maven Projects**.

## 2. Run the tests

**In IntelliJ:** open a test class such as `BasicExpressionsTest` and click the green ▶ next to the class
name (all its tests) or next to one test method. Right-click a package in `src/test/java` (for example
`recap`) → **Run 'Tests in …'** runs all tests below it.

**In a terminal**, inside the `paz1a-tutoring` folder:

```bash
mvn test                              # everything
mvn test -Dtest="**/recap/**"         # only the recap
mvn test -Dtest=BasicExpressionsTest  # one exercise
```

No Maven installed? Use the included Maven Wrapper the same way: `.\mvnw.cmd test` on Windows,
`./mvnw test` on macOS/Linux. It downloads Maven on first use and needs `JAVA_HOME` set to JDK 17+.

> At the start almost every test is red. That's expected: your job is to turn them green, one by one.

## 3. Work through the exercises

Go topic by topic. Topics 01–04 have only a recap; for 05–10 do the **recap first** as a warm-up, then the
**main exercise** with the same number. For each exercise:

1. Read the class. The Javadoc above each method says exactly what to return and which inputs to reject.
2. Run its test class and read the failure messages.
3. Implement **one method at a time** and re-run the tests after each change.
4. When the whole test class is green, move on to the next exercise.

Ground rules: don't edit the tests, don't change method signatures, and write your own loops for the
array exercises (no `java.util.Arrays`, no streams).

| Exercise | What to watch out for |
|----------|-----------------------|
| **Recap 01** `BasicExpressions` | `5 / 9` is `0`, because int ÷ int gives an int, so make one operand a `double`. `%` keeps the sign: `-3 % 2 == -1`, `-123 % 10 == -3`. |
| **Recap 02** `Conditionals` | Write `if / else if` chains from the highest grade down. Most bugs sit exactly on a boundary (90, age 6, age 65). Discounts don't add up: only the first matching rule counts. |
| **Recap 03** `LoopPractice` | Check the first and the last iteration (`<` vs `<=`). `countDigits` must work for 0 and for negative numbers. Make sure `isPrime` really tries divisors up to the square root. |
| **Recap 04** `MethodDecomposition` | Implement the small private helpers first, then build the public methods from them. Local variables live only inside their method. |
| **Recap 05** `ArrayBasics` | `null` is rejected, but an empty array is a valid input with a natural answer. `reverseArray` returns a *new* array. |
| **Recap 05** `ArrayChallenges` | Eight extra tasks from (\*) easy to (\*\*\*) hard; go in order. Methods that return an array build a *new* one. `rotateLeft` on an empty array: `x % 0` throws. |
| **Recap 06** `GridBasics` | `grid[r]` is a whole row and rows may differ in length, so use `grid[r].length`. For column sums, swap the loops: columns outside, rows inside. |
| **Recap 07** `Rectangle` | Store only width and height and compute the rest from them, so nothing gets out of date. Keep the checks in the setters. |
| **Recap 08** `Point`, `PointTools` | Read the first test: Java passes a *copy of the reference*. A shallow copy shares the points; a deep copy needs `copy()` for each one. |
| **Recap 09** shapes | Write `area()`/`perimeter()` in each subclass and `isLargerThan` once in `Shape`. `ShapeTools` uses only the `Shape` type, so no `instanceof`. |
| **Recap 10** `RecursionBasics` | A list is `null` or a node followed by a shorter list: base case `head == null`, recursive step on `head.getNext()`. No loops. |
| **05** `ArrayStats` | Input checks are already written, so read them. Beware of integer division (`5 / 2 == 2`) and `int` overflow when summing. `filterPositive` needs two passes: count first, then copy. |
| **06** `MatrixOps` | `matrix.length` is the number of rows, `matrix[0].length` the number of columns. This time **you** write the input checks (see `@throws`). |
| **07** `BankAccount` | Fields stay `private`, and the balance changes only through `deposit`/`withdraw`. `toString()` must match IntelliJ's **Code → Generate → toString()** output. |
| **08** `Player`, `TeamRoster` | `=` copies a *reference*, not an object. Write the `Player` copy constructor first, then use it in `createDeepCopyRoster()`. |
| **09** smart home | Subclasses call `super(id)` and use `getId()`/`isOn()`, because `Device`'s fields are private. `SmartHomeHub` must work for every `Device`, so no `instanceof`. |
| **10** `RecursiveUtils` | Write the base case first, then the recursive step. Loops are forbidden, and a test checks for them. |

## Troubleshooting

| Message | Fix |
|---------|-----|
| `release version 17 not supported` | Maven is running on an older Java. Point `JAVA_HOME` (terminal) or the project SDK (IntelliJ) to JDK 17+. |
| `package org.junit.jupiter.api does not exist` | Maven tool window → **Reload All Maven Projects**. |
| `solvedWithoutLoops` fails with *Cannot find …java* | Run the tests from the project folder (IntelliJ and Maven do that by default). |
