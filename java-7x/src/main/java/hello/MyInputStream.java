package hello;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MyInputStream extends ByteArrayInputStream {
    public MyInputStream(byte[] buf) {
        super(buf);
    }

    @Override
    public void close() throws IOException {
        super.close();
        System.out.println("MyInputStream is closed");
    }
}
