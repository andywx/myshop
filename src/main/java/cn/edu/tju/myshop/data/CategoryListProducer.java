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
package cn.edu.tju.myshop.data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;

import cn.edu.tju.myshop.model.Category;

@RequestScoped
public class CategoryListProducer {

    @Inject
    private CategoryRepository categoryRepository;

    private List<Category> categories;

    @Produces
    @Named
    public List<Category> getCategories() {
        return categories;
    }

    public void onCategoryListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Category rank) {
        retrieveAllCategoriesOrderedByName();
    }

    @PostConstruct
    public void retrieveAllCategoriesOrderedByName() {
    	categories = categoryRepository.findAllOrderedByName();
    }
}
