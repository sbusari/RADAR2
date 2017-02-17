# NASA ECS Model

Objectives, decisions and data from 2002 SEI Report "Making Architecture Design Decisions: An Economic Approach".

Note: CBAM estimate individual impact of architectural strategies on utility but not their combined impacts. Modelling combined impact requires richer application specific model.

## Utility Model

```
Objective Max ExpectedUtility	
			
ExpectedUtility = E(Utility)

Utility = 	HungRequestsRatio.weight * HungRequestsRatio.Utility +
			LostRequestsRatio.weight * LostRequestsRatio.Utility +
			FailedOrderRatio.weight * FailedOrderRatio.Utility +
			HungOrdersRatio.weight * HungOrdersRatio.Utility +
			LostOrdersRatio.weight * LostOrdersRatio.Utility +
			HelpNeededByUsers.weight * HelpNeededByUsers.Utility +
			FailureInfoGivenToUsers.weight * FailureInfoGivenToUsers.Utility +
			LimitOnOrders.weight * LimitOnOrders.Utility +
			NotificationsFrequency.weight * NotificationsFrequency.Utility +
			Performance.weight * Performance.Utility

     
HungRequestsRatio.weight = parameter(10)
LostRequestsRatio.weight = parameter(15)
FailedOrderRatio.weight = parameter(15)
HungOrdersRatio.weight = parameter(10)
LostOrdersRatio.weight = parameter(15)
HelpNeededByUsers.weight = parameter(10)
FailureInfoGivenToUsers.weight = parameter(5)
LimitOnOrders.weight  = parameter(5)
NotificationsFrequency.weight = parameter(10)
Performance.weight = parameter(5)

// Note: in following equations, should best and worst levels be parameters?
// A: not parameters because we assume they are fixed, given levels.

HungRequestRatio.Utility = (HungRequestRatio - HungRequestRatio.worst)/(HungRequestRatio.best - HungRequestRatio.worst)
HungRequestRatio.best = 0
HungRequestRatio.worst = 0.1

LostRequestsRatio.Utility = (LostRequestsRatio - LostRequestsRatio.worst)/(LostRequestsRatio.best - LostRequestsRatio.worst)
LostRequestsRatio.best = 0
LostRequestsRatio.worst = 5%

FailedOrderRatio.Utility = (FailedOrderRatio - FailedOrderRatio.worst)/(FailedOrderRatio.best - FailedOrderRatio.worst)
FailedOrderRatio.best = 0
FailedOrderRatio.worst = 10%

HungOrdersRatio.Utility = (HungOrdersRatio - HungOrdersRatio.worst)/(HungOrdersRatio.best - HungOrdersRatio.worst)
HungOrdersRatio.best = 0
HungOrdersRatio.worst = 10%

LostOrdersRatio.Utility = (LostOrdersRatio - LostOrdersRatio.worst)/(LostOrdersRatio.best - LostOrdersRatio.worst)
LostOrdersRatio.best = 0
LostOrdersRatio.worst = 10%

HelpNeededByUsers.Utility = (HelpNeededByUsers - HelpNeededByUsers.worst)/(HelpNeededByUsers.best - HelpNeededByUsers.worst)
HelpNeededByUsers.best = 0
HelpNeededByUsers.worst = 50%

FailureInfoGivenToUsers.Utility = (FailureInfoGivenToUsers - FailureInfoGivenToUsers.worst)/(FailureInfoGivenToUsers.best - FailureInfoGivenToUsers.worst)
FailureInfoGivenToUsers.best = 100%
FailureInfoGivenToUsers.worst = 10%

LimitOnOrders.Utility = (LimitOnOrders - LimitOnOrders.worst)/(LimitOnOrders.best - LimitOnOrders.worst)
LimitOnOrders.best = 0%
LimitOnOrders.worst = 50%

NotificationsFrequency.Utility = (NotificationsFrequency - NotificationsFrequency.worst)/(NotificationsFrequency.best - NotificationsFrequency.worst)
NotificationsFrequency.best = 1
NotificationsFrequency.worst = 1/1000

Performance.Utility = (Performance - Performance.worst)/(Performance.best - Performance.worst)
Performance.best = 90%
Performance.worst = 50%


```


## Quality Attributes Models

### Hung Request Ratio

```
HungRequestRatio = HungRequestRatio.current 
					* (1 - ReassignedHungRequestRatio) 
					* (1 - ForcedHungRequestRatio)
					
HungRequestRatio.current = parameter(5%)
					
ReassignedHungRequestRatio = decision("Order Reassignment"){
		"Current: not possible to reassign order" : 0
		"Allow Order Reassignment": parameter(60%)
}	

ForcedHungRequestRatio = decision("Forced Order Completion"){
		"Current: not possible to force order completion" : 0
		"Allow Forced Order Completion": parameter(40%)
}					
```

### Lost Request Ratio

```
LostRequestRatio = parameter(triangle(0, 0.5, 1))
```

### Failed Order Ratio

