package com.estudos.app.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="UserType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;




    public enum Enum{
        USER(1L, "USER"),
        ADMIN(2L, "ADMIN");

        private final Long id;
        private final String description;

        Enum(Long id, String description) {
            this.id = id;
            this.description = description;
        }
        public UserType getType(){
            return new UserType(id, description);
        }
        public String getRole(){
            return "ROLE" + description.toUpperCase();
        }
    }
    public static UserType fromString(String value) {
        return switch (value) {
            case "USER" -> Enum.USER.getType();
            case "ADMIN" -> Enum.ADMIN.getType();
            default -> throw new IllegalArgumentException("Invalid UserType value: " + value);
        };
    }

}
