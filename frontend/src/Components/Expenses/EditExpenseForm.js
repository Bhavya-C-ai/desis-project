import React, { useEffect,useState } from 'react';
import styled from 'styled-components';
import Button from '../Button/Button';
import { useGlobalContext } from '../../context/globalContext';

function EditExpenseForm({ id, title: initialTitle, amount: initialAmount, date: initialDate, category: initialCategory, description: initialDescription, onClose }) {
  const { updateExpense,setError,error } = useGlobalContext();
  const [title, setTitle] = useState(initialTitle);
  const [amount, setAmount] = useState(initialAmount);
  const [date, setDate] = useState(initialDate);
  const [category, setCategory] = useState(initialCategory);
  const [description, setDescription] = useState(initialDescription);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const updatedExpense = {
        id,
        title,
        amount,
        date,
        category,
        description,
      };
      await updateExpense(updatedExpense);
      onClose();
    } catch (error) {
      setError("Error updating expense: " + error.message);
    }
  };

  const handleCancel = () => {
    onClose(); 
  };

  return (
    <EditExpenseFormStyled>
      <form onSubmit={handleSubmit}>
        <input type="text" name="title" value={title} onChange={(e) => setTitle(e.target.value)} />
        <input type="number" name="amount" value={amount} onChange={(e) => setAmount(e.target.value)} />
        <input type="date" name="date" value={date} onChange={(e) => setDate(e.target.value)} />
        <input type="text" name="category" value={category} onChange={(e) => setCategory(e.target.value)} />
        <input type="text" name="description" value={description} onChange={(e) => setDescription(e.target.value)} />
        <div className="button-container">
          <Button name={'Submit'} bPad={'.5rem 1rem'} bRad={'10px'} bg={'var(--color-accent)'} color={'#fff'} />
          <Button name={'Cancel'} bPad={'.5rem 1rem'} bRad={'10px'} bg={'#fff'} color={'var(--primary-color)'} onClick={handleCancel} />
        </div>
      </form>
    </EditExpenseFormStyled>
  );
}

const EditExpenseFormStyled = styled.div`
  .button-container {
    display: flex;
    justify-content: space-between;
    margin-top: 1rem; /* Added margin to separate the buttons */
  }
`;

export default EditExpenseForm;