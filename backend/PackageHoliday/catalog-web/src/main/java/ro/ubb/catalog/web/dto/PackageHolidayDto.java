package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageHolidayDto extends BaseDto{
    private Long customerId;
    private Long hotelId;
    private String startDate;
    private String endDate;
    private int price;
}
