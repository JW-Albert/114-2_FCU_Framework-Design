# 快速搞懂 UML 類別圖 - 6 種關係線

---

## 📌 一張表搞定

| 關係 | 符號 | 含義 | Java 實現 | 關係強度 |
|------|------|------|---------|--------|
| **Dependency** (依賴) | ⇢ 虛線箭頭 | A 臨時使用 B | 方法參數 | 最弱 |
| **Association** (關聯) | → 實線箭頭 | A 長期持有 B | 實例變數 | 弱 |
| **Aggregation** (聚合) | ◇→ 空心菱形 | A 有多個 B (B可獨立) | List 集合 | 中 |
| **Composition** (組合) | ◆→ 實心菱形 | A 有 B (B不獨立) | 內部建立 | 強 |
| **Generalization** (繼承) | ⎿⎴> 實線三角 | B is-a A | `extends` | 最強 |
| **Realization** (實現) | ⎿⎴.. 虛線三角 | B 實現 A 的規格 | `implements` | 強 |

---

## 1️⃣ Dependency (依賴)

### 特徵
- **虛線箭頭** ⇢
- A 「臨時使用」B，不長期持有
- 只在方法中作為參數或局部變數存在
- **關係最弱**，改變 B 對 A 影響小

### UML 圖

```
┌─────────┐  ⇢⇢⇢⇢⇢  ┌─────────┐
│ Driver  │           │   Car   │
└─────────┘           └─────────┘
```

### Java 代碼

```java
public class Driver {
    public void drive(Car car) {  // ← Car 只是參數
        car.start();
        car.stop();
    }
    // 方法結束，與 Car 的關係就斷了
}
```

### 何時用
- 工具類調用
- 臨時計算或處理
- 方法參數傳遞

---

## 2️⃣ Association (關聯)

### 特徵
- **實線箭頭** →
- A 「長期持有」B
- B 是 A 的實例變數
- 物件整個生命週期都持有 B

### UML 圖

```
┌──────────┐  ───→  ┌──────────┐
│ Teacher  │        │ Student  │
├──────────┤        ├──────────┤
│ -name    │        │ -name    │
│ -student │────┐   │ -id      │
└──────────┘    └───┘──────────┘
```

### Java 代碼

```java
public class Teacher {
    private String name;
    private Student student;  // ← 長期持有
    
    public Teacher(String name, Student student) {
        this.name = name;
        this.student = student;
    }
    
    public void teach() {
        // 在 teach 方法中使用 student
        student.study();
    }
}
```

### 何時用
- 物件明確「擁有」另一個物件
- 一對一的持有關係
- 生命週期一般綁定

---

## 3️⃣ Aggregation (聚合)

### 特徵
- **空心菱形** ◇→
- 「整體-部分」關係，但**部分可獨立存在**
- A 包含多個 B，但 B 可以獨立
- **弱包含**：刪除整體，部分仍存活

### UML 圖

```
┌─────────────┐         ┌──────────┐
│ ClassRoom   │ ◇───→ * │ Student  │
├─────────────┤         ├──────────┤
│ -students[] │         │ -name    │
│ -number     │         │ -id      │
└─────────────┘         └──────────┘

(一個教室有多個學生，但學生可以轉班)
```

### Java 代碼

```java
public class ClassRoom {
    private List<Student> students;  // ◇ 聚合
    
    public ClassRoom() {
        students = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        students.add(student);  // ← 學生可以加入
    }
    
    public void removeStudent(Student student) {
        students.remove(student);  // ← 學生可以移除（轉班）
    }
    
    // 即使 ClassRoom 刪除，Student 物件仍然存在（可轉班）
}
```

### 何時用
- 整體有多個部分，但部分獨立存在
- 班級與學生
- 圖書館與圖書

---

## 4️⃣ Composition (組合)

### 特徵
- **實心菱形** ◆→
- 「整體-部分」關係，**部分不能獨立存在**
- **強包含**：刪除整體，部分也被刪除
- 部分物件通常在整體的建構子中建立

### UML 圖

```
┌──────────┐         ┌──────────┐
│  Book    │ ◆───→ * │  Page    │
├──────────┤         ├──────────┤
│ -pages[] │         │ -number  │
│ -title   │         │ -content │
└──────────┘         └──────────┘

(書有多個頁子，拆掉書，頁子就毀了)
```

