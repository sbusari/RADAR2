# Situation Awareness System (from ICSE'14 paper)

Note: Get parameters numbers from Table 1 in CMU report "Guided Exploration of the Architectural Solution Space in the Face of Uncertainty"


## Optimisation Objectives

```
Objective Max ENB						   

ENB = E(NB)
NB = BatteryUsage.weight * BatteryUsage.preference +
     ResponseTime.weight * ResponseTime.preference +
     Reliability.weight * Reliability.preference +
     RampUpTime.weight * RampUpTime.preference +
     Cost.weight * Cost.preference +
     DevelopmentTime.weight * DevelopmentTime.preference +
     DeploymentTime.weight * DeploymentTime.preference 
     
BatteryUsage.weight = parameter(9)
ResponseTime.weight = parameter(7)
Reliability.weight = parameter(3)
RampUpTime.weight = parameter(2)
Cost.weight = parameter(1)
DevelopmentTime.weight = parameter(2)
DeploymentTime.weight = parameter(2)

Objective Min Risk 
Risk = 1  - ( 	(1 - BatteryUsage.Risk) *
				(1 - ResponseTime.Risk) *
				(1 - Reliability.Risk) *
				(1 - RampUpTime.Risk) *
				(1 - Cost.Risk) *
				(1 - DevelopmentTime.Risk) *
				(1 - DeploymentTime.Risk)
			 ) 

```

## Battery Usage Model

```
BatteryUsage.Preference = (BatteryUsage - BatteryUsage.worst)
							/(BatteryUsage.best - BatteryUsage.worst)
BatteryUsage.Risk = 	P(BatteryUsage < BatteryUsage.must)

BatteryUsage.best = parameter(24) 
BatteryUsage.worst = parameter(111) 
BatteryUsage.must = parameter(52)

BatteryUsage = BatteryUsage.LocationFinding + 
               BatteryUsage.FileSharing + 
               BatteryUsage.ReportSyncing + 
               BatteryUsage.ChatProtocol + 
               BatteryUsage.MapAccess + 
               BatteryUsage.HardwarePlatform + 
               BatteryUsage.Connectivity + 
               BatteryUsage.DataBase + 
               BatteryUsage.ArchitecturalPattern + 
               BatteryUsage.DataExchangeFormat
                
BatteryUsage.LocationFinding = decision("Location Finding"){
		"GPS" : parameter(triangle(10, 10, 14))
		"radio triangulation" : parameter(triangle(4, 5, 6))
}

BatteryUsage.FileSharing = decision("File Sharing"){
		"OpenIntent" : parameter(triangle(5, 5, 6))
		"In house" : parameter(triangle(0, 0, 0))
}

BatteryUsage.ReportSyncing = decision("Report Syncing"){
		"Explicit" : parameter(triangle(1, 3, 4))
		"Implicit" : parameter(triangle(7, 8, 10))
}

BatteryUsage.ChatProtocol = decision("Chat Protocol"){
		"XMPP (Open Fire)" : parameter(triangle(4, 5, 6))
		"In house" : parameter(triangle(2, 3, 12))
}

BatteryUsage.MapAccess = decision("Map Access"){
		"On Demand (Google)" : parameter(triangle(4, 4, 12))
		"Cache on server" : parameter(triangle(4, 5, 12))
		"Preloaded (ESRI)" : parameter(triangle(5, 7, 7))
}

BatteryUsage.HardwarePlatform = decision("Hardware Platform"){
		"Nexus I (HTC)" : parameter(triangle(3, 5, 5))
		"Droid (Motorola)" : parameter(triangle(4, 5, 14))
}

BatteryUsage.Connectivity = decision("Connectivity"){
		"Wifi" : parameter(triangle(3, 4, 5))
		"3G on Nexus I" : parameter(triangle(1, 2, 3))
		"3G on Droid" : parameter(triangle(2, 4, 5))
		"Bluetooth" : parameter(triangle(2, 3, 15))
}

BatteryUsage.DataBase = decision("Database"){
		"MySQL" : parameter(triangle(3, 6, 7))
		"sqLite" : parameter(triangle(5, 5, 10))
}

BatteryUsage.ArchitecturalPattern = decision("Architectural Pattern"){
		"Peer-to-peer" : parameter(triangle(7, 8, 10))
		"Client-Server" : parameter(triangle(5, 6, 7))
		"Push-based" : parameter(triangle(2, 4, 4))
}

BatteryUsage.DataExchangeFormat = decision("Data Exchange Format"){
		"XML" : parameter(triangle(3, 4, 6))
		"Compressed XML" : parameter(triangle(5, 5, 7))
		"Unformatted data" : parameter(triangle(1, 1, 3))
}

```

