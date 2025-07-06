1. **Decorator** - First we call super, then we decorate it. 
2. If I don't override in the abstract class, I need to override in the children that inherit. If I do override in the abstract, I dont need to override in the children.
3. ALWAYS RETURN IF NEED TO RETURN! you always forget that.

4. **Singleton** - private constructor, private static instance, public Static getInstance()
5. **Enum** - .getValues();
6. **Functional Programming** - "::", and dont forget to use "apply()" or whatever needed.
7. **Abstract Class** - protected abstract (For things I want them to implement but dont want to implement myself.)

8. **Factory**- We want this to hold all the information related to creating object. It shouldnt compute anything.
Example - In one of the tests we had a Player(Farmer), Fields, and DecoratedFields.
    The factory should manage the price of the fields (Not the fields themselves), and create new ones.
    If needed, we can create another factory for DecoratedFields with the same functionality.
    So the factory held - public getPriceOfField and public getField functions. and shouldnt compute anything inside.
    it also held the prices of the fields.
    The fields shouldnt be aware of the price as they are not managing the game. they are just fields.
1. Remember to throw exception.

2.  **Observer** - the notifyAll is private.

3. **Regex** - "\\" for every special digit.

GENERAL LESSONS:
1. Classes are not static as they are High-level.
2. At the end make sure to check:
   a. I did what was asked. (Read again!)
   b. all functions have return values.
   c. @Override where its needed!
   d. Correct privacy modifiers.
   e. Code is neat (Global strings, etc.)