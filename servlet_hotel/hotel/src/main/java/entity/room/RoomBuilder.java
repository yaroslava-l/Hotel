package entity.room;

public class RoomBuilder {
    private Long id;

    private String name;

    private Integer price;

    private Integer places;

    private String type;

    private String state;


    public RoomBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public RoomBuilder setPrice(Integer price) {
        this.price = price;
        return this;

    }

    public RoomBuilder setPlaces(Integer places) {
        this.places = places;
        return this;

    }

    public RoomBuilder setType(String type) {
        this.type = type;
        return this;

    }

    public RoomBuilder setState(String state) {
        this.state = state;
        return this;

    }

    public RoomBuilder setName(String name) {
        this.name = name;
        return this;

    }

    public Room build() {
        return new Room(id, price, places, type, state, name);
    }
}
