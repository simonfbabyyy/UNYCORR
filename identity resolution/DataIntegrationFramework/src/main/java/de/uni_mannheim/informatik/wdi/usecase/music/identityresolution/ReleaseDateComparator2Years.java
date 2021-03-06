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
package de.uni_mannheim.informatik.wdi.usecase.music.identityresolution;

import de.uni_mannheim.informatik.wdi.identityresolution.Comparator;
import de.uni_mannheim.informatik.wdi.identityresolution.similarity.date.YearSimilarity;
import de.uni_mannheim.informatik.wdi.usecase.music.model.Release;

/**
 * {@link Comparator} for {@link Release}s based on the
 * {@link Release#getDate()} value, with a maximal difference of 2 years.
 * 
 * @author Robert Meusel (robert@dwslab.de)
 * 
 */
public class ReleaseDateComparator2Years extends Comparator<Release> {

	private YearSimilarity sim = new YearSimilarity(2);

	@Override
	public double compare(Release entity1, Release entity2) {
		double similarity = sim.calculate(entity1.getDate(), entity2.getDate());

		return similarity * similarity;
	}

}
