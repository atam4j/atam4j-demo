package me.atam.planes4sale;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface JDBIPlaneService {

    @SqlQuery("select id from planes where manufacturer = :manufacturer")
    public String findNameByManufacturer(@Bind("manufacturer") String manufacturer);
}
