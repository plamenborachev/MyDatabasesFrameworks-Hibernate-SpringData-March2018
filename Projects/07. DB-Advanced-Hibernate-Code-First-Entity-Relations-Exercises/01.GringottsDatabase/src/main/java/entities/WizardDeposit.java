package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String first_name;

    @Column(length = 60, nullable = false)
    private String last_name;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(columnDefinition = "TINYINT UNSIGNED", nullable = false)
    private short age;

    @Column(length = 100)
    private String magic_wand_creator;

    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private int magic_wand_size;

    @Column(length = 20)
    private String deposit_group;

    @Column
    private Date deposit_start_date;

    @Column
    private double deposit_amount;

    @Column
    private double deposit_interest;

    @Column
    private double deposit_charge;

    @Column
    private Date deposit_expiration_date;

    @Column
    private boolean is_deposit_expired;

    public WizardDeposit() {
    }

    public WizardDeposit(String first_name, String last_name, String notes, short age, String magic_wand_creator, int magic_wand_size, String deposit_group, Date deposit_start_date, double deposit_amount, double deposit_interest, double deposit_charge, Date deposit_expiration_date, boolean is_deposit_expired) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.notes = notes;
        this.age = age;
        this.magic_wand_creator = magic_wand_creator;
        this.magic_wand_size = magic_wand_size;
        this.deposit_group = deposit_group;
        this.deposit_start_date = deposit_start_date;
        this.deposit_amount = deposit_amount;
        this.deposit_interest = deposit_interest;
        this.deposit_charge = deposit_charge;
        this.deposit_expiration_date = deposit_expiration_date;
        this.is_deposit_expired = is_deposit_expired;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public short getAge() {
        return this.age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getMagic_wand_creator() {
        return this.magic_wand_creator;
    }

    public void setMagic_wand_creator(String magic_wand_creator) {
        this.magic_wand_creator = magic_wand_creator;
    }

    public int getMagic_wand_size() {
        return this.magic_wand_size;
    }

    public void setMagic_wand_size(int magic_wand_size) {
        this.magic_wand_size = magic_wand_size;
    }

    public String getDeposit_group() {
        return this.deposit_group;
    }

    public void setDeposit_group(String deposit_group) {
        this.deposit_group = deposit_group;
    }

    public Date getDeposit_start_date() {
        return this.deposit_start_date;
    }

    public void setDeposit_start_date(Date deposit_start_date) {
        this.deposit_start_date = deposit_start_date;
    }

    public double getDeposit_amount() {
        return this.deposit_amount;
    }

    public void setDeposit_amount(double deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public double getDeposit_interest() {
        return this.deposit_interest;
    }

    public void setDeposit_interest(double deposit_interest) {
        this.deposit_interest = deposit_interest;
    }

    public double getDeposit_charge() {
        return this.deposit_charge;
    }

    public void setDeposit_charge(double deposit_charge) {
        this.deposit_charge = deposit_charge;
    }

    public Date getDeposit_expiration_date() {
        return this.deposit_expiration_date;
    }

    public void setDeposit_expiration_date(Date deposit_expiration_date) {
        this.deposit_expiration_date = deposit_expiration_date;
    }

    public boolean isIs_deposit_expired() {
        return this.is_deposit_expired;
    }

    public void setIs_deposit_expired(boolean is_deposit_expired) {
        this.is_deposit_expired = is_deposit_expired;
    }
}
