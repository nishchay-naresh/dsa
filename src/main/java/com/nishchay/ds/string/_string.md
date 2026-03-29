

== code to get the frequency ==

    HashMap<Character, Integer> freq = new HashMap<>();
    freq.put(char, freq.getOrDefault(char, 0) + 1);

    Element is used as key and the frequency is used as the value 

GroupBy - <String, List<String>

        public record City(String cityName, String countryName) {}
   
        Map<String, List<String>> map = new HashMap<>();
        for (City c : cities) {
            map.computeIfAbsent(c.countryName(), k -> new ArrayList<>()).add(c.cityName());
        }