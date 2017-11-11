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
public class UserDto
{
	/** Attribute - Identifier */
    @Id
    @Column(length = 40)
    @GeneratedValue
    private String id ;

    /** Attribute - username */
    private String username ;
    
    /** Attribute - password */
    private String password ;
    
    /** Attribute - boolean */
    private boolean admin ;
    
    /**
     * Public constructor
     */
    public UserDto()
    {
    	// Empty constructor
    }

    /**
     * @param username with the username
     * @param password with the password
     * @param admin    as true if the user is admin
     */
    public UserDto(final String username, final String password, final boolean admin)
    {
        this.username = username ;
        this.password = password ;
        this.admin    = admin ;
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
	 * @return the username
	 */
	public String getUsername()
	{
		return this.username ;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(final String username)
	{
		this.username = username ;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return this.password ;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password)
	{
		this.password = password ;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin()
	{
		return this.admin ;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(final boolean admin)
	{
		this.admin = admin ;
	}
}
