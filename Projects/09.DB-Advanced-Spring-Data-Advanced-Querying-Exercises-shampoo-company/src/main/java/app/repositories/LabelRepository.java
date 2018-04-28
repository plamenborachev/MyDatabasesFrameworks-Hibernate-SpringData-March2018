package app.repositories;

import app.model.labels.BasicLabel;
import app.model.labels.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<BasicLabel, Long> {

    Label findById(long id);

}
