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

import java.io.PrintWriter ;
import java.io.StringWriter ;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity ;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pacobenitezchico.quizzes.api.exception.ApiException;
import com.pacobenitezchico.quizzes.api.model.QuizAnswer;
import com.pacobenitezchico.quizzes.domain.UserDto;
import com.pacobenitezchico.quizzes.utils.Constants;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
@RequestMapping(value = "/api", consumes = {"application/json"}, produces = {"application/json"})
@RestController
@CrossOrigin
@ConditionalOnClass(IRestListener.class)
public final class RestHandler implements IRestHandler
{
	/** Attribute - Rest Listener */
	@Autowired
	private IRestListener restListener ;
	
	/**
	 * Public constructor
	 */
    
    public RestHandler()
    {
    	// Empty constructor
    }
    
	/**
	 * @param httpSession with the HTTP session
	 * @param userName    with the user name
	 * @param password	  with the password
     * @return a new instance of ResponseEntity
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/authenticate")
	public ResponseEntity<?> authenticate(final HttpSession httpSession, @RequestParam(required=true) final String userName, @RequestParam(required=true) final String password)
	{
		try
		{
			// Authenticate
			final UserDto userDto = this.restListener.authenticate(userName, password) ;
			
			// Set session attribute as logged
			httpSession.setAttribute(Constants.SESSION_USER_LOGGED, userDto) ;
		
			// Return OK
			return ResponseEntity.ok().build() ;
		}
		catch (ApiException ApiException)
		{
			return ResponseEntity.status(ApiException.getStatus()).body(ApiException.getBodyExceptionMessage()) ;
		}
		catch (Exception exception)
		{
			final ApiException ApiException = new ApiException() ;

			ApiException.setError(exception.getMessage()) ;
			ApiException.setMessage(exception.getMessage()) ;
			ApiException.setException(this.getStackTrace(exception)) ;
			
			final String pathStringFilledValues = "/api/authenticate" ;
			ApiException.setPath(pathStringFilledValues) ;
			
			return new ResponseEntity<>(ApiException.getBodyExceptionMessage(), HttpStatus.valueOf(ApiException.getStatus())) ;
		}
	}
	
	/**
	 * @param httpSession with the HTTP session
	 * @return a new instance of ResponseEntity
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/deauthenticate")
	public ResponseEntity<?> deauthenticate(final HttpSession httpSession)
	{
		// Check there is any user logged
		this.getSessionUserLogged(httpSession) ;
		
		// Deautenticate the user
		httpSession.removeAttribute(Constants.SESSION_USER_LOGGED) ;
	
		// Return OK
		return ResponseEntity.ok().build() ;
	}

	/**
	 * @param httpSession with the HTTP session
     * @param userName    with the user name
	 * @return generate a new quiz
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/quiz")
	public ResponseEntity<?> generateQuiz(final HttpSession httpSession, @RequestParam(required=true) final String userName)
	{
		try
		{
			final UserDto userDto = this.restListener.checkAndStoreUserName(userName) ;
			
			if (userDto != null)
			{
				httpSession.setAttribute(Constants.SESSION_USER_LOGGED, userDto) ;
			}
			
			return ResponseEntity.ok().body(this.restListener.generateQuiz()) ;
		}
		catch (ApiException ApiException)
		{
			return ResponseEntity.status(ApiException.getStatus()).body(ApiException.getBodyExceptionMessage()) ;
		}
		catch (Exception exception)
		{
			final ApiException ApiException = new ApiException() ;

			ApiException.setError(exception.getMessage()) ;
			ApiException.setMessage(exception.getMessage()) ;
			ApiException.setException(this.getStackTrace(exception)) ;
			
			final String pathStringFilledValues = "/api/quiz" ;
			ApiException.setPath(pathStringFilledValues) ;
			
			return new ResponseEntity<>(ApiException.getBodyExceptionMessage(), HttpStatus.valueOf(ApiException.getStatus())) ;
		}
	}

	/**
	 * @param httpSession with the HTTP session
     * @param quizAnswers with the quiz answer
     * @return a new instance of ResponseEntity
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/quiz")
	public ResponseEntity<?> storeQuiz(final HttpSession httpSession, @RequestBody(required=true) final QuizAnswer[] quizAnswers)
	{
		try
		{
			// Get the user logged
			final UserDto sessionUserLogged = this.getSessionUserLogged(httpSession) ;
			
			// Store the quiz
			this.restListener.storeQuiz(quizAnswers, sessionUserLogged) ;
		
			return ResponseEntity.ok().build() ;
		}
		catch (ApiException ApiException)
		{
			return ResponseEntity.status(ApiException.getStatus()).body(ApiException.getBodyExceptionMessage()) ;
		}
		catch (Exception exception)
		{
			final ApiException ApiException = new ApiException() ;

			ApiException.setError(exception.getMessage()) ;
			ApiException.setMessage(exception.getMessage()) ;
			ApiException.setException(this.getStackTrace(exception)) ;
			
			final String pathStringFilledValues = "/api/quiz" ;
			ApiException.setPath(pathStringFilledValues) ;
			
			return new ResponseEntity<>(ApiException.getBodyExceptionMessage(), HttpStatus.valueOf(ApiException.getStatus())) ;
		}
	}
	
	/**
	 * @param httpSession with the HTTP session
     * @param candidate   with the candidate
	 * @return remember an existing quiz
	 */
	@Override
	@RequestMapping(method = RequestMethod.PATCH, value = "/quiz")
	public ResponseEntity<?> rememberQuiz(final HttpSession httpSession)
	{
		try
		{
			// Get the user logged
			final UserDto sessionUserLogged = this.getSessionUserLogged(httpSession) ;
			
			return ResponseEntity.ok().body(this.restListener.rememberQuiz(sessionUserLogged)) ;
		}
		catch (ApiException ApiException)
		{
			return ResponseEntity.status(ApiException.getStatus()).body(ApiException.getBodyExceptionMessage()) ;
		}
		catch (Exception exception)
		{
			final ApiException ApiException = new ApiException() ;

			ApiException.setError(exception.getMessage()) ;
			ApiException.setMessage(exception.getMessage()) ;
			ApiException.setException(this.getStackTrace(exception)) ;
			
			final String pathStringFilledValues = "/api/quiz" ;
			ApiException.setPath(pathStringFilledValues) ;
			
			return new ResponseEntity<>(ApiException.getBodyExceptionMessage(), HttpStatus.valueOf(ApiException.getStatus())) ;
		}
	}
	
