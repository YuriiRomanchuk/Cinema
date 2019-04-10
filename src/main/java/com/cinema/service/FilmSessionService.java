package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.FilmSessionDtoConverter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dao.FilmSessionDao;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.model.entity.FilmSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilmSessionService {

    private final FilmSessionDao filmSessionDao;
    private final FilmSessionDtoConverter filmSessionDtoConverter;

    public FilmSessionService(FilmSessionDao filmSessionDao, FilmSessionDtoConverter filmSessionDtoConverter) {
        this.filmSessionDao = filmSessionDao;
        this.filmSessionDtoConverter = filmSessionDtoConverter;
    }

    public  List<FilmSessionDto> showFilmSession(FilmSessionDto filmSessionDto) throws ServiceException {
        try {
            Date currentFilmSessionDate = TimeConverter.convertStringDate(filmSessionDto.getDate());
            Date beginOfDay = TimeConverter.receiveBeginOfDay(currentFilmSessionDate);
            Date endOfDay = TimeConverter.receiveEndOfDay(currentFilmSessionDate);
            List<FilmSession> filmsSession = filmSessionDao.findByFilters(beginOfDay, endOfDay, filmSessionDto.getFilmDto().getId());
            return prepareDailySchedule(filmsSession, beginOfDay, endOfDay);

        } catch (Exception e) {
            throw new ServiceException("Create film session failed", e);
        }
    }

    private List<FilmSessionDto> prepareDailySchedule(List<FilmSession> filmsSession, Date beginOfDay, Date endOfDay) {

        int timeStartSession = 9;

        List<FilmSessionDto> filmsSessionDto = new ArrayList<>();
        Date startSession = TimeConverter.addHourToDate(beginOfDay, timeStartSession);

        Date actualSessionDate = startSession;

        for (FilmSession filmSession : filmsSession) {

            while (!actualSessionDate.equals(filmSession.getDate())) {
                filmsSessionDto.add(createFilmSessionDtoEmpty(actualSessionDate));
                actualSessionDate = TimeConverter.addHourToDate(actualSessionDate, filmSession.getFilm().getRunningTime());
            }

            filmsSessionDto.add(filmSessionDtoConverter.convertFromFilmEntity(filmSession));
            actualSessionDate = TimeConverter.addHourToDate(actualSessionDate, filmSession.getFilm().getRunningTime());
        }

        while (actualSessionDate.before(endOfDay)) {
            filmsSessionDto.add(createFilmSessionDtoEmpty(actualSessionDate));
            actualSessionDate = TimeConverter.addHourToDate(actualSessionDate, 2);
        }

        return filmsSessionDto;
    }

    private FilmSessionDto createFilmSessionDtoEmpty(Date sessionDate) {
        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setDate(String.valueOf(sessionDate));
        return filmSessionDto;
    }

}
