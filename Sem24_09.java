package com.company;


import java.util.*;

public class Sem24_09 {

    public static void main(String[] args) {
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
        getLettersMap("smdakmkamkmnsckmdjvbjacklscabjcgeb");

        HashMap<String, String> myHashMap = new HashMap<String, String>();

        myHashMap.put("Basil", "07.12.1987");
        myHashMap.put("Kate", "12.10.1971");
        myHashMap.put("Lena", "11.01.1991");
        revertMap(myHashMap);
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

    // №2 Реализовать метод, который принимает словарь и возвращает словарь, где ключ
    // меняется местами со значениями.
    //                   Map<V, HashMap<>>
    private static <K, V> Map<V, K> revertMap(HashMap<? extends K, ? extends V> mapToRevert){
        HashMap<V, K> map = new HashMap<>();
        for(Map.Entry<? extends K, ? extends V> entry : mapToRevert.entrySet())
            map.put(entry.getValue(), entry.getKey());
        System.out.println("OLD" + mapToRevert);
        System.out.println("NEW" + map);
        //map.compute(K, (k, v) ->);
        return map;
    }
}