## Performance Model

```
ResponseTime.Preference = (ResponseTime - ResponseTime.worst)
							/(ResponseTime.best - ResponseTime.worst)
ResponseTime.Risk = 	P(ResponseTime < ResponseTime.must)

ResponseTime.best = parameter(203)
ResponseTime.worst = parameter(2850)
ResponseTime.must = parameter(882)

ResponseTime = ResponseTime.LocationFinding + 
               ResponseTime.FileSharing + 
               ResponseTime.ReportSyncing + 
               ResponseTime.ChatProtocol + 
               ResponseTime.MapAccess + 
               ResponseTime.HardwarePlatform + 
               ResponseTime.Connectivity + 
               ResponseTime.DataBase + 
               ResponseTime.ArchitecturalPattern + 
               ResponseTime.DataExchangeFormat
                
ResponseTime.LocationFinding = decision("Location Finding"){
		"GPS" : parameter(triangle(480, 500, 990))
		"radio triangulation" : parameter(triangle(50, 100, 600))
}

ResponseTime.FileSharing = decision("File Sharing"){
		"OpenIntent" : parameter(triangle(50, 65, 70))
		"In house" : parameter(triangle(40, 60, 100))
}

ResponseTime.ReportSyncing = decision("Report Syncing"){
		"Explicit" : parameter(triangle(20, 30, 50))
		"Implicit" : parameter(triangle(1, 4, 10))
}

ResponseTime.ChatProtocol = decision("Chat Protocol"){
		"XMPP (Open Fire)" : parameter(triangle(40, 60, 70))
		"In house" : parameter(triangle(30, 40, 200))
}

ResponseTime.MapAccess = decision("Map Access"){
		"On Demand (Google)" : parameter(triangle(700, 800, 900))
		"Cache on server" : parameter(triangle(1, 4, 500))
		"Preloaded (ESRI)" : parameter(triangle(1, 2, 3))
}

ResponseTime.HardwarePlatform = decision("Hardware Platform"){
		"Nexus I (HTC)" : parameter(triangle(40, 60, 65))
		"Droid (Motorola)" : parameter(triangle(50, 55, 200))
}

ResponseTime.Connectivity = decision("Connectivity"){
		"Wifi" : parameter(triangle(30, 35, 40))
		"3G on Nexus I" : parameter(triangle(20, 25, 40))
		"3G on Droid" : parameter(triangle(20, 25, 40))
		"Bluetooth" : parameter(triangle(25, 30, 200))
}

ResponseTime.DataBase = decision("Database"){
		"MySQL" : parameter(triangle(20, 25, 30))
		"sqLite" : parameter(triangle(8, 10, 50))
}

ResponseTime.ArchitecturalPattern = decision("Architectural Pattern"){
		"Peer-to-peer" : parameter(triangle(10, 20, 30))
		"Client-Server" : parameter(triangle(25, 30, 80))
		"Push-based" : parameter(triangle(15, 25, 40))
}

ResponseTime.DataExchangeFormat = decision("Data Exchange Format"){
		"XML" : parameter(triangle(20, 35, 80))
		"Compressed XML" : parameter(triangle(12, 20, 35))
		"Unformatted data" : parameter(triangle(3, 10, 15))
}

```

## Reliability Model


