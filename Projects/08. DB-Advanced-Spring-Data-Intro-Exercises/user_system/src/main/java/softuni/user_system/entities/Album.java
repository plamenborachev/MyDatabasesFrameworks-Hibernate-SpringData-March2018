package softuni.user_system.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "backgroundColor")
    private String backgroundColor;

    @Column(name = "is_public")
    private boolean isPublic;

    @ManyToMany
    @JoinTable(name = "albums_pictures",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "picture_id"))
    private Set<Picture> pictures;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User owner;

    public Album() {
        this.pictures = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<Picture> getPictures() {
        return this.pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
}
