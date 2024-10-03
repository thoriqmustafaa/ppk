package com.tugas1.Crud.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Past;
import com.tugas1.Crud.validation.MinAge;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MahasiswaDTO {
    private Long id;

    @NotEmpty(message = "NIM tidak boleh kosong")
    @Pattern(regexp = "^[0-9]{9}$", message = "NIM berisi 9 angka")
    private String nim;

    @NotEmpty(message = "Nama tidak boleh kosong")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Nama tidak boleh mengandung karakter khusus")
    private String nama;

    @NotEmpty(message = "Jurusan tidak boleh kosong")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Jurusan tidak boleh mengandung karakter khusus")
    private String jurusan;

    @NotNull(message = "Tanggal lahir tidak boleh kosong")
    @Past(message = "Tanggal lahir harus di masa lalu")
    @MinAge(value = 17, message = "Usia minimal harus 17 tahun")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;
}
