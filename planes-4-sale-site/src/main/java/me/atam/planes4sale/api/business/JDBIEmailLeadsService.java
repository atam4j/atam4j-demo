package me.atam.planes4sale.api.business;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface JDBIEmailLeadsService {

    @RegisterBeanMapper(EmailLead.class)
    @SqlQuery("select * from emailleads")
    List<EmailLead> getAllEmailLeads();

    @SqlUpdate("insert into emailleads (id, buyerEmail, buyerNumber, planeId , message, sellerEmail) values (:getId, :getBuyerEmail, :getBuyerNumber, :getPlaneId, :getMessage, :getSellerEmail )")
    void addEmailLead(@BindMethods EmailLead emailLead);

}
