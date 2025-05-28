import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        boolean answerOtherOperationValidation;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to the Library Management System!\n" +
                    "Select one of the following options:\n" +
                    "1. Add new books to the library.\n" +
                    "2. Register new members.\n" +
                    "3. Borrow books for members.\n" +
                    "4. Display the list of available books.\n" +
                    "5. Display the list of registered members.\n" +
                    "6. Display the list of registered books.\n" );
            int option = sc.nextInt();
            String answer = "";
            switch (option) {
                case 1:
                    boolean answerAddNewBook;
                    do{
                        System.out.println("Enter the book title: ");
                        String bookTitle = sc.next();
                        System.out.println("Enter the book author: ");
                        String bookAuthor = sc.next();
                        System.out.println("Enter the book ISBN: ");
                        String bookISBN = sc.next();
                        System.out.println("Is the book an eBook? (Y/N): ");
                        answer = sc.next();
                        if (answer.equalsIgnoreCase("y")) {
                            EBook newEBook = new EBook();
                            System.out.println("Enter the file format: ");
                            String EBookFileFormat = sc.next();
                            newEBook.setFileFormat(EBookFileFormat);
                            System.out.println("Enter the file size: ");
                            String EBookFileSize = sc.next();
                            newEBook.setFileSize(EBookFileSize);
                            newEBook.setTitle(bookTitle);
                            newEBook.setAuthor(bookAuthor);
                            newEBook.setISBN(bookISBN);
                            newEBook.setAvailability(true);
                            library.addBook(newEBook);
                        } else if (answer.equalsIgnoreCase("n")) {
                            Book newBook = new Book();
                            newBook.setTitle(bookTitle);
                            newBook.setAuthor(bookAuthor);
                            newBook.setISBN(bookISBN);
                            newBook.setAvailability(true);
                            library.addBook(newBook);
                        }
                        System.out.print("Do you want to regiter another book? (Y/N): ");
                        String answerAddBook = sc.next();
                        if (answerAddBook.equalsIgnoreCase("Y")) {
                            answerAddNewBook = true;
                        } else if (answerAddBook.equalsIgnoreCase("N")) {
                            answerAddNewBook = false;
                        } else {
                            System.out.println("You have entered an invalid option!");
                            answerAddNewBook = false;
                        }
                    }while(answerAddNewBook);
                    break;
                case 2:
                    boolean answerAddNewMember;
                    do{
                        System.out.println("Enter the name of the member: ");
                        String memberName = sc.next();
                        System.out.println("Enter the member ID: ");
                        String memberID = sc.next();
                        System.out.println("Is the member a premium member? (Y/N): ");
                        answer = sc.next();
                        if (answer.equalsIgnoreCase("y")) {
                            System.out.println("Enter membership expiration date: ");
                            String expirationDate = sc.next();
                            PremiumMember newPremiumMember = new PremiumMember();
                            newPremiumMember.setName(memberName);
                            newPremiumMember.setMemberId(memberID);
                            newPremiumMember.setMembershipExpiration(expirationDate);
                            library.addMember(newPremiumMember);
                        } else if (answer.equalsIgnoreCase("n")) {
                            Member newMember = new Member();
                            newMember.setName(memberName);
                            newMember.setMemberId(memberID);
                            library.addMember(newMember);
                        }
                        System.out.print("Do you want to register another member? (Y/N): ");
                        String answerAddMember = sc.next();
                        if (answerAddMember.equalsIgnoreCase("Y")) {
                            answerAddNewMember = true;
                        } else if (answerAddMember.equalsIgnoreCase("N")) {
                            answerAddNewMember = false;
                        } else {
                            System.out.println("You have entered an invalid option!");
                            answerAddNewMember = false;
                        }
                    }while(answerAddNewMember);
                    break;
                case 3:
                    boolean answerNewBorrowedBook;
                    do{
                        System.out.println("Enter the memberID: ");
                        String existingMemberID = sc.next();
                        System.out.println("Enter the book ISBN: ");
                        String existingBookISBN = sc.next();
                        library.borrowBook(existingMemberID, existingBookISBN);
                        System.out.print("Do you want to register another borrowed book? (Y/N): ");
                        String answerBorrowBook = sc.next();
                        if (answerBorrowBook.equalsIgnoreCase("Y")) {
                            answerNewBorrowedBook = true;
                        } else if (answerBorrowBook.equalsIgnoreCase("N")) {
                            answerNewBorrowedBook = false;
                        } else {
                            System.out.println("You have entered an invalid option!");
                            answerNewBorrowedBook = false;
                        }
                    }while(answerNewBorrowedBook);
                    break;
                case 4:
                    ArrayList<Object> books = library.getBooks();
                    if(!books.isEmpty()) {
                        for (Object book : books) {
                            if (book instanceof EBook existingEBook) {
                                if (existingEBook.getAvailability()){
                                    System.out.println("Title: " + existingEBook.getTitle() + " Author: " + existingEBook.getAuthor() + " ISBN: " + existingEBook.getISBN() + " Availability: " + existingEBook.getAvailability() + " File format: " + existingEBook.getFileFormat() + " File Size: " + existingEBook.getFileSize());
                                }
                            } else if (book instanceof Book existingBook) {
                                if (existingBook.getAvailability()){
                                    System.out.println("Title: " + existingBook.getTitle() + " Author: " + existingBook.getAuthor() + " ISBN: " + existingBook.getISBN() + " Availability: " + existingBook.getAvailability());
                                }
                            }
                        }
                    } else {
                        System.out.println("There is no books available in the library");
                    }
                    break;
                case 5:
                    ArrayList<Object> members = library.getMembers();
                    if(!members.isEmpty()) {
                        for (Object member : members) {
                            if (member instanceof PremiumMember existingPremiumMember) {
                                System.out.println("Name: " + existingPremiumMember.getName() + " MemberID: " + existingPremiumMember.getMemberId() + " Membership expiration: " + existingPremiumMember.getMembershipExpiration());
                                System.out.println("Borrowed books:");
                                ArrayList<Book> borrowedBooks = existingPremiumMember.getBorrowedBooks();
                                if(!(borrowedBooks == null)){
                                    for (Book borrowedBook : borrowedBooks) {
                                        System.out.println("Title: " + borrowedBook.getTitle() + " Author: " + borrowedBook.getAuthor() + " ISBN: " + borrowedBook.getISBN());
                                    }
                                } else {
                                    System.out.println("No borrowed books for this member");
                                }
                            } else if (member instanceof Member existingMember) {
                                System.out.println("Name: " + existingMember.getName() + " MemberID: " + existingMember.getMemberId());
                                System.out.println("Borrowed books:");
                                ArrayList<Book> borrowedBooks = existingMember.getBorrowedBooks();
                                if(!(borrowedBooks == null)){
                                    for (Book borrowedBook : borrowedBooks) {
                                        System.out.println("Title: " + borrowedBook.getTitle() + " Author: " + borrowedBook.getAuthor() + " ISBN: " + borrowedBook.getISBN());
                                    }
                                } else {
                                    System.out.println("No borrowed books for this member");
                                }
                            }
                            System.out.println("- - - - - - - - - - - - - - - - - - - - - - ");
                        }
                    } else {
                        System.out.println("There is no members registered");
                    }
                    break;
                case 6:
                    ArrayList<Object> registeredBooks = library.getBooks();
                    if(!registeredBooks.isEmpty()){
                        for (Object book : registeredBooks) {
                            if (book instanceof EBook existingEBook) {
                                System.out.println("Title: " + existingEBook.getTitle() + " Author: " + existingEBook.getAuthor() + " ISBN: " + existingEBook.getISBN() + " Availability: " + existingEBook.getAvailability() + " File format: " + existingEBook.getFileFormat() + " File Size: " + existingEBook.getFileSize());
                            } else if (book instanceof Book existingBook) {
                                System.out.println("Title: " + existingBook.getTitle() + " Author: " + existingBook.getAuthor() + " ISBN: " + existingBook.getISBN() + " Availability: " + existingBook.getAvailability());
                            }
                        }
                    } else {
                        System.out.println("No registered books");
                    }
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
            System.out.print("Do you want to make another operation? (Y/N): ");
            String answerOtherOperation = sc.next();
            if (answerOtherOperation.equalsIgnoreCase("Y")) {
                answerOtherOperationValidation = true;
            } else if (answerOtherOperation.equalsIgnoreCase("N")) {
                answerOtherOperationValidation = false;
            } else {
                System.out.println("You have entered an invalid option!");
                answerOtherOperationValidation = false;
            }
        } while (answerOtherOperationValidation);
    }
}
