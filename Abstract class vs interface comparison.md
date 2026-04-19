# Abstract Class vs Interface 完整比較表

---

## 📊 核心特性對比

| 特性 | Abstract Class | Interface |
|------|-----------------|-----------|
| **定義方式** | `abstract class Name { }` | `interface Name { }` |
| **能否實例化** | ❌ 不能 `new AbstractClass()` | ❌ 不能 `new Interface()` |
| **能否被繼承** | ✅ 能 `extends` | ✅ 能 `implements` |
| **繼承方式** | 單一繼承（只能 `extends` 一個） | 多重實現（可 `implements` 多個） |

---

## 🔑 屬性與欄位

| 面向 | Abstract Class | Interface |
|------|-----------------|-----------|
| **能否有屬性** | ✅ 能 | ❌ 不能（Java 8 前） |
| **屬性特性** | 任何訪問修飾符、任何型態 | 隱含 `public static final`（常數） |
| **實例變數** | ✅ 能有 | ❌ 不能有 |
| **靜態變數** | ✅ 能有 | ✅ 能有 |
| **初始化** | 在建構子中初始化 | 只能在宣告時初始化 |

### 程式碼示例

**Abstract Class**
```java
abstract class Animal {
    protected int age;              // ← 實例變數
    private String name;            // ← 實例變數
    public static int count = 0;    // ← 靜態變數
}
```

**Interface**
```java
interface Drawable {
    // 必須立即初始化，隱含 public static final
    int MAX_SIZE = 100;
    String DEFAULT_COLOR = "red";
    
    // ❌ 這不行：沒有初始值
    // int invalidField;
}
```

---

## 🎯 方法與抽象方法

| 面向 | Abstract Class | Interface |
|------|-----------------|-----------|
| **抽象方法** | ✅ 能有 `abstract void method();` | ✅ 能有（預設抽象） |
| **具體方法** | ✅ 能有已實現的方法 | ❌ 不能（Java 8 前） |
| **方法訪問修飾符** | `public`, `protected`, `private` | 預設 `public`（Java 9+ 可用 `private`） |
| **static 方法** | ✅ 能有 | ✅ 能有（Java 8+） |
| **default 方法** | ❌ 不能 | ✅ 能有（Java 8+） |

### 程式碼示例

**Abstract Class**
```java
abstract class Vehicle {
    // 抽象方法：子類別必須實現
    abstract void start();
    
    // 具體方法：子類別繼承或覆蓋
    public void stop() {
        System.out.println("停止中...");
    }
    
    // 受保護的方法
    protected void maintenance() { }
    
    // 靜態方法
    public static int getCount() { return count; }
}
```

**Interface (Java 8+)**
```java
interface Drawable {
    // 抽象方法（預設 public abstract）
    void draw();
    
    // 預設方法（Java 8+）
    default void redraw() {
        System.out.println("重新繪製");
    }
    
    // 靜態方法（Java 8+）
    static void printInfo() {
        System.out.println("Drawable 介面");
    }
    
    // 私有方法（Java 9+）
    private void internalHelper() { }
}
```

---

## 🏗️ 建構子

| 面向 | Abstract Class | Interface |
|------|-----------------|-----------|
| **能否有建構子** | ✅ 能有 | ❌ 不能有 |
| **建構子用途** | 初始化屬性、調用父類別建構子 | N/A |
| **必須呼叫 super** | ✅ 如果父類別有建構子 | N/A |

### 程式碼示例

**Abstract Class**
```java
abstract class Animal {
    protected String name;
    
    // 子類別必須實現建構子
    public Animal(String name) {
        this.name = name;
    }
    
    abstract void speak();
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);  // ← 必須呼叫父類別建構子
    }
    
    @Override
    void speak() { System.out.println("汪"); }
}
```

**Interface**
```java
interface Drawable {
    void draw();
    // ❌ 不能有建構子
    // public Drawable() { }
}

class Circle implements Drawable {
    private double radius;
    
    // 實現類別有自己的建構子
    public Circle(double radius) {
        this.radius = radius;  // ← 初始化自己的屬性
    }
    
    @Override
    public void draw() { }
}
```

---

## 👪 繼承與實現的多重性

