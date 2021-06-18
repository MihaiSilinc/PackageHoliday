package ro.ubb.catalog.core.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.Calendar;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageHoliday extends BaseEntity<Long> {
    private Long customerId;
    private Long hotelId;
    private String startDate;
    private String endDate;
    private int price;


public PackageHoliday(Long id, Long customerId, Long hotelId, String startDate, String endDate, int price) {
        this.setId(id);
        this.customerId = customerId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
}

