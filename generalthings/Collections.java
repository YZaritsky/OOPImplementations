package generalthings;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Objects;

public class Collections {
    // Usefull methods: add, clear, contains, isEmpty, remove, size, toArray

    // ** Collections - List **
    // Usefull methods: sort, indexOf

    // ArrayList
    // LinkedList
    // Allows: add(), remove(), contains(), size(), get(idx), set(idx);
    // ** MUST IMPORT: java.util.LinkedList
    LinkedList<String> myList = new LinkedList<String>();
    ArrayList<String> myList2 = new ArrayList<String>();

    // ** Collections - Set **

    // HashSet
    // Allows: add(), remove(), contains(), size();
    // TreeSet (Navigatable Set) -> Must provide Comparable Function to it if not
    // primitive type.


    // ** Collections - Map **

    // HashMap
    // Allows: put(k, v), remove(k), containsKey(k), containsValue(v), get(k),
    // size(), keySet(), values();
    // TreeMap (Navigatable Map)

    // int Object.hashCode() -> Returns the address of the object by default, or
    // hash int number if hashed.
    // Used in .equals(). if 2 objects are not equal because of equals() there
    // could still be cases where they have the same int value.

    // if we @Override equals, we also must @Override hashcode() based on the same
    // comparison parameters. We can use "objects.hash()" to do that.
    // Good Example:
    class Student {
        private final long id;
        private String name;
        private String email;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Student))
                return false;

            Student other = (Student) obj;
            return (other.id == this.id
                    && other.name.equals(this.name)
                    && other.email.equals(this.email));
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, email);
        }
    }

}