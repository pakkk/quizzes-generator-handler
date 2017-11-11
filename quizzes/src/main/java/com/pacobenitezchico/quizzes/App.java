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

package com.pacobenitezchico.quizzes ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.SpringApplication ;
import org.springframework.boot.autoconfigure.SpringBootApplication ;
import org.springframework.context.annotation.Bean ;
import org.springframework.context.annotation.ComponentScan ;
import org.springframework.context.annotation.Configuration ;

import com.pacobenitezchico.quizzes.api.rest.IRestListener;
import com.pacobenitezchico.quizzes.api.rest.RestHandler;
import com.pacobenitezchico.quizzes.api.rest.RestListener;
import com.pacobenitezchico.quizzes.repositories.RepositoryPopulator;


/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackageClasses = {RestHandler.class, RepositoryPopulator.class})
public class App
{

	/**
	 * Main method
	 * @param args with the input arguments
	 */
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args) ;
    }
    
    /**
     * Method which injects the listener
     */
	@Bean
	@Autowired
	IRestListener injectListenerJavaquizApi()
	{
		return new RestListener() ;
	}
}
