package sample;

public enum option {

    Member, Admin;

    private String value(){

        return name().toLowerCase();
    }

    public static option fromvalue(String v){

        return valueOf(v.toLowerCase());
    }
}
