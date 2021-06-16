package entity.room;

public class Room {

    private Long id;

    private String name;

    private Integer price;

    private Integer places;

    private String type;

    private String state;

    public Room(Long id, Integer price, Integer places, String type, String state, String name) {
        this.id = id;
        this.price = price;
        this.places = places;
        this.type = type;
        this.state = state;
        this.name = name;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public String getTypet() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
