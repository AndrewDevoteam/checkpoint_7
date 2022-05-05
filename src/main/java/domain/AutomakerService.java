package domain;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AutomakerService {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void searchByName() {
        System.out.println("Type the name of the automaker or empty to list all");
        String name = SCANNER.nextLine();
        List<AutoMaker> autoMakers = AutomakerRepository.findByName(name);
        autoMakers.forEach(a -> System.out.printf("[%d] - %s %n", a.getId(), a.getName()));
    }

    public static void addAutomaker() {
        System.out.println("Type the name of the Automaker");
        String name = SCANNER.nextLine();
        AutoMaker autoMaker = AutoMaker.builder()
                .name(name)
                .build();
        AutomakerRepository.save(autoMaker);
    }
}
