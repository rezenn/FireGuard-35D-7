
package model;


public class Operation {
    private int incidentId ;
    private String nameOfCaller;
    private String typeOfIncident;
    private String cause;
    private int noOfInjured;
    private int noOfCasualties;
    private String timeOfIncident;
    private String dateOfIncident;
    private String placeOfIncident;
    private String damageOfProperty;
    
    public Operation( int incidentId, String nameOfCaller, String typeOfIncident,String cause, int noOfInjured,
            int noOfCasualties, String timeOfIncident, String dateOfIncident, String placeOfIncident, String damageOfProperty){
    this.incidentId = incidentId;
    this.nameOfCaller = nameOfCaller;
    this.typeOfIncident = typeOfIncident;
    this.cause = cause;
    this.noOfInjured = noOfInjured;
    this.noOfCasualties = noOfCasualties;
    this.timeOfIncident = timeOfIncident;
    this.dateOfIncident = dateOfIncident;
    this.placeOfIncident = placeOfIncident;
    this.damageOfProperty = damageOfProperty;
    }
    public int getIncidentId (){
        return incidentId;
    }
    public void setIncidentId (int incidentId){
        this.incidentId = incidentId;
    }
    
    public String getNameOfCaller (){
        return nameOfCaller;
    }
    public void setNameOfCaller (String nameOfCaller){
    this.nameOfCaller = nameOfCaller;
    }
    
    public String getTypeOfIncident (){
        return typeOfIncident;
    }
    public void setTypeOfIncident (String typeOfIncident){
    this.typeOfIncident = typeOfIncident;
    }
    
    public String getCause (){
        return cause;
    }
    public void setCause (String cause){
    this.cause = cause;
    }
    
    public int getNoOfInjured (){
        return noOfInjured;
    }
    public void setNoOfInjured (int noOfInjured){
    this.noOfInjured = noOfInjured;
    }
    
    public int getNoOfCasualties (){
        return noOfCasualties;
    }
    public void setNoOfCasualties (int noOfCasualties){
    this.noOfCasualties = noOfCasualties;
   }
    
    public String getTimeOfIncident (){
        return timeOfIncident;
    }
    public void setTimeOfIncident (String timeOfIncident){
    this.timeOfIncident = timeOfIncident;
    }
    
    public String getDateOfIncident (){
        return dateOfIncident;
    }
    public void setDateOfIncident (String dateOfIncident){
    this.dateOfIncident = dateOfIncident;
    }
    
    public String getPlaceOfIncident (){
        return placeOfIncident;
    }
    public void setPlaceOfIncident (String placeOfIncident){
    this.placeOfIncident = placeOfIncident;
    }
    
    public String getDamageOfProperty (){
        return damageOfProperty;
    }
    public void setDamageOfProperty (String damageOfProperty){
    this.damageOfProperty = damageOfProperty;   
    }
}
