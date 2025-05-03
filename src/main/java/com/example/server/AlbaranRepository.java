package com.example.server;

import org.springframework.data.jpa.repository.JpaRepository;
import pojos.Albaranes;

public interface AlbaranRepository extends JpaRepository<Albaranes, Integer> {
}