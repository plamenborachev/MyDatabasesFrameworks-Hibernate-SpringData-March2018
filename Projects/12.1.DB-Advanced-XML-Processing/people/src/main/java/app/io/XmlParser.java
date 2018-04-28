package app.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component
public class XmlParser {

    public <T> void writeXML(T object, String fileName) throws JAXBException, IOException {
        String path = System.getProperty("user.dir") + File.separator + fileName;
        File f = new File(path);
        if(!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream outputStream = new FileOutputStream(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        jaxbMarshaller.marshal(object, bufferedWriter);
    }

    public <T> T read (Class<T> clazz, String fileName) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        
        try (
            InputStream is = clazz.getResourceAsStream(fileName);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
            ) {
            T result = (T) jaxbUnmarshaller.unmarshal(bfr);
            return result;
        }
    }
}
