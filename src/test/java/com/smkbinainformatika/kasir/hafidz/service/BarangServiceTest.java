package com.smkbinainformatika.kasir.hafidz.service;

import com.smkbinainformatika.kasir.hafidz.dao.BarangDao;
import com.smkbinainformatika.kasir.hafidz.model.Barang;
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

class BarangServiceTest {

    @org.junit.jupiter.api.Test
    @Order(1)
    void addBarang() {
        Barang laptop = new Barang();
        laptop.setKodeBarang("LP001");
        laptop.setNamaBarang("Laptop");
        laptop.setHargaBarang(5000000);
        BarangService.getInstance().addBarang(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("MO001");
        mouse.setNamaBarang("Mouse");
        mouse.setHargaBarang(100000);
        BarangService.getInstance().addBarang(mouse);

        Barang laptopGaming = new Barang();
        laptopGaming.setKodeBarang("LP002");
        laptopGaming.setNamaBarang("Laptop Gaming");
        laptopGaming.setHargaBarang(20000000);
        BarangService.getInstance().addBarang(laptopGaming);
    }
   
    @Test
    @Order(2)
    void getBarangList() {
        List<Barang> barangList = BarangService.getInstance().getBarangList();
        assertEquals(barangList.size(),3);
    }

    @Test
    @Order(3)
    void findByName() {
        List<Barang> resultList = BarangService.getInstance().findByName("Laptop");
        assertEquals(resultList.size(), 2);
        // Berlaku untuk pengecekan dengan mengambil satu sample data untuk memastikan: System.out.println(resultList.toArray()[0]);
    }

    @Test
    @Order(4)
    void saveBarangToDatabase() {
        BarangDao barangDao = new BarangDao();
        Barang laptop = new Barang();
        laptop.setKodeBarang("LP001");
        laptop.setNamaBarang("Laptop");
        laptop.setHargaBarang(5000000);
        laptop.setDateCreated(new Date());
        laptop.setLastModified(new Date());
        barangDao.save(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("MO001");
        mouse.setNamaBarang("Mouse");
        mouse.setHargaBarang(100000);
        mouse.setDateCreated(new Date());
        mouse.setLastModified(new Date());
        barangDao.save(mouse);

        Barang laptopGaming = new Barang();
        laptopGaming.setKodeBarang("LP002");
        laptopGaming.setNamaBarang("Laptop Gaming");
        laptopGaming.setHargaBarang(20000000);
        laptopGaming.setDateCreated(new Date());
        laptopGaming.setLastModified(new Date());
        barangDao.save(laptopGaming);
    }

    @Test
    @Order(5)
    void getDataById() {
        BarangDao barangDao = new BarangDao();
        Optional<Barang> barang1 = barangDao.get(1);
        barang1.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals(barang.getNamaBarang(), "Laptop");
                assertEquals(barang.getKodeBarang(), "LP001");
            }
        });
        Optional<Barang> barang2 = barangDao.get(2);
        barang2.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals(barang.getNamaBarang(), "Mouse");
                assertEquals(barang.getKodeBarang(), "MO001");
            }
        });
        Optional<Barang> barang3 = barangDao.get(3);
        barang3.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals(barang.getNamaBarang(), "Laptop Gaming");
                assertEquals(barang.getKodeBarang(), "LP002");
            }
        });
    }

    @Test
    @Order(6)
    void updateBarangByKodeBarang() {
        BarangDao barangDao = new BarangDao();
        Barang laptopGaming = new Barang();
        laptopGaming.setKodeBarang("LP002");
        laptopGaming.setNamaBarang("Laptop Gaming Ryzen 3 RTX 4090 TI 32GB RAM");
        laptopGaming.setHargaBarang(14000000);
        barangDao.update(laptopGaming);
        Optional<Barang> barang = barangDao.get(3);
        barang.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Laptop Gaming Ryzen 3 RTX 4090 TI 32GB RAM", barang.getNamaBarang());
                assertEquals(14000000, barang.getHargaBarang());
            }
        });
    }


}
