package org.irri.snpseek.brapi.repository;

import org.irri.snpseek.brapi.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, Integer> {

    @Query(value = """
            SELECT d.name
            FROM public.platform p
            JOIN public.db d ON d.db_id = p.db_id
            WHERE p.variantset_id = :variantSetId
            """, nativeQuery = true)
    List<String> findDatasetNamesByVariantSetId(@Param("variantSetId") Integer variantSetId);

    @Query(value = """
            SELECT p.variantset_id
            FROM public.platform p
            JOIN public.db d ON d.db_id = p.db_id
            WHERE d.name = :datasetName
            """, nativeQuery = true)
    List<Integer> findVariantSetIdsByDatasetName(@Param("datasetName") String datasetName);
}
