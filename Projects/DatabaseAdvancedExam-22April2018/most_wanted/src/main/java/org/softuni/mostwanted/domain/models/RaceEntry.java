package org.softuni.mostwanted.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "race_entries")
public class RaceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean hasFinished;

    @Column
    private Double finishTime;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Racer racer;

//    @ManyToOne
//    @JoinColumn(name = "race_id", referencedColumnName = "id")
//    private Race race;

    public RaceEntry() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Double getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Racer getRacer() {
        return this.racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

//    public Race getRace() {
//        return this.race;
//    }
//
//    public void setRace(Race race) {
//        this.race = race;
//    }
}
