package com.minitech.boot.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.minitech.boot.entity.UserLogSolr;


public interface UserLogSolrRepository extends SolrCrudRepository<UserLogSolr, Long> {
	
	List<UserLogSolr> findByJsonStringContaining(String jsonString);
	
	@Query(value = "*:*")
	@Facet(fields = { "ip" }, limit = 5)
	FacetPage<UserLogSolr> findAllFacetOnIp(Pageable page);

}
