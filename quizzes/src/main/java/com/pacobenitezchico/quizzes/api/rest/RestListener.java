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

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.pacobenitezchico.quizzes.api.exception.ApiException;
import com.pacobenitezchico.quizzes.api.model.QuizAnswer;
import com.pacobenitezchico.quizzes.api.model.QuizQuestion;
import com.pacobenitezchico.quizzes.domain.QuizDto;
import com.pacobenitezchico.quizzes.domain.UserDto;
import com.pacobenitezchico.quizzes.domain.UserQuizDto;
import com.pacobenitezchico.quizzes.repositories.jpa.JpaQuizDtoRepository;
import com.pacobenitezchico.quizzes.repositories.jpa.JpaUserDtoRepository;
import com.pacobenitezchico.quizzes.repositories.jpa.JpaUserQuizDtoRepository;
import com.pacobenitezchico.quizzes.utils.Constants;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class RestListener implements IRestListener
{
	/** Attribute - JPA User DTO Repository */
	@Autowired
    private JpaUserDtoRepository jpaUserDtoRepository ;
	
	/** Attribute - JPA Quiz DTO Repository */
	@Autowired
    private JpaQuizDtoRepository jpaQuizDtoRepository ;
	
	/** Attribute - JPA User Quiz DTO */
	@Autowired
    private JpaUserQuizDtoRepository jpaUserQuizDtoRepository ;
	
	/**
	 *
     * @param userName with the user name
     * @param password with the password
     * @return the User DTO
     * @throws ApiException with an occurred exception
	 */
    @Override
	public UserDto authenticate(final String userName, final String password) throws ApiException
	{
		// Check the user value
		this.checkUserNameValue(userName) ;
		
		// Check the password value
		this.checkPasswordValue(password) ;
    	
    	// Check the authenticate user
    	final UserDto userDto = this.jpaUserDtoRepository.authenticate(userName, password) ;
    	if (userDto == null)
    	{
    		final ApiException apiException = new ApiException() ;
    		
    		apiException.setError("USER_INVALID") ;
    		apiException.setMessage("Usuario y/o password incorrectos") ;
    		
    		throw apiException ;   		
    	}
    	
    	return userDto ;
	}
    
	/**
	 * @param password with the password
	 * @throws ApiException with an occurred exception
	 */
	private void checkPasswordValue(final String password) throws ApiException
	{
    	if (password == null || password.isEmpty())
    	{
    		final ApiException apiException = new ApiException() ;
    		
    		apiException.setError("PASSWORD_NULL_OR_EMPTY") ;
    		apiException.setMessage("Se debe introducir el password de usuario") ;
    		
    		throw apiException ;
    	}
	}
    
	/**
	 * @param userName with the candidate
	 * @return the User DTO
	 * @throws ApiException with an occurred exception
	 */
	@Override
	public UserDto checkAndStoreUserName(final String userName) throws ApiException
	{
		// Check the user value
		this.checkUserNameValue(userName) ;
    	
    	// Check the authenticate user
    	UserDto userDto = this.jpaUserDtoRepository.authenticate(userName, Constants.DEFAULT_CANDIDATE_PASSWORD) ;
    	if (userDto != null)
    	{
    		final ApiException apiException = new ApiException() ;
    		
    		apiException.setError("USER_ALREADY_EXISTS") ;
    		apiException.setMessage("El candidato ya existe") ;
    		
    		throw apiException ;   		
    	}
    	
    	// New instance of UserDto (Candidate)
    	userDto = new UserDto(userName, Constants.DEFAULT_CANDIDATE_PASSWORD, false) ;
    	
    	// Save the new candidate
    	this.jpaUserDtoRepository.saveAndFlush(userDto) ;
    	
    	return userDto ;
	}
	
	/**
	 * @return generate a new array of quizzes as array of QuizQuestion
	 */
    @Override
	public QuizQuestion[] generateQuiz() throws ApiException
	{
    	// Get the current number of Quiz rows
    	final long quizRows = this.jpaQuizDtoRepository.count() ;
    	
    	// Initialize the array of Quizzes
    	final QuizQuestion[] outcome = new QuizQuestion[Constants.QUESTION_NUMBER_PER_CANDIDATE] ;
    	
    	// Random instance
    	final Random random = new Random() ;
    	
    	// Get the questions
    	for (int i=0 ; i < Constants.QUESTION_NUMBER_PER_CANDIDATE ; i++)
    	{
    		int identifier  = random.nextInt((int) quizRows) ;
    		if (identifier == 0)
    		{
    			identifier ++ ;
    		}
    		
    		final QuizDto quizDto = this.jpaQuizDtoRepository.findOne(Constants.STRING_EMPTY + identifier) ;
    		
    		outcome[i] = new QuizQuestion() ;
    		
    		outcome[i].setIdentifier(quizDto.getId()) ;
    		outcome[i].setType(quizDto.getType()) ;
    		outcome[i].setQuestion(quizDto.getQuestion()) ;
    	}

		return outcome ;
	}
    
	/**
     * @param quizAnswers with the quiz answers user
     * @param userDto    with the user DTO
	 */
    @Override
	public void storeQuiz(final QuizAnswer[] quizAnswers, final UserDto userDto) throws ApiException
	{
		if (quizAnswers == null || quizAnswers.length == 0)
		{
    		final ApiException apiException = new ApiException() ;
    		
    		apiException.setError("QUIZ_ANSWERS_NULL_OR_EMPTY") ;
    		apiException.setMessage("Se debe introducir la lista de respuestas") ;
    		
    		throw apiException ;
		}
		
		final int quizAnswersLength = quizAnswers.length ;
		for (int i=0 ; i < quizAnswersLength ; i++)
		{
			// Get the quiz answer
			final QuizAnswer quizAnswer = quizAnswers[i] ;
			
			// Get the database quiz DTO related to
			final QuizDto quizDto 		= this.jpaQuizDtoRepository.findOne(quizAnswer.getQuestionId()) ;
			
			// Check if there is an existing row with the user and the quiz
			UserQuizDto userQuizDto 	= this.jpaUserQuizDtoRepository.getExistingUserQuiz(userDto, quizDto) ;
			if (userQuizDto == null)
			{
				// Otherwise, create a new instance of UserQuizDto
				userQuizDto = new UserQuizDto() ;
				
				// Set the attributes userDto and quizDto
				userQuizDto.setUser(userDto) ;
				userQuizDto.setQuiz(quizDto) ;
			}
			
			userQuizDto.setAnswer(quizAnswer.getUserAnswer()) ;
			
			// Save the answer
			this.jpaUserQuizDtoRepository.saveAndFlush(userQuizDto) ;
		}
	}

	/**
	 *
     * @param userDto with the user DTO
	 * @return remember an existing quiz as array of QuizQuestion
	 */
    @Override
	public QuizQuestion[] rememberQuiz(final UserDto userDto) throws ApiException
	{
    	// Get the list of UserQuizDto
    	final List<UserQuizDto> userQuizDtoList = this.getUserQuizDtoList(userDto) ;
    	
    	// Size of the list
    	final int userQuizDtoListLength 		= userQuizDtoList.size() ;
    	
    	// Initialize the array of stored Quizzes
    	final QuizQuestion[] outcome 			= new QuizQuestion[userQuizDtoListLength] ;
    	
    	// Get the stored quizzes
    	for (int i=0 ; i < userQuizDtoListLength ; i++)
    	{
    		final UserQuizDto userQuizDtoItem = userQuizDtoList.get(i) ;
    		final QuizDto quizDtoItem		  = userQuizDtoItem.getQuiz() ;
    		
    		final String identifier	  		  = quizDtoItem.getId() ;
    		final String type		  		  = quizDtoItem.getType() ;
    		final String question	  		  = quizDtoItem.getQuestion() ;
    		final String userAnswer			  = userQuizDtoItem.getAnswer() ;
    		
    		outcome[i] = new QuizQuestion(identifier, type, question, userAnswer, null) ;
    	}

		return outcome ;
	}

	/**
	 *
     * @param candidate the candidate
	 * @return check an existing quiz as array of QuizAnswerChecked
	 */
    @Override
	public QuizQuestion[] checkQuiz(final String userName) throws ApiException
	{
		// Check the user value
		this.checkUserNameValue(userName) ;
		
		// Get the user from the name
    	final UserDto userDto = this.jpaUserDtoRepository.getUserByName(userName) ;
    	if (userDto == null)
    	{
    		final ApiException apiException = new ApiException() ;
    		
    		apiException.setError("USER_INVALID") ;
    		apiException.setMessage("Nombre de usuario incorrecto") ;
    		
    		throw apiException ;   		
    	}
    	
    	// Get the list of UserQuizDto
    	final List<UserQuizDto> userQuizDtoList = this.getUserQuizDtoList(userDto) ;
    	
    	// Size of the list
    	final int userQuizDtoListLength 		= userQuizDtoList.size() ;
    	
    	// Initialize the array of stored Quizzes
    	final QuizQuestion[] outcome 			= new QuizQuestion[userQuizDtoListLength] ;
    	
    	// Get the stored quizzes
    	for (int i=0 ; i < userQuizDtoListLength ; i++)
    	{
    		final UserQuizDto userQuizDtoItem = userQuizDtoList.get(i) ;
    		final QuizDto quizDtoItem		  = userQuizDtoItem.getQuiz() ;
    		
    		final String identifier	  		  = quizDtoItem.getId() ;
    		final String type		  		  = quizDtoItem.getType() ;
    		final String question	  		  = quizDtoItem.getQuestion() ;
    		final String userAnswer			  = userQuizDtoItem.getAnswer() ;
    		final String checkedAnswer		  = quizDtoItem.getAnswer() ;
    		
    		outcome[i] = new QuizQuestion(identifier, type, question, userAnswer, checkedAnswer) ;
    	}

		return outcome ;
	}
    
	/**
	 * @param userName with the user name
	 * @throws ApiException with an occurred exception
	 */
	private void checkUserNameValue(final String userName) throws ApiException
	{
    	if (userName == null || userName.isEmpty())
    	{
    		final ApiException apiException = new ApiException() ;
    		
    		apiException.setError("USERNAME_NULL_OR_EMPTY") ;
    		apiException.setMessage("Se debe introducir el nombre de usuario") ;
    		
    		throw apiException ;
    	}
	}
	
	/**
	 * @param userDto with the user DTO
	 * @return a list of UserQuizDto
	 * @throws ApiException with an occurred exception
	 */
	private List<UserQuizDto> getUserQuizDtoList(final UserDto userDto) throws ApiException
	{
		final List<UserQuizDto> userQuizDtoList = this.jpaUserQuizDtoRepository.getUserQuizDtoFromUserDto(userDto) ;
    	
    	if (userQuizDtoList == null || userQuizDtoList.isEmpty())
    	{
    		final ApiException apiException = new ApiException() ;
    		
    		apiException.setError("QUIZ_ANSWERS_STORED_NULL_OR_EMPTY") ;
    		apiException.setMessage("No existen respuestas almacenadas para el usuario") ;
    		
    		throw apiException ;    		
    	}
    	
    	return userQuizDtoList ;
	}
    
	/**
	 *
	 * @return all the answers
	 */
    @Override
	public QuizQuestion[] fullAnswers() throws ApiException
	{
    	final List<QuizDto> quizDtoList = this.jpaQuizDtoRepository.findAll() ;
    	
    	if (quizDtoList == null || quizDtoList.isEmpty())
    	{
    		final ApiException apiException = new ApiException() ;
    		
    		apiException.setError("QUIZ_STORED_NULL_OR_EMPTY") ;
    		apiException.setMessage("No existen preguntas almacenadas") ;
    		
    		throw apiException ;    		
    	}

    	// Size of the list
    	final int quizDtoListLength  = quizDtoList.size() ;
    	
    	// Initialize the array of stored Quizzes
    	final QuizQuestion[] outcome = new QuizQuestion[quizDtoListLength] ;
    	
    	// Get the stored quizzes
    	for (int i=0 ; i < quizDtoListLength ; i++)
    	{
    		final QuizDto quizDtoItem  = quizDtoList.get(i) ;
    		
    		final String identifier	   = quizDtoItem.getId() ;
    		final String type		   = quizDtoItem.getType() ;
    		final String question	   = quizDtoItem.getQuestion() ;
    		final String checkedAnswer = quizDtoItem.getAnswer() ;
    		
    		outcome[i] = new QuizQuestion(identifier, type, question, null, checkedAnswer) ;
    	}

		return outcome ;
	}

	/**
	 *
     * @param questionId The question identifier
	 * @return all the answers
	 */
    @Override
	public QuizQuestion specificAnswer(final Integer questionId) throws ApiException
	{
    	final QuizDto quizDto = this.jpaQuizDtoRepository.findOne(Constants.STRING_EMPTY + questionId) ;
    	
    	if (quizDto == null)
    	{
    		final ApiException apiException = new ApiException() ;
    		
    		apiException.setError("QUIZ_STORED_NULL_OR_EMPTY") ;
    		apiException.setMessage("No existe ninguna pregunta almacenada con ese identificador") ;
    		
    		throw apiException ;    		
    	}
    	
    	final String identifier	   = quizDto.getId() ;
		final String type		   = quizDto.getType() ;
		final String question	   = quizDto.getQuestion() ;
		final String checkedAnswer = quizDto.getAnswer() ;
		
		return new QuizQuestion(identifier, type, question, null, checkedAnswer) ;
	}
    
    
    
}
