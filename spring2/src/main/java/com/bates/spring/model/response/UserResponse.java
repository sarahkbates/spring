package main.java.com.bates.spring.model.response;

public class UserResponse {

    private Long id;
    private String first_name;
    private String last_name;
    private String email;

    public UserResponse(){
        super();
    }

    public UserResponse(String first_name, String last_name, String email) {
        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public String getFirst_name() {

        return first_name;
    }

    public String getLast_name() {

        return last_name;
    }

    public String getEmail() {

        return email;
    }

    public void setFirst_name(String first_name) {

        this.first_name = first_name;
    }



    public void setLast_name(String last_name) {

        this.last_name = last_name;
    }



    public void setEmail(String email) {

        this.email = email;
    }
}
