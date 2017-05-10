package application.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="fullName", nullable=false)
    private String fullName;

    @NotBlank
    @Column(name="idCardSeries", nullable=false)
    private String idCardSeries;

    @NotBlank
    @Column(name="idCardNumber", nullable=false)
    private String idCardNumber;

    @NotBlank
    @Column(name="personalNumericalCode", nullable=false, unique = true)
    private String personalNumericalCode;

    @NotBlank
    @Column(name="address", nullable=false)
    private String address;

    public Patient() {
    }

    public Patient(String fullName, String idCardSeries, String idCardNumber, String personalNumericalCode, String address) {
        this.fullName = fullName;
        this.idCardSeries = idCardSeries;
        this.idCardNumber = idCardNumber;
        this.personalNumericalCode = personalNumericalCode;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdCardSeries() {
        return idCardSeries;
    }

    public void setIdCardSeries(String idCardSeries) {
        this.idCardSeries = idCardSeries;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPersonalNumericalCode() {
        return personalNumericalCode;
    }

    public void setPersonalNumericalCode(String personalNumericalCode) {
        this.personalNumericalCode = personalNumericalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth(){
        StringBuilder date = new StringBuilder();

        date.append(this.personalNumericalCode.substring(5,7));
        date.append("-");
        date.append(this.personalNumericalCode.substring(3,5));
        date.append("-");

        int year =Integer.parseInt(this.personalNumericalCode.substring(0,1));

        switch(year){
            case 1:
            case 2:
                date.append("19");
                break;
            case 3:
            case 4:
                date.append("18");
                break;
            case 5:
            case 6:
                date.append("20");
                break;
            default:
                date.append("19");
                break;
        }

        date.append(this.personalNumericalCode.substring(1,3));

        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        try {
            return format.parse(date.toString());
        } catch (ParseException e) {
            return new Date();
        }
    }
}
