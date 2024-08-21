package org.example;
import org.springframework.data.annotation.Id;
import java.io.*;

public class Sight implements Serializable {
    @Id
    private String id;
    private String sightName;
    private String zone;
    private String category;
    private String photoURL;
    private String description;
    private String  address;

    public Sight(String sightName,String zone,String category,String photoURL,String description,String  address ){
        setSightName(sightName);
        setZONE(zone);
        setCategory(category);
        setPhotoURL(photoURL);
        setdescription(description);
        setAddress(address);
    }
    public void setSightName(String sn){
        sightName = sn;
    }
    public void setZONE(String ze){
        zone = ze;
    }
    public void setCategory(String ce){
        category = ce;
    }
    public void setPhotoURL(String url){
        photoURL = url;
    }
    public void setAddress(String addr ){
        address = addr;
    }
    public void setdescription(String des ){
        description = des;
    }
    public String  getSightName(){
        return sightName;
    }
    public String  getZone(){
        return zone;
    }
    public String  getCategory(){
        return category;
    }
    public String  getPhotoURL(){
        return photoURL;
    }
    public String  getAddress(){
        return address;
    }
    public String  getDescription(){
        return description;
    }
}
