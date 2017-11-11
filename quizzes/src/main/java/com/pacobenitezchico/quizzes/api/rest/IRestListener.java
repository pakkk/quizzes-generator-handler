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

package com.pacobenitezchico.quizzes.api.rest ;

import com.pacobenitezchico.quizzes.api.exception.ApiException;
import com.pacobenitezchico.quizzes.api.model.QuizAnswer;
import com.pacobenitezchico.quizzes.api.model.QuizQuestion;
import com.pacobenitezchico.quizzes.domain.UserDto;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IRestListener
{
	/**
	 *
     * @param userName with the user name
     * @param password with the password
	 * @return the User DTO
	 * @throws ApiException with an occurred exception
	 */
	public UserDto authenticate(final String userName, final String password) throws ApiException ;
	
	/**
	 * @param userName with the user name
	 * @return the User DTO
	 * @throws ApiException with an occurred exception
	 */
	public UserDto checkAndStoreUserName(final String userName) throws ApiException ;

	/**
	 * @return generate a new quiz as array of QuizQuestion
	 * @throws ApiException with an occurred exception
	 */
	public QuizQuestion[] generateQuiz() throws ApiException ;
	
	/**
	 *
     * @param quizAnswersUser with the quiz answers
     * @param userDto	  	  with the user DTO
     * @throws ApiException with an occurred exception
	 */
	public void storeQuiz(final QuizAnswer[] quizAnswersUser, final UserDto userDto) throws ApiException ;

	/**
	 * @param userDto with the user DTO
	 * @return remember an existing quiz as array of QuizQuestion
	 * @throws ApiException with an occurred exception
	 */
	public QuizQuestion[] rememberQuiz(final UserDto userDto) throws ApiException ;

	/**
     * @param userName with the user name
	 * @return check an existing quiz as array of QuizQuestion
	 * @throws ApiException with an occurred exception
	 */
	public QuizQuestion[] checkQuiz(final String userName) throws ApiException ;
	
	/**
	 * @return all the answers as array of QuizQuestion
	 * @throws ApiException with an occurred exception
	 */
	public QuizQuestion[] fullAnswers() throws ApiException ;
	
	/**
     * @param questionId The question identifier
	 * @return a specific answer 
	 * @throws ApiException with an occurred exception
	 */
	public QuizQuestion specificAnswer(final Integer questionId) throws ApiException ;

}
