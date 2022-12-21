package bg.softuni.musicdbapp.model.view;

public class ArtistViewModel {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public ArtistViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArtistViewModel setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "ArtistViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
