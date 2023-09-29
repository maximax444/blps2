package maximax444.blps.service.usersXML;

import lombok.Getter;
import maximax444.blps.entity.Customer;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class XMLUserMarshaller {
    private List<Customer> users;
    private static String USER_XML_FILE_PATH = "./users.xml";

    public void writeUsersToXml(List<Customer> users) throws JAXBException, IOException {
        var userS = new UsersXML(users);
        JAXBContext context = JAXBContext.newInstance(UsersXML.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(userS, new File(USER_XML_FILE_PATH));
    }
    public List<Customer> readAllUsersFromXml() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(UsersXML.class);
        return ((UsersXML) context.createUnmarshaller()
                .unmarshal(new FileReader(USER_XML_FILE_PATH))).getUsers();
    }


    public void readAllUsersFromXmlIntoMemory() throws JAXBException, IOException {
        var f = new File(USER_XML_FILE_PATH);
        if (!f.exists()) {
            var created = f.createNewFile();
            if (!created) throw new IOException("can't create file to store users");
            writeUsersToXml(new ArrayList<Customer>());
        }
        var xmlUsers = readAllUsersFromXml();
        this.users = xmlUsers == null ? new ArrayList<>() : xmlUsers;
    }

    public void addUserIntoXmlFile(Customer user) throws JAXBException, IOException {
        this.users.add(user);
        writeUsersToXml(users);
    }
}