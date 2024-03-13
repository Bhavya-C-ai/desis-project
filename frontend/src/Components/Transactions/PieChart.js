
import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { Pie } from 'react-chartjs-2';

const PieChart = () => {
    const [chartData, setChartData] = useState({});
    
    const dummyData = {
        labels: ['Education', 'Groceries', 'Health', 'Subscriptions', 'Takeaways', 'Clothing', 'Travelling', 'Other'],
        datasets: [
            {
                data: [100, 200, 50, 75, 120, 80, 30, 50], // Replace with your actual expense data
                backgroundColor: [
                    '#FF6384',
                    '#36A2EB',
                    '#FFCE56',
                    '#4CAF50',
                    '#FF9800',
                    '#9C27B0',
                    '#2196F3',
                    '#E91E63',
                ],
            },
        ],
    };
    return (
        <PieChartStyled>
            <h2>Monthly Expenses by Category</h2>
            <Pie className='chart' data={dummyData} />
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
