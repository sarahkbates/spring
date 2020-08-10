package main.java.com.bates.spring.dto;

public class UserDto {

    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;


    public UserDto(){
        super();
    }

    public UserDto(Long id, String first_name, String last_name, String email, String password) {
        super();
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {

        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
