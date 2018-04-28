package softuni.user_system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.user_system.entities.Picture;
import softuni.user_system.repositories.PictureRepository;
import softuni.user_system.services.interfaces.PictureService;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void persist(Picture picture) {
        this.pictureRepository.save(picture);
    }
}
