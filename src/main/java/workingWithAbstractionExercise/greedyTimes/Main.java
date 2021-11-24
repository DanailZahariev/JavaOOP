
package workingWithAbstractionExercise.greedyTimes;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long capacityBag = Long.parseLong(scanner.nextLine());
        String[] inputData = scanner.nextLine().split("\\s+");

        var bag = new LinkedHashMap<String, LinkedHashMap<String, Long>>();
        long gold = 0;
        long gems = 0;
        long money = 0;

        for (int i = 0; i < inputData.length; i += 2) {
            String name = inputData[i];
            long count = Long.parseLong(inputData[i + 1]);

            String type = "";

            if (name.length() == 3) {
                type = "Cash";
            } else if (name.toLowerCase().endsWith("gem")) {
                type = "Gem";
            } else if (name.equalsIgnoreCase("gold")) {
                type = "Gold";
            }

            if (type.equals("")) {
                continue;
            } else if (capacityBag < bag.values().stream().map(Map::values).
                    flatMap(Collection::stream).mapToLong(e -> e).sum() + count) {
                continue;
            }

            switch (type) {
                case "Gem":
                    if (add(bag, count, type, "Gold")) continue;
                    break;
                case "Cash":
                    if (add(bag, count, type, "Gem")) continue;
                    break;
            }

            if (!bag.containsKey(type)) {
                bag.put((type), new LinkedHashMap<>());
            }

            if (!bag.get(type).containsKey(name)) {
                bag.get(type).put(name, 0L);
            }


            bag.get(type).put(name, bag.get(type).get(name) + count);

            switch (type) {
                case "Gold":
                    gold += count;
                    break;
                case "Gem":
                    gems += count;
                    break;
                case "Cash":
                    money += count;
                    break;
            }
        }

        for (var entry : bag.entrySet()) {
            Long sumValues = entry.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.printf("<%s> $%s%n", entry.getKey(), sumValues);

            entry.getValue().entrySet().stream().sorted((e1, e2) ->
                    e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }

    private static boolean add(LinkedHashMap<String, LinkedHashMap<String, Long>> bag, long count, String type, String gem) {
        if (!bag.containsKey(type)) {
            if (bag.containsKey(gem)) {
                return count > bag.get("Gold").values().stream().mapToLong(e -> e).sum();
            } else {
                return true;
            }
        } else return bag.get(type).values().stream().
                mapToLong(e -> e).sum() + count > bag.get(gem).values().stream().mapToLong(e -> e).sum();
    }
}