```
Reliability.Preference = (Reliability - Reliability.worst)
							/(Reliability.best - Reliability.worst)
Reliability.Risk = 	P(Reliability < Reliability.must)

Reliability.best = parameter(792)
Reliability.worst = parameter(535)
Reliability.must = parameter(721)

Reliability = Reliability.LocationFinding + 
               Reliability.FileSharing + 
               Reliability.ReportSyncing + 
               Reliability.ChatProtocol + 
               Reliability.MapAccess + 
               Reliability.HardwarePlatform + 
               Reliability.Connectivity + 
               Reliability.DataBase + 
               Reliability.ArchitecturalPattern + 
               Reliability.DataExchangeFormat
                
Reliability.LocationFinding = decision("Location Finding"){
		"GPS" : parameter(triangle(60, 75, 80))
		"radio triangulation" : parameter(triangle(90, 92, 99))
}

Reliability.FileSharing = decision("File Sharing"){
		"OpenIntent" : parameter(triangle(90, 95, 99))
		"In house" : parameter(triangle(80, 92, 96))
}

Reliability.ReportSyncing = decision("Report Syncing"){
		"Explicit" : parameter(triangle(85, 88, 90))
		"Implicit" : parameter(triangle(30, 40, 200))
}

Reliability.ChatProtocol = decision("Chat Protocol"){
		"XMPP (Open Fire)" : parameter(triangle(94, 95, 99))
		"In house" : parameter(triangle(60, 96, 97))
}

Reliability.MapAccess = decision("Map Access"){
		"On Demand (Google)" : parameter(triangle(70, 91, 92))
		"Cache on server" : parameter(triangle(85, 97, 98))
		"Preloaded (ESRI)" : parameter(triangle(85, 90, 99))
}

Reliability.HardwarePlatform = decision("Hardware Platform"){
		"Nexus I (HTC)" : parameter(0)
		"Droid (Motorola)" : parameter(0)
}

Reliability.Connectivity = decision("Connectivity"){
		"Wifi" : parameter(triangle(80, 85, 90))
		"3G on Nexus I" : parameter(triangle(80, 88, 99))
		"3G on Droid" : parameter(triangle(80, 88, 99))
		"Bluetooth" : parameter(triangle(50, 85, 85))
}

Reliability.DataBase = decision("Database"){
		"MySQL" : parameter(triangle(85, 90, 99))
		"sqLite" : parameter(triangle(70, 90, 95))
}

Reliability.ArchitecturalPattern = decision("Architectural Pattern"){
		"Peer-to-peer" : parameter(triangle(60, 66, 70))
		"Client-Server" : parameter(triangle(85, 95, 97))
		"Push-based" : parameter(triangle(90, 94, 99))
}

Reliability.DataExchangeFormat = decision("Data Exchange Format"){
		"XML" : parameter(0))
		"Compressed XML" : parameter(0)
		"Unformatted data" : parameter(0)
}

```

## Ramp Up Time Model

```
RampUpTime.Preference = (RampUpTime - RampUpTime.worst)
							/(RampUpTime.best - RampUpTime.worst)
RampUpTime.Risk = 	P(RampUpTime < RampUpTime.must)

RampUpTime.best = parameter(31)
RampUpTime.worst = parameter(83)
RampUpTime.must = parameter(58)

RampUpTime = RampUpTime.LocationFinding + 
               RampUpTime.FileSharing + 
               RampUpTime.ReportSyncing + 
               RampUpTime.ChatProtocol + 
               RampUpTime.MapAccess + 
               RampUpTime.HardwarePlatform + 
               RampUpTime.Connectivity + 
               RampUpTime.DataBase + 
               RampUpTime.ArchitecturalPattern + 
               RampUpTime.DataExchangeFormat
                
RampUpTime.LocationFinding = decision("Location Finding"){
		"GPS" : parameter(triangle(5, 6, 7))
		"radio triangulation" : parameter(triangle(7, 8, 9))
}

RampUpTime.FileSharing = decision("File Sharing"){
		"OpenIntent" : parameter(triangle(8, 9, 10))
		"In house" : parameter(triangle(5, 8, 12))
}

RampUpTime.ReportSyncing = decision("Report Syncing"){
		"Explicit" : parameter(triangle(2, 2, 3))
		"Implicit" : parameter(triangle(1, 2, 2))
}

RampUpTime.ChatProtocol = decision("Chat Protocol"){
		"XMPP (Open Fire)" : parameter(triangle(5, 6, 7))
		"In house" : parameter(triangle(3, 4, 14))
}

RampUpTime.MapAccess = decision("Map Access"){
		"On Demand (Google)" : parameter(triangle(7, 9, 10))
		"Cache on server" : parameter(triangle(7, 9, 10))
		"Preloaded (ESRI)" : parameter(triangle(10, 13, 14))
}

RampUpTime.HardwarePlatform = decision("Hardware Platform"){
		"Nexus I (HTC)" : parameter(0)
		"Droid (Motorola)" : parameter(0)
}

RampUpTime.Connectivity = decision("Connectivity"){
		"Wifi" : parameter(triangle(1, 3, 4))
		"3G on Nexus I" : parameter(triangle(1, 2, 3))
		"3G on Droid" : parameter(triangle(1, 2, 3))
		"Bluetooth" : parameter(triangle(1, 2, 8))
}

RampUpTime.DataBase = decision("Database"){
		"MySQL" : parameter(triangle(1, 2, 3))
		"sqLite" : parameter(triangle(3, 4, 5))
}

RampUpTime.ArchitecturalPattern = decision("Architectural Pattern"){
		"Peer-to-peer" : parameter(triangle(10, 11, 13))
		"Client-Server" : parameter(triangle(7, 8, 10))
		"Push-based" : parameter(triangle(9, 10, 12))
}

RampUpTime.DataExchangeFormat = decision("Data Exchange Format"){
		"XML" : parameter(triangle(2, 3, 4))
		"Compressed XML" : parameter(triangle(4, 5, 6))
		"Unformatted data" : parameter(triangle(1, 2, 3))
}

```

