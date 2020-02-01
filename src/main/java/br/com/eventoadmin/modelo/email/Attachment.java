package br.com.eventoadmin.modelo.email;

import javax.activation.DataSource;

/**
 *
 * @author Ayslan
 */
public class Attachment {

    private DataSource dataSource;
    private String fileDescription;
    private String fileName;

    public Attachment(DataSource dataSource, String fileDescription, String fileName) {
        this.dataSource = dataSource;
        this.fileDescription = fileDescription;
        this.fileName = fileName;
    }

    
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
    
}
