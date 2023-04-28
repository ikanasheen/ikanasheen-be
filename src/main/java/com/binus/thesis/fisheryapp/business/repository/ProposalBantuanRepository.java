package com.binus.thesis.fisheryapp.business.repository;

import com.binus.thesis.fisheryapp.business.model.ProposalBantuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalBantuanRepository extends JpaRepository<ProposalBantuan, String>, JpaSpecificationExecutor<ProposalBantuan> {

    List<ProposalBantuan> findByNelayanIdNelayanAndBantuanIdBantuanAndStatusProposalIn(String idNelayan, String idBantuan, List<String> status);
}
