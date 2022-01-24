# StackExchangeDataAnalysis

Introduction
Stack Exchange has released its data-dumps of all its publically available contents including all the stack 
exchange communitieslike stack Overflow, Super user, Ask Ubuntu, Server Fault, etc. The data includes 
posts, users, comments, badges, postfeedbacks, postHistory, postTags, postTypes, reviewTasks, tags, 
votes, etc. Analyze Stack Exchange data and generate insights. Process posts data and develop program 
to solve below mentioned problems.

Sample Post Data
<row Id="41" PostTypeId="1" AcceptedAnswerId="44" CreationDate="2014-05-14T11:15:40.907" 
Score="28" ViewCount="1897" Body="&lt;p&gt;R has many libraries which are aimed at Data Analysis 
(e.g. JAGS, BUGS, ARULES etc..), and is mentioned in popular textbooks such as: J.Krusche, Doing 
Bayesian Data Analysis; B.Lantz, &quot;Machine Learning with 
R&quot;.&lt;/p&gt;&#xA;&#xA;&lt;p&gt;I've seen a guideline of 5TB for a dataset to be considered as 
Big Data.&lt;/p&gt;&#xA;&#xA;&lt;p&gt;My question is: Is R suitable for the amount of Data typically 
seen in Big Data problems? &#xA;Are there strategies to be employed when using R with this size of 
dataset?&lt;/p&gt;&#xA;" OwnerUserId="136" LastEditorUserId="118" LastEditDate="2014-05-
14T13:06:28.407" LastActivityDate="2015-04-12T05:00:23.663" Title="Is the R language suitable for Big 
Data" Tags="&lt;bigdata&gt;&lt;r&gt;" AnswerCount="8" CommentCount="1" FavoriteCount="13" />

Format of Posts Data
1.	Id
2.	PostTypeId (listed in the PostTypestable)
  1)	Question
  2)	Answer
  3)	Orphaned tag wiki
  4)	Tag wiki excerpt
  5)	Tag wiki
  6)	Moderator nomination
  7)	“Wiki placeholder” (seems to only be the election description)
  8)	Privilege wiki
3.	AcceptedAnswerId (only present if PostTypeId is 1)
4.	Parent ID (only present if PostTypeId is 2)
5.	CreationDate
6.	DeletionDate(only non-null for the SEDEPostsWithDeleted table. Deleted posts are not present
7.	on Posts. Column not present on data dump.)
8.	Score
9.	ViewCount(nullable)
10.	Body (as rendered HTML, not Markdown)
11.	OwnerUserId (only present if user has not been deleted; always -1 for tag wiki entries, i.e. the 
12.	community user owns them)
13.	OwnerDisplayName (nullable)
14.	LastEditorUserId (nullable)
15.	LastEditorDisplayName (nullable)
16.	LastEditDate="2009-03-05T22:28:34.823" - the date and time of the most recent edit to the post 
17.	(nullable)
18.	LastActivityDate="2009-03-11T12:51:01.480" - the date and time of the most recent activity on 
19.	the post. For a question, this could be the post being edited, a new answer was posted, a bounty 
20.	was started, etc.
21.	Title (nullable)
22.	Tags (nullable)
23.	AnswerCount(nullable)
24.	CommentCount
25.	FavoriteCount
26.	ClosedDate (present only if the post is closed)
27.	CommunityOwnedDate (present only if post is community wikied)

KPIs
1. Count the total number of questions in the available data-set and collect the questions id of all 
the questions
2. Monthly questions count –provide the distribution of number of questions asked per month
3. Provide the number of posts which are questions and contains specified words in their title (like 
data, science, nosql, hadoop, spark)
4. The trending questions which are viewed and scored highly by the user – Top 10 highest viewed 
questions with specific tags
5. The questions that doesn’t have any answers –Number of questions with “0” number of 
answers
6. Number of questions with more than 2 answers
7. Number of questions which are active for last 6 months
8. Questions which are marked closed for each category – provide the distribution of number of 
closed questions per month
9. The most scored questions with specific tags – Top 10 questions having tag hadoop, spark
10. List of all the tags along with their counts
11. Number of question with specific tags(nosql, big data) which was asked in the specified time 
range (from 01-01-2015 to 31-12-2015)
12. Average time for a post to get a correct answer
