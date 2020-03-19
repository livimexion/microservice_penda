package com.isi.msamamependacissem2gl.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.isi.msamamependacissem2gl.dao.ProductDao;
import com.isi.msamamependacissem2gl.exceptions.ProductIntrouvableException;
import com.isi.msamamependacissem2gl.model.Product;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * auteur:Mame Penda Cisse
 * classe:M2GL
 */

@Api(description = "Mame Penda Cisse M2GL /crud des produits ")
@RestController
public class ProductController {
    @Autowired
    private ProductDao productDao;


    @GetMapping(value = "/products")
    public MappingJacksonValue listProducts(){
        List<Product> products = productDao.findAll();
        SimpleBeanPropertyFilter monFiltre =SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        FilterProvider listDeNosFilters = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
        MappingJacksonValue productsFilters = new MappingJacksonValue(products);
        productsFilters.setFilters(listDeNosFilters);

        return productsFilters;
    }



    @GetMapping (value = "/products/{id}")
    public Product getProducts(@PathVariable int id) {

        Product product = productDao.findById(id);
        if (product==null) throw new ProductIntrouvableException("le produit avec l'id: "+id+" est introuvable");

        return product;
    }
    /*

    @GetMapping (value = "test/products/{prixLimit}")
    public List<Product> testRequetes(@PathVariable int prixLimit){
        return productDao.findByPrixGreaterThan(400);
    }

     */
    @GetMapping (value = "/AdminProducts")
    public List<Product> calculMargeProduit() {

        return productDao.findAll() ;
    }

    @GetMapping (value = "test/products/{recherche}")
    public List<Product> testRecherche(@PathVariable String recherche){
        return productDao.findByNomLike("%"+recherche+"%");
    }

    @GetMapping (value = "AdminProducts/ordre")
    public List<Product> trierProduitsParOrdreAlphabetique(){
        return productDao.findByOrderByNomAsc();
    }



    @PostMapping(value = "/Products")
    public ResponseEntity<Void> addProduct(@Valid @RequestBody Product product){
        Product productAdded = productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(productAdded.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }

@DeleteMapping(value = "/products/{id}")
    public void deletProduct(@PathVariable int id){

        productDao.deleteById(id);
}

    @PutMapping(value = "/products")
    public void upProduct(@RequestBody Product product){

        productDao.save(product);
    }

}
