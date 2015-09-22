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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;

import cn.edu.tju.myshop.model.City;

@ApplicationScoped
public class CityRepository {

    @Inject
    private EntityManager em;

    public City findByCityId(String cityId) {
        return em.find(City.class, cityId);
    }

	public List<City> findByProvinceId(String provinceId) {
        return em.createQuery("SELECT c FROM Province p JOIN p.cities c WHERE p.provinceId= :provinceId", City.class)
        		.setParameter("provinceId", provinceId)
        		.getResultList();
    }
	
	public List<City> findByProvinceName(String provinceName) {
        return em.createQuery("SELECT c FROM Province p JOIN p.cities c WHERE p.name = :provinceName", City.class)
        		.setParameter("provinceName", provinceName)
        		.getResultList();
    }
    
}
