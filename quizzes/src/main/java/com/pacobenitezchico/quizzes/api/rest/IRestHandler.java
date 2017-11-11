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

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity ;

import com.pacobenitezchico.quizzes.api.model.QuizAnswer;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public interface IRestHandler
{
	/**
	 * @param httpSession with the HTTP session
	 * @param username	  with the user name
	 * @param password	  with the password
     * @return a new instance of ResponseEntity
	 */
	public ResponseEntity<?> authenticate(final HttpSession httpSession, final String username, final String password) ;
	
	/**
	 * @param httpSession with the HTTP session
	 * @return a new instance of ResponseEntity
	 */
	public ResponseEntity<?> deauthenticate(final HttpSession httpSession) ;

	/**
	 * @param httpSession with the HTTP session
     * @param userName    with the user name
     * @return a new instance of ResponseEntity
	 */
	public ResponseEntity<?> generateQuiz(final HttpSession httpSession, final String userName) ;

	/**
	 * @param httpSession with the HTTP session
     * @param quizAnswers with the quiz answers
     * @return a new instance of ResponseEntity
	 */
	public ResponseEntity<?> storeQuiz(final HttpSession httpSession, final QuizAnswer[] quizAnswers) ;
	
	/**
	 * @param httpSession with the HTTP session
     * @return a new instance of ResponseEntity
	 */
	public ResponseEntity<?> rememberQuiz(final HttpSession httpSession) ;

	/**
	 * @param httpSession with the HTTP session
     * @param userName    with the user name
     * @return a new instance of ResponseEntity
	 */
	public ResponseEntity<?> checkQuiz(final HttpSession httpSession, final String userName) ;
	
	/**
	 * @param httpSession with the HTTP session
	 * @return a new instance of ResponseEntity
	 */
	public ResponseEntity<?> fullAnswers(final HttpSession httpSession) ;

	/**
	 * @param httpSession with the HTTP session
     * @param answerId    with the answer identifier
     * @return a new instance of ResponseEntity
	 */
	public ResponseEntity<?> specificAnswer(final HttpSession httpSession, final Integer answerId) ;
}
