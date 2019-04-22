package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.statistics.FilmSale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmSaleResultSetConverter implements Converter<ResultSet, FilmSale> {

    private static final Logger LOGGER = LogManager.getLogger(FilmSaleResultSetConverter.class);
    private final FilmResultSetConverter filmResultSetConverter;

    public FilmSaleResultSetConverter(FilmResultSetConverter filmResultSetConverter) {
        this.filmResultSetConverter = filmResultSetConverter;
    }

    @Override
    public FilmSale convert(ResultSet resultSet) throws SQLException {
        FilmSale filmSale = new FilmSale();
        filmSale.setFilm(filmResultSetConverter.convert(resultSet));
        filmSale.setNumberOfTickets(resultSet.getInt("number_of_tickets"));
        LOGGER.debug("Film sale result set is converted!");
        return filmSale;
    }
}
