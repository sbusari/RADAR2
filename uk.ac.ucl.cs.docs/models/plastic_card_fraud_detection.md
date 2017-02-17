# Optimising the Design of Plastic Card Fraud Detection Systems

The purpose of a plastic card fraud detection system is to detect when plastic card accounts have been compromised by fraudsters who are using the account to steal funds (Hand et al, 2008).

Design decisions for fraud detection systems include:

* the **processing type** that can be continuous or batch; continuous processing analyses transactions individually, whereas batch processing analyses sets of transactions together;

* the **fraud detection method** which can be a *two-class supervised classification method* in which a classifier is trained from past samples of fraudulent and non-fraudulent transactions, or a *non-statistical rule-based method* that flags transactions matching configurations known to be high risk;

* the **blocking policy** that can include blocking an account as soon as the fraud detection algorithm flags a transaction as fraudulent, or only blocking the account after the suspected fraud has been confirmed by human investigators.

These design decisions have impacts on how quickly frauds are detected and compromised accounts blocked and on the cost-effectiveness of the fraud detection system.

The optimisation of plastic card fraud detection systems includes two main objectives (Hand et al., 2008):

* minimising financial loss 
* minimising investigation costs

Our optimisation model is based on our experience in analysing the scalability of a commercial financial fraud detection system (Duboc et al., 2008; Duboc et al. 20??) and extends a previous analysis of performance criteria for plastic card fraud detection tools (Hand et al., 2008) by modelling the impact of alternative design decisions on the above goals.

Our model is not perfect. ... But better approximation of relative importance of false alert and missed fraud than assuming the two class of misclassification to be of equal importance. Better and more useful approximation than qualitative intuition-based evaluation of alternatives with respect to optimisation goals (e.g. i*, QFD, AHP, etc.). An advantage of our model is that it can be validated against real-world data and modified to better fit the data if necessary.

## Modelling Financial Loss

This plastic card fraud detection system analyses transactions *after* they have been authorised by the bank. Therefore, if the fraud detection system detects a transaction as fraudulent, the bank will still lose the fraudulent transaction amount (unless the bank can prove the fraud is due to negligence from the cardholder or vendor, a concern we will not consider in our model). The purpose of the fraud detection system is to block compromised card accounts as quickly as possible so as to prevent further fraud. 

Following Hand et al. (2008), plastic card fraud detection system are thus evaluated by their ability to minimise future financial losses. We model the future financial loss as the product of the number of compromised accounts, the average number of fraudulent transactions that will be authorised on an account before it is blocked, and the average value of a fraudulent transaction:

    FinancialLoss = NbrCompromisedAccounts 
                    * NbrFraudPerAccountBeforeBlocked
                    * AverageFraudValue 

The `FinancialLoss` is measured in £ per day, `NbrCompromisedAccounts` in number of accounts per day, and `AverageFraudValue` in pound.

The number of compromised accounts is given by the total number of accounts multiplied by the percentage of compromised accounts: 

    NbrCompromisedAccounts = NbrAccounts * CompromisedAccountRatio

Both quantities are parameters that can be estimated from past data. For example:

    NbrAccounts = parameter(10^6)
    CompromisedAccountRatio = parameter(0.01%)
    
Here, we give point-based estimates for model parameters. Later, we will model and analyse uncertainty about all our model parameters.

<!--
The `parameter` keyword indicates that a variable is a model parameter and will allow us to distinguish random variables in the model from uncertainty about model parameter. For example:

    NumberTransactions = normal(10^9, 10^4)

means that the number of daily transactions follow a normal distribution with the above parameters. Whereas

    AverageNbrTransactions = parameter(normal(10^9, 10^4))

means that AverageNumberTransactions is a model parameter with uncertainty described by the above normal distribution.

This notation will allow us to combine stochastic model and uncertain model parameters.
-->  

The average fraudulent transaction value can also be estimated from past data. For example:

    AverageFraudValue = parameter(£500)

The average number of frauds on an account before it is blocked, `NbrFraudPerAccountBeforeBlocked`, depends on the blocking policy. If accounts are blocked as soon as the fraud detection system suspects a fraud, the number of fraud before the account is blocked is the number of fraud before detection. If accounts are blocked only after suspected fraud are confirmed by a fraud investigation, further frauds might occur after detection:

    NbrFraudPerAccountBeforeBlocked = decision("blocking policy"){
        "block first"          : NbrFraudBeforeDetection
        "investigage first"    : NbrFraudBeforeDetection + NbrFraudDuringInvestigation
    }
    
The average number of frauds before detection, `NbrFraudPerAccountBeforeBlocked`, depends on the processing type. 
 
