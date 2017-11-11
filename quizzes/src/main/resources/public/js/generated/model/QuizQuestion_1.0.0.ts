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
    export class QuizQuestion 
    {
        /**
         * Public constructor
         */
	    constructor(public identifier: number, public question: string, public answer: string)
	    {
			this.identifier = identifier ;
			this.question   = question ;
			this.answer     = answer ;
	    }
	
        /**
         * @param identifier the identifier to set
         */
        setIdentifier(identifier)
        {
            this.identifier = identifier ;
        }

        /**
         * @return the identifier
         */
        getIdentifier()
        {
            return this.identifier ;
        }
		
        /**
         * @param question the question to set
         */
        setQuestion(question)
        {
            this.question = question ;
        }

        /**
         * @return the question
         */
        getQuestion()
        {
            return this.question ;
        }
		
        /**
         * @param answer the answer to set
         */
        setAnswer(answer)
        {
            this.answer = answer ;
        }

        /**
         * @return the answer
         */
        getAnswer()
        {
            return this.answer ;
        }
		

        /**
         * @return the object as string
         */
        toString()
        {
            return JSON.stringify(this, null, 4) ;
        }
	
        /**
         * @return a new instance of the class with a random values
         */
        static generateRandomInstance()
        {
			var identifier = RandomValues.generateRandomNumber() ;
			var question = RandomValues.generateRandomString() ;
			var answer = RandomValues.generateRandomString() ;

            return new Quiz(identifier, question, answer) ;
        }

		/**
	 	 * @param internalValueParseGenerator with the json to be parsed
	 	 * @return an instance of this class
	 	 */	
        static parse(internalValueParseGenerator)
        {
            var identifier ;
            if (internalValueParseGenerator.identifier != null && internalValueParseGenerator.identifier != "undefined")
            {
			    identifier = ParserValues.numberParser(internalValueParseGenerator.identifier) ;
		    }
		
            var question ;
            if (internalValueParseGenerator.question != null && internalValueParseGenerator.question != "undefined")
            {
			    question = ParserValues.stringParser(internalValueParseGenerator.question) ;
		    }
		
            var answer ;
            if (internalValueParseGenerator.answer != null && internalValueParseGenerator.answer != "undefined")
            {
			    answer = ParserValues.stringParser(internalValueParseGenerator.answer) ;
		    }
		

            return new Quiz(identifier, question, answer) ;
        }
    };
}
