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

package com.pacobenitezchico.quizzes.utils;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public final class Constants
{
	/** Constant - User - JSON */
	public static final String USER_JSON    	          = "user.json" ;
	
	/** Constant - Quizzes - Radiobutton - JSON */
	public static final String QUIZZES_RADIOBUTTON_JSON   = "quizzes_radiobutton.json" ;
	
	/** Constant - Quizzes - Textarea - JSON */
	public static final String QUIZZES_TEXTAREA_JSON      = "quizzes_textarea.json" ;
	
	
	/** Constant - Session - User logged */
	public static final String SESSION_USER_LOGGED		  = "userLoggedQuiz" ;
	
	/** Constant - Default Candidate Password */
	public static final String DEFAULT_CANDIDATE_PASSWORD = "12345" ;
	
	/** Constant - Questions per candidate */
	public static final int QUESTION_NUMBER_PER_CANDIDATE = 10 ;
	
	/** Constant - String - Empty */
	public static final String STRING_EMPTY				  = "" ;
	
	/**
	 * Private constructor
	 */
	private Constants()
	{
		// Empty constructor
	}
}
