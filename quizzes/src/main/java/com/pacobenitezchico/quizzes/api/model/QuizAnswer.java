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

import org.apache.commons.lang3.builder.HashCodeBuilder ;

import com.fasterxml.jackson.annotation.JsonProperty ;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pacobenitezchico.quizzes.utils.JacksonMapper;
import com.pacobenitezchico.quizzes.utils.JacksonViews.QuizzesView;

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class QuizAnswer
{
	/** This is the question identifier */
    @JsonView(QuizzesView.class)
	private String questionId ;
	
	/** This is the user answer */
    @JsonView(QuizzesView.class)
	private String userAnswer ;

	/**
	 * Empty constructor for the class
	 */
	public QuizAnswer()
	{
		// Empty constructor
	}
	
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(final String questionId)
	{
		this.questionId = questionId ;
	}
	
	/**
	 * @return the questionId
	 */
	@JsonProperty("questionId")
	public String getQuestionId()
	{
		return this.questionId ;
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

	
	@Override
	public boolean equals(final Object aThat)
	{
		if (this == aThat) return true ;
		
		if (aThat == null) return false ;
		
		if (aThat.getClass() != this.getClass()) return false ;
		
		final QuizAnswer target = (QuizAnswer) aThat ;
		
		if (this.questionId != null && !this.questionId.equals(target.questionId))
		{
			return false ;
		}
		if (this.userAnswer != null && !this.userAnswer.equals(target.userAnswer))
		{
			return false ;
		}

		return true ;
	}

	@Override
	public int hashCode()
	{
		final HashCodeBuilder builder = new HashCodeBuilder() ;
		
		builder.append(this.questionId) ;
		builder.append(this.userAnswer) ;

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
