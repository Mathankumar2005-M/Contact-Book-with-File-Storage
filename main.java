import java.io.*;
import java.util.*;

// Contact class to represent individual contacts
class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    
    // Constructor
    public Contact(String name, String phoneNumber, String email, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
    
    // Getters
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    
    @Override
    public String toString() {
        return String.format("Name: %s\nPhone: %s\nEmail: %s\nAddress: %s", 
                           name, phoneNumber, email, address);
    }
}

// ContactBook class to manage contacts and file operations
class ContactBook {
    private ArrayList<Contact> contacts;
    private final String fileName = "contacts.dat";
    
    public ContactBook() {
        contacts = new ArrayList<>();
        loadContactsFromFile();
    }
    
    // Add a new contact
    public boolean addContact(String name, String phoneNumber, String email, String address) {
        // Check if contact already exists
        if (findContactByName(name) != null) {
            System.out.println("Contact with this name already exists!");
            return false;
        }
        
        Contact newContact = new Contact(name, phoneNumber, email, address);
        contacts.add(newContact);
        saveContactsToFile();
        System.out.println("Contact added successfully!");
        return true;
    }
    
    // Display all contacts
    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found!");
            return;
        }
        
        System.out.println("\n========== ALL CONTACTS ==========");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println("Contact " + (i + 1) + ":");
            System.out.println(contacts.get(i));
            System.out.println("--------------------------------");
        }
    }
    
    // Search contact by name
    public void searchContact(String name) {
        Contact contact = findContactByName(name);
        if (contact != null) {
            System.out.println("\n========== CONTACT FOUND ==========");
            System.out.println(contact);
        } else {
            System.out.println("Contact not found!");
        }
    }
    
    // Update existing contact
    public boolean updateContact(String name) {
        Contact contact = findContactByName(name);
        if (contact == null) {
            System.out.println("Contact not found!");
            return false;
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n========== UPDATE CONTACT ==========");
        System.out.println("Current details:");
        System.out.println(contact);
        
        System.out.print("Enter new name (or press Enter to keep current): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            contact.setName(newName);
        }
        
        System.out.print("Enter new phone number (or press Enter to keep current): ");
        String newPhone = scanner.nextLine().trim();
        if (!newPhone.isEmpty()) {
            contact.setPhoneNumber(newPhone);
        }
        
        System.out.print("Enter new email (or press Enter to keep current): ");
        String newEmail = scanner.nextLine().trim();
        if (!newEmail.isEmpty()) {
            contact.setEmail(newEmail);
        }
        
        System.out.print("Enter new address (or press Enter to keep current): ");
        String newAddress = scanner.nextLine().trim();
        if (!newAddress.isEmpty()) {
            contact.setAddress(newAddress);
        }
        
        saveContactsToFile();
        System.out.println("Contact updated successfully!");
        return true;
    }
    
    // Delete contact
    public boolean deleteContact(String name) {
        Contact contact = findContactByName(name);
        if (contact != null) {
            contacts.remove(contact);
            saveContactsToFile();
            System.out.println("Contact deleted successfully!");
            return true;
        } else {
            System.out.println("Contact not found!");
            return false;
        }
    }
    
    // Helper method to find contact by name
    private Contact findContactByName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }
    
    // Save contacts to file
    private void saveContactsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }
    
    // Load contacts from file
    @SuppressWarnings("unchecked")
    private void loadContactsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            contacts = (ArrayList<Contact>) ois.readObject();
            System.out.println("Contacts loaded from file successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("No existing contact file found. Starting with empty contact book.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }
    
    // Get total number of contacts
    public int getTotalContacts() {
        return contacts.size();
    }
    
    // Export contacts to text file
    public void exportContactsToText() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to export!");
            return;
        }
        
        try (PrintWriter writer = new PrintWriter("contacts_backup.txt")) {
            writer.println("========== CONTACT BOOK BACKUP ==========");
            writer.println("Total Contacts: " + contacts.size());
            writer.println("Export Date: " + new Date());
            writer.println("==========================================\n");
            
            for (int i = 0; i < contacts.size(); i++) {
                writer.println("Contact " + (i + 1) + ":");
                writer.println(contacts.get(i));
                writer.println("----------------------------------------");
            }
            
            System.out.println("Contacts exported to 'contacts_backup.txt' successfully!");
        } catch (IOException e) {
            System.out.println("Error exporting contacts: " + e.getMessage());
        }
    }
}

// Main class with user interface
public class ContactBookApp {
    private static ContactBook contactBook = new ContactBook();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    WELCOME TO CONTACT BOOK APP");
        System.out.println("========================================");
        
        while (true) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addNewContact();
                    break;
                case 2:
                    contactBook.displayAllContacts();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    updateContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    exportContacts();
                    break;
                case 7:
                    displayStats();
                    break;
                case 8:
                    System.out.println("Thank you for using Contact Book App!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Add New Contact");
        System.out.println("2. Display All Contacts");
        System.out.println("3. Search Contact");
        System.out.println("4. Update Contact");
        System.out.println("5. Delete Contact");
        System.out.println("6. Export Contacts");
        System.out.println("7. Contact Statistics");
        System.out.println("8. Exit");
        System.out.print("Enter your choice (1-8): ");
    }
    
    private static int getChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void addNewContact() {
        System.out.println("\n========== ADD NEW CONTACT ==========");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Enter address: ");
        String address = scanner.nextLine().trim();
        
        contactBook.addContact(name, phone, email, address);
    }
    
    private static void searchContact() {
        System.out.println("\n========== SEARCH CONTACT ==========");
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().trim();
        
        if (!name.isEmpty()) {
            contactBook.searchContact(name);
        } else {
            System.out.println("Please enter a valid name!");
        }
    }
    
    private static void updateContact() {
        System.out.println("\n========== UPDATE CONTACT ==========");
        System.out.print("Enter name of contact to update: ");
        String name = scanner.nextLine().trim();
        
        if (!name.isEmpty()) {
            contactBook.updateContact(name);
        } else {
            System.out.println("Please enter a valid name!");
        }
    }
    
    private static void deleteContact() {
        System.out.println("\n========== DELETE CONTACT ==========");
        System.out.print("Enter name of contact to delete: ");
        String name = scanner.nextLine().trim();
        
        if (!name.isEmpty()) {
            System.out.print("Are you sure you want to delete this contact? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (confirm.equals("y") || confirm.equals("yes")) {
                contactBook.deleteContact(name);
            } else {
                System.out.println("Delete operation cancelled.");
            }
        } else {
            System.out.println("Please enter a valid name!");
        }
    }
    
    private static void exportContacts() {
        System.out.println("\n========== EXPORT CONTACTS ==========");
        contactBook.exportContactsToText();
    }
    
    private static void displayStats() {
        System.out.println("\n========== CONTACT STATISTICS ==========");
        System.out.println("Total Contacts: " + contactBook.getTotalContacts());
        System.out.println("Storage File: contacts.dat");
        System.out.println("Backup File: contacts_backup.txt");
    }
}
