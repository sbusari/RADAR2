# Building Security Policy Decisions

This example analyses security policy decisions for an organisation concerned with leaks of confidential information. The problem is described in the following papers:

[1] T. Caulfield and D. Pym, "Improving Security Policy Decisions with Models," in IEEE Security & Privacy, vol. 13, no. 5, pp. 34-41, Sept.-Oct. 2015.

[2] T. Caulfield and D. Pym, "Modelling and simulating systems security policy." in Poc. of the 8th International Conference on Simulation Tools and Techniques (SIMUTools '15), pp. 9-18, Brussels, Belgium, 2015.

The organisation is concerned about its employees' habit of sharing documents through a globally shared drive. Having files on the shared drive increases the risk and potential damages caused by insiders' leaks.  The organisation is considering recommending its employees to share documents through emails or portable media devices instead of using the shared drive but this recommendation may create other security risk and may not be followed by employees.

The organisation is also concerned by loss and theft of documents stored on mobile phones, laptops and portable media devices. Theft of mobiles devices and portable media can happen outside and inside the organisation's building. To prevent thieves entering the building, the building entrance is equipped with automated gates that require employee to swipe their access card to access the building. Some employees, however, let other persons tailgate them when entering the building, for example to let other employees in when they have forgotten their access card. The organisation is considering adding a security guard at the building entrance to prevent tailgating. It is hoped this will reduce the risk of thefts inside the building although the actual impact is uncertain. Preventing all tailgating may annoy employees as they will waste time to enter the building when they have forgotten their access card.

TODO: give model overview.

TODO: outline how our model relates to and differs from [1, 2]

## Costs of Disclosures

The primary decision objectives are related to the uncertain costs associated to the disclosure of confidential documents:

```
Objective Min ExpectedCostOfDisclosures = E[Cost_of_Disclosures]
Objective Min RiskOfCatastrophicDisclosures = P[Cost_of_Disclosure > 10^6]
```

We assume three categories of confidential documents (high, medium, and low confidentiality) with different costs to the organisation if they are leaked:
    
```  
Cost_of_Disclosures = 
    Nbr_HighConfidentialityLeaks * Cost_HighConfidentialityLeak
    + Nbr_MediumConfidentialityLeaks * Cost_MediumConfidentialityLeak
    + Nbr_LowConfidentialityLeaks * Cost_LowConfidentialityLeak
    	
Nbr_HighConfidentialityLeaks = Nbr_LeakedDoc * Ratio_HighConfientialityDocs
Nbr_MediumConfidentialityLeaks = Nbr_LeakedDoc * Ratio_MediumConfientialityDocs
Nbr_LowConfidentialityLeaks = Nbr_LeakedDoc * Ratio_LowConfientialityDocs
   	
Ratio_HighConfidentialityDocs = parameter(1%)
Ratio_MediumConfidentialityDocs = parameter(5%)
Ratio_LowConfidentialityDocs = parameter(50%)
// other docs are not confidential 
    
Cost_HighConfidentialityLeak = parameter(10^6)
Cost_MediumConfidentialityLeak = parameter(10^4)
Cost_LowConfidentialityLeak = parameter(10^2)
```  
In this model, the ratios and leak costs for each type of document is given a point-based estimates but they could be uncertain. (TODO: add uncertainty to the model parameter)  
    
## Documents Leaks

Documents leaks are the result of insider's leaks or of theft by outsider who gained accessed to the building. 

```    
Nbr_LeakedDoc = 
		Nbr_DocsLeakedByInsiders + Nbr_DocsStolenByOutsiders
		           		
```

### Insiders' Leaks

```
Nbr_DocsLeakedByInsiders = 
       Probability_InsidersLeak * 
       Nbr_DocsOnSharedDrive * 
       SharedDriveLeakRange
       
Probability_InsidersLeak = parameter(10^-3)
SharedDriveLeakRange = parameter(triangle(10%, 50%, 100%))
	// the portion of documents on shared drive that are leaked by insider
```

### Attackers' Intrusions

```
Nbr_DocsStolenByOutsiders = 
		Nbr_OfficeIntrustions
		* Nbr_DocsStolenPerIntrusion
		
Nbr_OfficeIntrusions = decision("Building entry security"){
		"not guarded"	: parameter(triangle(0, 3, 6))
		"guarded" 		: parameter(triangle(0, 1, 2))
}

Nbr_DocsStolenPerIntrusion = 
		Nbr_ExternalMediaStolenPerIntrusion *
		Nbr_DocsPerExternalMedia
		
Nbr_ExternalMediaStolenPerIntrusion = 
		Nbr_ExternalMediaInUse *
		Percentage_MediaStolenPerInstrusion
		
Percentage_MediaStolenPerInstrusion = 
		parameter(triangle(0, 1%, 10%))

```
    
## Documents Sharing  

The number of documents on the shared drive and on external media depends on the organisation's document sharing policy:  
    
``` 
Nbr_DocsOnSharedDrive = decision("Document Sharing Policy"){
    		"Neutral": parameter(143)
    		"Recommend Email": parameter(44)
    		"Recommend External Media": parameter(91)
} 

Nbr_DocsOnExternalMedia = decision("Document Sharing Policy"){
    		"Neutral": parameter(0)
    		"Recommend Email": parameter(0)
    		"Recommend External Media": parameter(52)
}

Nbr_ExternalMediaInUse = Nbr_DocsOnExternalMedia / Nbr_DocsPerMedia

Nbr_DocsPerMedia = parameter(triangle(0, 5, 10)) 

    
```

Data about the number of documents on shared drive and on external media come from Table 1 in [1].

## Relation with Previous Analysis

...

