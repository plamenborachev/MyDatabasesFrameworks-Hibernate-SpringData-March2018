package softuni.user_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.user_system.entities.Picture;
import softuni.user_system.entities.User;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

}
