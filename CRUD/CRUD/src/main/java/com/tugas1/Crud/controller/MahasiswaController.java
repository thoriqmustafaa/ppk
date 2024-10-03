package com.tugas1.Crud.controller;

import com.tugas1.Crud.dto.MahasiswaDTO;
import com.tugas1.Crud.entity.Mahasiswa;
import com.tugas1.Crud.repository.MahasiswaRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MahasiswaController {
    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @GetMapping("/")
    public String listMahasiswa(Model model, @RequestParam(required = false) String query) {
        List<Mahasiswa> mahasiswas;
        if (query == null) {
            mahasiswas = mahasiswaRepository.findAll();
        } else {
            mahasiswas = mahasiswaRepository.findByNimContainingOrNamaContaining(query, query);
        }
        model.addAttribute("mahasiswas", mahasiswas);
        return "show";
    }

    @GetMapping("/add")
    public String addMahasiswaForm(Model model) {
        model.addAttribute("mahasiswa", new MahasiswaDTO());
        return "add";
    }

    @PostMapping("/add")
    public String addMahasiswa(@Valid @ModelAttribute("mahasiswa") MahasiswaDTO mahasiswaDTO, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "add";
        }
        // Map MahasiswaDTO to Mahasiswa entity
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim(mahasiswaDTO.getNim());
        mahasiswa.setNama(mahasiswaDTO.getNama());
        mahasiswa.setJurusan(mahasiswaDTO.getJurusan());
        mahasiswa.setTanggalLahir(mahasiswaDTO.getTanggalLahir());
        mahasiswaRepository.save(mahasiswa);
        return "redirect:/";
    }

    @GetMapping("/edit/{nim}")
    public String editMahasiswaForm(@PathVariable("nim") String nim, Model model) {
        Mahasiswa mahasiswa = mahasiswaRepository.findByNim(nim)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Mahasiswa NIM: " + nim));

        // Map Mahasiswa entity to MahasiswaDTO
        MahasiswaDTO mahasiswaDTO = new MahasiswaDTO();
        mahasiswaDTO.setNim(mahasiswa.getNim());
        mahasiswaDTO.setNama(mahasiswa.getNama());
        mahasiswaDTO.setJurusan(mahasiswa.getJurusan());
        mahasiswaDTO.setTanggalLahir(mahasiswa.getTanggalLahir());

        model.addAttribute("mahasiswa", mahasiswaDTO);
        return "edit";
    }

    @PostMapping("/edit/{nim}")
    public String editMahasiswa(@PathVariable("nim") String nim,
            @Valid @ModelAttribute("mahasiswa") MahasiswaDTO mahasiswaDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit";
        }
        // Fetch existing Mahasiswa entity
        Mahasiswa existingMahasiswa = mahasiswaRepository.findByNim(nim)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Mahasiswa NIM: " + nim));

        // Update entity fields with DTO values
        existingMahasiswa.setNim(mahasiswaDTO.getNim());
        existingMahasiswa.setNama(mahasiswaDTO.getNama());
        existingMahasiswa.setJurusan(mahasiswaDTO.getJurusan());
        existingMahasiswa.setTanggalLahir(mahasiswaDTO.getTanggalLahir());

        // Save updated entity
        mahasiswaRepository.save(existingMahasiswa);
        return "redirect:/";
    }

    @GetMapping("/detail/{nim}")
    public String detailMahasiswaForm(@PathVariable("nim") String nim, Model model) {
        Mahasiswa mahasiswa = mahasiswaRepository.findByNim(nim)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Mahasiswa NIM: " + nim));
        model.addAttribute("mahasiswa", mahasiswa);
        return "detail";
    }

    @GetMapping("/delete/{nim}")
    public String deleteMahasiswa(@PathVariable("nim") String nim) {
        Mahasiswa mahasiswa = mahasiswaRepository.findByNim(nim)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Mahasiswa NIM: " + nim));
        mahasiswaRepository.delete(mahasiswa);
        return "redirect:/";
    }
}
