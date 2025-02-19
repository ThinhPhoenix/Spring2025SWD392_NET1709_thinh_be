package com.fpt.swd392.cvsts.repositories;

import com.fpt.swd392.cvsts.entities.User;
import com.fpt.swd392.cvsts.entities.VaccinationRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findById(String id);

    @Query(value = "select * from vaccination_records where id = :id", nativeQuery = true)
    Optional<VaccinationRecord> findVaccinationRecordById(@Param("id") String id);

    @Query(value = "select * from vaccination_records where customer_id = :customerId", nativeQuery = true)
    List<VaccinationRecord> findVaccinationRecordsByUserId(@Param("customerId") String customerId);

    @Query(value = """
            select 
	            u.id,
                u.fullname,
                u.email,
                u.phone_number,
                u.gender,
                a.unit_number,
                a.ward,
                a.district,
                a.province,
                u.role,
                u.birthday,
                u.created_at
            from users u
            left join addresses a on u.id = a.user_id
            where u.id = :id
                """, nativeQuery = true)
    Optional<Object[]> getUserInfo(@Param("id") String id);
}
