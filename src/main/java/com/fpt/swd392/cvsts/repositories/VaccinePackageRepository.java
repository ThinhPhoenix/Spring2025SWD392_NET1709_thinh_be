package com.fpt.swd392.cvsts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.swd392.cvsts.entities.VaccinePackage;

@Repository
public interface VaccinePackageRepository extends JpaRepository<VaccinePackage, String> {

    @Query(value = """
            select 
	            vp.id as vaccine_package_id,
	            vp.name as vaccine_package_name,
	            vp.description,
	            vp.total_price,
	            dt.name as disease_name,
	            v.vaccine_name,
	            v.country_of_origin,
	            vpd.dose_number,
                sum(v.price_per_dose) OVER (PARTITION BY vp.id) as total_vaccine_price
            from vaccine_packages vp
            inner join vaccine_package_details vpd on vp.id = vpd.vaccine_package_id
            inner join vaccines v on vpd.vaccine_id = v.id
            inner join disease_types dt on v.disease_type_id = dt.id
            order by vp.name asc
                """, nativeQuery = true)
    List<Object[]> getAllVaccinePackagesDetails();

    @Query(value = """
            select id, name from vaccine_packages
                """, nativeQuery = true)
    List<Object[]> getVaccinePackageIdandName();
}
