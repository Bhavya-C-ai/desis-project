import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { useGlobalContext } from '../../context/globalContext';
import { InnerLayout } from '../../styles/Layouts';
import { dollar } from '../../utils/Icons';
import PieChart from '../Chart/PieChart';
import ModifiedExpenses from './ModifiedExpenses';

function Suggestion() {
    const { expenses, modifiedExpenses, getModifiedExpensesByLevel, getExpenses } = useGlobalContext();
    const [savingsPerCategory, setSavingsPerCategory] = useState({});
    const [savingsLevel, setSavingsLevel] = useState('moderate'); // Default savings level

    useEffect(() => {
        getModifiedExpensesByLevel(savingsLevel); // Pass savingsLevel to fetch modified expenses
        getExpenses();
    }, [savingsLevel]);

    useEffect(() => {
        if (expenses && modifiedExpenses) {
            const savings = calculateSavings(expenses, modifiedExpenses);
            setSavingsPerCategory(savings);
        }
    }, [expenses, modifiedExpenses]);


    // Function to calculate potential savings per category
    const calculateSavings = (originalExpenses, modifiedExpenses) => {
        const savings = {};

        // Check if originalExpenses and modifiedExpenses are arrays
        if (!Array.isArray(originalExpenses) || !Array.isArray(modifiedExpenses)) {
            console.error('Original expenses and modified expenses must be arrays.');
            return savings; // Return empty savings object if inputs are not arrays
        }

        // Group original and modified expenses by category
        const originalByCategory = groupExpensesByCategory(originalExpenses);
        const modifiedByCategory = groupExpensesByCategory(modifiedExpenses);

        // Calculate savings for each category
        for (const category in originalByCategory) {
            // Check if modified expenses exist for the category
            if (!modifiedByCategory.hasOwnProperty(category)) {
                console.warn(`No modified expenses found for category: ${category}`);
                continue; // Skip calculating savings for this category
            }

            const originalTotal = calculateTotal(originalByCategory[category]);
            const modifiedTotal = calculateTotal(modifiedByCategory[category]);
            savings[category] = originalTotal - modifiedTotal;
        }

        return savings;
    };


    // Function to group expenses by category
    const groupExpensesByCategory = expenses => {
        return expenses.reduce((acc, expense) => {
            const category = expense.category;
            acc[category] = acc[category] || [];
            acc[category].push(expense);
            return acc;
        }, {});
    };

    // Function to calculate total expenses
    const calculateTotal = expenses => {
        return expenses.reduce((total, expense) => total + expense.amount, 0);
    };

    const handleSavingsLevelChange = (level) => {
        setSavingsLevel(level);
    };

    return (
        <SuggestionsStyled>
            <InnerLayout>
                <h1 style={{ textAlign: 'center' }}>Suggestions</h1>
                <div className="savings-options">
                    <Button onClick={() => handleSavingsLevelChange('little')}>Save a Little</Button>
                    <Button onClick={() => handleSavingsLevelChange('moderate')}>Save Moderately</Button>
                    <Button onClick={() => handleSavingsLevelChange('lot')}>Save a Lot</Button>
                </div>
                <div className="stats-con">
                    <div className="chart-con">
                        <h2>Potential Expense by Category</h2>
                        <PieChart expenses={modifiedExpenses} />
                    </div>
                    <div className="savings-con">
                        <h2 style={{ marginBottom: '10px' }}>Potential Savings</h2>
                        {Object.entries(savingsPerCategory).map(([category, savings]) => (
                            <div className="savings-item" key={category}>
                                <p>
                                    {category}: {dollar} {savings}
                                </p>
                            </div>
                        ))}
                    </div>
                </div>
                <div className="history-con">
                    <ModifiedExpenses />
                </div>
            </InnerLayout>
        </SuggestionsStyled>
    );
}

const SuggestionsStyled = styled.div`
    .savings-options {
        display: flex;
        justify-content: center;
        margin-bottom: 20px;
    }

    .stats-con {
        height: 450px;
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 2rem;

        .chart-con,
        .savings-con {
            height: 450px; /* Increase the height of both chart and savings containers */
            background-color: #f7f7f7;
            border-radius: 10px;
            padding: 1.5rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .savings-con {
            background-color: #f7f7f7;
            border-radius: 10px;
            padding: 1.5rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);

            .savings-item {
                margin-bottom: 1rem;
                h2 {
                    font-size: 1.5rem;
                    color: #333;
                    margin-bottom: 0.5rem;
                }
                p {
                    font-size: 1.2rem;
                    color: #555;
                }
            }
        }

        .history-con {
            background-color: #f7f7f7;
            border-radius: 10px;
            padding: 1.5rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
    }
`;

const Button = styled.button`
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin 10px;

    &:hover {
        background-color: #0056b3;
    }

    &:focus {
        outline: none;
    }
`;

export default Suggestion;
