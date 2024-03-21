import React from 'react';
import { Pie } from 'react-chartjs-2';
import styled from 'styled-components';
import { useGlobalContext } from '../../context/globalContext';

function PieChart() {
    const { modifiedExpenses, expenses } = useGlobalContext();

    // Function to aggregate expenses by category
    const aggregateExpensesByCategory = expenses => {
        if (!expenses) {
            console.error("Expenses array is undefined or null.");
            return;
        }
        const aggregatedData = {};
        expenses.forEach((expense) => {
            if (aggregatedData[expense.category]) {
                aggregatedData[expense.category] += expense.amount;
            } else {
                aggregatedData[expense.category] = expense.amount;
            }
        });
        return {
            labels: Object.keys(aggregatedData),
            datasets: [
                {
                    label: 'Modified Expenses',
                    data: Object.values(aggregatedData),
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)', // Red
                        'rgba(54, 162, 235, 0.6)', // Blue
                        'rgba(255, 206, 86, 0.6)', // Yellow
                        'rgba(75, 192, 192, 0.6)', // Green
                        'rgba(153, 102, 255, 0.6)', // Purple
                        'rgba(255, 159, 64, 0.6)', // Orange
                        'rgba(255, 119, 255, 0.6)', // Magenta
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 0.6)', // Red
                        'rgba(54, 162, 235, 0.6)', // Blue
                        'rgba(255, 206, 86, 0.6)', // Yellow
                        'rgba(75, 192, 192, 0.6)', // Green
                        'rgba(153, 102, 255, 0.6)', // Purple
                        'rgba(255, 159, 64, 0.6)', // Orange
                        'rgba(255, 119, 255, 0.6)', // Magenta
                    ],
                    borderWidth: 1,
                },
            ],
        };
    };

    const data = aggregateExpensesByCategory(modifiedExpenses);

    return (
        <PieChartStyled>
            <Pie data={data} />
        </PieChartStyled>
    );
}

const PieChartStyled = styled.div`
    background: #FCF6F9;
    border: 2px solid #FFFFFF;
    box-shadow: 0px 1px 15px rgba(0, 0, 0, 0.06);
    padding: 1rem;
    border-radius: 20px;
    height: 90%;
`;

export default PieChart;
