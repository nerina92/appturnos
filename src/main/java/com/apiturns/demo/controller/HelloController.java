package com.apiturns.demo.controller;

import com.apiturns.demo.conexionDB.Conexion;
import com.apiturns.demo.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/hello/user")
    public String sayHelloUser(@RequestParam(value = "id", defaultValue = "0") int id) {
        Conexion con = new Conexion();
        //si no pasan ningun parámetro se devuelven todos los usuarios
        if(id==0){
            String userslist="";
            ArrayList<User> list = con.getUsers();
            for(int i=0; i<list.size();i++){
                userslist+=list.get(i).toString()+"\n";
            }
            return userslist;
        }else{
            //si se pasa un parametro se devuelve solo ese usuario
            return con.getUser(id).toString();
        }
    }

    /*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
	http://127.0.0.1:8080/hello/user/
	Header: Content-Type = application/JSON
	body (raw, JSON) : ejemplo de obeto:
	{
        "name":"Saul",
        "lastname":"Chay",
        "pass":"1234",
        "email":"german_sport@hotmail.com",
        "dni":31450335,
        "type_user_id":2
    }
	*/
    @PostMapping("/hello/user")
    public boolean addUser(@RequestBody User user) {
        Conexion con = new Conexion();
        return con.insertUser(user);
    }

    /*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
    http://127.0.0.1:8080/api/users/
    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {

        userService.save(user);

        //este metodo actualizará al usuario enviado

        return user;
    }*/

    /*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
    http://127.0.0.1:8080/hello/user/1*/
    @DeleteMapping("hello/user/{userId}")
    public String deteteUser(@PathVariable int userId) {
        Conexion con = new Conexion();
        User user = con.getUser(userId);

        if(user == null) {
            throw new RuntimeException("User id not found -"+userId);
        }

        con.deleteUser(userId);

        //Esto método, recibira el id de un usuario por URL y se borrará de la bd.
        return "Deleted user id - "+userId;
    }

    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + email y pass del
    usuario para comprobar el login, url: http://127.0.0.1:8080/hello/user/login?email=algo@algo.com&pass=elpassingresado*/
    @GetMapping("/hello/user/login")
    public boolean sayHello(@RequestParam(value = "email", defaultValue = "") String email,@RequestParam(value = "pass", defaultValue = "") String pass) {
        Conexion con = new Conexion();
        User user = con.getUser(email);
        if(user == null) {
            return false;
           //throw new RuntimeException("User email not found -"+email);
        }else{
            //compruebo si el pass ingresado es el correcto comparando con la base de datos.
            if(user.getPass().equals(pass)){
                return true;
            }else {
                return false;
            }
        }

    }


}
