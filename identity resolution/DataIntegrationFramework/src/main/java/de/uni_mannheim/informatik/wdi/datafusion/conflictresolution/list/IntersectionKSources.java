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
package de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.list;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import de.uni_mannheim.informatik.wdi.datafusion.conflictresolution.ConflictResolutionFunction;
import de.uni_mannheim.informatik.wdi.model.Fusable;
import de.uni_mannheim.informatik.wdi.model.FusableValue;
import de.uni_mannheim.informatik.wdi.model.FusedValue;
import de.uni_mannheim.informatik.wdi.model.Matchable;

/**
 * Intersection {@link ConflictResolutionFunction}: returns the values which are at least
 * included in k lists
 * 
 * @author Robert Meusel (robert@dwslab.de)
 * 
 * @param <ValueType>
 * @param <RecordType>
 */
public class IntersectionKSources<ValueType, RecordType extends Matchable & Fusable>
		extends ConflictResolutionFunction<List<ValueType>, RecordType> {

	/**
	 * Number of lists in which an element has to appear at least
	 */
	private int k;

	/**
	 * Constructor for this particular {@link ConflictResolutionFunction}
	 * 
	 * @param k
	 *            ,number of lists a value needs to appear at least
	 */
	public IntersectionKSources(int k) {
		this.k = k;
	}

	@Override
	public FusedValue<List<ValueType>, RecordType> resolveConflict(
			Collection<FusableValue<List<ValueType>, RecordType>> values) {

		HashMap<ValueType, Integer> valueDistribution = new HashMap<ValueType, Integer>();

		for (FusableValue<List<ValueType>, RecordType> value : values) {
			// make the values unique
			HashSet<ValueType> set = new HashSet<ValueType>(value.getValue());
			// create the distribution
			for (ValueType v : set) {
				Integer c = valueDistribution.get(v);
				if (c == null) {
					c = 0;
				}
				c += 1;
				valueDistribution.put(v, c);
			}

		}

		List<ValueType> intersection = new LinkedList<>();
		for (ValueType v : valueDistribution.keySet()) {
			if (valueDistribution.get(v) >= k) {
				intersection.add(v);
			}
		}
		FusedValue<List<ValueType>, RecordType> fused = new FusedValue<>(
				intersection);

		// list the original records that contributed to this intersection
		for (FusableValue<List<ValueType>, RecordType> value : values) {

			for (ValueType v : value.getValue()) {
				if (intersection.contains(v)) {
					fused.addOriginalRecord(value);
					break;
				}
			}

		}

		return fused;
	}
}
