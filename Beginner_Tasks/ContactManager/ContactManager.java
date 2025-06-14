package contacts;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n==== Contact Management System ====");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    contacts.add(new Contact(name, phone, email));
                    System.out.println("✅ Contact added successfully!");
                    break;
                case 2:
                    System.out.println("\n--- Contact List ---");
                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println((i + 1) + ". " + contacts.get(i));
                    }
                    break;
                case 3:
                    System.out.print("Enter contact number to update: ");
                    int updateIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (updateIndex >= 0 && updateIndex < contacts.size()) {
                        System.out.print("Enter new name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter new phone: ");
                        phone = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        email = scanner.nextLine();
                        Contact c = contacts.get(updateIndex);
                        c.setName(name);
                        c.setPhone(phone);
                        c.setEmail(email);
                        System.out.println("✅ Contact updated successfully!");
                    } else {
                        System.out.println("❌ Invalid contact number.");
                    }
                    break;
                case 4:
                    System.out.print("Enter contact number to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (deleteIndex >= 0 && deleteIndex < contacts.size()) {
                        contacts.remove(deleteIndex);
                        System.out.println("✅ Contact deleted successfully!");
                    } else {
                        System.out.println("❌ Invalid contact number.");
                    }
                    break;
                case 5:
                    System.out.println("👋 Exiting Contact Manager...");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }

        } while (choice != 5);

        scanner.close();
    }
}