	/**
	 * @param httpSession with the HTTP session
     * @param candidate   with the candidate
	 * @return check an existing quiz
	 */
	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/quiz")
	public ResponseEntity<?> checkQuiz(final HttpSession httpSession, @RequestParam(required=true) final String userName)
	{
		try
		{
			// Get the user logged
			final UserDto sessionUserLogged = this.getSessionUserLogged(httpSession) ;
			
			if (!sessionUserLogged.isAdmin())
			{
				throw new RuntimeException("Para poder validar las preguntas has de ser Administrador") ;
			}
			
			return ResponseEntity.ok().body(this.restListener.checkQuiz(userName)) ;
		}
		catch (ApiException ApiException)
		{
			return ResponseEntity.status(ApiException.getStatus()).body(ApiException.getBodyExceptionMessage()) ;
		}
		catch (Exception exception)
		{
			final ApiException ApiException = new ApiException() ;

			ApiException.setError(exception.getMessage()) ;
			ApiException.setMessage(exception.getMessage()) ;
			ApiException.setException(this.getStackTrace(exception)) ;
			
			final String pathStringFilledValues = "/api/quiz" ;
			ApiException.setPath(pathStringFilledValues) ;
			
			return new ResponseEntity<>(ApiException.getBodyExceptionMessage(), HttpStatus.valueOf(ApiException.getStatus())) ;
		}
	}

