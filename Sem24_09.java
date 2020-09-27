package com.company;


import java.util.*;

public class Sem24_09 {

    public static void main(String[] args) {
	// write your code here
        Map<Integer, String> status = new HashMap<Integer, String>();
        status.put(1, "German");
        status.put(2, "QWERTY");

        String first = status.get(1);
        System.out.println(first);

        Set<Integer> keys = status.keySet();

        Collection<String> values = status.values();

        status.replace(1, "NEW_STRING");
        status.remove(2);

        for(Map.Entry<Integer, String> items: status.entrySet()){
            System.out.printf("Ключ: %d, Значение: %s", items.getKey(),
                    items.getValue());

        }

        Map<Integer, Person> personMap = new HashMap<Integer, Person>();
        personMap.put(1, new Person("Mark"));
        personMap.put(2, new Person("Frank"));

        for (Map.Entry<Integer, Person> person: personMap.entrySet()){
            System.out.printf("Ключ: %d, Значение: %s", person.getKey(),
                    person.getValue().getName());
        }

    }

    // №1
    public static void getLettersMap(String text){
        text = text.toLowerCase();

        HashMap<Character, Integer> lettersMap = new HashMap<>();
        for (int i = 0; i < text.length();i++){
            char ch = text.charAt(i);
            lettersMap.compute(ch, (k, v)->v==null?1:v+1);
        }
        ArrayList<Map.Entry<Character, Integer>> listToSort = new ArrayList<>(lettersMap.entrySet());
        listToSort.sort((o1, o2) -> Character.compare(o1.getKey(), o2.getKey()));
        for (Map.Entry<Character, Integer> entri: listToSort){
            System.out.println(entri.getKey() + " -> " + entri.getValue());
        }
    }

    // №2
    public static <K, V> Map<V, HashMap<K>> revertMap(HashMap<? extends K, ? extends V> mapToRevert){
        HashMap<V, HashMap<K>> map = new HashMap<>();
        map.compute(V, (k, v) ->{
           return ;
        });
    }
}
