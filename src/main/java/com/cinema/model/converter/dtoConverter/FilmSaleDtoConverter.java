package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmSaleDto;
import com.cinema.model.statistics.FilmSale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FilmSaleDtoConverter implements Converter<FilmSale, FilmSaleDto> {

    private static final Logger LOGGER = LogManager.getLogger(FilmSaleDtoConverter.class);

    @Override
    public FilmSaleDto convert(FilmSale filmSale) {
        FilmSaleDto filmSaleDto = new FilmSaleDto();
        filmSaleDto.setFilmId(filmSale.getFilm().getId());
        filmSaleDto.setFilmName(filmSale.getFilm().getName());
        filmSaleDto.setFilmNameEnglish(filmSale.getFilm().getNameEnglish());
        filmSaleDto.setReleaseDate(TimeConverter.changeDataToStringFormat(filmSale.getFilm().getReleaseDate(), "yyyy"));
        filmSaleDto.setNumberOfTickets(filmSale.getNumberOfTickets());
        LOGGER.debug("Film sale dto is converted from entity!");
        return filmSaleDto;
    }
}
