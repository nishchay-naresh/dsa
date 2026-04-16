

== code to get the frequency ==

    HashMap<Character, Integer> freq = new HashMap<>();
    freq.put(char, freq.getOrDefault(char, 0) + 1);

    Element is used as key and the frequency is used as the value 


GroupBy =  Map<String, Long>
* Frequency map - hashmap<String, Long>

        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

* Ordered frequency map - LinkedHashMap<String, Long>

        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))


* char/Character stream handling


        Map<Character, Long> freqMap = input.chars()
        .mapToObj(i -> (char) i)
        .collect(Collectors.groupingBy(
            Function.identity(),
            LinkedHashMap::new,
            Collectors.counting()
        ));

        Character firstUniqueChar = freqMap.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().orElse('$');


* GroupBy =  Map<String, List<String>>

        public record City(String cityName, String countryName) {}
   
        Map<String, List<String>> map = new HashMap<>();
        for (City c : cities) {
            map.computeIfAbsent(c.countryName(), k -> new ArrayList<>()).add(c.cityName());
        }

        Map<String, List<String>> countryWiseCity = cities.stream().collect(
        Collectors.groupingBy(
                c -> c.countryName(),
                Collectors.mapping(e -> e.cityName(), Collectors.toList())
        ));




https://www.geeksforgeeks.org/dsa/top-50-string-coding-problems-for-interviews/