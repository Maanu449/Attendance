package cfd.ram.attendance;

/**
 * Created by mansi on 29/1/18.
 */

public class Prof {
    String profName, profEmail;
    public Prof(){

    }

    public Prof(String profName, String profEmail){
        this.profName=profName;
        this.profEmail=profEmail;
    }

    public void setProfName(String profName){
        this.profName=profName;
    }

    public String getProfName(){
        return profName;
    }
    public void setProfEmail(String profEmail){
        this.profEmail=profEmail;
    }

    public String getProfEmail(){
        return profEmail;
    }
}
