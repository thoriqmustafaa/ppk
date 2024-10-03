package com.example.Penugasan1.repository;

import com.example.Penugasan1.entity.Mahasiswa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
    List<Mahasiswa> findByNim(String nim);

    List<Mahasiswa> findByNama(String nama);

    List<Mahasiswa> findByNimContainingOrNamaContaining(String nim, String nama);
}