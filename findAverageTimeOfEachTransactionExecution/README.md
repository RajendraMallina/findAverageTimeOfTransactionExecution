Problem Statement :- 

Given a file which contains series of transactional laws 
Try to find out the average time between start and end of each transaction  
T1234, 2020 – 03 – 01 , 3:15 pm, start 
T1235, 2020 – 03 – 01 , 3:16 pm, start 
T1236, 2020 – 03 – 01 , 3:17 pm, start 
T1234, 2020 – 03 – 01 , 3:18 pm, End 
T1235, 2020 – 03 – 01 , 3:18 pm, End 


Possible Exception Cases :-
	1)Transaction end without start.
	2)Transaction restart without end.
	3)Invalid Transaction data.
	
Covered all above exceptions. 

Find sample test data files in the testdata folder.

Written unit tests to cover different cases.

Note:- We can run the program through the App.java as Java Application and AppTest.java as Junit test run.
	