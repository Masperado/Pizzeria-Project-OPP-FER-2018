package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.LoyaltyProgram;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.repository.LoyaltyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LoyaltyProgramService {

    @Autowired
    LoyaltyProgramRepository loyaltyProgramRepository;

    @Transactional
    public void save(LoyaltyProgram loyaltyProgram) {
        loyaltyProgramRepository.save(loyaltyProgram);
    }

    @Transactional
    public LoyaltyProgram findByUser(User user) {
        return loyaltyProgramRepository.findByUser(user);
    }

    @Transactional
    public void deleteByUser(User user) {
        if (user == null) {
            return;
        }
        loyaltyProgramRepository.deleteByUser(user);
    }

    @Transactional
    public List<LoyaltyProgram> findAll() {
        return loyaltyProgramRepository.findAll();
    }

    @Transactional
    public LoyaltyProgram findOne(Integer id) {
        return loyaltyProgramRepository.findOne(id);
    }

    @Transactional
    public void createLoyaltyProgram(User userForm) {
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setUser(userForm);
        loyaltyProgram.setTotalPizzasToGetFreeOne(LoyaltyProgram.TOTAL_PIZZAS_TO_GET_FREE);
        loyaltyProgram.setPizzasRemainingUntilFree(LoyaltyProgram.TOTAL_PIZZAS_TO_GET_FREE);
        loyaltyProgramRepository.save(loyaltyProgram);
    }
}
