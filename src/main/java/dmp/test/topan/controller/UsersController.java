package dmp.test.topan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dmp.test.topan.entity.Users;
import dmp.test.topan.repository.UsersRepository;

@RestController
public class UsersController {
    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    @PostMapping("/saveUsers")
    public Users saveUsers(@RequestBody Users users){
        return usersRepository.save(users);
    }

    @GetMapping("/getUsers/{id}")
    public Users getUsersById(@PathVariable Long id){
        return usersRepository.findById(id).orElse(null);
    }

    @PostMapping("/updateUsers/{id}")
    public Users updateUsersById(@PathVariable Long id, @RequestBody Users users){
        return usersRepository.findById(id).map(usr ->{
            usr.setUsername(users.getUsername());
            usr.setPassword(users.getPassword());
            return usersRepository.save(usr);
        })
        .orElseGet(() -> {
            users.setId(id);
            return usersRepository.save(users);
        });
    }

    @DeleteMapping("/deleteUsers/{id}")
    public String deleteUsersById(@PathVariable Long id){
        Users users = usersRepository.findById(id).orElse(null);
        usersRepository.deleteById(id);
        return "Users deleted : "+users;
    }
}