	/**
	 * @param httpSession with the HTTP session
	 * @return all the answers
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/answers")
	public ResponseEntity<?> fullAnswers(final HttpSession httpSession)
	{
		try
		{
			// Get the user logged
			final UserDto sessionUserLogged = this.getSessionUserLogged(httpSession) ;
			
			if (!sessionUserLogged.isAdmin())
			{
				throw new RuntimeException("Para poder ver todas las respuestas de las preguntas de la BBDD, has de ser Administrador") ;
			}
			
			return ResponseEntity.ok().body(this.restListener.fullAnswers()) ;
		}
		catch (ApiException ApiException)
		{
			return ResponseEntity.status(ApiException.getStatus()).body(ApiException.getBodyExceptionMessage()) ;
		}
		catch (Exception exception)
		{
			final ApiException ApiException = new ApiException() ;

			ApiException.setError(exception.getMessage()) ;
			ApiException.setMessage(exception.getMessage()) ;
			ApiException.setException(this.getStackTrace(exception)) ;
			
			final String pathStringFilledValues = "/api/answers" ;
			ApiException.setPath(pathStringFilledValues) ;
			
			return new ResponseEntity<>(ApiException.getBodyExceptionMessage(), HttpStatus.valueOf(ApiException.getStatus())) ;
		}
	}

	/**
	 * @param httpSession with the HTTP session
     * @param answerId    with the answer identifier
	 * @return all the answers
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/answers/{answerId}")
	public ResponseEntity<?> specificAnswer(final HttpSession httpSession, @PathVariable final Integer answerId)
	{
		try
		{
			// Get the user logged
			final UserDto sessionUserLogged = this.getSessionUserLogged(httpSession) ;
			
			if (!sessionUserLogged.isAdmin())
			{
				throw new RuntimeException("Para poder ver respuestas especificas a preguntas de la BBDD, has de ser Administrador") ;
			}
			
			return ResponseEntity.ok().body(this.restListener.specificAnswer(answerId)) ;
		}
		catch (ApiException ApiException)
		{
			return ResponseEntity.status(ApiException.getStatus()).body(ApiException.getBodyExceptionMessage()) ;
		}
		catch (Exception exception)
		{
			final ApiException ApiException = new ApiException() ;

			ApiException.setError(exception.getMessage()) ;
			ApiException.setMessage(exception.getMessage()) ;
			ApiException.setException(this.getStackTrace(exception)) ;
			
			final String pathStringFilledValues = this.specificAnswerPathExceptionAssignment(answerId) ;
			ApiException.setPath(pathStringFilledValues) ;
			
			return new ResponseEntity<>(ApiException.getBodyExceptionMessage(), HttpStatus.valueOf(ApiException.getStatus())) ;
		}
	}
	
	/**
	 * @param httpSession with the HTTP session
     * @return the user logged
	 */
	private UserDto getSessionUserLogged(final HttpSession httpSession)
	{
		// Get the session attribute
		final UserDto sessionUserLogged = (UserDto) httpSession.getAttribute(Constants.SESSION_USER_LOGGED) ;
		
		// Check if there is any user logged
		if (sessionUserLogged == null)
		{
			throw new RuntimeException("No existe ningun usuario logueado") ;
		}
		
		return sessionUserLogged ;
	}

	/**
	 * The following method assign every path parameter with the value
	 * @return the path string with the filled path values
     */
	private String specificAnswerPathExceptionAssignment(final Integer answerId)
	{
		String pathString = "/api/answers/{answerId}" ;
		
		pathString = pathString.replace("{answerId}", "" + answerId) ;

		return pathString ;
	}


	/**
	 * @param exception with the exception to convert to String
	 * @return stack trace converted to string
	 */
	private String getStackTrace(final Exception exception)
	{
		final StringWriter stringWriter = new StringWriter() ;
		final PrintWriter printWriter   = new PrintWriter(stringWriter) ;
		exception.printStackTrace(printWriter) ;
		
		return stringWriter.toString() ; 
	}
}
