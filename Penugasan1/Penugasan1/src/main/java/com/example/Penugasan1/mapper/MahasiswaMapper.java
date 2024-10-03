package com.example.Penugasan1.mapper;

import com.example.Penugasan1.dto.MahasiswaDTO;
import com.example.Penugasan1.entity.Mahasiswa;

public class MahasiswaMapper {
    // map Student entity to Student Dto
    public static MahasiswaDTO mapToMahasiswaDTO(Mahasiswa mahasiswa) {
        MahasiswaDTO mahasiswaDto = MahasiswaDTO.builder()
                .id(mahasiswa.getId())
                .nama(mahasiswa.getNama())
                .nim(mahasiswa.getNim())
                .jurusan(mahasiswa.getJurusan())
                .tanggalLahir(mahasiswa.getTanggalLahir())
                .build();
        return mahasiswaDto;
    }

    // map Student Dto ke Student Entity
    public static Mahasiswa mapToMahasiswa(MahasiswaDTO mahasiswaDto) {
        Mahasiswa mahasiswa = Mahasiswa.builder()
                .id(mahasiswaDto.getId())
                .nama(mahasiswaDto.getNama())
                .nim(mahasiswaDto.getNim())
                .jurusan(mahasiswaDto.getJurusan())
                .tanggalLahir(mahasiswaDto.getTanggalLahir())
                .build();
        return mahasiswa;
    }
}
