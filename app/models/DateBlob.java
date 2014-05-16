package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import play.Logger;
import play.Play;
import play.db.Model.BinaryField;
import play.exceptions.UnexpectedException;
import play.libs.Codec;
import play.libs.IO;

public class DateBlob implements BinaryField, UserType {

    private String path;
    private String type;
    private File file;

    public DateBlob() {}

    private DateBlob(String path, String type) {
        this.path = path;
        this.type = type;
    }

    public InputStream get() {
        if(exists()) {
            try {
                return new FileInputStream(getFile());
            } catch(Exception e) {
                throw new UnexpectedException(e);
            }
        }
        return null;
    }

    // Make a copy of play.db.jpa.Blob, rename the 'UUID' field to 'path', and replace the set method with this.
    public void set(InputStream is, String type) {
        final String datePath = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        final File dateDirectory = new File(getStore(), datePath);
        dateDirectory.mkdirs();
        this.path = datePath + Codec.UUID();
        this.type = type;
        IO.write(is, getFile());
    }

    public long length() {
        return getFile().length();
    }

    public String type() {
        return type;
    }

    public boolean exists() {
        return path != null && getFile().exists();
    }

    public File getFile() {
        if(file == null) {
            file = new File(getStore(), path);
        }
        Logger.info("PATH %s", file.getAbsolutePath());
        return file;
    }

    //

    public int[] sqlTypes() {
        return new int[] {Types.VARCHAR};
    }

    public Class returnedClass() {
        return DateBlob.class;
    }

    public boolean equals(Object o, Object o1) throws HibernateException {
        return o == null ? false : o.equals(o1);
    }

    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    public Object nullSafeGet(ResultSet rs, String[] names, Object o) throws HibernateException, SQLException {
        String val = (String) StringType.INSTANCE.nullSafeGet(rs, names[0]);
        if(val == null || val.length() == 0 || !val.contains("|")) {
            return new DateBlob();
        }
        return new DateBlob(val.split("[|]")[0], val.split("[|]")[1]);
    }

    public void nullSafeSet(PreparedStatement ps, Object o, int i) throws HibernateException, SQLException {
        if(o != null) {
            ps.setString(i, ((DateBlob)o).path + "|" + ((DateBlob)o).type);
        } else {
            ps.setNull(i, Types.VARCHAR);
        }
    }

    public Object deepCopy(Object o) throws HibernateException {
        if(o == null) {
            return null;
        }
        return new DateBlob(this.path, this.type);
    }

    public boolean isMutable() {
        return true;
    }

    public Serializable disassemble(Object o) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object assemble(Serializable srlzbl, Object o) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //

    public static String getUUID(String dbValue) {
        return dbValue.split("[|]")[0];
    }

    public static File getStore() {
        String name = Play.configuration.getProperty("attachments.path", "attachments");
        File store = null;
        if(new File(name).isAbsolute()) {
            store = new File(name);
        } else {
            store = Play.getFile(name);
        }
        if(!store.exists()) {
            store.mkdirs();
        }
        return store;
    }

}