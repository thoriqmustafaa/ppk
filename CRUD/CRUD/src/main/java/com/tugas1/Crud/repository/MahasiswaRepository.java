package com.tugas1.Crud.repository;

import com.tugas1.Crud.entity.Mahasiswa;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
    Optional<Mahasiswa> findByNim(String nim);

    List<Mahasiswa> findByNama(String nama);

    List<Mahasiswa> findByNimContainingOrNamaContaining(String nim, String nama);
}