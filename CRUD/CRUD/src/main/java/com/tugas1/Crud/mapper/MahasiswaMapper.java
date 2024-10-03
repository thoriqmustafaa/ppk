package com.tugas1.Crud.mapper;

import com.tugas1.Crud.dto.MahasiswaDTO;
import com.tugas1.Crud.entity.Mahasiswa;

public class MahasiswaMapper {
    // map Mahasiswa entity to Mahasiswa Dto
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

    // map Mahasiswa Dto ke Mahasiswa Entity
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
