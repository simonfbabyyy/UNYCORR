/**
* 
* Copyright (C) 2015 Data and Web Science Group, University of Mannheim (code@dwslab.de)
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
package de.uni_mannheim.informatik.wdi.usecase.companies;

/**
 * @author Robert
 *
 */
public class Company_IdentityResolutionAndFusion_PMML_Main {

	// before running this class, replace the TODOs with the correct files
	public static void main(String[] args) throws Exception {
		// DB pedia
		Company_IdentityResolution_DBpedia_PMML_Main.main(null);
		// FullContact
		Company_IdentityResolution_FullContact_PMML_Main.main(null);
		// Data Fusion
		Company_DataFusion_Main.main(null);
	}

}