### Java 代碼

```java
public class Book {
    private List<Page> pages;  // ◆ 組合
    private String title;
    
    public Book(String title, int pageCount) {
        this.title = title;
        this.pages = new ArrayList<>();
        
        // 在建構子中建立 Page 物件
        for (int i = 0; i < pageCount; i++) {
            pages.add(new Page(i + 1));
        }
    }
    
    public void addPage(String content) {
        Page newPage = new Page(pages.size() + 1);
        newPage.setContent(content);
        pages.add(newPage);
    }
    
    public void destroy() {
        pages.clear();  // ← 書毀了，頁子也跟著毀
    }
}
```

### 何時用
- 整體和部分命運相同
- 刪除整體時，部分也刪除
- 書與頁面
- 汽車與引擎

---

## 5️⃣ Generalization (繼承)

### 特徵
- **實線三角箭頭** ⎿⎴>
- 「是」的關係：B **is-a** A
- B 繼承 A 的屬性和方法
- **最強的關係**（最高耦合度）

### UML 圖

```
        ┌──────────┐
        │ Animal   │ (父類)
        ├──────────┤
        │ -name    │
        │ +eat()   │
        └──────────┘
             △
             │ (繼承)
      ───────┼───────
     │       │       │
  ┌──┴──┐ ┌──┴──┐ ┌──┴──┐
  │ Dog │ │ Cat │ │Bird │ (子類)
  └─────┘ └─────┘ └─────┘
```

### Java 代碼

```java
// 父類別
public abstract class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public abstract void eat();
    
    public void sleep() {
        System.out.println(name + " is sleeping...");
    }
}

// 子類別
public class Dog extends Animal {  // ← 繼承 Animal
    public Dog(String name) {
        super(name);  // 呼叫父類別建構子
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating meat");
    }
    
    public void bark() {
        System.out.println("Woof! Woof!");
    }
}
```

### 何時用
- 明確的「是」的關係
- 對物件進行分類
- ⚠️ **要謹慎！** 過度使用會導致高耦合

---

## 6️⃣ Realization (實現)

### 特徵
- **虛線三角箭頭** ⎿⎴..
- B **實現** A 的規格
- B 有 A 的能力（can-do）
- 用於實現介面

### UML 圖

```
        ┌──────────┐
        │ Drawable │ (介面)
        ├──────────┤
        │+draw()   │
        └──────────┘
             △
             │ (實現)
      ───────┼───────
     │       │       │
  ┌──┴──┐ ┌──┴──┐ ┌──┴──┐
  │ Dog │ │Circle│ │Tree │ (實現類別)
  └─────┘ └─────┘ └─────┘
```

### Java 代碼

```java
// 介面
public interface Drawable {
    void draw();
}

// 實現類別
public class Dog implements Drawable {  // ← 實現 Drawable
    private String name;
    
    public Dog(String name) {
        this.name = name;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a dog: " + name);
    }
}

public class Circle implements Drawable {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }
}

// 使用
public class GraphicSystem {
    public static void drawAll(List<Drawable> items) {
        for (Drawable item : items) {
            item.draw();  // ← 多態
        }
    }
    
    public static void main(String[] args) {
        List<Drawable> items = new ArrayList<>();
        items.add(new Dog("Buddy"));
        items.add(new Circle(5.0));
        
        drawAll(items);
    }
}
```

### 何時用
- 定義物件的能力或規格
- 無關類別實現同一介面
- **推薦！** 比繼承更靈活

---

## 🎯 快速比較

### 依賴 vs 關聯

```
依賴：
void process(Car car) {  // ← 參數，臨時用
    car.start();
}

關聯：
class Driver {
    private Car myCar;   // ← 成員變數，長期持有
}
```

### 聚合 vs 組合

```
聚合（空心菱形）：
class School {
    List<Student> students;  // ← 學生可轉校
}

組合（實心菱形）：
class Book {
    List<Page> pages = new ArrayList<>();  // ← 頁子不能獨立
}
```

### 繼承 vs 實現

```
繼承（is-a）：
class Dog extends Animal  // ← Dog 是一種 Animal

實現（can-do）：
class Dog implements Pet  // ← Dog 有 Pet 的能力
```

