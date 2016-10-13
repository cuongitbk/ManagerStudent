package Entity;

import java.io.File;
import java.security.PrivateKey;
import java.util.PriorityQueue;

/**
 * Created by Tran on 11/10/2016.
 */
public class FileEntity {
    private String name;
    private String path;
    private String md5;
    private long lastModify;

   public FileEntity()
    {

    }

   public FileEntity(String name, String path, String md5 , long lastModify)
    {
        this.name = name;
        this.path = path;
        this.md5 = md5;
        this.lastModify = lastModify;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getLastModify() {
        return lastModify;
    }

    public void setLastModify(long lastModify) {
        this.lastModify = lastModify;
    }




}
