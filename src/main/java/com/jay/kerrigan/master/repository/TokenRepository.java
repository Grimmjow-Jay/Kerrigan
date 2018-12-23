package com.jay.kerrigan.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jay.kerrigan.common.entity.table.Token;

public interface TokenRepository extends JpaRepository<Token, String> {

}
