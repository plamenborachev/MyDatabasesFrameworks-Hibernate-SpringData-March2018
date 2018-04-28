package app.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonParser {

    private Gson gson;
    private final FileParser fileParser;

    @Autowired
    public JsonParser(FileParser fileParser) {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        this.fileParser = new FileParser();
    }

    public <T> T importJson(Class<T> tClass, String fileName) throws IOException {
        String content = this.fileParser.readFile(fileName);
        return this.gson.fromJson(content, tClass);
    }

    public <T> void exportJson(T object, String fileName) throws IOException {
        String content = this.gson.toJson(object);
        this.fileParser.writeFile(fileName, content);
    }
}
