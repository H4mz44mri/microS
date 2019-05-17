package tn.esprims.moviecatalogservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprims.moviecatalogservice.models.User;
import tn.esprims.moviecatalogservice.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/all")
    public List<User> getMovies()
    {
        List<User> list = userRepository.findAll();
        return list;
    }

    @RequestMapping("/find/{userId}")
    public Optional<User> findUser(@PathVariable("userId") Integer userId)
    {
        return userRepository.findById(userId);
    }

    @PostMapping("/add")
    public List<User> addUser(@RequestBody User user)
    {
        userRepository.save(user);
        return userRepository.findAll();
    }

}
