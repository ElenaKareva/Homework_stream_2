import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(d -> d.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);

        List<String> conscript = persons.stream()
                .filter(c -> c.getAge() >= 18 && c.getAge() < 27)
                .filter(c -> c.getSex().equals(Sex.MAN))
                .map(c -> c.getFamily())
                .collect(Collectors.toList());
        System.out.println("Cписок фамилий призывников: ");
        for (String conscriptPerson : conscript) {
            System.out.println(conscriptPerson);
        }

        List<Person> workablePeople = persons.stream()
                .filter(w -> w.getEducation().equals(Education.HIGHER))
                .filter(w -> w.getAge() >= 18)
                .filter(w -> (w.getAge() < 65 && w.getSex().equals(Sex.MAN)) || (w.getAge() < 60 && w.getSex().equals(Sex.WOMAN)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Работоспособное население: ");
        for (Person workablePerson : workablePeople) {
            System.out.println(workablePerson);
        }
    }
}
