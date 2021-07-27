package contact;

import com.scalar.db.config.DatabaseConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public abstract class Contact {
    private static final String SCALARDB_PROPERTIES =
            System.getProperty("user.dir") + File.separator + "scalardb.properties";
    protected static final String NAMESPACE = "contacts";
    protected static final String TABLE_NAME = "email";
    protected static final String EMAIL = "email";
    protected static final String NAME = "name";
    protected static final String PHONE = "phone";
    protected DatabaseConfig dbConfig;

    public Contact() throws IOException {
        dbConfig = new DatabaseConfig(new FileInputStream(SCALARDB_PROPERTIES));
    }

    abstract void add(String name, String email, String phone) throws Exception;

    abstract void getPhone(String name, String email) throws Exception;

    abstract void getList(String name) throws Exception;

}
