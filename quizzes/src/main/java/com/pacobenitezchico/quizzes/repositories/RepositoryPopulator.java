/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.pacobenitezchico.quizzes.repositories;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.init.Jackson2ResourceReader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pacobenitezchico.quizzes.domain.UserDto;
import com.pacobenitezchico.quizzes.domain.QuizDto;
import com.pacobenitezchico.quizzes.repositories.jpa.JpaUserDtoRepository;
import com.pacobenitezchico.quizzes.repositories.jpa.JpaQuizDtoRepository;
import com.pacobenitezchico.quizzes.utils.Constants;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
@Component
public class RepositoryPopulator implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware
{
	/** Attribute - Resource Reader */
    private final Jackson2ResourceReader resourceReader ;
    
    /** Attribute - Application context */
    private ApplicationContext applicationContext ;

    /**
     * Public constructor
     */
    public RepositoryPopulator()
    {
        ObjectMapper mapper = new ObjectMapper() ;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) ;
        
        this.resourceReader = new Jackson2ResourceReader(mapper) ;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext ;
    }

	@Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        if (event.getApplicationContext().equals(this.applicationContext))
        {
        	// User Repository
        	final JpaUserDtoRepository userRepository = BeanFactoryUtils.beanOfTypeIncludingAncestors(this.applicationContext, JpaUserDtoRepository.class) ;

            if (userRepository != null && userRepository.count() == 0)
            {
                this.populateUser(userRepository) ;
            }
            
            // Quiz repository
            final JpaQuizDtoRepository quizRepository = BeanFactoryUtils.beanOfTypeIncludingAncestors(this.applicationContext, JpaQuizDtoRepository.class) ;

            if (quizRepository != null && quizRepository.count() == 0)
            {
                this.populateQuizzes(quizRepository) ;
            }
        }
    }
	
    /**
     * @param jpaRepository with the JPA Repository
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void populateUser(final JpaRepository jpaRepository)
    {
    	final Resource users = new ClassPathResource(Constants.USER_JSON) ;
    	final Object entity  = this.getEntityFromResource(users) ;
    	
    	if (entity instanceof Collection)
        {
            for (final UserDto userDto : (Collection<UserDto>) entity)
            {
                if (userDto != null)
                {
                    jpaRepository.save(userDto) ;
                }
            }
        }
        else
        {
            jpaRepository.save(entity) ;
        }
    }

    /**
     * @param jpaRepository with the JPA Repository
     */
    @SuppressWarnings({ "rawtypes" })
    public void populateQuizzes(final JpaRepository jpaRepository)
    {
    	// Populate from the quizzes text area JSON
    	this.populateFromQuizzesTextArea(jpaRepository) ;
    	
    	// Populate from the quizzes radio button JSON
    	this.populateFromQuizzesRadiobutton(jpaRepository) ;
    }

    /**
     * @param jpaRepository with the JPA Repository
     */
    @SuppressWarnings("rawtypes")
	private void populateFromQuizzesTextArea(final JpaRepository jpaRepository)
    {
    	final Resource quizzesTextArea = new ClassPathResource(Constants.QUIZZES_TEXTAREA_JSON) ;
    	final Object entity 		   = this.getEntityFromResource(quizzesTextArea) ;
    	
    	this.populate(jpaRepository, entity) ;
	}
    
    /**
     * @param jpaRepository with the JPA Repository
     */
    @SuppressWarnings("rawtypes")
	private void populateFromQuizzesRadiobutton(final JpaRepository jpaRepository)
    {
    	final Resource quizzesTextArea = new ClassPathResource(Constants.QUIZZES_RADIOBUTTON_JSON) ;
    	final Object entity 		   = this.getEntityFromResource(quizzesTextArea) ;
    	
    	this.populate(jpaRepository, entity) ;
	}

    /**
     * @param jpaRepository with the JPA Repository
     * @param entity	    with the entity
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void populate(final JpaRepository jpaRepository, final Object entity)
	{
		if (entity instanceof Collection)
        {
            for (final QuizDto quizDto : (Collection<QuizDto>) entity)
            {
                if (quizDto != null)
                {
                    jpaRepository.save(quizDto) ;
                }
            }
        }
        else
        {
            jpaRepository.save(entity) ;
        }
	}

	/**
     * @param resource with the resource
     * @return the entity
     */
    private Object getEntityFromResource(final Resource resource)
    {
        try
        {
            return this.resourceReader.readFrom(resource, this.getClass().getClassLoader()) ;
        }
        catch (Exception exception)
        {
            throw new RuntimeException(exception) ;
        }
    }
}
