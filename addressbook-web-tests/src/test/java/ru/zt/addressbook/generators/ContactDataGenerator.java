package ru.zt.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.zt.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

@Parameter(names = "-c", description = "Contact count")
public int count;

@Parameter(names = "-f", description = "Target file")
public String file;


//запускаемый файл с функцией, который в качестве параметров принимает массив строк
//в качестве параметров передается колличество контактов и путь к файлу
  public static void main(String[] args) throws IOException {
    //создаеем обект текущего класса
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
      generator.run();
    }catch(ParameterException ex){
      jCommander.usage();
      return;
    }
  }

private void run() throws IOException {
  //1 часть: генерация данных
  List<ContactData> contacts = generateContacts(count);
  //2 часть: запись данных в файл
  save(contacts, new File(file));

}

private void save(List<ContactData> contacts, File file) throws IOException {
  //открытие файла
  Writer writer = new FileWriter(file);
  for (ContactData contact:contacts){
    writer.write(String.format("%s;%s;%s\n",contact.getLastname(),contact.getFirstname(),contact.getGroup()));
  }
  //закрытие файла
  writer.close();
}

private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i=0;i<count;i++){
      contacts.add(new ContactData().withLastname(String.format("Lastname %s", i)).withFirstname(String.format("Firstname %s", i))
              .withAddress(String.format("Address %s", i)).withEmail_1(String.format("Email_1 %s", i)).withEmail_2(String.format("Email_2 %s", i)).withEmail_3(String.format("Email_3 %s", i)).withHomePhone(String.format("withHomePhone %s", i))
              .withMobilePhone(String.format("MobilePhone %s", i)).withWorkPhone(String.format("withWorkPhone %s", i)).withGroup((String.format("test %s", i))));
    }
  return contacts;
}



}
