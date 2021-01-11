package common;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileReader {

    public static byte[] read(final String filePath) throws IOException {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        final InputStream fileStream = classLoader.getResourceAsStream(filePath);
        if (fileStream == null) {
            return null;
        }
        IOUtils.copy(fileStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
