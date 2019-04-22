package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.statistics.FilmSale;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmSaleResultSetConverter implements Converter<ResultSet, FilmSale> {

    private final FilmResultSetConverter filmResultSetConverter;

    public FilmSaleResultSetConverter(FilmResultSetConverter filmResultSetConverter) {
        this.filmResultSetConverter = filmResultSetConverter;
    }

    @Override
    public FilmSale convert(ResultSet resultSet) throws SQLException {
        FilmSale filmSale = new FilmSale();
        filmSale.setFilm(filmResultSetConverter.convert(resultSet));
        filmSale.setNumberOfTickets(resultSet.getInt("number_of_tickets"));
        return filmSale;
    }
}
