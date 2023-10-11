package com.smkbinainformatika.kasir.hafidz.service;

import com.smkbinainformatika.kasir.hafidz.model.Stok;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class StokServiceTest {

    @Order(1)
    @Test
    void addStok() {
        int id = 1;
        Stok laptop = new Stok();
        laptop.setId(id++);
        laptop.setKodeBarang("LP001");
        laptop.setStokBarang(10);
        StokService.getInstance().addStok(laptop);

        Stok mouse = new Stok();
        mouse.setId(id++);
        mouse.setKodeBarang("MO001");
        mouse.setStokBarang(67);
        StokService.getInstance().addStok(mouse);

        Stok laptopgaming = new Stok();
        laptopgaming.setId(id++);
        laptopgaming.setKodeBarang("LP002");
        laptopgaming.setStokBarang(5);
        StokService.getInstance().addStok(laptopgaming);
    }

    @Order(2)
    @Test
    void getStok() {
        List<Stok> stokList = StokService.getInstance().getStok();
        assertEquals(stokList.size(),3);
    }

    @Order(3)
    @Test
    void findByKodeBarang() {
        List<Stok> resultList = StokService.getInstance().findByKodeBarang("LP001");
        assertEquals(resultList.size(), 1);
        // Berlaku untuk pengecekan dengan mengambil satu sample data untuk memastikan: System.out.println(resultList.toArray()[0]);
    }

}