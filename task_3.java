import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public void borrowBook() { isAvailable = false; }
    public void returnBook() { isAvailable = true; }

    @Override
    public String toString() {
        return title + " by " + author + " [ISBN: " + isbn + "] - " + 
               (isAvailable ? "Available" : "Not Available");
    }
}

class Member {
    private String name;
    private String memberId;
    private List<Book> borrowedBooks;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getMemberId() { return memberId; }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.borrowBook();
            System.out.println(name + " borrowed: " + book.getTitle());
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
            System.out.println(name + " returned: " + book.getTitle());
        } else {
            System.out.println("You don't have this book.");
        }
    }

    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " has not borrowed any books.");
        } else {
            System.out.println(name + " borrowed books:");
            for (Book b : borrowedBooks) {
                System.out.println(" - " + b);
            }
        }
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }
    public void addMember(Member member) { members.add(member); }

    public void viewBooks() {
        System.out.println("\n--- Library Books ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public Member findMember(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) return m;
        }
        return null;
    }

    public Book findBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        library.addBook(new Book("Harry Potter", "J.K. Rowling", "101"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "102"));
        library.addMember(new Member("Alice", "M001"));
        library.addMember(new Member("Bob", "M002"));

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Member Borrowed Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    library.viewBooks();
                    break;
                case 2:
                    System.out.print("Enter Member ID: ");
                    String mid = sc.nextLine();
                    Member mem = library.findMember(mid);
                    if (mem == null) {
                        System.out.println("Member not found.");
                        break;
                    }
                    System.out.print("Enter Book ISBN: ");
                    String bisbn = sc.nextLine();
                    Book book = library.findBook(bisbn);
                    if (book == null) {
                        System.out.println("Book not found.");
                        break;
                    }
                    mem.borrowBook(book);
                    break;
                case 3:
                    System.out.print("Enter Member ID: ");
                    mid = sc.nextLine();
                    mem = library.findMember(mid);
                    if (mem == null) {
                        System.out.println("Member not found.");
                        break;
                    }
                    System.out.print("Enter Book ISBN: ");
                    bisbn = sc.nextLine();
                    book = library.findBook(bisbn);
                    if (book == null) {
                        System.out.println("Book not found.");
                        break;
                    }
                    mem.returnBook(book);
                    break;
                case 4:
                    System.out.print("Enter Member ID: ");
                    mid = sc.nextLine();
                    mem = library.findMember(mid);
                    if (mem != null) {
                        mem.viewBorrowedBooks();
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
