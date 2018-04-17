package com.example.daterangedemo;


import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String range = resultSet.getString(strings[0]);

    if (resultSet.wasNull() || range == null) {
      return null;
    }

    Matcher matches = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3})")
            .matcher(range);

    List<String> allMatches = new ArrayList<>();
    while (matches.find()) {
      allMatches.add(matches.group());
    }
    PGTimestamp pgFrom = new PGTimestamp(allMatches.indexOf(0));
    PGTimestamp pgTo = new PGTimestamp(allMatches.indexOf(1));
    TimeStampRange ts = TimeStampRange.builder()
        .dateFrom(pgFrom.toLocalDateTime())
        .dateTo(pgTo.toLocalDateTime())
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

    PGTimestamp pgFrom = new PGTimestamp(PGTimestamp.valueOf(value.dateFrom).getTime());
    PGTimestamp pgTo = new PGTimestamp(PGTimestamp.valueOf(value.dateTo).getTime());
    return "[" + pgFrom + "," + pgTo + ")";
  }

  @Override
  public Object deepCopy(Object o) throws HibernateException {
    return o;
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
