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

package com.pacobenitezchico.quizzes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserQuizDto
{
	/** Attribute - Identifier */
    @Id
    @Column(length = 40)
    @GeneratedValue
    private String id ;

    /** Attribute - Candidate */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity= UserDto.class)
    @JoinColumn(name = "user_id")
    private UserDto user ;
    
    /** Attribute - Quiz */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity= QuizDto.class)
    @JoinColumn(name = "quiz_id")
    private QuizDto quiz ;
    
    /** Attribute - answer */
    private String answer ;
    
    /**
     * Public constructor
     */
    public UserQuizDto()
    {
    	// Empty constructor
    }

    /**
     * @return the user quiz identifier
     */
    public String getId()
    {
        return this.id ;
    }

    /**
     * @param id with the identifier
     */
    public void setId(final String id)
    {
        this.id = id ;
    }

	/**
	 * @return the User
	 */
	public UserDto getUser()
	{
		return this.user ;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(final UserDto user)
	{
		this.user = user ;
	}

	/**
	 * @return the quiz
	 */
	public QuizDto getQuiz()
	{
		return this.quiz ;
	}

	/**
	 * @param quiz the quiz to set
	 */
	public void setQuiz(final QuizDto quiz)
	{
		this.quiz = quiz ;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer()
	{
		return this.answer ;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(final String answer)
	{
		this.answer = answer ;
	}
}
