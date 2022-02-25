package se.lexicon.LibraryWorkShop.models.entity;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int appUserId;
    @Column(unique=true)
    private String username;
    private String password;
    private LocalDate regDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_details_id")
    private Details userDetails;

    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "borrower"
    )
    private List<BookLoan> bookLoans;

    public AppUser(int appUserId, String username, String password, LocalDate regDate) {
        this.appUserId = appUserId;
        this.username = username;
        this.password = password;
        this.regDate = regDate;
    }


    public AppUser() {
    }
    public int getAppUserId() {
        return appUserId;
    }

    public List<BookLoan> getBookLoans() {
        return bookLoans;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        this.userDetails = userDetails;
    }

    public void AddBookLoan(BookLoan bookLoan){
        if (bookLoan!=null&& bookLoans!=null) bookLoans.add(bookLoan);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", userDetails=" + userDetails +
                '}';
    }


}
