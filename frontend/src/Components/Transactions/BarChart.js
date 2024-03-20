import React, { useState, useEffect } from "react";
import { Bar } from "react-chartjs-2";
import { BarElement } from "chart.js";
import Chart from "chart.js/auto";
import styled from "styled-components";
import { useGlobalContext } from "../../context/globalContext";

const BarChart = () => {
  const { expenses, getExpenses } = useGlobalContext();
  const [chartData, setChartData] = useState();
  const getMonthName = (index) => {
    const months = [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December",
    ];
    return months[(index + 12) % 12];
  };
  const labels = [];
  for (let i = 11; i >= 0; i--) {
    const currentDate = new Date();
    labels.push(getMonthName(currentDate.getMonth() - i - 1));
  }
  useEffect(() => {
    getExpenses();
  }, []);

  useEffect(() => {
    const currentDate = new Date();
    const twelveMonthsAgo = new Date();
    twelveMonthsAgo.setMonth(twelveMonthsAgo.getMonth() - 12);

    // const filteredExpenses = expenses.filter((expense) => {
    //   const expenseDate = new Date(expense.date.replace(" ", "T"));
    //   return expenseDate >= twelveMonthsAgo && expenseDate <= currentDate;
    // });
    twelveMonthsAgo.setDate(0);

    const startOfCurrentMonth = new Date(
      currentDate.getFullYear(),
      currentDate.getMonth(),
      1
    );

    const filteredExpenses = expenses.filter((expense) => {
      const expenseDate = new Date(expense.date.replace(" ", "T"));
      return (
        expenseDate >= twelveMonthsAgo && expenseDate < startOfCurrentMonth
      );
    });

    // console.log("filtered expenses: ", filteredExpenses);

    const monthlyExpenses = Array(12).fill(0);

    filteredExpenses.forEach((expense) => {
      const expenseDate = new Date(expense.date.replace(" ", "T"));
      const monthIndex =
        (expenseDate.getMonth() - currentDate.getMonth() + 12) % 12;
      monthlyExpenses[monthIndex] += parseFloat(expense.amount);
      console.log(
        "expenseDate: ",
        expenseDate,
        "monthIndex: ",
        monthIndex,
        "monthlyExpenses[monthIndex]: ",
        monthlyExpenses[monthIndex]
      );
    });

    setChartData({
      labels: labels,
      datasets: [
        {
          label: "Monthly Expenses",
          data: monthlyExpenses,
          backgroundColor: [
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
          borderWidth: 1,
        },
      ],
    });
    console.log("set krne k baad chartdata ", chartData);
  }, [expenses]);
  Chart.register(BarElement);
  if (!chartData) {
    return <div>Loading...</div>;
  }
  return (
    <BarChartStyled>
      <h2>Monthly Expenses Bar Chart</h2>
      <div>
        <Bar
          options={{
            maintainAspectRatio: true,
            scales: {
              yAxes: [
                {
                  ticks: {
                    beginAtZero: true,
                  },
                },
              ],
            },
          }}
          data={chartData ?? {}}
        />
      </div>
    </BarChartStyled>
  );
};

const BarChartStyled = styled.div`
  text-align: center;
  margin: 20px;
  max-width: 1200px;

  h2 {
    font-size: 1.5rem;
    margin-bottom: 10px;
  }
  .chart {
    width: 100%;
  }
`;

export default BarChart;
