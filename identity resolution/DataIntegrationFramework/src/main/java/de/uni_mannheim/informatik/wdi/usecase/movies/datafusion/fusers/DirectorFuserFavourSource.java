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
package de.uni_mannheim.informatik.wdi.usecase.movies.datafusion.fusers;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.meta.FavourSources;
import de.uni_mannheim.informatik.wdi.model.FusedValue;
import de.uni_mannheim.informatik.wdi.model.RecordGroup;
import de.uni_mannheim.informatik.wdi.usecase.movies.model.Movie;

/**
 * {@link AttributeValueFuser} for the director of {@link Movie}s.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class DirectorFuserFavourSource extends AttributeValueFuser<String, Movie> {

	public DirectorFuserFavourSource() {
		super(new FavourSources<String, Movie>());
	}

	@Override
	public boolean hasValue(Movie record) {
		return record.hasValue(Movie.DIRECTOR);
	}

	@Override
	protected String getValue(Movie record) {
		return record.getDirector();
	}

	@Override
	public void fuse(RecordGroup<Movie> group, Movie fusedRecord) {
		FusedValue<String, Movie> fused = getFusedValue(group);
		fusedRecord.setDirector(fused.getValue());
		fusedRecord.setAttributeProvenance(Movie.DIRECTOR,
				fused.getOriginalIds());
	}

}
