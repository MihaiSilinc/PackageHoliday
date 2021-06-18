package ro.ubb.catalog.core.service;



import ro.ubb.catalog.core.model.Hotel;
import ro.ubb.catalog.core.model.Pair;
import ro.ubb.catalog.core.model.exceptions.ValidatorException;

import java.io.IOException;
import java.util.List;

public interface InterfaceHotelService {
    List<Hotel> getHotels();

    void addHotel(Hotel Hotel) throws ValidatorException, IOException;

    void removeHotel(Long idHotel) throws ValidatorException, IOException;

    void updateHotel(Hotel Hotel) throws ValidatorException, IOException;

    List<Hotel> filterHotel(int stars);

    List<Pair<Hotel, Long>> reportHotels();
}