```
FailedOrderRatio = decision("Order Persistence Strategy"){
		"Current: store when processed" : parameter(5%)
		"Store as soon as received" 	 : parameter(2%)
}

```

### Hung Orders Ratio

```

HungOrdersRatio = HungOrdersRatio.current *
					(1 - SkippedHungOrderRatio) *
					(1 - RetriedHungOrderRatio)
					
SkippedHungOrderRatio = decision("Order Segmentation"){
		"Current: no order segmentation" : 0
		"Orders are segmented"			  : parameter(60%)
}

RetriedHungOrderRatio = decision("Hung Order Recovery"){
		"Current: no order retry" 	: 0
		"Allow Order Retry"			: parameter(40%)
}


```

### Lost Orders Ratio

```
LostOrdersRatio = decision("Order Persistence Strategy"){
		"Current: store when processed" : parameter(1%)
		"Store as soon as received" 	 : parameter(0%)
}
```

### Help Needed by Users

```
HelpNeededByUsers = LostOrdersRatio * HelpNeededPerLostOrder * HelpNeededForTrackingGranularity

HelpNeededPerLostOrder = decision("Failed Order Notification"){
		"Current: no notification": parameter(25%)
		"User notified of failed order": parameter(20%)
}

HelpNeededForTrackingGranularity = decision(Order Tracking){
 		"Current: order level"			: 1
 		"Granule-level order tracking"	: parameter(40%)
 }
```

### Failure Information

```
FailureInfoGivenToUsers = 1- RatioUsersNotGettingInfo
RatioUsersNotGettingInfo = RatioUsersNotGettingInfo.current * 
							(1 - NotificationOrderEffect) * 
							(1 - OrderTrackingGranularityEffect) *
							(1 - UserInformationEffect)
							
RatioUsersNotGettingInfo.current = parameter(50%)

NotificationOrderEffect = decision("Failed Order Notification"){
		"Current: no notification": 0
		"User notified of failed order": parameter(80%)
}

OrderTrackingGranularityEffect = decision(Order Tracking){
 		"Current: order level"			: 0
 		"Granule-level order tracking"	: parameter(90%)
 }
 
UserInformationEffect = decision("Available User Information"){
		"Current: no link to user info"		: 0
		"Link to user information"			: parameter(20%)
}
```


### Limit on Order

```
LimitOnOrders = decision("Order Chunking"){
		"Current: no oder chunking": parameter(30%)
		"Order Chunking"			: parameter(15%)
}
```

### Notification Frequency

```
NotificationFrequency = decision("Order Bundling"){
		"No Order Bundling" : parameter(1)
		"Order Bundling"	: paramter(0.01)
}
```

### Performance

```
Performance = decision("Order Bundling"){
		"No Order Bundling" : parameter(60%)
		"Order Bundling"	: parameter(55%)
}
```

## Cost Model

```
Objective Min Cost

Cost = Cost.OrderReassignment +
		Cost.ForcedOrderCompletion +
		Cost.OrderPersistenceStrategy +
		Cost.OrderSegmentation +
		Cost.HungOrderRecovery +
		Cost.FailedOrderNotification +
		Cost.OrderTracking +
		Cost.AvailableUserInformation +			

Cost.OrderReassignment = decision("Order Reassignment"){
		"Current: not possible to reassign order" : 0
		"Allow Order Reassignment": parameter(triangle(360, 400, 440))
}	

Cost.ForcedOrderCompletion = decision("Forced Order Completion"){
		"Current: not possible to force order completion" : 0
		"Allow Forced Order Completion": parameter(triangle(180, 200, 220))
}

Cost.OrderPersistenceStrategy = decision("Order Persistence Strategy"){
		"Current: store when processed" : 0
		"Store as soon as received" 	 : parameter(triangle(1200, 1200, 7150))
}

Cost.OrderSegmentation = decision("Order Segmentation"){
		"Current: no order segmentation" : 0
		"Orders are segmented"		 : parameter(triangle(180, 200, 220))
}

Cost.HungOrderRecovery = decision("Hung Order Recovery"){
		"Current: no order retry" 	: 0
		"Allow Order Retry"		: parameter(triangle(180, 200, 220))
}

Cost.FailedOrderNotification = decision("Failed Order Notification"){
		"Current: no notification": 0
		"User notified of failed order": parameter(triangle(270, 300, 330))
}

Cost.OrderTracking = decision(Order Tracking){
 		"Current: order level"		: 0
 		"Granule-level order tracking"	: parameter(triangle(900, 1000, 1650))
}

Cost.AvailableUserInformation = decision("Available User Information"){
		"Current: no link to user info"		: 0
		"Link to user information"		: parameter(triangle(90, 100, 440))
}

Cost.OrderChunking = decision("Order Chunking"){
		"Current: no oder chunking"	: 0
		"Order Chunking"		: parameter(triangle(360, 400, 440))
}

Cost.OrderBundling = decision("Order Bundling"){
		"No Order Bundling" 	: 0
		"Order Bundling"	: parameter(triangle(360, 400, 440))
}		
			

```



