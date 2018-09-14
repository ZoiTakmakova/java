package ru.zt.addressbook.model;

public class GroupData {


private int id = Integer.MAX_VALUE;
private String name;
private String header;
private String footer;

public int getId() {
  return id;
}

public GroupData withId(int id) {
  this.id = id;
  return this;//позволяет вызывать каскады - вытягивать методы в цепочку
}

public GroupData withName(String name) {
  this.name = name;
  return this;//позволяет вызывать каскады - вытягивать методы в цепочку
}

public GroupData withHeader(String header) {
  this.header = header;
  return this;//позволяет вызывать каскады - вытягивать методы в цепочку
}

public GroupData withFooter(String footer) {
  this.footer = footer;
  return this;//позволяет вызывать каскады - вытягивать методы в цепочку
}

public String getName() {
  return name;
}

public String getHeader() {
  return header;
}

public String getFooter() {
  return footer;
}


@Override
public String toString() {
  return "GroupData{" +
          "id=" + id +
          ", name='" + name + '\'' +
          '}';
}

@Override
public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;

  GroupData groupData = (GroupData) o;

  if (id != groupData.id) return false;
  return name != null ? name.equals(groupData.name) : groupData.name == null;
}

/* hashCode - битовая строка фиксированной длины, полученная из массива произвольной длины
hashCode — это целочисленный результат работы метода, которому в качестве входного параметра передан объект
-Если hashCode разные, то и входные объекты гарантированно разные.
-Если хhashCode равны, то входные объекты не всегда равны.
Ситуация, когда у разных объектов одинаковые hashCode называется — коллизией.
 */
@Override
public int hashCode() {
  int result = id;
  result = 31 * result + (name != null ? name.hashCode() : 0);
  return result;
}
}
