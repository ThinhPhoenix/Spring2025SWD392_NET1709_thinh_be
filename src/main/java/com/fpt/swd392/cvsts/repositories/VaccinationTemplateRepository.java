package com.fpt.swd392.cvsts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fpt.swd392.cvsts.entities.VaccinationTemplate;

@Repository
public interface VaccinationTemplateRepository extends JpaRepository<VaccinationTemplate, String>{
    @Query(value = """
            select 
	            tdi.from_vaccine_id, 
                tdi.from_dose_number, 
                tdi.to_vaccine_id, 
                tdi.to_dose_number, 
                tdi.days_between 
            from vaccination_templates vt
            inner join template_dose_intervals tdi on vt.id = tdi.template_id
            where vt.package_id = :packageId and vt.is_active = true
            order by tdi.id asc, tdi.from_dose_number asc
                """, nativeQuery = true)
    List<Object[]> getTemplateDoseIntervals(@Param("packageId") String packageId);
}