| 面向 | Abstract Class | Interface |
|------|-----------------|-----------|
| **單一繼承** | ✅ 一個類別只能 `extends` 一個父類別 | N/A |
| **多重實現** | N/A | ✅ 一個類別可 `implements` 多個介面 |
| **結合使用** | ✅ 可同時 `extends` 抽象類 和 `implements` 多個介面 | ✅ 介面可 `extends` 其他介面 |

### 程式碼示例

```java
// 抽象類別：單一繼承
abstract class Vehicle {
    abstract void start();
}

// ❌ 多重繼承不允許
// class Car extends Vehicle, Machine { }

// 介面：多重實現
interface Drawable {
    void draw();
}

interface Comparable {
    int compareTo(Object o);
}

// ✅ 多重實現允許
class SmartCar extends Vehicle implements Drawable, Comparable {
    @Override
    void start() { }
    
    @Override
    public void draw() { }
    
    @Override
    public int compareTo(Object o) { return 0; }
}

// ✅ 介面也可互相繼承
interface Shape extends Drawable {
    double getArea();
}
```

---

## 🎨 訪問控制

| 修飾符 | Abstract Class | Interface |
|--------|-----------------|-----------|
| `public` | ✅ 方法、屬性都可 | ✅ 預設所有成員（Java 8+ 除外） |
| `protected` | ✅ 方法、屬性都可 | ❌ 不支持（介面成員對所有實現者都可見） |
| `private` | ✅ 方法、屬性都可 | ✅ 私有方法（Java 9+） |
| `package-private` | ✅ 支持（預設） | ❌ 不支持 |

### 程式碼示例

**Abstract Class - 靈活的訪問控制**
```java
abstract class Animal {
    public int age;              // 公開給所有人
    protected String name;       // 只給子類別
    private String internalId;   // 只給自己
    
    public abstract void speak();           // 公開抽象方法
    protected abstract void hunt();         // 保護抽象方法
    private void internalLogic() { }       // 私有具體方法
}
```

**Interface - 統一的訪問控制**
```java
interface Drawable {
    void draw();  // 預設 public abstract，所有實現者都能看到
    
    // Java 9+ 允許私有方法
    private void internalHelper() {
        // 只給 default 方法使用
    }
    
    default void redraw() {
        internalHelper();  // ← 可以調用
    }
}
```

---

## 📝 設計理念與用途

| 面向 | Abstract Class | Interface |
|------|-----------------|-----------|
| **設計理念** | 「我能做什麼」+ 「我該做什麼」 | 「我該做什麼」 |
| **代表概念** | 一種東西的體系（is-a） | 一種能力（can-do） |
| **目的** | 代碼重用 + 規格定義 | 規格定義 + 解耦 |
| **常見模式** | 超類別定義體系 | 定義協議或能力 |

### 程式碼示例

**Abstract Class - 體系設計**
```java
// ❌ 不好的例子：用介面定義體系
interface Animal { void eat(); }
class Dog implements Animal { }
class Cat implements Animal { }
// 失去了「動物」的共同概念和屬性

// ✅ 好的例子：用抽象類別定義體系
abstract class Animal {
    protected int age;           // 共同屬性
    protected String name;       // 共同屬性
    
    public void sleep() { }      // 共同行為
    abstract void eat();         // 各自實現
}

class Dog extends Animal {
    @Override
    void eat() { System.out.println("吃肉"); }
}

class Cat extends Animal {
    @Override
    void eat() { System.out.println("吃魚"); }
}
```

**Interface - 能力設計**
```java
// 定義能力，無關血緣
interface Drawable {
    void draw();
}

interface Comparable {
    int compareTo(Object o);
}

// 不同體系的類別可實現同一介面
class Dog implements Drawable, Comparable {
    @Override
    public void draw() { System.out.println("畫狗"); }
    
    @Override
    public int compareTo(Object o) { return 0; }
}

class Circle implements Drawable, Comparable {
    @Override
    public void draw() { System.out.println("畫圓"); }
    
    @Override
    public int compareTo(Object o) { return 0; }
}
```

---

## 🔄 多態與綁定

