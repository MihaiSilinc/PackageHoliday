package ro.ubb.catalog.core.model;
import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Customer extends BaseEntity<Long> {
    private String name;
    private int age;

    public Customer(Long id, String name, int age) {
        this.setId(id);
        this.name = name;
        this.age = age;
    }
}
