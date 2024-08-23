# MiniJava
Mini-Java is a subset of Java. MiniJava compiler implement a compiler for the Mini-java
programming language.

# Rules of MiniJava
```
Goal --> Source EOF
Source --> ClassDeclarations MainClass
MainClass --> class Identifier { public static void main() { VarDeclarations Statements}}
ClassDeclarations --> ClassDeclaration ClassDeclarations | lambda
ClassDeclaration --> class Identifier Extension { FieldDeclarations MethodDeclarations }
Extension --> extends Identifier | lambda
FieldDeclarations --> FieldDeclaration FieldDeclarations | lambda
FieldDeclaration --> static Type Identifier ;
VarDeclarations --> VarDeclaration VarDeclarations | lambda
VarDeclaration --> Type Identifier ;
MethodDeclarations --> MethodDeclaration MethodDeclarations | lambda
MethodDeclaration --> public static Type Identifier ( Parameters ) { VarDeclarations Statements return GenExpression ; }
Parameters --> Type Identifier Parameter | lambda
Parameter --> , Type Identifier Parameter | lambda
Type --> boolean | int
Statements --> Statements Statement | lambda
Statement --> { Statements } | if ( GenExpression ) Statement else Statement | while ( GenExpression ) Statement | System.out.println ( GenExpression ) ; | Identifier = GenExpression ;
GenExpression --> Expression | RelExpression
Expression --> Expression + Term | Expression - Term | Term
Term --> Term * Factor | Factor
Factor --> ( Expression ) | Identifier | Identifier . Identifier | Identifier . Identifier ( Arguments ) | true | false | Integer
RelExpression --> RelExpression && RelTerm | RelTerm
RelTerm --> Expression == Expression | Expression < Expression
Arguments --> GenExpression Argument | lambda
Argument --> , GenExpression Argument | lambda
Identifier --> <IDENTIFIER_LITERAL>
Integer --> <INTEGER_LITERAL>
```
در مرحله دوم مشاهده کردیم که از TypeAddress به عنوان enum استفاده شده که در کلاس address به عنوان ورودی گرفته می شود و در نهایت با توجه به تایپ آدرس یک خروجی استرینگ داده می شود. 

از آنجایی که این مورد باعث پیچیده تر شدن کد شده بود TypeAddress را به یک Interface تبدیل کردیم و در آن متدی تحت عنوان toString تعریف کردیم. در ادامه باقی حالات enum را به عنوان کلاس های مختلف تبدیل کردیم تا خروجی مورد نظر را تحویل دهد. این کار حجم و پیچیدگی کد را کاهش می دهد و باعث می شود که دیگر نیازی به یک فرایند چند مرحله ای برای بدست آوردن آدرس نشویم. همچنین اگر برای آدرس دهی ها نیاز باشد که تابع های دیگری به این کلاس ها اضافه کنیم می توانیم آن ها را ساده تر پیاده سازی کنیم .  switch که در کلاس address استفاده شده بود هم از بین می رود و دیگر نیازی به برسی یک مرحله اضافه تر نخواهد بود و در لحظه ای که آدرس را می خواهیم با فراخوانی این توابع از کلاسهایشان می توانیم به آدرس مورد نظر برسیم. 

این موارد نمونه ای از استفاده strategy را نشان می دهند. 

### TypeAddress Interface : 
![alt text](<images/Screenshot from 2024-08-23 20-08-23.png>)

### Other Enum states : 
#### Direct
![alt text](<images/Screenshot from 2024-08-23 20-10-58.png>)

#### Imidiate
![alt text](<images/Screenshot from 2024-08-23 20-11-00.png>)

#### Indirect
![alt text](<images/Screenshot from 2024-08-23 20-11-02.png>)


و در نهایت کلاس ها به این صورت شد :‌

![alt text](<images/Screenshot from 2024-08-23 20-08-21.png>)

قرمز بودن Imidiate به خاطر گیتهاب است

