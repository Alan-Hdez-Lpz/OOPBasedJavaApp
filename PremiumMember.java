import java.util.ArrayList;

public class PremiumMember  extends Member{
    private String membershipExpiration;

    private ArrayList<Book> extraBorrowedBooks;

    public ArrayList<Book> getExtraBorrowedBooks() {
        return extraBorrowedBooks;
    }

    public String getMembershipExpiration() {
        return membershipExpiration;
    }

    public void setExtraBorrowedBooks(ArrayList<Book> extraBorrowedBooks) {
        this.extraBorrowedBooks = extraBorrowedBooks;
    }

    public void setMembershipExpiration(String membershipExpiration) {
        this.membershipExpiration = membershipExpiration;
    }

    @Override
    public String getName() {
        return super.getName() + " (Premium member)";
    }

    @Override
    public ArrayList<Book> getBorrowedBooks() {
        ArrayList<Book> borrowedBooksExtra = getExtraBorrowedBooks();
        if(!(borrowedBooksExtra == null)){
            for(Book book : borrowedBooksExtra){
                super.getBorrowedBooks().add(book);
            }
        }
        return super.getBorrowedBooks();
    }
}
