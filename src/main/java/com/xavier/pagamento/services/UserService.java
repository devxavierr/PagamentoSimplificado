package com.xavier.pagamento.services;

import com.xavier.pagamento.domain.user.User;
import com.xavier.pagamento.domain.user.UserType;
import com.xavier.pagamento.dtos.userDTO;
import com.xavier.pagamento.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do tipo Lojista não pode fazer transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id)
                .orElseThrow(()-> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public User createUser(userDTO user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }


    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
