package app.service;

import app.domain.model.PhoneNumber;

/**
 * Created by User on 30.7.2017 г..
 */
public interface PhoneService {

    PhoneNumber create (PhoneNumber phoneNumber);

    void save(PhoneNumber phoneNumber);
}
