package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

/**
 * @author Stanislav Rymar
 */

@XmlRootElement
public class UserData {

    private String name;
    private String surname;
    private String patronymic;
    private String passport;
    private String birthDay;
    private String gender;
    private String address;
    private String city;
    private String country;
    private String telephone;

    public String getName() {
        return name;
    }

    @XmlElement(required = true)
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement(required = true)
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    @XmlElement
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassport() {
        return passport;
    }

    @XmlElement(required = true)
    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getBirthDay() {
        return birthDay;
    }

    @XmlElement(required = true)
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    @XmlElement(required = true)
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    @XmlElement(required = true)
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    @XmlElement(required = true)
    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    @XmlElement(required = true)
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


}
