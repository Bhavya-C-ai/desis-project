import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { Pie } from "react-chartjs-2";
import { useGlobalContext } from "../../context/globalContext";

const PieChart = () => {
  const { expenses, getExpenses } = useGlobalContext();

  // State for pie chart data
  const [pieChartData, setPieChartData] = useState(null);

  useEffect(() => {
    getExpenses();
  }, []);

  useEffect(() => {
    if (expenses.length > 0) {
      const currentDate = new Date();
      const currentMonth = currentDate.getMonth() + 1;
      const currentYear = currentDate.getFullYear();

      const currentMonthExpenses = expenses.filter((expense) => {
        const expenseDate = new Date(expense.date);
        const expenseMonth = expenseDate.getMonth() + 1;
        const expenseYear = expenseDate.getFullYear();
        return expenseMonth === currentMonth && expenseYear === currentYear;
      });

      const aggregatedData = {};
      currentMonthExpenses.forEach((expense) => {
        if (aggregatedData[expense.category]) {
          aggregatedData[expense.category] += expense.amount;
        } else {
          aggregatedData[expense.category] = expense.amount;
        }
      });

      setPieChartData({
        labels: Object.keys(aggregatedData),
        datasets: [
          {
            data: Object.values(aggregatedData),
            backgroundColor: [
              // '#FF6384',
              // '#36A2EB',
              // '#FFCE56',
              // '#4CAF50',
              // '#FF9800',
              // '#9C27B0',
              // '#2196F3',
              // '#E91E63',
              // "#4CAF50"
              "#FF6384",
              "#36A2EB",
              "#FFCE56",
              "#4CAF50",
              "#FF9800",
              "#9C27B0",
              "#2196F3",
              "#E91E63",
              "#FF5733",
              "#FF33F5",
              "#332FFF",
              "#33FFA7",
            ],
          },
        ],
      });
    }
  }, [expenses]);

  if (!pieChartData) {
    return <div>Loading...</div>;
  }

  return (
    <PieChartStyled>
      <h2>Expenses by Category</h2>
      <Pie className="chart" data={pieChartData} />
      {console.log(pieChartData)}
    </PieChartStyled>
  );
};

const PieChartStyled = styled.div`
  text-align: center;
  margin: 20px;
  max-width: 700px;

  h2 {
    font-size: 1.5rem;
    margin-bottom: 10px;
  }
  .chart {
    width: 100%;
  }
`;

export default PieChart;
