package BS.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import BS.entity.UserProfile;

@Repository
public interface UserProfileDAO extends JpaRepository<UserProfile, String> {

    @Query("select u from UserProfile u where u.username = ?1")
    UserProfile findByUsername(String username);

    @Modifying
    @Transactional
    @Query("delete from UserProfile u where u.username = ?1")
    void deleteByUsername(String username);

    @Modifying
    @Transactional
    @Query("update UserProfile u set u.fullName = ?1, u.age = ?2, u.address = ?3, u.phoneNumber = ?4 where u.username = ?5")
    void updateUser(String fullName, int age, String address, String phoneNumber, String username);
}
