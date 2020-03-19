package com.isi.msamamependacissem2gl.dao;

import com.isi.msamamependacissem2gl.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * auteur:Mame Penda Cisse
 * classe:M2GL
 */
import java.util.List;
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    //public List<Product>findAll();
    List<Product>findByPrixGreaterThan(int prixLimit);
    List<Product>findByNomLike(String recherche);
    List<Product>findByOrderByNomAsc();
    Product findById(int id);
    Product deleteById(int id);



    //public Product save(Product product);
}
