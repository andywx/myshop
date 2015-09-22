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

import cn.edu.tju.myshop.model.County;

@ApplicationScoped
public class CountyRepository {

    @Inject
    private EntityManager em;

    public County findByCountyId(String countyId) {
        return em.find(County.class, countyId);
    }

	public List<County> findByCityId(String cityId) {
        return em.createQuery("SELECT ct FROM City c JOIN c.counties ct WHERE c.cityId = :cityId", County.class)
        		.setParameter("cityId", cityId)
        		.getResultList();
    }
	
	public List<County> findByProvinceAndCityName(String provinceName, String cityName) {
        return em.createQuery("SELECT ct FROM City c JOIN c.counties ct JOIN c.province p WHERE c.name = :cityName AND p.name = :provinceName", County.class)
        		.setParameter("cityName", cityName)
        		.setParameter("provinceName", provinceName)
        		.getResultList();
    }
    
}
