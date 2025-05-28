import java.util.ArrayList;
import java.util.List;

public class Library {
    private ArrayList<Object> books = new ArrayList<>();
    private ArrayList<Object> members = new ArrayList<>();

    public ArrayList<Object> getBooks() {
        return books;
    }

    public ArrayList<Object> getMembers() {
        return members;
    }

    public void setBooks(ArrayList<Object> books) {
        this.books = books;
    }

    public void setMembers(ArrayList<Object> members) {
        this.members = members;
    }

    public void addBook(Book book) {
        ArrayList<Object> finalBooks = getBooks();
        finalBooks.add(book);
        setBooks(finalBooks);
        System.out.println("Book added");
    }

    public void addBook(EBook eBook) {
        ArrayList<Object> finalBooks = getBooks();
        finalBooks.add(eBook);
        setBooks(finalBooks);
        System.out.println("EBook added");
    }

    public void addMember(Member member) {
        ArrayList<Object> finalMembers = getMembers();
        finalMembers.add(member);
        setMembers(finalMembers);
        System.out.println("Member added");
    }

    public void addMember(PremiumMember premiumMember) {
        ArrayList<Object> finalMembers = getMembers();
        finalMembers.add(premiumMember);
        setMembers(finalMembers);
        System.out.println("Premium member added");
    }

    public void borrowBook(String memberID, String isbn) {
        if(validateBook(isbn) && validateMember(memberID)){
            addBookToMember(memberID,getBook(isbn));
        } else {
            System.out.println("Book not available/registered or member not registered");
        }
    }

    public boolean validateBook(String isbn) {
        ArrayList<Object> registeredBooks = getBooks();
        boolean validation = false;
        if(!registeredBooks.isEmpty()) {
            for (Object book : registeredBooks) {
                if (!(book instanceof EBook)) {
                    Book registeredBook = (Book) book;
                    if (registeredBook.getISBN().equals(isbn)) {
                        validation = true;
                    }
                }
            }
        } else {
            System.out.println("Not registered books");
        }
        return validation;
    }

    public Book getBook(String isbn) {
        ArrayList<Object> registeredBooks = getBooks();
        Book foundBook = null;
        for (Object book : registeredBooks) {
            if (!(book instanceof EBook)) {
                Book registeredBook = (Book) book;
                if (registeredBook.getISBN().equals(isbn)) {
                    foundBook = registeredBook;
                }
            }
        }
        return foundBook;
    }

    public boolean validateMember(String memberID) {
        ArrayList<Object> members = getMembers();
        boolean validation = false;
        if(!members.isEmpty()) {
            for (Object member : members) {
                if (member instanceof PremiumMember existingPremiumMember) {
                    if(existingPremiumMember.getMemberId().equals(memberID)) {
                        validation = true;
                    }
                } else if (member instanceof Member existingMember) {
                    if(existingMember.getMemberId().equals(memberID)) {
                        validation = true;
                    }
                }
            }
        } else {
            System.out.println("Not registered members");
        }
        return validation;
    }

    public void updateBookAvailability(String isbn) {
        ArrayList<Object> registeredBooks = getBooks();
        for (Object book : registeredBooks) {
            if (!(book instanceof EBook)) {
                Book registeredBook = (Book) book;
                if (registeredBook.getISBN().equals(isbn)) {
                    registeredBook.setAvailability(false);
                }
            }
        }
    }

    public void addBookToMember(String memberID, Book book) {
        ArrayList<Object> members = getMembers();
        for (Object member : members) {
            if (member instanceof PremiumMember existingPremiumMember) {
                if(existingPremiumMember.getMemberId().equals(memberID)) {
                    ArrayList<Book> borrowedBooks = existingPremiumMember.getBorrowedBooks();
                    if (!(existingPremiumMember.getBorrowedBooks() == null)){
                        if (existingPremiumMember.getBorrowedBooks().size() < 1){
                            borrowedBooks.add(book);
                            existingPremiumMember.setBorrowedBooks(borrowedBooks);
                            updateBookAvailability(book.getISBN());
                            System.out.println("Book borrowed");
                        } else {
                            borrowedBooks = existingPremiumMember.getExtraBorrowedBooks();
                            if(borrowedBooks == null){
                                borrowedBooks = new ArrayList<>();
                            }
                            borrowedBooks.add(book);
                            existingPremiumMember.setExtraBorrowedBooks(borrowedBooks);
                            updateBookAvailability(book.getISBN());
                            System.out.println("Book borrowed");
                        }
                    } else{
                        borrowedBooks = new ArrayList<>();
                        borrowedBooks.add(book);
                        existingPremiumMember.setBorrowedBooks(borrowedBooks);
                        updateBookAvailability(book.getISBN());
                        System.out.println("Book borrowed");
                    }
                }
            } else if (member instanceof Member existingMember) {
                if(existingMember.getMemberId().equals(memberID)) {
                    ArrayList<Book> borrowedBooks = existingMember.getBorrowedBooks();
                    if (!(existingMember.getBorrowedBooks() == null)){
                        if (existingMember.getBorrowedBooks().size() < 1){
                            borrowedBooks.add(book);
                            existingMember.setBorrowedBooks(borrowedBooks);
                            updateBookAvailability(book.getISBN());
                            System.out.println("Book borrowed");
                        } else {
                            System.out.println("you exceeded the borrowed books number be a premium member\n");
                        }
                    } else {
                        borrowedBooks = new ArrayList<>();
                        borrowedBooks.add(book);
                        existingMember.setBorrowedBooks(borrowedBooks);
                        updateBookAvailability(book.getISBN());
                        System.out.println("Book borrowed");
                    }
                }
            }
        }
    }
}
