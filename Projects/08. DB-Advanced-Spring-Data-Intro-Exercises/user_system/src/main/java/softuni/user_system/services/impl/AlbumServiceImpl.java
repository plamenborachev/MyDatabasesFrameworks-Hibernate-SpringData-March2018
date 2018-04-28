package softuni.user_system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.user_system.entities.Album;
import softuni.user_system.repositories.AlbumRepository;
import softuni.user_system.services.interfaces.AlbumService;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void persist(Album album) {
        this.albumRepository.save(album);
    }
}