| 面向 | Abstract Class | Interface |
|------|-----------------|-----------|
| **向上轉型** | ✅ 自動 | ✅ 自動 |
| **動態綁定** | ✅ 支持 | ✅ 支持 |
| **多態容器** | ✅ 支持 | ✅ 支持 |
| **運行時檢查** | ✅ `instanceof` | ✅ `instanceof` |

### 程式碼示例

```java
// 抽象類別多態
abstract class Shape { abstract void draw(); }
class Circle extends Shape { 
    @Override
    void draw() { System.out.println("圓"); } 
}
class Square extends Shape { 
    @Override
    void draw() { System.out.println("方形"); } 
}

Shape s = new Circle();
s.draw();  // 動態綁定：執行 Circle.draw()

// 介面多態
interface Drawable { void draw(); }
class Dog implements Drawable {
    @Override
    public void draw() { System.out.println("狗"); }
}
class Tree implements Drawable {
    @Override
    public void draw() { System.out.println("樹"); }
}

Drawable d = new Dog();
d.draw();  // 動態綁定：執行 Dog.draw()

// 多態容器
List<Shape> shapes = new ArrayList<>();
shapes.add(new Circle());
shapes.add(new Square());

List<Drawable> drawables = new ArrayList<>();
drawables.add(new Dog());
drawables.add(new Tree());
```

---

## 🛠️ 實踐應用場景

### 何時用 Abstract Class

| 場景 | 說明 | 範例 |
|------|------|------|
| **定義類別體系** | 有共同的父類別概念 | `Vehicle` 的 `Car`, `Bike`, `Truck` |
| **共享實現** | 多個子類別有共同的已實現方法 | `Animal` 的 `sleep()` 方法所有動物都一樣 |
| **共享狀態** | 子類別需要共同的屬性和初始化邏輯 | `Vehicle` 的 `speed`, `color` 屬性 |
| **控制訪問** | 需要 `protected` 或 `private` 成員 | 內部資料結構不想給外界看 |
| **有建構子邏輯** | 需要複雜的初始化 | `Database` 連接需要驗證 |

**程式碼示例**
```java
abstract class Vehicle {
    protected int speed;        // 共享屬性
    protected String color;
    
    public Vehicle(int speed) {  // 複雜初始化
        if (speed < 0) throw new IllegalArgumentException();
        this.speed = speed;
    }
    
    public void start() { }      // 共享實現
    
    abstract void turnLeft();    // 各自實現
}

class Car extends Vehicle {
    public Car(int speed) {
        super(speed);  // 委託初始化
    }
    
    @Override
    void turnLeft() { System.out.println("轉方向盤"); }
}
```

### 何時用 Interface

| 場景 | 說明 | 範例 |
|------|------|------|
| **定義能力** | 無關的類別也能實現同一介面 | `Drawable` - 狗、樹、房子都可以畫 |
| **定義契約** | 多個實現者有相同的行為規格 | `Comparable` - 各種類別都可比較 |
| **解耦依賴** | 使用者只依賴介面，不依賴實現 | 支付系統依賴 `PaymentGateway` |
| **多重實現** | 一個類別需要多個身分 | `SmartPhone` 既是 `Phone` 又是 `Camera` |
| **無共享狀態** | 實現者各有各的屬性 | `Comparable` 無共享屬性 |

**程式碼示例**
```java
// 定義能力
interface Drawable { void draw(); }
interface Comparable { int compareTo(Object o); }
interface Serializable { byte[] serialize(); }

// 無關的類別可以實現相同的介面
class Dog implements Drawable, Comparable, Serializable {
    @Override
    public void draw() { System.out.println("畫狗"); }
    
    @Override
    public int compareTo(Object o) { return 0; }
    
    @Override
    public byte[] serialize() { return new byte[0]; }
}

class Circle implements Drawable, Comparable, Serializable {
    @Override
    public void draw() { System.out.println("畫圓"); }
    
    @Override
    public int compareTo(Object o) { return 0; }
    
    @Override
    public byte[] serialize() { return new byte[0]; }
}

// 使用介面：無需知道具體類別
void drawAll(List<Drawable> items) {
    for (Drawable item : items) {
        item.draw();  // ← 多態
    }
}

drawAll(Arrays.asList(new Dog(), new Circle()));
```

