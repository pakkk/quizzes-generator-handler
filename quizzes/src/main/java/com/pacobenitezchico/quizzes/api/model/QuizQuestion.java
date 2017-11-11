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

package com.pacobenitezchico.quizzes.api.model ;

import com.fasterxml.jackson.annotation.JsonProperty ;
import org.apache.commons.lang3.builder.HashCodeBuilder ;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pacobenitezchico.quizzes.utils.JacksonMapper;
import com.pacobenitezchico.quizzes.utils.JacksonViews.QuizzesView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class QuizQuestion
{
	/** This is the question identifier */
    @JsonView(QuizzesView.class)
	private String identifier ;
    
	/** This is the HTML type */
    @JsonView(QuizzesView.class)
	private String type ;
    
	/** This is the question */
    @JsonView(QuizzesView.class)
	private String question ;
    
	/** This is the user answer */
    @JsonView(QuizzesView.class)
	private String userAnswer ;
    
	/** This is the checked answer */
    @JsonView(QuizzesView.class)
	private String checkedAnswer ;

	/**
	 * Empty constructor for the class
	 */
	public QuizQuestion()
	{
		// Empty constructor
	}
	
	/**
	 * @param identifier 	with the identifier
	 * @param type			with the type
	 * @param question	    with the question
	 * @param userAnswer	with the user answer
	 * @param checkedAnswer with the checked answer
	 */
	public QuizQuestion(final String identifier, final String type, final String question, final String userAnswer, final String checkedAnswer)
	{
		this.identifier    = identifier ;
		this.type 		   = type ;
		this.question 	   = question ;
		this.userAnswer    = userAnswer ;
		this.checkedAnswer = checkedAnswer ;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(final String identifier)
	{
		this.identifier = identifier ;
	}
	
	/**
	 * @return the identifier
	 */
	@JsonProperty("identifier")
	public String getIdentifier()
	{
		return this.identifier ;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(final String type)
	{
		this.type = type ;
	}
	
	/**
	 * @return the type
	 */
	@JsonProperty("type")
	public String getType()
	{
		return this.type ;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(final String question)
	{
		this.question = question ;
	}
	
	/**
	 * @return the question
	 */
	@JsonProperty("question")
	public String getQuestion()
	{
		return this.question ;
	}
	
	/**
	 * @param userAnswer the userAnswer to set
	 */
	public void setUserAnswer(final String userAnswer)
	{
		this.userAnswer = userAnswer ;
	}
	
	/**
	 * @return the userAnswer
	 */
	@JsonProperty("userAnswer")
	public String getUserAnswer()
	{
		return this.userAnswer ;
	}
	
	/**
	 * @param checkedAnswer the checkedAnswer to set
	 */
	public void setCheckedAnswer(final String checkedAnswer)
	{
		this.checkedAnswer = checkedAnswer ;
	}
	
	/**
	 * @return the checkedAnswer
	 */
	@JsonProperty("checkedAnswer")
	public String getCheckedAnswer()
	{
		return this.checkedAnswer ;
	}

	@Override
	public boolean equals(final Object aThat)
	{
		if (this == aThat) return true ;
		
		if (aThat == null) return false ;
		
		if (aThat.getClass() != this.getClass()) return false ;
		
		QuizQuestion target = (QuizQuestion) aThat ;

		if (this.identifier != null && !this.identifier.equals(target.identifier))
		{
			return false ;
		}
		if (this.question != null && !this.question.equals(target.question))
		{
			return false ;
		}
		if (this.userAnswer != null && !this.userAnswer.equals(target.userAnswer))
		{
			return false ;
		}
		if (this.checkedAnswer != null && !this.checkedAnswer.equals(target.checkedAnswer))
		{
			return false ;
		}

		return true ;
	}

	@Override
	public int hashCode()
	{
		final HashCodeBuilder builder = new HashCodeBuilder() ;
		
		builder.append(this.identifier) ;
		builder.append(this.type) ;
		builder.append(this.question) ;
		builder.append(this.userAnswer) ;
		builder.append(this.checkedAnswer) ;

		return builder.toHashCode() ;
	}
	
	@Override
    public String toString()
    {
		try
	    {
	      return JacksonMapper.getInstance().writerWithDefaultPrettyPrinter().writeValueAsString(this) ;
	    }
	    catch (JsonProcessingException jsonProcessingExc)
	    {
	      return "Exception converting to Json string: " + jsonProcessingExc.getMessage() ;
	    }
    }	
}
