package com.thfp.userservice.entity.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(String id) throws Exception {
//        Optional<User> user = userRepository.findById(id);
//
//        // return first
//        if (user.isEmpty())
//            throw new Exception("user not found");
//
//        return user.get();


        return userRepository.findById(id).orElseThrow(
                // lambda functions
                () -> new Exception("user not found")
        );
    }

    public User save(User user) {
            return userRepository.save(user);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User update(String id, User user) throws Exception {
        User checkUser = findById(id);

        checkUser.setEmail(user.getEmail());
        checkUser.setName(user.getName());

        return this.userRepository.save(checkUser);
    }

    public void delete(String id) throws Exception {
        User user = findById(id);

        userRepository.delete(user);
    }
}
