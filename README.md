# Outreach Assistant


As a global talent member at AIESEC, my responsibilities include generating leads through contacting local
owners to partner with our club and holding sales meetings. As work piles up at times, I have to juggle between schoolwork
 and my club commitments. Given the mass number of reputable companies of various sizes in different industries, I also struggle with
determining which ones I should contact. Hence, I developed a tool that is able to 
speed up the outreach process and rid the humdrum routine of continuously checking 
whether they are suitable companies as well as inputting a mass amount of data into Salesforce. 

Ta-da! This application is an **outreach tool** that is designed to guide people in 
**outreach positions** through each phase by maximizing efficiency and increasing their 
success in striking deals with other companies. The application consists of 
three components including **pre-contact**, **mid-contact** as well as **post-contact** respectively. 

## Pre-Contact
Pre-contact handles adding new companies to potential companies to be contacted. It also filters out companies
that do not meet the input size range as well as prioritizes the list of companies that should be contacted based on 
industry or size.  

## Mid-Contact 
Mid-contact manages booking meetings, ensuring no sales meetings are booked on the same day as well as
letting the user know which meetings are least spaced out. In addition, it also updates the list of companies
that are being contacted.  

## Post-Contact
Post-contact simply takes care of the follow-up based on companies' interest or satisfaction levels as well as
display the companies that have been contacted or have yet to be contacted as well as 
the ones that have been followed up. 


## User-Stories
- As a user, I want to be able to add companies to the list of interested companies. 
- As a user, I want to be able to view the list of companies I should contact in order as well as the list of companies 
to be followed up. 
- As a user, I want to be able to book sales meetings and know which meetings are least spaced out
so that I can change booking times. 
- As a user, I want to be able to update the company status if they are contacted or followed up. 
- As a user, I want to be able to save my company lists to file 
- As a user, I want to be able to load my company lists from file 
- As a user, I want to be able to save the meetings to file
- As a user, I want to be able to load the meetings from file 


## Phase 4: Task 2
- CompanySizeRange class is made more robust and tested


## Phase 4: Task 3

If I had more time to work on the project...
- I would refactor the persistence package so that I would have a saveable interface which is implemented by separate
CompanyListReader and MeetingsListReader. Because of the similarities between 
the methods in the CompanyListWriter and MeetingsListWriter, I would also make
an abstract class to which the two can extend to reduce coupling. 
- Refactoring can also be done in the DisplayMeetings and DisplayPrioritizedCompanies classes
since the two serve the same purpose which is to display the list of meetings/ companies
to the panel 
- UpdateFollowedUp and DropDownMenuForUpdate are also similar in terms of structure hence an abstract class
can abstract away the similarities between the two



