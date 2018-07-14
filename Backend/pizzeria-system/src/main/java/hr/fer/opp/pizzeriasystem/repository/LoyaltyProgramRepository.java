package hr.fer.opp.pizzeriasystem.repository;

import hr.fer.opp.pizzeriasystem.models.LoyaltyProgram;
import hr.fer.opp.pizzeriasystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Integer> {

    LoyaltyProgram findByUser(User user);
    void deleteByUser(User user);

}
