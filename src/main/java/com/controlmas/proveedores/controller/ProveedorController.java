package com.controlmas.proveedores.controller;

import com.controlmas.proveedores.models.entity.Proveedores;
import com.controlmas.proveedores.models.repository.IProveedorRepository;
import com.controlmas.proveedores.models.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("titulo", "Listado de Proveedores");
        model.addAttribute("proveedorList", proveedorService.findAll());
        return "listar";
    }

    @GetMapping("/crear")
    public String crear(Model model){
        Proveedores nuevoProveedor = new Proveedores();
        model.addAttribute("proveedor", nuevoProveedor);
        model.addAttribute("titulo", "Crear Proveedor");
        return "form";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("proveedor") Proveedores proveedor, Model model){
        proveedorService.save(proveedor);
        return "redirect:/listar";
    }

}
