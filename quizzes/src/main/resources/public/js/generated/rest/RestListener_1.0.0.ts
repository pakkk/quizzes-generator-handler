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

/**
 * ------------------------------------------------
 * @author Francisco Manuel Benitez Chico
 * ------------------------------------------------
 */
module javaquiz_api
{
    export class RestListener
    {
	/**
	 * Successful call
	 */
	fullAnswers(outcome: Quiz[])
	{
		printOutcome("fullAnswers", outcome) ;
	}

	/**
	 * Common error call - CommonException
	 * @param commonException with the common exception
	 */
	fullAnswersCommonException(outcome: CommonException)
	{
		printOutcome("fullAnswersCommonException", outcome) ;
	}
	/**
	 * Successful call
	 */
	setAdminKey()
	{
		printOutcomeNoOutcome("setAdminKey") ;
	}

	/**
	 * Common error call - CommonException
	 * @param commonException with the common exception
	 */
	setAdminKeyCommonException(outcome: CommonException)
	{
		printOutcome("setAdminKeyCommonException", outcome) ;
	}
	/**
	 * Successful call
	 */
	existsCandidate(outcome: boolean)
	{
		printOutcome("existsCandidate", outcome) ;
	}

	/**
	 * Common error call - CommonException
	 * @param commonException with the common exception
	 */
	existsCandidateCommonException(outcome: CommonException)
	{
		printOutcome("existsCandidateCommonException", outcome) ;
	}
	/**
	 * Successful call
	 */
	specificAnswer(outcome: Quiz[])
	{
		printOutcome("specificAnswer", outcome) ;
	}

	/**
	 * Common error call - CommonException
	 * @param commonException with the common exception
	 */
	specificAnswerCommonException(outcome: CommonException)
	{
		printOutcome("specificAnswerCommonException", outcome) ;
	}
	/**
	 * Successful call
	 */
	generateQuiz(outcome: Quiz[])
	{
		printOutcome("generateQuiz", outcome) ;
	}

	/**
	 * Common error call - CommonException
	 * @param commonException with the common exception
	 */
	generateQuizCommonException(outcome: CommonException)
	{
		printOutcome("generateQuizCommonException", outcome) ;
	}
	/**
	 * Successful call
	 */
	rememberQuiz(outcome: Quiz[])
	{
		printOutcome("rememberQuiz", outcome) ;
	}

	/**
	 * Common error call - CommonException
	 * @param commonException with the common exception
	 */
	rememberQuizCommonException(outcome: CommonException)
	{
		printOutcome("rememberQuizCommonException", outcome) ;
	}
	/**
	 * Successful call
	 */
	storeQuiz()
	{
		printOutcomeNoOutcome("storeQuiz") ;
	}

	/**
	 * Common error call - CommonException
	 * @param commonException with the common exception
	 */
	storeQuizCommonException(outcome: CommonException)
	{
		printOutcome("storeQuizCommonException", outcome) ;
	}
	/**
	 * Successful call
	 */
	checkQuiz(outcome: Quiz[])
	{
		printOutcome("checkQuiz", outcome) ;
	}

	/**
	 * Common error call - CommonException
	 * @param commonException with the common exception
	 */
	checkQuizCommonException(outcome: CommonException)
	{
		printOutcome("checkQuizCommonException", outcome) ;
	}
    }
}
