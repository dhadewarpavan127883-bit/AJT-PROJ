/*
 * Library Management System - Console Version
 * Project Name: mini
 * Author: DHADEWAR
 */

package mini;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Book class represents a single book in the library.
 */
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    // Constructor to initialize book details
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    // Display one book record
    public void displayBook() {
        String status = isIssued ? "Issued" : "Available";
        System.out.printf("%-5d %-20s %-20s %-10s\n", id, title, author, status);
    }
}

/**
 * Library class handles all book-related operations.
 */
class Library {
    private final ArrayList<Book> books = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    /**
     * Add a new book to the library.
     */
    public void addBook() {
        try {
            System.out.println("\n--- Add New Book ---");
            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // clear buffer

            // Check for duplicate ID
            for (Book b : books) {
                if (b.id == id) {
                    System.out.println("⚠️ Book ID already exists! Try again.");
                    return;
                }
            }

            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();
            System.out.print("Enter Author Name: ");
            String author = sc.nextLine();

            books.add(new Book(id, title, author));
            System.out.println("✅ Book Added Successfully!");
        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input! Please enter numeric ID.");
            sc.nextLine(); // clear invalid input
        }
    }

    /**
     * Display all books in the library.
     */
    public void displayBooks() {
        System.out.println("\n--- Library Books ---");
        if (books.isEmpty()) {
            System.out.println("No books available in the library!");
            return;
        }
        System.out.printf("%-5s %-20s %-20s %-10s\n", "ID", "Title", "Author", "Status");
        System.out.println("------------------------------------------------------------");
        for (Book b : books) {
            b.displayBook();
        }
    }

    /**
     * Issue a book to a student.
     */
    public void issueBook() {
        System.out.println("\n--- Issue Book ---");
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                if (!b.isIssued) {
                    b.isIssued = true;
                    System.out.println("📘 Book Issued Successfully!");
                } else {
                    System.out.println("⚠️ Book is already issued!");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }

    /**
     * Return a book to the library.
     */
    public void returnBook() {
        System.out.println("\n--- Return Book ---");
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                if (b.isIssued) {
                    b.isIssued = false;
                    System.out.println("📗 Book Returned Successfully!");
                } else {
                    System.out.println("⚠️ Book was not issued!");
                }
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }

    /**
     * Search a book by title or author.
     */
    public void searchBook() {
        sc.nextLine(); // clear buffer
        System.out.println("\n--- Search Book ---");
        System.out.print("Enter keyword (Title or Author): ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Book b : books) {
            if (b.title.toLowerCase().contains(keyword) || b.author.toLowerCase().contains(keyword)) {
                if (!found) {
                    System.out.printf("%-5s %-20s %-20s %-10s\n", "ID", "Title", "Author", "Status");
                    System.out.println("------------------------------------------------------------");
                }
                b.displayBook();
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ No matching books found!");
        }
    }

    /**
     * Remove a book permanently from the library.
     */
    public void removeBook() {
        System.out.println("\n--- Remove Book ---");
        System.out.print("Enter Book ID to remove: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                books.remove(b);
                System.out.println("🗑️ Book Removed Successfully!");
                return;
            }
        }
        System.out.println("❌ Book not found!");
    }
}

/**
 * Main class - Entry point of the program.
 */
public class Mini {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        int choice;

        do {
            System.out.println("\n========= 📚 Library Management System =========");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Remove Book");
            System.out.println("7. Exit");
            System.out.println("===============================================");
            System.out.print("Enter your choice: ");
            
            while (!sc.hasNextInt()) {
                System.out.println("❌ Please enter a valid number!");
                sc.next();
                System.out.print("Enter your choice: ");
            }
            
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> library.addBook();
                case 2 -> library.displayBooks();
                case 3 -> library.issueBook();
                case 4 -> library.returnBook();
                case 5 -> library.searchBook();
                case 6 -> library.removeBook();
                case 7 -> System.out.println("👋 Exiting Library System. Goodbye!");
                default -> System.out.println("⚠️ Invalid choice! Please try again.");
            }

        } while (choice != 7);
    }
}
