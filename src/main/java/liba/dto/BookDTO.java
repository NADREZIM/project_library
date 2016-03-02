package liba.dto;

import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public class BookDTO {
    private long id;
    private String author;
    private String title;
    private int count;
    private List<UserDTO> users;
    private List<ReportDTO>report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users)
    {
        if (users==null)
            this.users = null;
        else
            this.users = users;
    }

    public List<ReportDTO> getReport()
    {
        return report;
    }

    public void setReport(List<ReportDTO> report) {
        if (report==null)
            this.report = null;
        else
            this.report = report;

    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", users=" + users +
                ", report=" + report +
                '}';
    }
}
