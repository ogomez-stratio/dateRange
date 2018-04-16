package com.example.daterangedemo;


import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGTimestamp;

public class TsRangeType implements UserType{

  private static final int[] SQL_TYPES = { Types.OTHER };

  @Override
  public int[] sqlTypes() {
    return SQL_TYPES;
  }

  @Override
  public Class returnedClass() {
    return TimeStampRange.class;
  }

  @Override
  public boolean equals(Object o, Object o1) throws HibernateException {
    return o.equals(o1);
  }

  @Override
  public int hashCode(Object o) throws HibernateException {
    return o.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet resultSet, String[] strings,
      SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
    Date from = resultSet.getDate(strings[0]);
    Date to = resultSet.getDate(strings[1]);
    if (resultSet.wasNull() || from == null) {
      return null;
    }
    PGTimestamp pgFrom = new PGTimestamp(from.getTime());
    PGTimestamp pgTo = new PGTimestamp(to.getTime());
    TimeStampRange ts = TimeStampRange.builder()
        .dateFrom(pgFrom.getTime())
        .dateTo(pgTo.getTime())
        .build();
    return ts;
  }

  @Override
  public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i,
      SessionImplementor sessionImplementor) throws HibernateException, SQLException {
    if (o == null) {
      preparedStatement.setNull(i, Types.VARCHAR);
    } else {
      //this http://postgresql.1045698.n5.nabble.com/Inserting-Information-in-PostgreSQL-interval-td2175203.html#a2175205
      preparedStatement.setObject(i,getInterval((TimeStampRange) o) , Types.OTHER);
    }

  }

  private static String getInterval(TimeStampRange value){
    return "[" + value.dateFrom + "," + value.dateTo + ")";
  }

  @Override
  public Object deepCopy(Object o) throws HibernateException {
    return null;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object o) throws HibernateException {
    return (Serializable) o;
  }

  @Override
  public Object assemble(Serializable serializable, Object o) throws HibernateException {
    return serializable;
  }

  @Override
  public Object replace(Object o, Object o1, Object o2) throws HibernateException {
    return o;
  }

}
