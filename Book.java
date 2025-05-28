public class Book extends Object {
    private String title;
    private String author;
    private String isbn;
    private boolean availability;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return isbn;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
