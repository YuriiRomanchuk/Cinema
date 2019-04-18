package com.cinema.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryData {

    private final String query;
    private final DataSource.SqlConsumer<PreparedStatement> parameters;
    private final DataSource.SqlConsumer<ResultSet> resultProcessor;

    public QueryData(String query, DataSource.SqlConsumer<PreparedStatement> parameters) {
        this(query, parameters, preparedStatement -> {
        });
    }

    public QueryData(String query,
                     DataSource.SqlConsumer<PreparedStatement> parameters,
                     DataSource.SqlConsumer<ResultSet> resultProcessor) {
        this.query = query;
        this.parameters = parameters;
        this.resultProcessor = resultProcessor;
    }

    public String getQuery() {
        return query;
    }

    public DataSource.SqlConsumer<PreparedStatement> getParameters() {
        return parameters;
    }

    public DataSource.SqlConsumer<ResultSet> getResultProcessor() {
        return resultProcessor;
    }
}
