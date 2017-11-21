/*
 * Copyright (c) 2016.  tdcarefor.me. All rights reserved.
 *
 */

package me.tdcarefor.axon.saga.repository.jdbc;

import org.axonframework.saga.repository.jdbc.GenericSagaSqlSchema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by tzw on 16/4/13.
 */
public class MysqlSagaSqlSchema extends GenericSagaSqlSchema {

    @Override
    public PreparedStatement sql_createTableAssocValueEntry(Connection conn) throws SQLException {
        final String s = "create table AssociationValueEntry (\n" +
                "        id int not null AUTO_INCREMENT,\n" +
                "        associationKey varchar(255),\n" +
                "        associationValue varchar(255),\n" +
                "        sagaId varchar(255),\n" +
                "        sagaType varchar(255),\n" +
                "        primary key (id)\n" +
                "    );\n";
        return conn.prepareStatement(s);
    }
}
