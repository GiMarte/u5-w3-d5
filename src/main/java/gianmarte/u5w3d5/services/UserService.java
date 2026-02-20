package gianmarte.u5w3d5.services;

import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.enums.Role;
import gianmarte.u5w3d5.exceptions.BadRequestException;
import gianmarte.u5w3d5.exceptions.NotFoundException;
import gianmarte.u5w3d5.payloads.UserDTO;
import gianmarte.u5w3d5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(UserDTO dto){
        this.userRepository.findByUsername(dto.username()).ifPresent(user -> {
            throw new BadRequestException("L'user " + user.getUsername() + " è già in uso!");
        });
        User newOrganizer = new User(Role.ORGANIZER, dto.username(), dto.password());
        User savedOrg = this.userRepository.save(newOrganizer);
       System.out.printf("L'utente con id " + savedOrg.getId() + " è stato salvato");
       return savedOrg;
    }



    public User findById(Long userId) {
        return this.userRepository.findById(userId)
                                   .orElseThrow(() -> new NotFoundException(userId));
    }

    public Page<User> findAll(int page, int size) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size);
        return this.userRepository.findAll(pageable);
    }

}
