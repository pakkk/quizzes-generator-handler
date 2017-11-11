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

package com.pacobenitezchico.quizzes.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pacobenitezchico.quizzes.domain.UserDto;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface JpaUserDtoRepository extends JpaRepository<UserDto, String>
{
	/**
	 * @param username with the user name
	 * @param password with the password
	 * @return the user with these features
	 */
    @Query(nativeQuery = true, value = " select us.* " +
    								   " from user_dto us " +
    								   " where lower(us.username) LIKE concat('%', lower(:username), '%') " +
    						           " and   us.password LIKE :password ")
    UserDto authenticate(@Param("username") final String username, @Param("password") final String password) ;
    
	/**
	 * @param username with the user name
	 * @return the user with these features
	 */
    @Query(nativeQuery = true, value = " select us.* " +
    								   " from user_dto us " +
    								   " where lower(us.username) LIKE concat('%', lower(:username), '%') ")
    UserDto getUserByName(@Param("username") final String username) ;
}
