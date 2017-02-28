package softgroup.ua.jpa;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_data", schema = "casino")
public class UserDataEntity {
    private String passport;
    private String name;
    private String surname;
    private String patronymic;
    private byte gender;
    private Timestamp birthDay;
    private String country;
    private String city;
    private String adress;
    private String telephone;
    private UserEntity userByLoginId;

    @Id
    @Column(name = "passport")
    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "gender")
    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "birth_day")
    public Timestamp getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Timestamp birthDay) {
        this.birthDay = birthDay;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDataEntity that = (UserDataEntity) o;

        if (gender != that.gender) return false;
        if (passport != null ? !passport.equals(that.passport) : that.passport != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (birthDay != null ? !birthDay.equals(that.birthDay) : that.birthDay != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = passport != null ? passport.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (int) gender;
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "login_id", referencedColumnName = "login_id", nullable = false)
    public UserEntity getUserByLoginId() {
        return userByLoginId;
    }

    public void setUserByLoginId(UserEntity userByLoginId) {
        this.userByLoginId = userByLoginId;
    }
}
