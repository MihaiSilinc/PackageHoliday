package ro.ubb.catalog.web.converter;


import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Hotel;
import ro.ubb.catalog.web.dto.HotelDto;

@Component
public class HotelConverter extends BaseConverter<Hotel, HotelDto> {
    @Override
    public Hotel convertDtoToModel(HotelDto dto) {
        Hotel model = new Hotel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setLocation(dto.getLocation());
        model.setStars(dto.getStars());
        model.setCapacity(dto.getCapacity());

        return model;
    }

    @Override
    public HotelDto convertModelToDto(Hotel hotel) {
        HotelDto dto = new HotelDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setLocation(hotel.getLocation());
        dto.setStars(hotel.getStars());
        dto.setCapacity(hotel.getCapacity());
        return dto;
    }
}
