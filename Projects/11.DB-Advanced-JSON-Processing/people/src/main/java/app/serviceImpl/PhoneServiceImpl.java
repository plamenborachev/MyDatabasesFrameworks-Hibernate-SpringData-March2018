package app.serviceImpl;

import app.domain.model.PhoneNumber;
import app.repository.PersonRepository;
import app.repository.PhoneRepository;
import app.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by User on 30.7.2017 г..
 */
@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public PhoneNumber create(PhoneNumber phoneNumber) {

        return this.phoneRepository.save(phoneNumber);
    }

    @Override
    public void save(PhoneNumber phoneNumber) {
        this.phoneRepository.save(phoneNumber);
    }
}
