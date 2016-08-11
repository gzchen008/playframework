package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by cgz on 16-8-2.
 */
@Entity
@Table(name = "tb_user")
public class User extends Model {
    public String email;
    public String password;
    public String fullName;
    public boolean isAdmin;

    public User(String email, String password, String fullName, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.isAdmin = isAdmin;
    }


    public static User check(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
}
