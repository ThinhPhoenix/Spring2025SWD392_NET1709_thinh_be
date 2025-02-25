package com.fpt.swd392.cvsts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fpt.swd392.cvsts.entities.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, String>{
    @Query(value = """
			Select id, vaccine_name from vaccines
			""",
			nativeQuery = true)
    List<Object[]> getVaccineIdandName();
}
