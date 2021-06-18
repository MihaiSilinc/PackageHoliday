package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Hotel extends BaseEntity<Long> {
    private String name;
    private String location;
    private int stars;
    private int capacity;

    public Hotel(Long id, String name, String location, int stars, int capacity) {
        this.setId(id);
        this.name = name;
        this.location = location;
        this.stars = stars;
        this.capacity = capacity;
    }
}
