# Diagrams

One picture per topic, showing what Java actually does in memory. Start with **00** (the map), then read each
diagram before or while you work on its exercises. Click an image to open it in full size.

Every diagram uses the same colours:
**blue** = Stack (method frames, local variables), **violet** = Heap (objects, arrays) and the arrows into it,
**gray** = Java code, **orange** = a question / condition or a copied value, **green** = true / valid,
**red** = false / error, **teal** = a return value, **yellow hand-drawn notes** = explanations.

| # | Diagram | Practise with |
|---|---------|---------------|
| 00 | [Java learning map](#00--java-learning-map) | |
| 01 | [Variables & primitive types](#01--variables-primitive-types--memory) | recap `exercise01/BasicExpressions` |
| 02 | [Conditions & short-circuit](#02--conditions--short-circuit) | recap `exercise02/Conditionals` |
| 03 | [Loops](#03--loops) | recap `exercise03/LoopPractice` |
| 04 | [Methods & the call stack](#04--methods--the-call-stack) | recap `exercise04/MethodDecomposition` |
| 05 | [Arrays](#05--arrays) | recap `exercise05/ArrayBasics`, `exercise05/ArrayStats` |
| 06 | [2D arrays](#06--2d-arrays) | `exercise06/MatrixOps` |
| 07 | [Classes, objects & encapsulation](#07--classes-objects--encapsulation) | `exercise07/BankAccount` |
| 08 | [Pass-by-value & references](#08--pass-by-value--references) | `exercise08/Player`, `TeamRoster` |
| 09 | [Inheritance & dynamic dispatch](#09--inheritance--dynamic-dispatch) | `exercise09` smart home |
| 10 | [Recursion & linked structures](#10--recursion--linked-structures) | `exercise10/RecursiveUtils` |

**How to study with them:** cover the right half of a diagram and predict the result before you read the notes.
Trace with a finger, one arrow at a time. Then answer the questions under the diagram, and only then open the answers.

---

## 00 · Java learning map
![Java learning map](00-roadmap.png)

## 01 · Variables, primitive types & memory
![Variables, primitive types and memory](01-variables-primitives.png)

**Check yourself**
1. What does `System.out.println(7 / 2);` print? And `7 / 2.0`?
2. `double d = 1 / 4;` What is in `d`?
3. What is `7 % 3`?

<details><summary>Answers</summary>

1. `3` (int ÷ int cuts the fraction off), then `3.5`.
2. `0.0`: `1 / 4` is computed as int first (`0`), only then widened.
3. `1`, the remainder that integer division threw away.
</details>

## 02 · Conditions & short-circuit
![Conditions and short-circuit evaluation](02-conditions-short-circuit.png)

**Check yourself**
1. What do `describeTemperature(20)` and `describeTemperature(-5)` return?
2. With `user = null`, does `if (user != null || user.isActive())` crash?

<details><summary>Answers</summary>

1. `"warm"` (20 >= 20 is true, so the boundary belongs to "warm"), and `"freezing"` (no test is true, so the `else` runs).
2. Yes. `false || …` does NOT know the answer yet, so `user.isActive()` runs on `null` → NullPointerException.
</details>

## 03 · Loops
![The four phases of a for loop and nested loops](03-loops.png)

**Check yourself**
1. How many times does the body of `for (int i = 5; i < 3; i++)` run?
2. What does `for (int i = 0; i <= 3; i++) System.out.print(i);` print?
3. An outer loop of 4 rounds with an inner loop of 5 rounds: how many times does the inner body run?

<details><summary>Answers</summary>

1. 0 times: the condition is checked first and is false right away.
2. `0123`: `<=` includes 3.
3. 4 × 5 = 20.
</details>

## 04 · Methods & the call stack
![Methods and the call stack](04-call-stack.png)

**Check yourself**
1. If `cylinderVolume` did `radius = 0;`, what would `r` in `main` be?
2. While `square` runs, how many frames are on the stack?

<details><summary>Answers</summary>

1. Still `2.0`: `radius` is a copy.
2. Three: `main`, `cylinderVolume`, `square`.
</details>

## 05 · Arrays
![Arrays in memory and index bounds](05-arrays.png)

**Check yourself**
1. `int[] a = new int[5];` What is `a.length` and the last valid index?
2. `boolean[] b = new boolean[2];` What is `b[1]`?
3. `int[] x = {1, 2, 3}; int[] y = x; y[0] = 99;` What is `x[0]`?

<details><summary>Answers</summary>

1. `5`, and index `4`.
2. `false` (the default value).
3. `99`: `y = x` copies the arrow, so both variables point at the SAME array (see diagram 08).
</details>

## 06 · 2D arrays
![2D arrays as arrays of arrays](06-2d-arrays.png)

**Check yourself**
1. `int[][] m = new int[4][7];` What are `m.length` and `m[0].length`?
2. With the nested loop from section B, is `m[0][1]` or `m[1][0]` visited first?

<details><summary>Answers</summary>

1. `4` rows, `7` cells per row.
2. `m[0][1]`: the whole row 0 is finished before row 1 starts.
</details>

## 07 · Classes, objects & encapsulation
![Classes, objects and encapsulation](07-classes-objects.png)

**Check yourself**
1. Why does `student1.age = 30;` not compile in `main`?
2. After `student1.setAge(-3);` what is the age?
3. `Student a = new Student("X", 1); Student b = a;` How many Student objects exist?

<details><summary>Answers</summary>

1. `age` is `private`: only code inside `Student` may touch it.
2. Unchanged: the check `if (age > 0)` refuses it.
3. One. `b = a` copies the arrow, not the object.
</details>

## 08 · Pass-by-value & references
![Pass-by-value for primitives and references](08-pass-by-value.png)

**Check yourself**
1. `static void inc(int n) { n++; }` After `int k = 1; inc(k);` what is `k`?
2. `static void rename(Person q) { q.setName("Bob"); }` After `rename(p);` what is `p.getName()`?
3. Does `static void swap(Person a, Person b) { Person t = a; a = b; b = t; }` swap the caller's variables?

<details><summary>Answers</summary>

1. `1`: `n` was a copy.
2. `"Bob"`: `q` holds a copy of the address, so it changes the same object.
3. No. It only swaps the method's own copies of the arrows.
</details>

## 09 · Inheritance & dynamic dispatch
![Inheritance, overriding and dynamic dispatch](09-inheritance-dispatch.png)

**Check yourself**
1. `Shape s = new Rectangle(2, 3);` What does `s.getArea()` return?
2. Only `Circle` has `getRadius()`. Does `Shape s = new Circle(1); s.getRadius();` compile?
3. Can you write `new Shape()`?

<details><summary>Answers</summary>

1. `6.0`: the real object is a Rectangle, so its version runs.
2. No. The declared type `Shape` decides what you may call, and Shape has no `getRadius()`.
3. No, `Shape` is abstract.
</details>

## 10 · Recursion & linked structures
![Recursion on the call stack and a linked list](10-recursion-linked.png)

**Check yourself**
1. What is `power(3, 2)`, and how many `power` frames are on the stack at the deepest point?
2. What happens if you delete the base case?
3. What does `length` return for a list with one node?

<details><summary>Answers</summary>

1. `9`, and 3 frames: `power(3, 2)`, `power(3, 1)`, `power(3, 0)`.
2. The calls never stop (n goes 0, -1, -2, …) → `StackOverflowError`.
3. `1`: `1 + length(null)` = `1 + 0`.
</details>
