package me.atam.planes4sale.api.business;

import me.atam.planes4sale.Plane;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindFields;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface JDBIEmailLeadsService {

    @RegisterBeanMapper(EmailLead.class)
    @SqlQuery("select * from emailleads")
    List<EmailLead> getAllEmailLeads();

    @RegisterBeanMapper(EmailLead.class)
    @SqlUpdate("insert into emailleads (id , buyerEmail , planeId , message, sellerEmail)")
    void addEmailLead(@BindFields EmailLead emailLead);

}
