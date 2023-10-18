package com.smkbinainformatika.kasir.hafidz.service;

import com.smkbinainformatika.kasir.hafidz.dao.StokDao;
import com.smkbinainformatika.kasir.hafidz.model.Stok;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

    @Order(4)
    @Test
    void saveStokDatabase() {
        int id = 1;
        StokDao stokDao = new StokDao();
        Stok laptop = new Stok();
        laptop.setId(id++);
        laptop.setKodeBarang("LP001");
        laptop.setStokBarang(10);
        laptop.setDateCreated(new Date());
        laptop.setLastModified(new Date());
        stokDao.save(laptop);

        Stok mouse = new Stok();
        mouse.setId(id++);
        mouse.setKodeBarang("MO001");
        mouse.setStokBarang(67);
        mouse.setDateCreated(new Date());
        mouse.setLastModified(new Date());
        stokDao.save(mouse);

        Stok laptopgaming = new Stok();
        laptopgaming.setId(id++);
        laptopgaming.setKodeBarang("LP002");
        laptopgaming.setStokBarang(5);
        laptopgaming.setDateCreated(new Date());
        laptopgaming.setLastModified(new Date());
        stokDao.save(laptopgaming);
    }

    @Order(5)
    @Test
    void getStokDatabase() {
        StokDao stokDao = new StokDao();
        Optional<Stok> stok1 = stokDao.get(1);
        stok1.ifPresent(new Consumer<Stok>() {
            @Override
            public void accept(Stok stok) {
                assertEquals(stok.getKodeBarang(), "LP001");
                assertEquals(stok.getStokBarang(), 10);
            }
        });

        Optional<Stok> stok2 = stokDao.get(2);
        stok2.ifPresent(new Consumer<Stok>() {
            @Override
            public void accept(Stok stok) {
                assertEquals(stok.getKodeBarang(), "MO001");
                assertEquals(stok.getStokBarang(), 67);
            }
        });

        Optional<Stok> stok3 = stokDao.get(3);
        stok3.ifPresent(new Consumer<Stok>() {
            @Override
            public void accept(Stok stok) {
                assertEquals(stok.getKodeBarang(), "LP002");
                assertEquals(stok.getStokBarang(), 5);
            }
        });

    }

}