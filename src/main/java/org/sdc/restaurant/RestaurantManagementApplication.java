package org.sdc.restaurant;

import java.util.Scanner;

public class RestaurantManagementApplication {
    public static void main(String[] args) {
        while (true) {
            System.out.println(
                    """
                            -------\tWelcome to Restaurant Management Application\t-------\s
                            Choose option:
                            1. Menu Management
                            2. Bill Order Management
                            0. Exit
                            """
            );

            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            System.out.println(line);

            if (line.equals("0")) {
                break;
            }
        }
    }
}