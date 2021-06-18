package ro.ubb.catalog.core.repository;




import ro.ubb.catalog.core.model.Hotel;

import java.util.List;

public interface HotelRepository extends InterfaceRepository<Hotel, Long> {
    List<Hotel> findAllByStars(int stars);
}
