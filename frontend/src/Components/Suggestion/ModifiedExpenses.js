import React from 'react';
import styled from 'styled-components';
import { useGlobalContext } from '../../context/globalContext';

function ModifiedExpenses() {
    const { modifiedExpenses } = useGlobalContext();

    // Function to calculate total expenses per category
    const calculateTotalPerCategory = () => {
        const totalPerCategory = {};

        // Calculate total amount per category
        modifiedExpenses.forEach(expense => {
            if (totalPerCategory[expense.category]) {
                totalPerCategory[expense.category] += expense.amount;
            } else {
                totalPerCategory[expense.category] = expense.amount;
            }
        });

        return totalPerCategory;
    };

    const totalPerCategory = calculateTotalPerCategory();

    return (
        <ModifiedExpensesStyled>
            <h2 style={{ marginTop: '10px' }}>Total Allocated Amount Per Category</h2>
            <div className="category-list">
                {Object.entries(totalPerCategory).map(([category, total]) => (
                    <div className="category-item" key={category}>
                        <p>{category}</p>
                        <p>{total}</p>
                    </div>
                ))}
            </div>
        </ModifiedExpensesStyled>
    );
}

const ModifiedExpensesStyled = styled.div`
    .category-list {
        display: flex;
        flex-direction: column;
        gap: 0.5rem;
    }

    .category-item {
        display: flex;
        justify-content: space-between;
    }
`;

export default ModifiedExpenses;