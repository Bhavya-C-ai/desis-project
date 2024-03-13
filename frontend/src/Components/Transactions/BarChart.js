import React, { useState } from "react";
import { Bar } from "react-chartjs-2";
import { BarElement } from "chart.js";
import Chart from "chart.js/auto";
import styled from "styled-components";

const BarChart = () => {
  const [chartData, setChartData] = useState({});
  const dummyData = {
    labels: [
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
    ],
    datasets: [
        {
          label: 'Monthly Expenses',
          data: [10020, 12300, 700, 11030, 3300, 950, 1050, 1250, 980, 1150, 1050, 1100],
          backgroundColor: [
            '#FF6384',
            '#36A2EB',
            '#FFCE56',
            '#4CAF50',
            '#FF9800',
            '#9C27B0',
            '#2196F3',
            '#E91E63',
            '#FF6384',
            '#36A2EB',
            '#FFCE56',
            '#4CAF50',
          ],
          borderColor: [
            '#FF6384',
            '#36A2EB',
            '#FFCE56',
            '#4CAF50',
            '#FF9800',
            '#9C27B0',
            '#2196F3',
            '#E91E63',
            '#FF6384',
            '#36A2EB',
            '#FFCE56',
            '#4CAF50',
          ],
          borderWidth: 1,
        },
    ],
  };
  Chart.register(BarElement);

  return (
    <BarChartStyled>
      <h2>Monthly Expenses Bar Chart</h2>
      <div>
        <Bar
          options={{
            maintainAspectRatio: true,
            scales: {
              yAxes: [{
                ticks: {
                  beginAtZero: true,
                },
              }],
            },
          }}
          data={dummyData}
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
