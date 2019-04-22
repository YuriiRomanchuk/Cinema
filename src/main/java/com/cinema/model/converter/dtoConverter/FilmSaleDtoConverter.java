package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmSaleDto;
import com.cinema.model.statistics.FilmSale;

public class FilmSaleDtoConverter implements Converter<FilmSale, FilmSaleDto> {
    @Override
    public FilmSaleDto convert(FilmSale filmSale) {
        FilmSaleDto filmSaleDto = new FilmSaleDto();
        filmSaleDto.setFilmId(filmSale.getFilm().getId());
        filmSaleDto.setFilmName(filmSale.getFilm().getName());
        filmSaleDto.setFilmNameEnglish(filmSale.getFilm().getNameEnglish());
        filmSaleDto.setReleaseDate(TimeConverter.changeDataToStringFormat(filmSale.getFilm().getReleaseDate(),"yyyy"));
        filmSaleDto.setNumberOfTickets(filmSale.getNumberOfTickets());
        return filmSaleDto;
    }
}
