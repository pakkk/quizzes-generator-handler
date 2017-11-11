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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class QuizDto
{
	/** Attribute - Identifier */
    @Id
    @Column(length = 40)
    @GeneratedValue
    private String id ;

    /** Attribute - Type */
    private String type ;
    
    /** Attribute - HTML Input */
    private String input ;
    
    /** Attribute - Question */
    private String question ;
    
    /** Attribute - Answer */
    @Column(length = 2048)
    private String answer ;

    /**
     * Public constructor
     */
    public QuizDto()
    {
    	// Empty constructor
    }

    /**
     * @param type	   with the type
     * @param input    with the HTML input type
     * @param question with the question
     * @param answer   with the answer
     */
    public QuizDto(final String type, final String input, final String question, final String answer)
    {
        this.type 	  = type ;
        this.question = question ;
        this.answer   = answer ;
    }

    /**
     * @return the identifier
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
	 * @return the type
	 */
	public String getType()
	{
		return this.type ;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type)
	{
		this.type = type ;
	}
	
	/**
	 * @return the input
	 */
	public String getInput()
	{
		return this.input ;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(final String input)
	{
		this.input = input ;
	}

	/**
	 * @return the question
	 */
	public String getQuestion()
	{
		return this.question ;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(final String question)
	{
		this.question = question ;
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
		this.answer = answer;
	}
}