---

## 🎓 常見誤區

### 誤區 1：介面不能有實現

```java
// Java 8 前：正確（介面沒有實現）
interface OldInterface {
    void method();  // 只有簽名
}

// Java 8+：介面可以有實現
interface NewInterface {
    void abstractMethod();        // 抽象方法
    
    default void concreteMethod() {  // ← 有實現
        System.out.println("預設實現");
    }
}
```

### 誤區 2：抽象類別不能有抽象方法

```java
// ✅ 正確：抽象類別必須至少有一個抽象方法（或被設計為完全具體）
abstract class Correct {
    abstract void mustImplement();
}

// ⚠️ 有可能：抽象類別沒有任何抽象方法（但這很奇怪）
abstract class WeirdButValid {
    public void allConcrete() { }
    // 為什麼要標記 abstract？可能是為了防止實例化
}
```

### 誤區 3：介面比抽象類別更「純粹」

```java
// 不是「更純粹」，而是「設計目的不同」
// 抽象類別：定義「什麼東西」
// 介面：定義「什麼能力」

abstract class Dog extends Animal {  // Dog 是什麼東西
    // 有 Animal 的共同屬性
    // 有 Dog 特有的行為
}

class Dog implements Drawable {  // Dog 有繪製能力
    // Dog 可能繼承自 Animal
    // 但額外承諾了「可以畫」
}
```

---

## 📚 選擇決策流程

```
開始
  │
  ├─ 是否需要共享實現或屬性？
  │  ├─ YES → 抽象類別
  │  └─ NO → 繼續
  │
  ├─ 是否需要單一繼承體系？
  │  ├─ YES → 抽象類別
  │  └─ NO → 繼續
  │
  ├─ 是否需要多重身分？
  │  ├─ YES → 介面
  │  └─ NO → 繼續
  │
  ├─ 是否是無關類別的共同能力？
  │  ├─ YES → 介面
  │  └─ NO → 繼續
  │
  └─ 預設：介面（更靈活）
```

---

## 💡 最佳實踐

### 1. 層級設計

```java
// 最底層：介面定義能力
interface Readable { double read(); }
interface Reportable { String report(); }

// 中層：抽象類別定義體系
abstract class Sensor {
    protected String id;
    public void save() { }      // 共享實現
    public abstract void calibrate();
}

// 最頂層：具體類別實現功能
class TemperatureSensor extends Sensor 
    implements Readable, Reportable {
    @Override
    public double read() { return temperature; }
    
    @Override
    public String report() { return "溫度：" + temperature; }
    
    @Override
    public void calibrate() { }
}
```

### 2. 優先使用介面（Prefer Interfaces）

```java
// ❌ 依賴具體實現
void processCircle(Circle c) { }
void processSquare(Square s) { }

// ✅ 依賴介面
void process(Drawable d) { }
void process(Comparable c) { }
```

### 3. 組合優於繼承

```java
// ❌ 過度繼承
class FlyingDog extends Dog {  // Dog 不應該飛
    @Override
    void fly() { }
}

// ✅ 用介面表達能力
class Dog implements Drawable {
    // Dog 基本功能
}

class FlyingDog extends Dog implements Flyable {
    @Override
    public void fly() { }  // 額外能力
}
```

---

## 📊 快速對比表（一頁版）

| 項目 | Abstract Class | Interface |
|------|---|---|
| 實例化 | ❌ | ❌ |
| 屬性 | ✅ | ❌ (常數除外) |
| 具體方法 | ✅ | ✅ (Java 8+) |
| 抽象方法 | ✅ | ✅ |
| 建構子 | ✅ | ❌ |
| 單一繼承 | ✅ | N/A |
| 多重實現 | N/A | ✅ |
| protected 成員 | ✅ | ❌ |
| 訪問控制 | 靈活 | 受限 |
| 用途 | 體系 + 代碼重用 | 能力 + 解耦 |
| 最佳場景 | 有共同狀態 | 無共同狀態 |

---

## 🎯 一句話總結

```
抽象類別：「我們是什麼」（是一種東西的體系）
介面：「我們能做什麼」（是一種能力的定義）
```
