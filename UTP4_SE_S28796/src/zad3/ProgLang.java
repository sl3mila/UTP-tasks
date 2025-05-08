package zad3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    private Map<String, Set<String>> langs;
    private Map<String, Set<String>> progs;

    public ProgLang(String file) throws IOException {
        this.langs = new HashMap<>();
        this.progs = new HashMap<>();

        readFile(file);
    }

    private void readFile(String file) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                String lang = split[0];

                Set<String> programmers = Arrays.asList(split)
                        .subList(1, split.length)
                        .stream()
                        .collect(Collectors.toSet());

                langs.put(lang, programmers);

                for (String x : programmers) {
                    progs.computeIfAbsent(x, s -> new HashSet<>()).add(lang);
                }
            }
        }
    }

    private Map<String, Set<String>> copy(Map<String, Set<String>> file) {
            return file.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, s -> new HashSet<>(s.getValue())));
    }

    public Map<String, Set<String>> getLangsMap() {
        return  copy(langs);
    }

    public Map<String, Set<String>> getProgsMap() {
        return copy(progs);
    }

    private <K, V> Map<K, V> sorted(Map<K, V> mapa, Comparator<Map.Entry<K, V>> comp) {
        return mapa.entrySet()
                .stream()
                .sorted()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (s1, s2) -> s1, LinkedHashMap::new));
    }

    public Map<String, Set<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(progs, Comparator.comparingInt(s -> -s.getValue().size()));
    }

    public Map<String, Set<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(langs, Comparator.comparingInt(s -> -s.getValue().size()));
    }

    private <K, V> Map<K, V> filtered(Map<K, V> mapa, Predicate<Map.Entry<K, V>> filter) {
        return mapa
                .entrySet()
                .stream()
                .filter(filter)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (s1, s2) -> s1, LinkedHashMap::new));
    }
    public Map<String, Set<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
        Predicate<Map.Entry<String, Set<String>>> filter =  s -> s.getValue().size() > i;
        return filtered(progs, filter);
    }
}