## Cost Model

```
Cost.Preference = (Cost - Cost.worst)
							/(Cost.best - Cost.worst)
Cost.Risk = 	P(Cost < Cost.must)

Cost.best = parameter(550)
Cost.worst = parameter(2250)
Cost.must = parameter(1290)

Cost = Cost.LocationFinding + 
               Cost.FileSharing + 
               Cost.ReportSyncing + 
               Cost.ChatProtocol + 
               Cost.MapAccess + 
               Cost.HardwarePlatform + 
               Cost.Connectivity + 
               Cost.DataBase + 
               Cost.ArchitecturalPattern + 
               Cost.DataExchangeFormat
                
Cost.LocationFinding = decision("Location Finding"){
		"GPS" : parameter(triangle(50, 80 100))
		"radio triangulation" : parameter(0)
}

Cost.FileSharing = decision("File Sharing"){
		"OpenIntent" : parameter(0)
		"In house" : parameter(0)
}

Cost.ReportSyncing = decision("Report Syncing"){
		"Explicit" : parameter(0)
		"Implicit" : parameter(0)
}

Cost.ChatProtocol = decision("Chat Protocol"){
		"XMPP (Open Fire)" : parameter(0)
		"In house" : parameter(0)
}

Cost.MapAccess = decision("Map Access"){
		"On Demand (Google)" : parameter(0)
		"Cache on server" : parameter(triangle(700, 900, 950))
		"Preloaded (ESRI)" : parameter(triangle(100, 170, 200))
}

Cost.HardwarePlatform = decision("Hardware Platform"){
		"Nexus I (HTC)" : parameter(triangle(500, 525, 530))
		"Droid (Motorola)" : parameter(triangle(520, 520, 600))
}

Cost.Connectivity = decision("Connectivity"){
		"Wifi" : parameter(triangle(70, 80, 85))
		"3G on Nexus I" : parameter(triangle(360, 400, 600))
		"3G on Droid" : parameter(triangle(360, 400, 600))
		"Bluetooth" : parameter(triangle(50, 70, 200))
}

Cost.DataBase = decision("Database"){
		"MySQL" : parameter(0)
		"sqLite" : parameter(0)
}

Cost.ArchitecturalPattern = decision("Architectural Pattern"){
		"Peer-to-peer" : parameter(0)
		"Client-Server" : parameter(0)
		"Push-based" : parameter(0)
}

Cost.DataExchangeFormat = decision("Data Exchange Format"){
		"XML" : parameter(0)
		"Compressed XML" : parameter(0)
		"Unformatted data" : parameter(0)
}

```

## Development Time Model


