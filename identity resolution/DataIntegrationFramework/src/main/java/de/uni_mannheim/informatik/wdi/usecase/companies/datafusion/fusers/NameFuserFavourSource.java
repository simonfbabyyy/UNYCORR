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
package de.uni_mannheim.informatik.wdi.usecase.companies.datafusion.fusers;

import de.uni_mannheim.informatik.wdi.datafusion.AttributeValueFuser;
import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.meta.FavourSources;
import de.uni_mannheim.informatik.wdi.model.FusedValue;
import de.uni_mannheim.informatik.wdi.model.RecordGroup;
import de.uni_mannheim.informatik.wdi.usecase.companies.model.Company;

/**
 * {@link AttributeValueFuser} for the names of {@link Company}s.
 * 
 * @author Robert Meusel (robert@dwslab.de)
 * 
 */
public class NameFuserFavourSource extends AttributeValueFuser<String, Company> {

	public NameFuserFavourSource() {
		super(new FavourSources<String, Company>());
	}

	@Override
	public void fuse(RecordGroup<Company> group, Company fusedRecord) {

		// get the fused value
		FusedValue<String, Company> fused = getFusedValue(group);

		// set the value for the fused record
		fusedRecord.setName(fused.getValue());

		// add provenance info
		fusedRecord.setAttributeProvenance(Company.NAME, fused.getOriginalIds());
	}

	@Override
	public boolean hasValue(Company record) {
		return record.hasValue(Company.NAME);
	}

	@Override
	protected String getValue(Company record) {
		return record.getName();
	}

}
