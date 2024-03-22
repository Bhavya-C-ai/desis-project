# Personal Expense Tracker

## Introduction
This project aims to develop a user-friendly application for expense tracking and financial management. The application will streamline the process for users to record their expenses, track spending patterns over time, visualize data through charts and graphs, identify cost-cutting opportunities, and improve financial decision-making. To enhance user engagement, the application will gamify the experience by tracking user activity, maintaining a streak for continuous expense tracking, and sending notifications via email regarding expenses, incomes, and current balances.

### Flowchart
![flow](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/73e5ee46-0c01-4237-9ecc-6af44f5888eb)

### ER Diagram
![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/d71b32d7-0019-4428-9a2b-746db90a5708)



### Key Features:
- Record income and expenses  :pencil2:
  ![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/eef19222-055f-42f3-aa1a-698a399cd974)
![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/4fee6037-7cf0-4896-bca5-86e4f8906aae)

- Get a visual representation of your expenses :chart:
    ![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/56bbd5b9-2e14-4453-a4cf-4c9e14a4ca57)

  ![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/10cf57e5-fc3c-413a-9cb2-f3038ed0210a)

- Get monthly update on income, expense and balance via e-mail :envelope:
   ![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/dbc5b23b-3c25-4964-b5de-ca8a1c4c5195)
  ![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/2f8f117e-b169-4448-b036-cfd3a8ee3cc0)
 


- Spending allocation suggestions 💸
  ![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/a60d53e6-b4c5-4782-8a0c-9b3cd5bedb31)


### Tech Stacks:
1. Frontend
    - ReactJS
    - ChartJS
    - Styled-components
2. Backend
    - Spring Boot
    - Quartz - Job Scheduler
    - Spring Mail
3. Database
    - SQL

## Folder Structure
![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/612af8d8-7a9f-4e90-b4be-1c89f2f1860f)


### Backend
![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/95d817d8-6c42-46d6-b97c-35484f6166cd)

### Frontend
![image](https://github.com/Bhavya-C-ai/desis-project/assets/96719986/6f4d5d9a-d317-4b20-9a11-1fe3ec801cdf)




### Prerequisites

- Node version 18.x.x
- JDK 21
- Set up SQL server and create a database using sql_query.txt
- Replace the credentials with your setup in application.properties

### Start Server (port: 8080)

Execute the main method in the ```shell expensetracker-backend.src.main.java.ExpensetrackerBackendApplication``` class from IDE

### Install Frontend packages

```shell
cd frontend
npm i
```

### Start client (port: 3000)
```shell
npm start
```