```
DevelopmentTime.Preference = (DevelopmentTime - DevelopmentTime.worst)
							/(DevelopmentTime.best - DevelopmentTime.worst)
DevelopmentTime.Risk = 	P(DevelopmentTime < DevelopmentTime.must)

DevelopmentTime.best = parameter(61) 
DevelopmentTime.worst = parameter(149)
DevelopmentTime.must = parameter(111)

DevelopmentTime = DevelopmentTime.LocationFinding + 
               DevelopmentTime.FileSharing + 
               DevelopmentTime.ReportSyncing + 
               DevelopmentTime.ChatProtocol + 
               DevelopmentTime.MapAccess + 
               DevelopmentTime.DataBase + 
               DevelopmentTime.ArchitecturalPattern + 
               DevelopmentTime.DataExchangeFormat
                
DevelopmentTime.LocationFinding = decision("Location Finding"){
		"GPS" : parameter(triangle(3, 4, 5))
		"radio triangulation" : parameter(triangle(10, 14, 15))
}

DevelopmentTime.FileSharing = decision("File Sharing"){
		"OpenIntent" : parameter(triangle(3, 4, 6))
		"In house" : parameter(triangle(5, 6, 15))
}

DevelopmentTime.ReportSyncing = decision("Report Syncing"){
		"Explicit" : parameter(triangle(5, 6, 7))
		"Implicit" : parameter(triangle(3, 4, 4))
}

DevelopmentTime.ChatProtocol = decision("Chat Protocol"){
		"XMPP (Open Fire)" : parameter(triangle(5, 6, 8))
		"In house" : parameter(triangle(7, 8, 20))
}

DevelopmentTime.MapAccess = decision("Map Access"){
		"On Demand (Google)" : parameter(triangle(14, 18, 21))
		"Cache on server" : parameter(triangle(14, 18, 21))
		"Preloaded (ESRI)" : parameter(triangle(20, 27, 30))
}


DevelopmentTime.DataBase = decision("Database"){
		"MySQL" : parameter(triangle(15, 17, 18))
		"sqLite" : parameter(triangle(15, 16, 22))
}

DevelopmentTime.ArchitecturalPattern = decision("Architectural Pattern"){
		"Peer-to-peer" : parameter(triangle(20, 26, 30))
		"Client-Server" : parameter(triangle(15, 16, 20))
		"Push-based" : parameter(triangle(20, 24, 25))
}

DevelopmentTime.DataExchangeFormat = decision("Data Exchange Format"){
		"XML" : parameter(triangle(6, 7, 8))
		"Compressed XML" : parameter(triangle(7, 9, 10))
		"Unformatted data" : parameter(triangle(3, 4, 5))
}

```

## Deployment Time Model

```
DeploymentTime.Preference = (DeploymentTime - DeploymentTime.worst)
							/(DeploymentTime.best - DeploymentTime.worst)
DeploymentTime.Risk = 	P(DeploymentTime < DeploymentTime.must)

DeploymentTime.best = parameter(21) 
DeploymentTime.worst = parameter(72) 
DeploymentTime.must = parameter(38)

DeploymentTime = DeploymentTime.LocationFinding + 
               DeploymentTime.FileSharing + 
               DeploymentTime.ReportSyncing + 
               DeploymentTime.ChatProtocol + 
               DeploymentTime.MapAccess + 
               DeploymentTime.Connectivity + 
               DeploymentTime.DataBase + 
               DeploymentTime.ArchitecturalPattern
                               
DeploymentTime.LocationFinding = decision("Location Finding"){
		"GPS" : parameter(triangle(2, 3, 3))
		"radio triangulation" : parameter(triangle(1, 1, 2))
}

DeploymentTime.FileSharing = decision("File Sharing"){
		"OpenIntent" : parameter(triangle(1, 1, 2))
		"In house" : parameter(0)
}

DeploymentTime.ReportSyncing = decision("Report Syncing"){
		"Explicit" : parameter(triangle(1, 2, 2))
		"Implicit" : parameter(1)
}

DeploymentTime.ChatProtocol = decision("Chat Protocol"){
		"XMPP (Open Fire)" : parameter(triangle(1, 1, 2))
		"In house" : parameter(0)
}

DeploymentTime.MapAccess = decision("Map Access"){
		"On Demand (Google)" : parameter(0)
		"Cache on server" : parameter(triangle(3, 4, 5))
		"Preloaded (ESRI)" : parameter(triangle(3, 4, 5))
}


DeploymentTime.Connectivity = decision("Connectivity"){
		"Wifi" : parameter(triangle(5, 6, 7))
		"3G on Nexus I" : parameter(triangle(2, 3, 4))
		"3G on Droid" : parameter(triangle(2, 3, 4))
		"Bluetooth" : parameter(triangle(4, 5, 15))
}

DeploymentTime.DataBase = decision("Database"){
		"MySQL" : parameter(triangle(10, 15, 16))
		"sqLite" : parameter(triangle(13, 14, 22))
}

DeploymentTime.ArchitecturalPattern = decision("Architectural Pattern"){
		"Peer-to-peer" : parameter(triangle(14, 18, 21))
		"Client-Server" : parameter(triangle(7, 9, 10))
		"Push-based" : parameter(triangle(8, 9, 12))
}


```