For continuous processing, the mean number of fraud before detection is the infinite series:

      1 * probability(fraud is detected after 1 fraudulent transactions)
    + 2 * probability(fraud is detected after 2 fraudulent transactions)
    + 3 * probability(fraud is detected after 3 fraudulent transactions)
    + ... 
    
The probability that a fraudulent transaction is detected is the true alert rate (the ratio of the number of detected fraud over the number of fraud). Factoring the above series yield that for continuous processing

    NbrFraudBeforeDetection = 1/ TrueAlertRate

In batch processing, transactions are analysed at the end of every day. Batch processing thus introduces a delay between a fraudulent transaction and its detections, a delay during which additional fraud might occur, but because transactions are analysed in groups rather than individually, the batch processing may have a better true alert rate than the continuous processing. Assuming that batch processing adds on average a delay of half a day to fraud detection, our models assumes that

    NbrFraudBeforeDetection = NbrFraudsPerCompromisedAccountPerDay / 2 * Batch_TrueAlertRate

Thus, the final equation for estimating `NbrFraudBeforeDetection` is: 

    NbrFraudBeforeDetection = decision("processing type"){
    	"continuous"    :    1 / ContinuousTrueAlertRate
    	"batch"         :    NbrFraudsPerCompromisedAccountPerDay / 2 * BatchTrueAlertRate
    }

<!-- TODO: make the BatchDelay a parameter rather than a constant, here 1/2 day -->
    
The average number of frauds per day per compromised account is a parameter than could be estimated from past data. For example:

    NbrFraudsPerCompromisedAccountPerDay = parameter(3)
    
The average number of fraud per account committed during the investigation period is also proportional to the number of frauds per compromised account per day. Assume the investigation introduces an average delay of 8 hours (1/3 day) before blocking an account, we have:

    NbrFraudDuringInvestigation = NbrFraudsPerCompromisedAccountPerDay * InvestigationDelay
    InvestigationDelay = parameter(1/3)
    
   
The true alerts rates depend on the fraud detection methods and their parameters. True alerts rates are typically estimated by analysing the performance of the fraud detection method on past data. 

To keep our model simple, we assume the classifier has three settings `high`, `medium`, and `low` that generates high, medium, or low number of alerts; and the rule-based approach has a single fixed true alert rate. 

For the continuous true alert rate:

	ContinuousTrueAlertRate = decision("fraud detection method"){
		"continuous_classifier" :  ContinuousAlertThreshold
		"continuous_rule-based" :  parameter(0,75)
	}
	
	ContinuousAlertThreshold = decision("continuous classifier threshold level"){
		"high"   : parameter(0.9)
		"medium" : paramater(0,8)
		"low"    : parameter(0,7)
	}

For the batch true alert rate:

	BatchTrueAlertRate = decision("fraud detection method"){
		"batch_classifier" :  BatchAlertThreshold
		"batch_rule-based" :  parameter(0,80)
	}
	
	BatchAlertThreshold = decision("batch classifier threshold level"){
		"high"   : parameter(0.95)
		"medium" : paramater(0,85)
		"low"    : parameter(0,75)
	}

<!-- Add graphical model -->  

## Modelling Investigation Costs

Our model of investigation costs is simpler. The average investigation cost per day is simply the number of alerts per day multiplied by the average investigation cost per alert:

    InvestigationCost = NbrAlerts * CostPerAlert
    
We assume the average cost per alert can be estimated. For example:
    
    CostPerAlert = parameter(50)

The number of generated alerts is the sum of the number of true alerts and false alerts:

    NbrAlerts = NbrTrueAlerts + NbrFalseAlerts

The number of true and false alerts are functions of the number of accounts, the percentage of compromised accounts, and the true and false alert rates:
        
    NbrTrueAlerts = NbrFraud * TrueAlertRate
    NbrFalseAlerts = NbrLegitTransactions * TrueNegativeRate
    

The true alert rate (the ratio of the number true alert over the number of fraud, a.k.a. sensitivity) and true negative rate (the ratio of the number of un-flagged legitimate transactions over the total number of legitimate transactions, a.k.a. specificity) vary with the processing type:

	TrueAlertRate = decision("processing type"){
	    "continuous" : ContinuousTrueAlertRate
	    "batch"      : BatchTrueAlertRate
	}

	TrueNegativeRate = decision("processing type"){
	    "continuous" : ContinuousTrueNegativeRate
	    "batch"      : BatchTrueNegativeRate
	}	
	
