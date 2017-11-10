package ilmaAPI;

import java.util.Scanner;

class InputScanner {
    static String getInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the city name:");
        String name = scanner.next();
        scanner.close();
        return name;
    }
}
