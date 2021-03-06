/** 
 *
 * Copyright (C) 2015 Data and Web Science Group, University of Mannheim, Germany (code@dwslab.de)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.uni_mannheim.informatik.wdi.usecase.movies.datafusion.evaluation;

import java.util.HashSet;
import java.util.Set;

import de.uni_mannheim.informatik.wdi.datafusion.EvaluationRule;
import de.uni_mannheim.informatik.wdi.usecase.movies.model.Actor;
import de.uni_mannheim.informatik.wdi.usecase.movies.model.Movie;

/**
 * {@link EvaluationRule} for the actors of {@link Movie}s. The rule simply
 * compares the full set of actors of two {@link Movie}s and returns true, in
 * case they are identical.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class ActorsEvaluationRule extends EvaluationRule<Movie> {

	@Override
	public boolean isEqual(Movie record1, Movie record2) {
		Set<String> actors1 = new HashSet<>();

		for (Actor a : record1.getActors()) {
			// note: evaluating using the actor's name only suffices for simple
			// lists
			// in your project, you should have actor ids which you use here
			// (and in the identity resolution)
			actors1.add(a.getName());
		}

		Set<String> actors2 = new HashSet<>();
		for (Actor a : record2.getActors()) {
			actors2.add(a.getName());
		}

		return actors1.containsAll(actors2) && actors2.containsAll(actors1);
	}

}
