package com.example.Penugasan1.controller;

import com.example.Penugasan1.dto.MahasiswaDTO;
import com.example.Penugasan1.entity.Mahasiswa;
import com.example.Penugasan1.repository.MahasiswaRepository;
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
    public String addMahasiswa(@Valid @ModelAttribute("mahasiswa") MahasiswaDTO mahasiswaDTO, BindingResult result, Model model) {
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


    @GetMapping("/edit/{id}")
    public String editMahasiswaForm(@PathVariable("id") Long id, Model model) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Mahasiswa Id: " + id));

        // Map Mahasiswa entity to MahasiswaDTO
        MahasiswaDTO mahasiswaDTO = new MahasiswaDTO();
        mahasiswaDTO.setId(mahasiswa.getId());
        mahasiswaDTO.setNim(mahasiswa.getNim());
        mahasiswaDTO.setNama(mahasiswa.getNama());
        mahasiswaDTO.setJurusan(mahasiswa.getJurusan());
        mahasiswaDTO.setTanggalLahir(mahasiswa.getTanggalLahir());

        model.addAttribute("mahasiswa", mahasiswaDTO);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editMahasiswa(@PathVariable("id") Long id, @Valid @ModelAttribute("mahasiswa") MahasiswaDTO mahasiswaDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit";
        }
        // Fetch existing Mahasiswa entity
        Mahasiswa existingMahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Mahasiswa Id: " + id));

        // Update entity fields with DTO values
        existingMahasiswa.setNim(mahasiswaDTO.getNim());
        existingMahasiswa.setNama(mahasiswaDTO.getNama());
        existingMahasiswa.setJurusan(mahasiswaDTO.getJurusan());
        existingMahasiswa.setTanggalLahir(mahasiswaDTO.getTanggalLahir());

        // Save updated entity
        mahasiswaRepository.save(existingMahasiswa);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detailMahasiswaForm(@PathVariable("id") Long id, Model model) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Mahasiswa Id: " + id));
        model.addAttribute("mahasiswa", mahasiswa);
        return "detail";
    }


    @GetMapping("/delete/{id}")
    public String deleteMahasiswa(@PathVariable("id") Long id) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Mahasiswa Id: " + id));
        mahasiswaRepository.delete(mahasiswa);
        return "redirect:/";
    }
}
