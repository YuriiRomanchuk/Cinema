package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.FilmSaleDtoConverter;
import com.cinema.model.converter.dtoConverter.FilmSessionDtoConverter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dao.FilmSessionDao;
import com.cinema.model.dto.FilmSaleDto;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.model.entity.FilmSession;
import com.cinema.model.statistics.FilmSale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FilmSessionService {

    private final FilmSessionDao filmSessionDao;
    private final FilmSessionDtoConverter filmSessionDtoConverter;
    private final FilmSaleDtoConverter filmSaleDtoConverter;

    public FilmSessionService(FilmSessionDao filmSessionDao,
                              FilmSessionDtoConverter filmSessionDtoConverter,
                              FilmSaleDtoConverter filmSaleDtoConverter) {
        this.filmSessionDao = filmSessionDao;
        this.filmSessionDtoConverter = filmSessionDtoConverter;
        this.filmSaleDtoConverter = filmSaleDtoConverter;
    }

    public List<FilmSessionDto> showFilmSessions(FilmSessionDto filmSessionDto) throws ServiceException {
        try {
            Date currentFilmSessionDate = new Date();
            if (filmSessionDto.getDate() != null) {
                currentFilmSessionDate = TimeConverter.convertStringToDate(filmSessionDto.getDate(), "E MMM dd kk:mm:ss Z yyyy");
            }

            int filmIdFilter = filmSessionDto.getFilmDto().getId();
            Date beginOfDay = TimeConverter.receiveBeginOfDay(currentFilmSessionDate);
            Date endOfDay = TimeConverter.receiveEndOfDay(currentFilmSessionDate);
            List<FilmSession> filmsSession = filmSessionDao.findByFilters(beginOfDay, endOfDay, filmIdFilter);
            return prepareDailySchedule(filmsSession, beginOfDay, endOfDay, filmIdFilter);
        } catch (Exception e) {
            throw new ServiceException("Create film session failed", e);
        }
    }

    private List<FilmSessionDto> prepareDailySchedule(List<FilmSession> filmsSession, Date beginOfDay, Date endOfDay, int filmIdFilter) {

        int timeStartSession = 9;
        List<FilmSessionDto> filmsSessionDto = new ArrayList<>();
        Date startSession = TimeConverter.addHourToDate(beginOfDay, timeStartSession);
        Date actualSessionDate = startSession;

        for (FilmSession filmSession : filmsSession) {

            while (!actualSessionDate.equals(filmSession.getDate()) && filmIdFilter < 0 && actualSessionDate.before(endOfDay)) {
                filmsSessionDto.add(createFilmSessionDtoEmpty(actualSessionDate));
                actualSessionDate = TimeConverter.addHourToDate(actualSessionDate, filmSession.getFilm().getRunningTime());
            }

            filmsSessionDto.add(filmSessionDtoConverter.convertFromFilmEntity(filmSession));
            actualSessionDate = TimeConverter.addHourToDate(actualSessionDate, filmSession.getFilm().getRunningTime());
        }

        while (actualSessionDate.before(endOfDay) && filmIdFilter < 0) {
            filmsSessionDto.add(createFilmSessionDtoEmpty(actualSessionDate));
            actualSessionDate = TimeConverter.addHourToDate(actualSessionDate, 2);
        }
        return filmsSessionDto;
    }

    private FilmSessionDto createFilmSessionDtoEmpty(Date sessionDate) {
        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setDate(TimeConverter.changeDataToStringFormat(sessionDate, "yyyy-MM-dd kk:mm:ss"));
        return filmSessionDto;
    }

    public void addFilmSession(FilmSessionDto filmSessionDto) throws ServiceException {
        try {
            Date date = TimeConverter.convertStringToDate(filmSessionDto.getDate(), "E MMM dd kk:mm:ss Z yyyy");
            int filmId = filmSessionDto.getFilmDto().getId();
            int roomId = filmSessionDto.getRoomDto().getId();
            filmSessionDao.insert(filmId, roomId, date);
            filmSessionDto.getFilmDto().setId(-1);
        } catch (Exception e) {
            throw new ServiceException("Create film session failed", e);
        }
    }

    public void deleteFilmSession(FilmSessionDto filmSessionDto) throws ServiceException {
        try {
            filmSessionDao.delete(filmSessionDto.getId());
            filmSessionDto.getFilmDto().setId(-1);
        } catch (Exception e) {
            throw new ServiceException("Delete film session failed", e);
        }
    }

    public FilmSessionDto receiveFilmSessionById(int filmSessionId) throws ServiceException {
        try {
            return filmSessionDtoConverter.convertFromFilmEntity(filmSessionDao.findById(filmSessionId));
        } catch (Exception e) {
            throw new ServiceException("Film session receive failed", e);
        }
    }

    public List<FilmSaleDto> receiveFilmSalesByDate(Date currentDate) throws ServiceException {
        try {
            Date beginOfDay = TimeConverter.receiveBeginOfDay(currentDate);
            Date endOfDay = TimeConverter.receiveEndOfDay(currentDate);
            List<FilmSale> filmSales = filmSessionDao.findFilmSalesByDate(beginOfDay, endOfDay);
            return filmSales.stream().map(filmSale -> filmSaleDtoConverter.convert(filmSale)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Film session receive failed", e);
        }
    }
}