Models of the continuous and batch true alert rates have already been defined. The models for the continuous and batch true negative rate follow the same structure: 
	
	ContinuousTrueNegativeRate = decision("fraud detection method"){
		"continuous_classifier" :  ContinuousClassierTrueNegativeRate
		"continuous_rule-based" :  parameter(0,99)
	}
	
	ContinuousClassierTrueNegativeRate = decision("continuous classifier threshold level"){
		"high"   : parameter(0.90)
		"medium" : paramater(0,99)
		"low"    : parameter(0,999)
	}

	BatchTrueNegativeRate = decision("fraud detection method"){
		"batch_classifier" :  BatchClassifierTrueNegativeRate
		"batch_rule-based" :  parameter(0,995)
	}
	
	BatchClassifierTrueNegativeRate = decision("batch classifier threshold level"){
		"high"   : parameter(0.99)
		"medium" : paramater(0,995)
		"low"    : parameter(0,999)
	}
     
Finally, the number of fraudulent and legitimate transactions depend on the number of accounts and average number of transactions per account:
    
    NbrFraud = NbrAccounts * CompromisedAccountRatio * NbrFraudPerCompromisedAccountPerDay
    NbrLegitTransactions = NbrAccounts  * NbrLegitTransactionsPerAccountPerDay

The average number of accounts and compromised account ratio are model parameters that we have already estimated above. The average number of legitimate per account and fraudulent transactions per compromised account per day are also model parameters that could estimated from past data. For example:

    NbrFraudPerCompromisedAccountPerDay  = parameter(3)
    NbrLegitTransactionsPerAccountPerDay = parameter(2)
 

## The Full Model

Here is the full model:

```
	\\ Financial Loss

    FinancialLoss = NbrCompromisedAccounts 
                    * NbrFraudPerAccountBeforeBlocked
                    * AverageFraudValue 

    NbrCompromisedAccounts = NbrAccounts * CompromisedAccountRatio

    NbrAccounts = parameter(10^6)
    CompromisedAccountRatio = parameter(0.01%)

    AverageFraudValue = parameter(£500)

    NbrFraudPerAccountBeforeBlocked = decision("blocking policy"){
        "block first"          : NbrFraudBeforeDetection
        "investigage first"    : NbrFraudBeforeDetection + NbrFraudDuringInvestigation
    }
    
    NbrFraudBeforeDetection = decision("processing type"){
    	"continuous"    :    1 / Contin
      uousTrueAlertRate
    	"batch"         :    NbrFraudsPerCompromisedAccountPerDay / 2 * BatchTrueAlertRate
    }
    
    NbrFraudsPerCompromisedAccountPerDay = parameter(3)
    
    NbrFraudDuringInvestigation = NbrFraudsPerCompromisedAccountPerDay * InvestigationDelay
    InvestigationDelay = parameter(1/3)
    
    ContinuousTrueAlertRate = decision("fraud detection method"){
		"continuous_classifier" :  ContinuousAlertThreshold
		"continuous_rule-based" :  parameter(0,75)
	}
	
	ContinuousAlertThreshold = decision("continuous classifier threshold level"){
		"high"   : parameter(0.9)
		"medium" : paramater(0,8)
		"low"    : parameter(0,7)
	}

	BatchTrueAlertRate = decision("fraud detection method"){
		"batch_classifier" :  BatchAlertThreshold
		"batch_rule-based" :  parameter(0,80)
	}
	
	BatchAlertThreshold = decision("batch classifier threshold level"){
		"high"   : parameter(0.95)
		"medium" : paramater(0,85)
		"low"    : parameter(0,75)
	}
	
	// Investigation Cost

    InvestigationCost = NbrAlerts * CostPerAlert
        
    CostPerAlert = parameter(50)

    NbrAlerts = NbrTrueAlerts + NbrFalseAlerts
        
    NbrTrueAlerts = NbrFraud * TrueAlertRate
    NbrFalseAlerts = NbrLegitTransactions * TrueNegativeRate
    
	TrueAlertRate = decision("processing type"){
	    "continuous" : ContinuousTrueAlertRate
	    "batch"      : BatchTrueAlertRate

	}

	TrueNegativeRate = decision("processing type"){
	    "continuous" : ContinuousTrueNegativeRate
	    "batch"      : BatchTrueNegativeRate
	}	
		
	ContinuousTrueNegativeRate = decision("fraud detection method"){
		"continuous_classifier" :  ContinuousClassierTrueNegativeRate
		"continuous_rule-based" :  parameter(0,99)
	}
	
	ContinuousClassierTrueNegativeRate = decision("continuous classifier threshold level"){
		"high"   : parameter(0.90)
		"medium" : paramater(0,99)
		"low"    : parameter(0,999)
	}

	BatchTrueNegativeRate = decision("fraud detection method"){
		"batch_classifier" :  BatchClassifierTrueNegativeRate
		"batch_rule-based" :  parameter(0,995)
	}
	
	BatchClassifierTrueNegativeRate = decision("batch classifier threshold level"){
		"high"   : parameter(0.99)
		"medium" : paramater(0,995)
		"low"    : parameter(0,999)
	}


```

