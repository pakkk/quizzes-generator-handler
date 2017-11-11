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

package com.pacobenitezchico.quizzes.utils ;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pacobenitezchico.quizzes.api.model.QuizQuestion;


/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
public class JacksonMapper extends ObjectMapper 
{
	/** Serial Version UID */
	private static final long serialVersionUID = 3753496109727081103L ;
	
	/** Private static instance */
	private static ObjectMapper INSTANCE = new JacksonMapper() ;

	public static ObjectMapper getInstance()
	{
		return INSTANCE ;
	}

	/**
	 * Private constructor
	 */
	private JacksonMapper()
	{
		this.setDefaultTyping(new FilteredTypeResolverBuilder(ObjectMapper.DefaultTyping.NON_FINAL).init(JsonTypeInfo.Id.MINIMAL_CLASS, null)
																							  	   .inclusion(JsonTypeInfo.As.WRAPPER_OBJECT)) ;
		this.setSerializationInclusion(JsonInclude.Include.NON_NULL) ;
		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) ;

		this.setConfig(getSerializationConfig().withView(JacksonViews.QuizzesView.class)) ;
	}

	protected static class FilteredTypeResolverBuilder extends DefaultTypeResolverBuilder
	{
		/** Serial Version UID */
		private static final long serialVersionUID = -8145899696439248804L ;

		/**
		 * @param defaultTyping with the default typing
		 */
		public FilteredTypeResolverBuilder(final DefaultTyping defaultTyping)
		{
			super(defaultTyping) ;
		}

		/**
		 * @param javaTypeSource with the java type
		 * @return true if it is an expected java type
		 */
		public boolean useForType(final JavaType javaTypeSource)
		{
			boolean use = super.useForType(javaTypeSource) ;

			if (use)
			{
				use = !javaTypeSource.getRawClass().equals(java.math.BigDecimal.class) && !javaTypeSource.getRawClass().equals(QuizQuestion.class) ;
			}

        	return use ;
		}
	}
}
