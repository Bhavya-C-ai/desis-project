import React, { useContext, useState } from "react"
import axios from 'axios'


const BASE_URL = "http://localhost:8080/api/v1/";


const GlobalContext = React.createContext()

export const GlobalProvider = ({children}) => {

    const [incomes, setIncomes] = useState([])
    const [expenses, setExpenses] = useState([])
    const [modifiedExpenses, setModifiedExpenses] = useState([])
    const [error, setError] = useState(null)
    const [streak, setStreak] = useState(0)
    const [lastAddedDate, setLastAddedDate] = useState(null)


    const fetchStreak = async () => {
        try {
            const response = await axios.get(`${BASE_URL}/get-streak`);
            setStreak(response.data.streak);
        } catch (error) {
            console.error('Error fetching streak:', error);
        }
    };

    const fetchLastAddedDate = async () => {
        try {
            const response = await axios.get(`${BASE_URL}/get-last-added-date`);
            setLastAddedDate(response.data.lastAddedDate);
        } catch (error) {
            console.error('Error fetching last added date:', error);
        }
    };

    const sendStreakToBackend = async (updatedStreak) => {
        try {
            // Make a POST request to update the streak on the backend
            const response = await axios.post(`${BASE_URL}/update-streak`, { streak: updatedStreak });
            if (response.status === 200) {
                console.log('Streak updated successfully on the backend');
                // Handle success if needed
            } else {
                console.error('Failed to update streak on the backend');
                // Handle failure if needed
            }
        } catch (error) {
            console.error('Error updating streak on the backend:', error);
            // Handle error if needed
        }
    };

    //calculate incomes
    const addIncome = async (income) => {
        const response = await axios.post(`${BASE_URL}add-income`, income)
            .catch((err) =>{
                setError(err.response.data.message)
            })
        getIncomes()
    }

    const getIncomes = async () => {
        const response = await axios.get(`${BASE_URL}get-incomes`)
        setIncomes(response.data)
        console.log(response.data)
    }

    const deleteIncome = async (id) => {
        const res  = await axios.delete(`${BASE_URL}delete-income/${id}`)
        getIncomes()
    }

    const totalIncome = () => {
        let totalIncome = 0;
        incomes.forEach((income) =>{
            totalIncome = totalIncome + income.amount
        })

        return totalIncome;
    }


    //calculate incomes
    const addExpense = async (income) => {
        const response = await axios.post(`${BASE_URL}add-expense`, income)
            .catch((err) =>{
                setError(err.response.data.message)
            })
        getExpenses()
    }

    const getExpenses = async () => {
        const response = await axios.get(`${BASE_URL}get-expenses`)
        setExpenses(response.data)
        console.log(response.data)
    }

    const deleteExpense = async (id) => {
        const res  = await axios.delete(`${BASE_URL}delete-expense/${id}`)
        getExpenses()
    }

    const totalExpenses = () => {
        let totalIncome = 0;
        expenses.forEach((income) =>{
            totalIncome = totalIncome + income.amount
        })

        return totalIncome;
    }


    const totalBalance = () => {
        return totalIncome() - totalExpenses()
    }

    const transactionHistory = () => {
        const history = [...incomes, ...expenses]
        history.sort((a, b) => {
            return new Date(b.createdAt) - new Date(a.createdAt)
        })

        return history.slice(0, 3)
    }

    const getModifiedExpensesByLevel = async (level) => {
        try {
            const response = await axios.get(`${BASE_URL}get-suggestion/${level}`);
            setModifiedExpenses(response.data);
        } catch (error) {
            setError(error.response.data.message);
        }
    };

    const updateExpense = async (updatedExpense) => {
        try {
            console.log("hii")
            const response = await axios.put(`${BASE_URL}update-expense/${updatedExpense.id}`, updatedExpense);
            // Handle response if needed
            getExpenses(); // Refresh expenses after updating
        } catch (error) {
            setError(error.response.data.message);
        }
    };
    


    return (
        <GlobalContext.Provider value={{
            addIncome,
            getIncomes,
            modifiedExpenses,
            incomes,
            deleteIncome,
            expenses,
            totalIncome,
            addExpense,
            getExpenses,
            deleteExpense,
            totalExpenses,
            totalBalance,
            transactionHistory,
            error,
            getModifiedExpensesByLevel,
            updateExpense,
            setError,
            fetchStreak,
            fetchLastAddedDate,
            sendStreakToBackend
        }}>
            {children}
        </GlobalContext.Provider>
    )
}

export const useGlobalContext = () =>{
    return useContext(GlobalContext)
}
