package com.example.Penugasan1.dto;

import java.sql.Date;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MahasiswaDTO {
    private Long id;

    @NotEmpty(message = "NIM tidak boleh kosong")
    @Pattern(regexp = "^[0-9]+$", message = "NIM hanya boleh berisi angka")
    private String nim;

    @NotEmpty(message = "Nama tidak boleh kosong")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Nama tidak boleh mengandung karakter khusus")
    private String nama;

    @NotEmpty(message = "Jurusan tidak boleh kosong")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Jurusan tidak boleh mengandung karakter khusus")
    private String jurusan;

    @NotNull(message = "Tanggal lahir tidak boleh kosong")
    private Date tanggalLahir;
}
