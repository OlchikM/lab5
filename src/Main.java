import managers.CollectionManager;

import java.util.HashSet;
import java.util.Iterator;

public class Main{
    public static void main(String[] args){
        CollectionManager c = new CollectionManager();
        System.out.println(c.getCollectionType());
        HashSet<String> set = new HashSet<>();
        set.add("apple");
        set.add("banana");

        Iterator<String> iterator = set.iterator();
        String lastElement = null;
        while (iterator.hasNext()) {
            lastElement = iterator.next();
        }

        if (lastElement != null) {
            System.out.println("Последний элемент: " + lastElement);
        } else {
            System.out.println("Множество пустое");
        }
    }

}