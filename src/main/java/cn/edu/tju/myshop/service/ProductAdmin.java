/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.tju.myshop.service;

import cn.edu.tju.myshop.data.ProductRepository;
import cn.edu.tju.myshop.model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class ProductAdmin {

    @Inject
    private Logger log;
    
    @Inject
    private ProductRepository productRepository;

    public void add(Product product) throws Exception {
        log.info("Adding " + product.getName());
        productRepository.add(product);
    }
    
    public void delete(int id) {
    	productRepository.delete(id);
    }
    
    public void update(Product product) {
    	productRepository.update(product);
    }
}
