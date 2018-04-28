package app.retake.io.api;

import org.springframework.stereotype.Component;

import java.io.IOException;

public interface FileIO {

    String read(String file) throws IOException;

    void write(String fileContent, String file) throws IOException;
}
