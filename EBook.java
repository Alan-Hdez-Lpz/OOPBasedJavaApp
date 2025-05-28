public class EBook extends Book {
    private String fileFormat;
    private String fileSize;

    public String getFileFormat() {
        return fileFormat;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String getTitle() {
        return super.getTitle() + " (EBook)";
    }
}
