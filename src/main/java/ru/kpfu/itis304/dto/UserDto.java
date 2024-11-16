package ru.kpfu.itis304.dto;

public class UserDto {
    private String name;
    private Integer score;
    private String login;

    public UserDto(String name, Integer score, String login) {
        this.name = name;
        this.score = score;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
