package me.atam.planes4sale;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface JDBIPlaneService  {

    @RegisterBeanMapper(Plane.class)
    @SqlQuery("select * from planes where lower(manufacturer) = :manufacturer")
    List<Plane> findNameByManufacturer(@Bind("manufacturer") String manufacturer);
}


