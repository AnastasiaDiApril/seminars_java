import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {

    private final Map<String, Numbers> store = new LinkedHashMap<>();

    void addNumber(String name, long number) {
        var numbers = store.get(name);
        if (numbers == null) {
            numbers = new Numbers();
            store.put(name, numbers);
        }
        numbers.addNumber(number);
    }

    void printBook() {
        for (var item : sortByNumberCount(store.entrySet())) {
            System.out.print(item.getKey());
            System.out.print("     phone:");
            for (var number : item.getValue().getAllNumbers()) {
                System.out.print(number);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private Set<Map.Entry<String, Numbers>> sortByNumberCount(Set<Map.Entry<String, Numbers>> set) {
        return set.stream()
                .sorted(
                        Collections.reverseOrder(
                                Comparator.comparingInt(a -> a.getValue().getAllNumbers().size()))
                )
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    private static class Numbers {
        private final List<Long> store = new ArrayList<>();

        void addNumber(Long phone) {
            store.add(phone);
        }

        Collection<Long> getAllNumbers() {
            return Collections.unmodifiableCollection(store);
        }
    }

    public static void main(String[] args) {
        var phoneBook = new PhoneBook();

        phoneBook.addNumber("Chuck", 2467);

        phoneBook.addNumber("Bob", 1234);
        phoneBook.addNumber("Bob", 5678);
        phoneBook.addNumber("Bob", 2568);

        phoneBook.addNumber("Alice", 1358);
        phoneBook.addNumber("Alice", 73346);

        phoneBook.printBook();
    }
}

