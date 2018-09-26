package ru.zt.addressbook.generators;

import ru.zt.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  //запускаемый файл с функцией, который в качестве параметров принимает массив строк
//в качестве параметров передается колличество контактов и путь к файлу
  public static void main(String[] args) throws IOException {
    //1й параметр
    int count = Integer.parseInt(args[0]);
    //2й параметр
    File file = new File(args[1]);
    //1 часть: генерация данных
    List<ContactData> contacts = generateContacts(count);
    //2 часть: запись данных в файл
    save(contacts, file);
  }
private static void save(List<ContactData> contacts, File file) throws IOException {
  //открытие файла
  Writer writer = new FileWriter(file);
  for (ContactData contact:contacts){
    writer.write(String.format("%s;%s;%s\n",contact.getLastname(),contact.getFirstname(),contact.getGroup()));
  }
  //закрытие файла
  writer.close();
}

private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i=0;i<count;i++){
      contacts.add(new ContactData().withLastname(String.format("Lastname %s", i)).withFirstname(String.format("Firstname %s", i))
              .withGroup((String.format("group %s", i))));
    }
  return contacts;
}



}
