package cn.edu.tju.myshop.bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import cn.edu.tju.myshop.util.File;

@ManagedBean
@SessionScoped
public class FileUploadBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<File> files = new ArrayList<File>();
    private int uploadsAvailable = 1;
    private boolean autoUpload = false;
    private boolean useFlash = false;
    
    public int getSize() {
        if (getFiles().size()>0){
            return getFiles().size();
        }else 
        {
            return 0;
        }
    }

    public FileUploadBean() {
    }

    public void paint(OutputStream stream, Object object) throws IOException {
        stream.write(getFiles().get((Integer)object).getData());
    }
    
    public void listener(FileUploadEvent event) throws Exception{
        UploadedFile item = event.getUploadedFile();
        File file = new File();
        file.setLength(item.getData().length);
        file.setName(item.getName());
        file.setData(item.getData());
        files.clear();
        files.add(file);
        file.setName(System.currentTimeMillis() + "-" + file.getName());
        saveToLocation();
    }  
      
    public String clearUploadData() {
        files.clear();
        setUploadsAvailable(1);
        return null;
    }
    
    public long getTimeStamp(){
        return System.currentTimeMillis();
    }
    
    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) { 
        this.files = files;
    }

    public int getUploadsAvailable() {
        return uploadsAvailable;
    }

    public void setUploadsAvailable(int uploadsAvailable) {
        this.uploadsAvailable = uploadsAvailable;
    }

    public boolean isAutoUpload() {
        return autoUpload;
    }

    public void setAutoUpload(boolean autoUpload) {
        this.autoUpload = autoUpload;
    }

    public boolean isUseFlash() {
        return useFlash;
    }

    public void setUseFlash(boolean useFlash) {
        this.useFlash = useFlash;
    }
    
    private void saveToLocation() {
    	File file = files.get(0);
    	String fileName = file.getName();
    	byte[] bytes = file.getData();
    	FileOutputStream outputStream = null;
    	try {
    	    String filePath = "/images/" + fileName;
    	    String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
    	    java.io.File f = new java.io.File(realPath + filePath);
    	    outputStream = new FileOutputStream(f);
    	    outputStream.write(bytes);
    	    outputStream.close();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
    
}
