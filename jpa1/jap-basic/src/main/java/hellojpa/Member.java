package hellojpa;

import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private String id;
    @Column(name = "USER_NAME", nullable = false)
    private String username;


    @Embedded
    private Period workPeriod;
    @Embedded
    private Address homeAddress;


    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD",joinColumns =
            @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS",joinColumns =
    @JoinColumn(name = "MEMBER_ID")
    )
    private List<Address> addressHistory = new ArrayList<>();


    public Set<String> getFavriteFoods() {
        return favriteFoods;
    }

    public void setFavriteFoods(Set<String> favriteFoods) {
        this.favriteFoods = favriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