---

## 📊 完整系統範例

### UML 類別圖

```
                ┌──────────┐
                │  Person  │ (父類)
                ├──────────┤
                │ -name    │
                │ +getName()
                └──────────┘
                     △
          ───────────┼───────────
         │           │           │
    ┌────┴────┐  ┌──┴──┐   ┌───┴────┐
    │ Student │  │Teacher  │ Manager │
    └────┬────┘  └──────────┘   └────────┘
         │
         │ (關聯)
         │
    ┌────┴──────────────┐
    │   Course          │
    ├───────────────────┤
    │ -courseId         │
    │ -courseName       │
    │ -maxCapacity      │
    │ +enroll(Student)  │
    └───────────────────┘
         △
         │ (實現)
         │
    ┌────┴──────────────┐
    │ <<interface>>      │
    │ Educable          │
    ├───────────────────┤
    │ +study()          │
    │ +pass()           │
    └───────────────────┘

    School ◇────→ * Department
    
    Department ◆────→ * Classroom
```

### Java 代碼

```java
// 介面（實現）
public interface Educable {
    void study();
    boolean pass();
}

// 父類別（繼承）
public abstract class Person {
    protected String name;
    
    public Person(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

// Student 繼承 Person，實現 Educable
public class Student extends Person implements Educable {
    private String studentId;
    private List<Course> courses;  // ← 關聯
    
    public Student(String name, String studentId) {
        super(name);
        this.studentId = studentId;
        this.courses = new ArrayList<>();
    }
    
    public void enroll(Course course) {
        courses.add(course);  // ← 關聯關係
    }
    
    @Override
    public void study() {
        System.out.println(name + " is studying");
    }
    
    @Override
    public boolean pass() {
        System.out.println(name + " passed the exam");
        return true;
    }
}

// Course（組合 Department，聚合 Student）
public class Course {
    private String courseId;
    private String courseName;
    private int maxCapacity;
    private List<Student> enrolledStudents;  // ◇ 聚合
    
    public Course(String courseId, String courseName, int maxCapacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        if (enrolledStudents.size() < maxCapacity) {
            enrolledStudents.add(student);
        }
    }
}

// Department（組合 Classroom）
public class Department {
    private String deptName;
    private List<Classroom> classrooms;  // ◆ 組合
    
    public Department(String deptName) {
        this.deptName = deptName;
        this.classrooms = new ArrayList<>();
        // 建立 classroom 物件（組合）
        classrooms.add(new Classroom("101"));
        classrooms.add(new Classroom("102"));
    }
}
```

---

## 💡 記憶技巧

```
虛實判關係強度：
  虛線 = 依賴（最弱，臨時）
  實線 = 其他（關聯、聚合、組合、繼承）

菱形看獨立性：
  空心 ◇ = 聚合（部分獨立，如：班級←→學生）
  實心 ◆ = 組合（命運相同，如：書←→頁子）

三角看語義：
  實線 ⎿⎴> = 繼承（is-a，如：Dog extends Animal）
  虛線 ⎿⎴.. = 實現（can-do，如：Dog implements Pet）
```

---

## ✅ 實戰應用

### 怎麼判斷用哪種關係？

1. **是臨時使用嗎？** → 依賴
2. **是長期持有嗎？** → 關聯
3. **是「有多個」且可分離？** → 聚合
4. **是「有」且命運相同？** → 組合
5. **是「是」的關係？** → 繼承
6. **是「能做」的能力？** → 實現

### 優先級建議

```
優先用：
  1. 依賴（最不耦合）
  2. 實現介面（靈活）
  3. 關聯（必要時）

少用：
  4. 聚合和組合（區分困難）
  5. 繼承（容易過度設計）
```

---

## 🎓 90 秒速成版

| | 依賴 | 關聯 | 聚合 | 組合 | 繼承 | 實現 |
|---|------|------|------|------|------|------|
| **線型** | 虛→ | 實→ | 空◇ | 實◆ | 實三 | 虛三 |
| **含義** | 臨時 | 長期 | 多且獨 | 多且依 | is-a | can-do |
| **例子** | 參數 | 成員 | 班級-學生 | 書-頁 | 狗-動物 | 狗-寵物 |

**就這樣，GO！** 🚀
